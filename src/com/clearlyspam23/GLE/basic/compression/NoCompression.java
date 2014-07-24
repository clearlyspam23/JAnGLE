package com.clearlyspam23.GLE.basic.compression;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.clearlyspam23.GLE.template.CompressionFormat;

public class NoCompression extends CompressionFormat{

	@Override
	public String getName() {
		return "None";
	}

	@Override
	public void compress(String input, File file) throws IOException {
		FileOutputStream fstream = new FileOutputStream(file);
		byte[] b = input.getBytes();
		fstream.write(b);
		fstream.close();
		
	}

	@Override
	public String decompress(File file) throws IOException {
		 byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		 return new String(encoded);
	}

}
