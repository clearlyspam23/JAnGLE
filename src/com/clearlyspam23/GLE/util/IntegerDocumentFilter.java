package com.clearlyspam23.GLE.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class IntegerDocumentFilter extends DocumentFilter {
	
	@Override
    public void insertString(FilterBypass fb, int off, String str, AttributeSet attr) 
        throws BadLocationException 
    {
    	String current = fb.getDocument().getText(0, fb.getDocument().getLength());
    	if(off==0&&str.length()>0&&str.charAt(0)=='-'&&(current.length()<=0||current.charAt(0)!='-')){
    		fb.insertString(off++, str.substring(0, 1), attr);
    		str = str.substring(1);
    	}
//    	if("0".equals(current)||"-0".equals(current)){
//    		while(str.length()>1&&"00".equals(str.substring(0, 2)))
//    			str = str.substring(1);
//    		fb.replace(fb.getDocument().getLength()-1, 1, "", attr);
//    	}
        fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits
    } 
    @Override
    public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr) 
        throws BadLocationException 
    {
    	String current = fb.getDocument().getText(0, fb.getDocument().getLength());
    	if(off==0&&str.length()>0&&str.charAt(0)=='-'&&(current.length()<=0||current.charAt(0)!='-')){
    		fb.insertString(off++, str.substring(0, 1), attr);
    		str = str.substring(1);
    	}
//    	if("0".equals(current)||"-0".equals(current)){
//    		while(str.length()>1&&"00".equals(str.substring(0, 2)))
//    			str = str.substring(1);
//    		fb.replace(fb.getDocument().getLength()-1, 1, "", attr);
//    	}
        fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
    }

}
