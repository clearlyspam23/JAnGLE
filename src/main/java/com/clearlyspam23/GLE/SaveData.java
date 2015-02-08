package com.clearlyspam23.GLE;

import java.util.List;

public class SaveData{
	private String openTemplate;
	private List<String> openLevels;
	private String currentLevel;
	
	public SaveData(){
		
	}
	
	public SaveData(String openTemplate, List<String> openLevels, String currentLevel){
		this.setOpenTemplate(openTemplate);
		this.setOpenLevels(openLevels);
		this.setCurrentLevel(currentLevel);
	}

	public String getOpenTemplate() {
		return openTemplate;
	}

	public void setOpenTemplate(String openTemplate) {
		this.openTemplate = openTemplate;
	}

	public List<String> getOpenLevels() {
		return openLevels;
	}

	public void setOpenLevels(List<String> openLevels) {
		this.openLevels = openLevels;
	}

	public String getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}
}
