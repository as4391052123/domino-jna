package com.mindoo.domino.jna.structs.viewformat;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.List;

import com.mindoo.domino.jna.structs.BaseStructure;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class NotesViewTableFormat4Struct extends BaseStructure {
	/** Length of this structure */
	public short Length;
	/** Reserved for future use */
	public int Flags;
	/** see viewprop.h - way to repeat image */
	public short RepeatType;
	
	public NotesViewTableFormat4Struct() {
		super();
	}
	
	public static NotesViewTableFormat4Struct newInstance() {
		return AccessController.doPrivileged(new PrivilegedAction<NotesViewTableFormat4Struct>() {

			@Override
			public NotesViewTableFormat4Struct run() {
				return new NotesViewTableFormat4Struct();
			}
		});
	}

	protected List<String> getFieldOrder() {
		return Arrays.asList("Length", "Flags", "RepeatType");
	}
	/**
	 * @param Length Length of this structure<br>
	 * @param Flags Reserved for future use<br>
	 * @param RepeatType see viewprop.h - way to repeat image
	 */
	public NotesViewTableFormat4Struct(short Length, int Flags, short RepeatType) {
		super();
		this.Length = Length;
		this.Flags = Flags;
		this.RepeatType = RepeatType;
	}
	
	public static NotesViewTableFormat4Struct newInstance(final short Length, final int Flags, final short RepeatType) {
		return AccessController.doPrivileged(new PrivilegedAction<NotesViewTableFormat4Struct>() {

			@Override
			public NotesViewTableFormat4Struct run() {
				return new NotesViewTableFormat4Struct(Length, Flags, RepeatType);
			}
		});
	}

	public NotesViewTableFormat4Struct(Pointer peer) {
		super(peer);
	}
	
	public static NotesViewTableFormat4Struct newInstance(final Pointer peer) {
		return AccessController.doPrivileged(new PrivilegedAction<NotesViewTableFormat4Struct>() {

			@Override
			public NotesViewTableFormat4Struct run() {
				return new NotesViewTableFormat4Struct(peer);
			}
		});
	}

	public static class ByReference extends NotesViewTableFormat4Struct implements Structure.ByReference {
		
	};
	public static class ByValue extends NotesViewTableFormat4Struct implements Structure.ByValue {
		
	};
}
