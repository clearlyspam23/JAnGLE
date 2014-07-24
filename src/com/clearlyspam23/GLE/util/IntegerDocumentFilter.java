package com.clearlyspam23.GLE.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.apache.commons.lang3.math.NumberUtils;

public class IntegerDocumentFilter extends DocumentFilter {
	
    @Override
    public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr) 
        throws BadLocationException 
    {
    	String current = fb.getDocument().getText(0, fb.getDocument().getLength());
    	String total = new StringBuilder(current).replace(off, off+len, str).toString();
    	if(NumberUtils.isNumber(total)&&total.indexOf('.')<0
    			||"-".equals(str)&&(off==0||"0".equals(current))
    			||"0".equals(current)&&NumberUtils.isNumber(str))
    	{
    		if("-".equals(str)){
    			if("0".equals(current))
    				fb.replace(0, 1, str, attr);
    			else
    				fb.replace(off, len, str, attr);
    			return;
    		}
    		int i = 0;
    		if(total.indexOf('-')<0)
    			i = NumberUtils.toInt(total, Integer.MAX_VALUE);
    		else
    			i = NumberUtils.toInt(total, Integer.MIN_VALUE);
    		if("0".equals(current)||i==Integer.MAX_VALUE||i==Integer.MIN_VALUE)
    			fb.replace(0, fb.getDocument().getLength(), Integer.toString(i), attr);
    		else
    			fb.replace(off, len, str, attr);
    	}
    }

}
