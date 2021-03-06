package com.mindoo.domino.jna.directory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import com.mindoo.domino.jna.IItemTableData;
import com.mindoo.domino.jna.NotesDatabase;
import com.mindoo.domino.jna.NotesSearch.ISearchMatch;
import com.mindoo.domino.jna.constants.FileType;
import com.mindoo.domino.jna.constants.Search;
import com.mindoo.domino.jna.utils.StringTokenizerExt;

import lotus.domino.DbDirectory;

/**
 * This class scans a local or remote Domino data directory for folders or database of
 * various kinds. It has a much better scan performance compared to {@link DbDirectory}
 * in the legacy API and has more functionality, e.g. scanning subdirectories,
 * folders and more file types.
 * 
 * @author Karsten Lehmann
 */
public class DirectoryScanner {
	private String m_serverName;
	private String m_directory;
	private EnumSet<FileType> m_fileTypes;

	/**
	 * Creates a new scanner instance
	 * 
	 * @param serverName server name, either abbreviated, canonical or common name
	 * @param directory directory to scan or "" for top level
	 * @param fileTypes type of data to return e,g, {@link FileType#DBANY} or {@link FileType#DIRS}, optionally you can add a flag like {@link FileType#RECURSE}
	 */
	public DirectoryScanner(String serverName, String directory, EnumSet<FileType> fileTypes) {
		m_serverName = serverName;
		m_directory = directory==null ? "" : directory;
		m_fileTypes = fileTypes;
	}
	
	/**
	 * Starts the directory scan. During the scan, we call {@link #entryRead(SearchResultData)} with
	 * every entry we found
	 * 
	 * @return search result; override {@link #isAccepted(SearchResultData)} to apply your own filtering or {@link #entryRead(SearchResultData)} to read results while scanning
	 */
	public List<SearchResultData> scan() {
		return scan(null);
	}
	
