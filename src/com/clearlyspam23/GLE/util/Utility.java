package com.clearlyspam23.GLE.util;

import java.util.ArrayList;

public class Utility {
	
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

}
