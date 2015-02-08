package com.clearlyspam23.GLE.resources;

import java.io.File;
import java.io.IOException;

public class Resource<T> {
	
	private File backingFile;
	private T data;
	private long lastModified;
	private ResourceLoader<T> loader;
	
	public Resource(ResourceLoader<T> loader){
		this.loader = loader;
	}
	
	public Resource(ResourceLoader<T> loader, File backingFile){
		this.loader = loader;
		setBackingFile(backingFile);
	}

	public File getBackingFile() {
		return backingFile;
	}

	public boolean setBackingFile(File backingFile) {
		this.backingFile = backingFile;
		try {
			reloadData();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	private void reloadData() throws IOException{
		data = loader.loadResource(backingFile);
		lastModified = backingFile.lastModified();
	}
	
	public T getData() throws IOException{
		if(backingFile==null)
			return null;
		if(backingFile.lastModified()!=lastModified)
			reloadData();
		return data;
	}

}
