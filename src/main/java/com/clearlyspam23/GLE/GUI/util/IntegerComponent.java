package com.clearlyspam23.GLE.GUI.util;

import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import org.apache.commons.lang3.math.NumberUtils;

import com.clearlyspam23.GLE.util.IntegerDocumentFilter;

public class IntegerComponent extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8908075517690943424L;

	public IntegerComponent() {
		this(true);
	}
	
	public IntegerComponent(boolean allowsNegative){
		DocumentFilter filter = new IntegerDocumentFilter(allowsNegative);
		PlainDocument doc = new PlainDocument();
		doc.setDocumentFilter(filter);
		setDocument(doc);
	}
	
	public int getValue(){
		if(getText().length()<=0||!NumberUtils.isNumber(getText()))
			return 0;
		return Integer.parseInt(getText());
	}
	
	public void setValue(int value){
		setText(Integer.toString(value));
	}

}
