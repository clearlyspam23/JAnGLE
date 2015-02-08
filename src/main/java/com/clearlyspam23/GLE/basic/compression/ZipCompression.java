package com.clearlyspam23.GLE.basic.compression;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import com.clearlyspam23.GLE.template.CompressionFormat;

public class ZipCompression extends CompressionFormat {

	@Override
	public String getName() {
		return "Zip";
	}

	@Override
	public void compress(String input, File file) throws IOException {
		ZipOutputStream zstream = new ZipOutputStream(new FileOutputStream(file));
		ZipEntry e = new ZipEntry(file.getName());
		zstream.putNextEntry(e);
		zstream.write(input.getBytes());
		zstream.closeEntry();
		zstream.close();
	}

	@Override
	public String decompress(File file) throws IOException {
		ZipFile f = new ZipFile(file);
		ZipEntry e = f.getEntry(file.getName());
		InputStream is = f.getInputStream(e);
		java.util.Scanner s = new java.util.Scanner(is);
		s.useDelimiter("\\A");
		String ans = s.hasNext() ? s.next() : "";
		s.close();
		f.close();
	    return ans;
	}

}
