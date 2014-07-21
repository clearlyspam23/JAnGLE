package com.clearlyspam23.GLE;

import java.util.ArrayList;
import java.util.List;

public class PluginManager {
	
	private List<CoordinateSystem> recognizedCoordinateSystems = new ArrayList<CoordinateSystem>();
	@SuppressWarnings("rawtypes")
	private List<PLanguageOptions> recognizedProgrammingLanguages = new ArrayList<PLanguageOptions>();
	private List<ParameterMacro> recognizedMacros = new ArrayList<ParameterMacro>();
	@SuppressWarnings("rawtypes")
	private List<LayerDefinition> recognizedLayerDefs = new ArrayList<LayerDefinition>();
	private List<CompressionFormat> recognizedCompressions = new ArrayList<CompressionFormat>();
	private List<Serializer> recognizedSerializers = new ArrayList<Serializer>();
	@SuppressWarnings("rawtypes")
	private List<PropertyDefinition> recognizedProperties = new ArrayList<PropertyDefinition>();
	public List<CoordinateSystem> getRecognizedCoordinateSystems() {
		return recognizedCoordinateSystems;
	}
	public void addCoordinateSystems(CoordinateSystem s) {
		recognizedCoordinateSystems.add(s);
	}
	@SuppressWarnings("rawtypes")
	public List<PLanguageOptions> getRecognizedProgrammingLanguages() {
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
	@SuppressWarnings("rawtypes")
	public List<LayerDefinition> getRecognizedLayerDefs() {
		return recognizedLayerDefs;
	}
	public void setRecognizedLayerDefs(LayerDefinition<?, ?> layerDef) {
		recognizedLayerDefs.add(layerDef);
	}
	public List<CompressionFormat> getRecognizedCompressions() {
		return recognizedCompressions;
	}
	public void addCompression(CompressionFormat compression) {
		recognizedCompressions.add(compression);
	}
	public List<Serializer> getRecognizedSerializers() {
		return recognizedSerializers;
	}
	public void addSerializer(Serializer serializer) {
		recognizedSerializers.add(serializer);
	}
	@SuppressWarnings("rawtypes")
	public List<PropertyDefinition> getRecognizedProperties() {
		return recognizedProperties;
	}
	public void addProperty(PropertyDefinition<?, ?> property) {
		recognizedProperties.add(property);
	}
	
	

}
