package com.mindoo.domino.jna.structs.viewformat;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.List;

import com.mindoo.domino.jna.structs.BaseStructure;
import com.mindoo.domino.jna.structs.NotesNFMTStruct;
import com.mindoo.domino.jna.structs.NotesTFMTStruct;
import com.mindoo.domino.jna.structs.collation.NotesCollateDescriptorStruct;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class NotesViewColumnFormatStruct extends BaseStructure {
	/** VIEW_COLUMN_FORMAT_SIGNATURE */
	public short Signature;
	/** see VCF1_xxx */
	public short Flags1;
	/** Item name string size */
	public short ItemNameSize;
	/** Title string size */
	public short TitleSize;
	/** Compiled formula size */
	public short FormulaSize;
	/** Constant value size */
	public short ConstantValueSize;
	/**
	 * Display width - 1/8 ave. char <br>
	 * width units
	 */
	public short DisplayWidth;
	/** Display font ID */
	public int FontID;
	/** see VCF2_xxx */
	public short Flags2;
	/**
	 * Number format specification<br>
	 * C type : NFMT
	 */
	public NotesNFMTStruct NumberFormat;
	/**
	 * Time format specification<br>
	 * C type : TFMT
	 */
	public NotesTFMTStruct TimeFormat;
	/** See VIEW_COL_xxx */
	public short FormatDataType;
	/** List Separator */
	public short ListSep;
	public NotesViewColumnFormatStruct() {
		super();
	}
	
	public static NotesViewColumnFormatStruct newInstance() {
		return AccessController.doPrivileged(new PrivilegedAction<NotesViewColumnFormatStruct>() {

			@Override
			public NotesViewColumnFormatStruct run() {
				return new NotesViewColumnFormatStruct();
			}
		});
	}

	protected List<String> getFieldOrder() {
		return Arrays.asList("Signature", "Flags1", "ItemNameSize", "TitleSize", "FormulaSize", "ConstantValueSize", "DisplayWidth", "FontID", "Flags2", "NumberFormat", "TimeFormat", "FormatDataType", "ListSep");
	}
	public NotesViewColumnFormatStruct(Pointer peer) {
		super(peer);
	}
	
	public static NotesViewColumnFormatStruct newInstance(final Pointer peer) {
		return AccessController.doPrivileged(new PrivilegedAction<NotesViewColumnFormatStruct>() {

			@Override
			public NotesViewColumnFormatStruct run() {
				return new NotesViewColumnFormatStruct(peer);
			}
		});
	}

	public static class ByReference extends NotesViewColumnFormatStruct implements Structure.ByReference {
		
	};
	public static class ByValue extends NotesViewColumnFormatStruct implements Structure.ByValue {
		
	};
}
