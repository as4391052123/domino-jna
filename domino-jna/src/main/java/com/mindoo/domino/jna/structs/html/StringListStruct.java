package com.mindoo.domino.jna.structs.html;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mindoo.domino.jna.structs.BaseStructure;
import com.mindoo.domino.jna.utils.NotesStringUtils;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
/**
 * <i>native declaration : line 2</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class StringListStruct extends BaseStructure {
	/** number of strings in the list - 0 indicates empty list ('first' is undefined) */
	public int count;
	/**
	 * pointer to first string in list - undefined if count==0<br>
	 * C type : char*
	 */
	public Pointer first;
	
	public StringListStruct() {
		super();
	}

	/**
	 * Converts all string list values to a Java {@link List}
	 * 
	 * @return list
	 */
	public List<String> getValues() {
		if (count==0)
			return Collections.emptyList();

		List<String> strValues = new ArrayList<String>(count);
		int offset = 0;
		
		Pointer stringStartPtr = first;
		
		for (int i=0; i<count; i++) {
			while (stringStartPtr.getByte(offset)!=0) {
				offset++;
			}
			String currStr = NotesStringUtils.fromLMBCS(stringStartPtr, offset);
			strValues.add(currStr);
			stringStartPtr = stringStartPtr.share(offset+1);
		}
		return strValues;
	}

	public static StringListStruct newInstance() {
		return AccessController.doPrivileged(new PrivilegedAction<StringListStruct>() {

			@Override
			public StringListStruct run() {
				return new StringListStruct();
			}
		});
	}


	protected List<String> getFieldOrder() {
		return Arrays.asList("count", "first");
	}

	/**
	 * @param count number of strings in the list - 0 indicates empty list ('first' is undefined)<br>
	 * @param first pointer to first string in list - undefined if count==0<br>
	 * C type : char*
	 */
	public StringListStruct(int count, Pointer first) {
		super();
		this.count = count;
		this.first = first;
	}

	public static StringListStruct newInstance(final int count, final Pointer first) {
		return AccessController.doPrivileged(new PrivilegedAction<StringListStruct>() {

			@Override
			public StringListStruct run() {
				return new StringListStruct(count, first);
			}
		});
	}

	public StringListStruct(Pointer peer) {
		super(peer);
	}

	public static StringListStruct newInstance(final Pointer peer) {
		return AccessController.doPrivileged(new PrivilegedAction<StringListStruct>() {

			@Override
			public StringListStruct run() {
				return new StringListStruct(peer);
			}
		});
	}

	public static class ByReference extends StringListStruct implements Structure.ByReference {

	};

	public static class ByValue extends StringListStruct implements Structure.ByValue {

	};
}
