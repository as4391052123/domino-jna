package com.mindoo.domino.jna.structs;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class NotesGlobalInstanceId64Struct extends BaseStructure {
	/**
	 * database Creation time/date<br>
	 * C type : DBID
	 */
	public NotesTimeDateStruct File;
	/**
	 * note Modification time/date<br>
	 * C type : TIMEDATE
	 */
	public NotesTimeDateStruct Note;
	/**
	 * note ID within database<br>
	 * C type : NOTEID
	 */
	public int NoteID;
	
	/**
	 * @deprecated only public to be used by JNA; use static newInstance method instead to run in AccessController.doPrivileged block
	 */
	public NotesGlobalInstanceId64Struct() {
		super();
	}
	
	public static NotesGlobalInstanceId64Struct newInstance() {
		return AccessController.doPrivileged(new PrivilegedAction<NotesGlobalInstanceId64Struct>() {

			@Override
			public NotesGlobalInstanceId64Struct run() {
				return new NotesGlobalInstanceId64Struct();
			}
		});
	}
	
	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("File", "Note", "NoteID");
	}
	
	/**
	 * @param file database Creation time/date<br>
	 * C type : DBID<br>
	 * @param note note Modification time/date<br>
	 * C type : TIMEDATE<br>
	 * @param noteID note ID within database<br>
	 * C type : NOTEID
	 * @deprecated only public to be used by JNA; use static newInstance method instead to run in AccessController.doPrivileged block
	 */
	public NotesGlobalInstanceId64Struct(NotesTimeDateStruct file, NotesTimeDateStruct note, int noteID) {
		super();
		this.File = file;
		this.Note = note;
		this.NoteID = noteID;
	}
	
	public static NotesGlobalInstanceId64Struct newInstance(final NotesTimeDateStruct file, final NotesTimeDateStruct note, final int noteID) {
		return AccessController.doPrivileged(new PrivilegedAction<NotesGlobalInstanceId64Struct>() {

			@Override
			public NotesGlobalInstanceId64Struct run() {
				return new NotesGlobalInstanceId64Struct(file, note, noteID);
			}
		});
	}
	
	/**
	 * @deprecated only public to be used by JNA; use static newInstance method instead to run in AccessController.doPrivileged block
	 * 
	 * @param peer pointer
	 */
	public NotesGlobalInstanceId64Struct(Pointer peer) {
		super(peer);
	}
	
	public static NotesGlobalInstanceId64Struct newInstance(final Pointer peer) {
		return AccessController.doPrivileged(new PrivilegedAction<NotesGlobalInstanceId64Struct>() {

			@Override
			public NotesGlobalInstanceId64Struct run() {
				return new NotesGlobalInstanceId64Struct(peer);
			}
		});
	}
	
	public static class ByReference extends NotesGlobalInstanceId32Struct implements Structure.ByReference {

	};
	
	public static class ByValue extends NotesGlobalInstanceId32Struct implements Structure.ByValue {

	};
}
