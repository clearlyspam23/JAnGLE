package com.clearlyspam23.GLE.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.clearlyspam23.GLE.Nameable;

public class Utility {
	
	public static final String NEWLINE = System.getProperty("line.separator");
	
	public static ArrayList<String> tokenizeBySpaceAndQuote(String s)
	{
		//i dont know how to regex
		ArrayList<String> ans = new ArrayList<String>();
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < s.length(); i++)
		{
			if(Character.isWhitespace(s.charAt(i)))
			{
				if(buf.length()>0)
				{
					ans.add(buf.toString());
					buf = new StringBuffer();
				}
			}
			else if(s.charAt(i)=='"')
			{
				for(i++; i < s.length(); i++)
				{
					if(s.charAt(i)=='"')
					{
						ans.add(buf.toString());
						buf = new StringBuffer();
						break;
					}
					else
						buf.append(s.charAt(i));
				}
			}
			else
				buf.append(s.charAt(i));
		}
		ans.add(buf.toString());
		return ans;
	}
	
	public static void setModelTo(JComboBox<String> box, List<? extends Nameable> namedList){
		String[] model = new String[namedList.size()];
		for(int i = 0; i < namedList.size(); i++)
			model[i] = namedList.get(i).getName();
		
		box.setModel(new DefaultComboBoxModel<String>(model));
		box.setSelectedIndex(model.length>0 ? 0 : -1);
	}
	
	public static boolean isValidIndex(JComboBox<?> box, List<?> list){
		return isValidIndex(box.getSelectedIndex(), list);
	}
	
	public static boolean isValidIndex(int index, List<?> list){
		return index>=0&&index<list.size();
	}
	
	public static <T> T getValue(JComboBox<?> box, List<T> list){
		return list.get(box.getSelectedIndex());
	}
	
	public static <T> T tryGetValue(JComboBox<?> box, List<T> list){
		if(Utility.isValidIndex(box, list))
			return getValue(box, list);
		return null;
	}
	
	public static <T> void trySetIndex(T t, List<T> list, JComboBox<?> box){
		int index = list.indexOf(t);
		if(index>=0)
			box.setSelectedIndex(index);
	}
	
	public static <T> void trySetIndex(T t, List<T> list, JComboBox<?> box, int indexOnFailure){
		int index = list.indexOf(t);
		if(index<0)
			index = indexOnFailure;
		box.setSelectedIndex(index);
	}

}
