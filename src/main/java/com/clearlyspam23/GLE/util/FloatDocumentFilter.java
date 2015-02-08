package com.clearlyspam23.GLE.util;

import java.text.DecimalFormat;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.apache.commons.lang3.math.NumberUtils;

public class FloatDocumentFilter extends DocumentFilter {
	
	private static final DecimalFormat formatter = new DecimalFormat("#.####");
	
	private boolean allowsNegative;
	
	public FloatDocumentFilter(){
		this(true);
	}
	
	public FloatDocumentFilter(boolean allowsNegative){
		this.allowsNegative = allowsNegative;
	}
	
    @Override
    public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr) 
        throws BadLocationException 
    {
    	String current = fb.getDocument().getText(0, fb.getDocument().getLength());
    	String total = new StringBuilder(current).replace(off, off+len, str).toString();
    	if((NumberUtils.isNumber(total)
    			||allowsNegative&&"-".equals(str)&&(off==0||"0".equals(current))
    			||"0".equals(current)&&(NumberUtils.isNumber(str)||".".equals(str)&&off==1))&&!(!allowsNegative&&"-".equals(str)))
    	{
    		if("-".equals(str)){
    			if("0".equals(current))
    				fb.replace(0, 1, str, attr);
    			else
    				fb.replace(off, len, str, attr);
    			return;
    		}
    		if(".".equals(str)&&"0".equals(current)){
    			fb.replace(off, len, str, attr);
    			return;
    		}
    		double d = 0;
    		if(total.indexOf('-')<0)
    			d = NumberUtils.toDouble(total, Double.MAX_VALUE);
    		else
    			d = NumberUtils.toDouble(total, Double.MIN_VALUE);
    		if("0".equals(current)||d==Double.MAX_VALUE||d==Double.MIN_VALUE)
    			fb.replace(0, fb.getDocument().getLength(), formatter.format(d), attr);
    		else
    			fb.replace(off, len, str, attr);
    	}
    }

}
