package com.clearlyspam23.GLE;

import java.util.ArrayList;
import java.util.List;

public class PluginManager {
	
	private List<CoordinateSystem> recognizedCoordinateSystems = new ArrayList<CoordinateSystem>();
	private List<PLanguageOptions<?>> recognizedProgrammingLanguages = new ArrayList<PLanguageOptions<?>>();
	private List<ParameterMacro> recognizedMacros = new ArrayList<ParameterMacro>();
	private List<LayerDefinition<?, ?>> recognizedLayerDefs = new ArrayList<LayerDefinition<?, ?>>();
	public List<CoordinateSystem> getRecognizedCoordinateSystems() {
		return recognizedCoordinateSystems;
	}
	public void addCoordinateSystems(CoordinateSystem s) {
		recognizedCoordinateSystems.add(s);
	}
	public List<PLanguageOptions<?>> getRecognizedProgrammingLanguages() {
		return recognizedProgrammingLanguages;
	}
	public void addProgrammingLanguage(PLanguageOptions<?> pLang) {
		recognizedProgrammingLanguages.add(pLang);
	}
	public List<ParameterMacro> getRecognizedMacros() {
		return recognizedMacros;
	}
	public void addMacro(ParameterMacro macro) {
		recognizedMacros.add(macro);
	}
	public List<LayerDefinition<?, ?>> getRecognizedLayerDefs() {
		return recognizedLayerDefs;
	}
	public void setRecognizedLayerDefs(LayerDefinition<?, ?> layerDef) {
		recognizedLayerDefs.add(layerDef);
	}

}