	/**
	 * Starts the directory scan. During the scan, we call {@link #entryRead(SearchResultData)} with
	 * every entry we found
	 * 
	 * @param formula optional search formula to filter the returned entries, see {@link SearchResultData#getRawData()} for available fields, e.g. $path="mydb.nsf" or @Word($info;@char(10);2)="db category name"
	 * @return search result; override {@link #isAccepted(SearchResultData)} to apply your own filtering or {@link #entryRead(SearchResultData)} to read results while scanning
	 */
	public List<SearchResultData> scan(String formula) {
		final List<SearchResultData> lookupResult = new ArrayList<DirectoryScanner.SearchResultData>();
		
		NotesDatabase dir = new NotesDatabase(m_serverName, m_directory, "");
		try {
			dir.searchFiles(formula, null, EnumSet.of(Search.FILETYPE, Search.SUMMARY), m_fileTypes, null, new NotesDatabase.SearchCallback() {

				@Override
				public Action noteFound(NotesDatabase parentDb, ISearchMatch searchMatch, IItemTableData summaryBufferData) {

					Map<String,Object> dataAsMap = summaryBufferData.asMap(true);

					Object typeObj = dataAsMap.get("$type");
					if (typeObj instanceof String) {
						String typeStr = (String) typeObj;
						if ("$DIR".equals(typeStr)) {
							String folderName = null;
							Object folderNameObj = dataAsMap.get("$TITLE");
							if (folderNameObj instanceof String) {
								folderName = (String) folderNameObj;
							}

							String folderPath = null;
							Object folderPathObj = dataAsMap.get("$path");
							if (folderPathObj instanceof String) {
								folderPath = (String) folderPathObj;
							}

							FolderData folderData = new FolderData();
							folderData.setRawData(dataAsMap);
							folderData.setFolderName(folderName);
							folderData.setFolderPath(folderPath);

							if (isAccepted(folderData)) {
								lookupResult.add(folderData);
							}
							
							DirectoryScanner.Action action = entryRead(folderData);
							return action == DirectoryScanner.Action.Continue ? Action.Continue : Action.Stop;
						}
						else if ("$NOTEFILE".equals(typeStr)) {
							String dbTitle = "";
							String dbCategory = "";
							String dbTemplateName = "";
							String dbInheritTemplateName = "";
							
							Object infoObj = dataAsMap.get("$Info");
							if (infoObj instanceof String) {
								// parse weird $Info format:
								// $info=Database title\n
								// Database category\n
								// #1Database template\n
								// #2Database inherit template
								String infoStr = ((String) infoObj).replace("\r", "");
								StringTokenizerExt st = new StringTokenizerExt(infoStr, "\n");
								if (st.hasMoreTokens()) {
									dbTitle = st.nextToken();
									
									boolean secondLine = true;
									while (st.hasMoreTokens()) {
										String currLine = st.nextToken();
										
										if (secondLine) {
											secondLine = false;
											
											if (!currLine.startsWith("1#") && !currLine.startsWith("2#")) {
												dbCategory = currLine;
												continue;
											}
										}
										
										if (currLine.startsWith("#1")) {
											dbTemplateName = currLine.substring(2);
										}
										else if (currLine.startsWith("#2")) {
											dbInheritTemplateName = currLine.substring(2);
										}
									}
								}
							}

							Calendar dbCreated = null;
							Object createdObj = dataAsMap.get("$DBCREATED");
							if (createdObj instanceof Calendar) {
								dbCreated = (Calendar) createdObj;
							}

							Calendar dbModified = null;
							Object modifiedObj = dataAsMap.get("$Modified");
							if (modifiedObj instanceof Calendar) {
								dbModified = (Calendar) modifiedObj;
							}

							Calendar lastFixup = null;
							Object lastFixupObj = dataAsMap.get("$lastfixup");
							if (lastFixupObj instanceof Calendar) {
								lastFixup = (Calendar) lastFixupObj;
							}
							
							Calendar lastCompact = null;
							Object lastCompactObj = dataAsMap.get("$lastcompact");
							if (lastCompactObj instanceof Calendar) {
								lastCompact = (Calendar) lastCompactObj;
							}
							
							Calendar nonDataMod  = null;
							Object nonDataModObj = dataAsMap.get("$nondatamod");
							if (nonDataModObj instanceof Calendar) {
								nonDataMod = (Calendar) nonDataModObj;
							}

							String fileName = null;
							Object fileNameObj = dataAsMap.get("$TITLE");
							if (fileNameObj instanceof String) {
								fileName = (String) fileNameObj;
							}

							String filePath = null;
							Object filePathObj = dataAsMap.get("$path");
							if (filePathObj instanceof String) {
								filePath = (String) filePathObj;
							}

							DatabaseData dbData = new DatabaseData();
							dbData.setRawData(dataAsMap);
							dbData.setTitle(dbTitle);
							dbData.setCreated(dbCreated);
							dbData.setModified(dbModified);
							dbData.setLastFixup(lastFixup);
							dbData.setLastCompact(lastCompact);
							dbData.setDesignModifiedDate(nonDataMod);
							dbData.setFileName(fileName);
							dbData.setFilePath(filePath);
							dbData.setCategory(dbCategory);
							dbData.setTemplateName(dbTemplateName);
							dbData.setInheritTemplateName(dbInheritTemplateName);
							
							if (isAccepted(dbData)) {
								lookupResult.add(dbData);
							}

							DirectoryScanner.Action action = entryRead(dbData);
							return action == DirectoryScanner.Action.Continue ? Action.Continue : Action.Stop;
						}
					}

					//report default data object if we cannot detect the type
					SearchResultData unknownData = new SearchResultData();
					unknownData.setRawData(dataAsMap);
					DirectoryScanner.Action action = entryRead(unknownData);
					return action == DirectoryScanner.Action.Continue ? Action.Continue : Action.Stop;
				}
			});
		}
		finally {
			dir.recycle();
		}
		return lookupResult;
	}

	/**
	 * Override this method to filter the scan result. The default implementation always returns true.
	 * 
	 * @param data either {@link SearchResultData} or for known types one of its subclasses {@link FolderData} or {@link DatabaseData}
	 * @return true if accepted
	 */
	protected boolean isAccepted(SearchResultData data) {
		return true;
	}

	public static enum Action {Continue, Stop}
	
	/**
	 * Implement this method to get notified about each directory entry found and be
	 * able to cancel the scan process. The default implementation just returns {@link Action#Continue}.
	 * 
	 * @param data either {@link SearchResultData} or for known types one of its subclasses {@link FolderData} or {@link DatabaseData}
	 * @return action to continue scanning or stop
	 */
	protected Action entryRead(SearchResultData data) {
		return Action.Continue;
	}

	/**
	 * Base class for directory scan search results
	 * 
	 * @author Karsten Lehmann
	 */
	public static class SearchResultData {
		private Map<String,Object> m_rawData;

		/**
		 * Returns the raw data of the search result entry
		 * 
		 * @return data
		 */
		public Map<String,Object> getRawData() {
			return m_rawData;
		}

		/**
		 * Sets the raw data of the search result entry
		 * 
		 * @param rawData data
		 */
		void setRawData(Map<String,Object> rawData) {
			this.m_rawData = rawData;
		}
		
	}
	
	/**
	 * Subclass of {@link SearchResultData} that is used to return
	 * parsed data of folders.
	 * 
	 * @author Karsten Lehmann
	 */
	public static class FolderData extends SearchResultData {
		private String m_folderName;
		private String m_folderPath;
		
		/**
		 * Returns the name of the folder
		 * 
		 * @return name
		 */
		public String getFolderName() {
			return m_folderName;
		}
		
		/**
		 * Sets the name of the folder
		 * 
		 * @param folderName name
		 */
		private void setFolderName(String folderName) {
			this.m_folderName = folderName;
		}
		
