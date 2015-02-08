package com.clearlyspam23.GLE.template;

import java.io.File;
import java.io.IOException;

import com.clearlyspam23.GLE.Nameable;

public abstract class CompressionFormat implements Nameable{
	
	public abstract void compress(String input, File file) throws IOException;
	
	public abstract String decompress(File file) throws IOException;

}
