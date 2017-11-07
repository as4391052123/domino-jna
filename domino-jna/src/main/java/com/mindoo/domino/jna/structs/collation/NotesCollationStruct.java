package com.mindoo.domino.jna.structs.collation;
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
public class NotesCollationStruct extends BaseStructure {
	/** Size of entire buffer in bytes */
	public short BufferSize;
	/** Number of items following */
	public short Items;
	/** See COLLATION_FLAG_xxx */
	public byte Flags;
	/** Must be COLLATION_SIGNATURE */
	public byte signature;
	public NotesCollationStruct() {
		super();
	}
	
	public static NotesCollationStruct newInstance() {
		return AccessController.doPrivileged(new PrivilegedAction<NotesCollationStruct>() {

			@Override
			public NotesCollationStruct run() {
				return new NotesCollationStruct();
			}
		});
	}

	protected List<String> getFieldOrder() {
		return Arrays.asList("BufferSize", "Items", "Flags", "signature");
	}
	/**
	 * @param BufferSize Size of entire buffer in bytes<br>
	 * @param Items Number of items following<br>
	 * @param Flags See COLLATION_FLAG_xxx<br>
	 * @param signature Must be COLLATION_SIGNATURE
	 */
	public NotesCollationStruct(short BufferSize, short Items, byte Flags, byte signature) {
		super();
		this.BufferSize = BufferSize;
		this.Items = Items;
		this.Flags = Flags;
		this.signature = signature;
	}
	
	public static NotesCollationStruct newInstance(final short BufferSize, final short Items, final byte Flags, final byte signature) {
		return AccessController.doPrivileged(new PrivilegedAction<NotesCollationStruct>() {

			@Override
			public NotesCollationStruct run() {
				return new NotesCollationStruct(BufferSize, Items, Flags, signature);
			}
		});
	}

	public NotesCollationStruct(Pointer peer) {
		super(peer);
	}
	
	public static NotesCollationStruct newInstance(final Pointer p) {
		return AccessController.doPrivileged(new PrivilegedAction<NotesCollationStruct>() {

			@Override
			public NotesCollationStruct run() {
				return new NotesCollationStruct(p);
			}
		});
	}

	public static class ByReference extends NotesCollationStruct implements Structure.ByReference {
		
	};
	public static class ByValue extends NotesCollationStruct implements Structure.ByValue {
		
	};
}