		/**
		 * Returns the complete relative path of the folder in the
		 * data directory
		 * 
		 * @return path
		 */
		public String getFolderPath() {
			return m_folderPath;
		}
		
		/**
		 * Sets the complete relative path of the folder in the
		 * data directory
		 * 
		 * @param folderPath path
		 */
		private void setFolderPath(String folderPath) {
			this.m_folderPath = folderPath;
		}
	}

	/**
	 * Subclass of {@link SearchResultData} that is used to return
	 * parsed data of databases.
	 * 
	 * @author Karsten Lehmann
	 */
	public static class DatabaseData extends SearchResultData {
		private String m_title;
		private String m_fileName;
		private String m_filePath;
		private Calendar m_created;
		private Calendar m_modified;
		private Calendar m_lastFixup;
		private Calendar m_lastCompact;
		private Calendar m_nonDataMod;
		private String m_category;
		private String m_templateName;
		private String m_ineritTemplateName;
		
		/**
		 * Returns the database title
		 * 
		 * @return title
		 */
		public String getTitle() {
			return m_title;
		}
		
		/**
		 * Sets the database title
		 * 
		 * @param title title
		 */
		private void setTitle(String title) {
			this.m_title = title;
		}

		/**
		 * Returns the filename of the database
		 * 
		 * @return filename
		 */
		public String getFileName() {
			return m_fileName;
		}

		/**
		 * Sets the filename of the database
		 * 
		 * @param fileName filename
		 */
		private void setFileName(String fileName) {
			this.m_fileName = fileName;
		}

		/**
		 * Returns the complete relative path of the database in the data directory
		 * 
		 * @return path
		 */
		public String getFilePath() {
			return m_filePath;
		}

		/**
		 * Sets the complete relative path of the database in the data directory
		 * 
		 * @param filePath path
		 */
		private void setFilePath(String filePath) {
			this.m_filePath = filePath;
		}

		/**
		 * Returns the database creation date
		 * 
		 * @return creation date
		 */
		public Calendar getCreated() {
			return m_created;
		}

		/**
		 * Sets the database creation date
		 * 
		 * @param created creation date
		 */
		private void setCreated(Calendar created) {
			this.m_created = created;
		}

		/**
		 * Returns the database modification date
		 * 
		 * @return modification date
		 */
		public Calendar getModified() {
			return m_modified;
		}

		/**
		 * Sets the database modification date
		 * 
		 * @param modified modification date
		 */
		private void setModified(Calendar modified) {
			this.m_modified = modified;
		}
		
		/**
		 * Returns the date of the last fixup
		 * 
		 * @return last fixup
		 */
		public Calendar getLastFixup() {
			return this.m_lastFixup;
		}
		
		/**
		 * Sets the date of the last db fixup
		 * 
		 * @param lastFixup last fixup
		 */
		private void setLastFixup(Calendar lastFixup) {
			this.m_lastFixup = lastFixup;
		}
		
		/**
		 * Returns the date of the last compact
		 * 
		 * @return last compact
		 */
		public Calendar getLastCompact() {
			return this.m_lastCompact;
		}
		
		/**
		 * Sets the date of the last db compact
		 * 
		 * @param lastCompact last compact
		 */
		private void setLastCompact(Calendar lastCompact) {
			this.m_lastCompact = lastCompact;
		}
		
		/**
		 * Returns the date of the last design change
		 * 
		 * @return design modified date
		 */
		public Calendar getDesignModifiedDate() {
			return this.m_nonDataMod;
		}
		
		/**
		 * Sets the date of the last design change
		 * 
		 * @param nonDataMod design modified date
		 */
		private void setDesignModifiedDate(Calendar nonDataMod) {
			this.m_nonDataMod = nonDataMod;
		}
		
		/**
		 * Returns the database category
		 * 
		 * @return category or empty string
		 */
		public String getCategory() {
			return this.m_category;
		}
		
		/**
		 * Sets the database category
		 * 
		 * @param category category
		 */
		private void setCategory(String category) {
			this.m_category = category;
		}
		
		/**
		 * Returns the template name
		 * 
		 * @return template name if this database is a template, empty string otherwise
		 */
		public String getTemplateName() {
			return m_templateName;
		}
		
		private void setTemplateName(String templateName) {
			this.m_templateName = templateName;
		}
		
		/**
		 * Returns the name of the template that this database inherits its design from
		 * 
		 * @return inherit template name or empty string
		 */
		public String getInheritTemplateName() {
			return m_ineritTemplateName;
		}
		
		/**
		 * Sets the inherit template name
		 * 
		 * @param inheritTemplateName inherit template name
		 */
		private void setInheritTemplateName(String inheritTemplateName) {
			this.m_ineritTemplateName = inheritTemplateName;
		}
	}
}
