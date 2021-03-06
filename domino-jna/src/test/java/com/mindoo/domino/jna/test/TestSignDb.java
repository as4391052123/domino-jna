package com.mindoo.domino.jna.test;

import java.util.EnumSet;

import org.junit.Test;

import com.mindoo.domino.jna.NotesDatabase;
import com.mindoo.domino.jna.NotesDatabase.SignCallback;
import com.mindoo.domino.jna.NotesViewEntryData;
import com.mindoo.domino.jna.constants.NoteClass;

import lotus.domino.Session;

/**
 * Tests cases for database searches
 * 
 * @author Karsten Lehmann
 */
public class TestSignDb extends BaseJNATestClass {

	@Test
	public void testSignDb() {

		runWithSession(new IDominoCallable<Object>() {

			@Override
			public Object call(Session session) throws Exception {
				NotesDatabase db = new NotesDatabase(session, "", "test/signtest.nsf");
				db.signAll(EnumSet.of(NoteClass.ALLNONDATA), new SignCallback() {

					@Override
					public boolean shouldSign(NotesViewEntryData noteData, String currSigner) {
						return true;
					}

					@Override
					public boolean shouldReadSummaryDataFromDesignCollection() {
						return true;
					}
					
					@Override
					public Action noteSigned(NotesViewEntryData noteData) {
						System.out.println("Note signed: ID="+noteData.getNoteId()+", data="+noteData.getColumnDataAsMap());
						return Action.Continue;
					}
					
				});
				
				return null;
			}
		});
	}

}
