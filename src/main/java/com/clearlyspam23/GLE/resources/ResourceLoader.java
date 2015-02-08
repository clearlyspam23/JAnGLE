package com.clearlyspam23.GLE.resources;

import java.io.File;
import java.io.IOException;

public interface ResourceLoader<T> {
	
	public T loadResource(File file) throws IOException;

}
