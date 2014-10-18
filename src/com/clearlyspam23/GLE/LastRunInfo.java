package com.clearlyspam23.GLE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.yaml.snakeyaml.Yaml;

import com.clearlyspam23.GLE.level.Level;

public class LastRunInfo {
	
	private static final File DEFAULT_LAST_RUN = new File(".jangle");
	
	private Yaml yaml = new Yaml();
	private File output;
	private SaveData lastData;
	
	public LastRunInfo(){
		this(DEFAULT_LAST_RUN);
	}
	
	public LastRunInfo(File file){
		output = file;
	}
	
	public void save(JAnGLEData data){
		ArrayList<String> list = new ArrayList<String>();
		for(Level l : data.getOpenLevels()){
			if(l.getSaveFile()!=null)
				list.add(l.getSaveFile().getPath());
		}
		String openTemplate = null;
		if(data.getOpenTemplate()!=null)
			openTemplate = data.getOpenTemplate().getTemplateFile().getPath();
		String currentLevel = null;
		if(data.getCurrentLevel()!=null&&data.getCurrentLevel().getSaveFile()!=null){
			currentLevel = data.getCurrentLevel().getSaveFile().getPath();
		}
		lastData = new SaveData(openTemplate, list, currentLevel);
		try {
			FileWriter writer = new FileWriter(output);
			yaml.dump(lastData, writer);
			writer.close();
		} catch (IOException e) {
		}
	}
	
	public boolean load(){
		try {
			FileReader reader = new FileReader(output);
			lastData = (SaveData) yaml.load(reader);
			return true;
		} catch (FileNotFoundException e) {
			//subtly fail, this means that this is our first time running, probably shouldnt error
		}
		return false;
	}
	
	public File getTemplateFile(){
		return new File(lastData.getOpenTemplate());
	}
	
	public boolean hasData(){
		return lastData!=null;
	}
	
	public boolean hasOpenTemplate(){
		return hasData()&&lastData.getOpenTemplate()!=null;
	}
	
	public boolean isCurrentLevel(File level){
		return level.getPath().equals(lastData.getCurrentLevel());
	}
	
	public File[] getOpenLevels(){
		File[] files = new File[lastData.getOpenLevels().size()];
		for(int i = 0; i < files.length; i++)
			files[i] = new File(lastData.getOpenLevels().get(i));
		return files;
	}

}
