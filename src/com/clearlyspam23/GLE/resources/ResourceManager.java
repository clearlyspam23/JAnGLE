package com.clearlyspam23.GLE.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
	
	private static ResourceManager manager;
	
	public static ResourceManager get(){
		if(manager==null)
			manager = new ResourceManager();
		return manager;
	}
	
	private static final class Resource<T>{
		
		private File backingFile;
		private long lastModified;
		private T data;
		private ResourceLoader<T> loader;
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Resource(String file, ResourceLoader loader) throws IOException{
			this.loader = loader;
			setFile(file);
		}
		
		public T getResource() throws IOException{
			if(lastModified!=backingFile.lastModified()){
				data = loader.loadResource(backingFile);
				lastModified = backingFile.lastModified();
			}
			return data;
		}
		
		@SuppressWarnings({"unchecked", "rawtypes" })
		public void setResourceLoader(ResourceLoader loader){
			this.loader = loader;
		}
		
		public final void setFile(String file) throws IOException{
			backingFile = new File(file);
			data = loader.loadResource(backingFile);
			lastModified = backingFile.lastModified();
		}

	}
	
	private Map<Class<?>, Map<String, Resource<?>>> resourceFileMap = new HashMap<Class<?>, Map<String, Resource<?>>>();
	private Map<Class<?>, ResourceLoader<?>> loaderMap = new HashMap<Class<?>, ResourceLoader<?>>();
	
	private ResourceManager(){
		registerResourceType(BufferedImage.class, new ImageResourceLoader());
	}
	
	public <T> boolean isResourceTypeRegistered(Class<T> type){
		return resourceFileMap.get(type)!=null;
	}
	
	public <T> void registerResourceType(Class<T> type){
		resourceFileMap.put(type, new HashMap<String, Resource<?>>());
	}
	
	public <T> void registerResourceType(Class<T> type, ResourceLoader<T> loader){
		loaderMap.put(type, loader);
		resourceFileMap.put(type, new HashMap<String, Resource<?>>());
	}
	
	/**
	 * gets an arbitrary resource from the given filename
	 * If this resource has been loaded before, and the file has not been changed, it will simply load a version of it from memory
	 * if the file has been changed or the resource has not been loaded yet, a new copy will be loaded into the manager.
	 * uses the loader passed into this function for all future loads, but may not necessarily reload the resource requested.
	 * @param filename a string representation of the file to be loaded
	 * @param type the type of the resource to load
	 * @param resourceLoader the ResourceLoader used to load this resource
	 * @return the resource represented by the given filename
	 */
	public <T> T getResource(String filename, Class<T> type, ResourceLoader<?> resourceLoader){
		try {
			if(!isResourceTypeRegistered(type))
				throw new RuntimeException("Type " + type + " is not registered");
			if(!resourceFileMap.get(type).containsKey(filename))
				resourceFileMap.get(type).put(filename, new Resource<T>(filename, resourceLoader));
			else
				resourceFileMap.get(type).get(filename).setResourceLoader(resourceLoader);
			return type.cast(resourceFileMap.get(type).get(filename).getResource());
		} catch (IOException e) {
			
		}
		catch(Exception e){
			//do something cool here too
		}
		return null;
	}
	
	/**
	 * gets an arbitrary resource from the given filename
	 * If this resource has been loaded before, and the file has not been changed, it will simply load a version of it from memory
	 * if the file has been changed or the resource has not been loaded yet, a new copy will be loaded into the manager.
	 * uses the default loader passed in on registration to load the resource. If there is no default loader, this method will throw a NullPointerException()
	 * @param filename a string representation of the file to be loaded
	 * @param type the type of the resource to load
	 * @return the resource represented by the given filename
	 */
	public <T> T getResource(String filename, Class<T> type){
		return getResource(filename, type, loaderMap.get(type));
	}
	
	/**
	 * convenience method to load image resources.
	 * Translates directly to getResource(filename, BufferedImage.class);
	 * @param filename the image file to be loaded
	 * @return the loaded image as a BufferedImage
	 */
	public BufferedImage getImageResource(String filename){
		return getResource(filename, BufferedImage.class);
	}

}
