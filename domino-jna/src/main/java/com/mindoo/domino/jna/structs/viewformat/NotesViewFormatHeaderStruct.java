package com.mindoo.domino.jna.structs.viewformat;
import com.mindoo.domino.jna.structs.BaseStructure;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.List;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class NotesViewFormatHeaderStruct extends BaseStructure {
	/** Version number */
	public byte Version;
	/** View Style - Table,Calendar */
	public byte ViewStyle;
	public NotesViewFormatHeaderStruct() {
		super();
	}
	
	public static NotesViewFormatHeaderStruct newInstance() {
		return AccessController.doPrivileged(new PrivilegedAction<NotesViewFormatHeaderStruct>() {

			@Override
			public NotesViewFormatHeaderStruct run() {
				return new NotesViewFormatHeaderStruct();
			}
		});
	}

	protected List<String> getFieldOrder() {
		return Arrays.asList("Version", "ViewStyle");
	}
	/**
	 * @param Version Version number<br>
	 * @param ViewStyle View Style - Table,Calendar
	 */
	public NotesViewFormatHeaderStruct(byte Version, byte ViewStyle) {
		super();
		this.Version = Version;
		this.ViewStyle = ViewStyle;
	}
	
	public static NotesViewFormatHeaderStruct newInstance(final byte Version, final byte ViewStyle) {
		return AccessController.doPrivileged(new PrivilegedAction<NotesViewFormatHeaderStruct>() {

			@Override
			public NotesViewFormatHeaderStruct run() {
				return new NotesViewFormatHeaderStruct(Version, ViewStyle);
			}
		});
	}

	public NotesViewFormatHeaderStruct(Pointer peer) {
		super(peer);
	}
	
	public static NotesViewFormatHeaderStruct newInstance(final Pointer peer) {
		return AccessController.doPrivileged(new PrivilegedAction<NotesViewFormatHeaderStruct>() {

			@Override
			public NotesViewFormatHeaderStruct run() {
				return new NotesViewFormatHeaderStruct(peer);
			}
		});
	}

	public static class ByReference extends NotesViewFormatHeaderStruct implements Structure.ByReference {
		
	};
	public static class ByValue extends NotesViewFormatHeaderStruct implements Structure.ByValue {
		
	};
}
