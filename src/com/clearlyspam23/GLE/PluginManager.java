package com.clearlyspam23.GLE;

import java.util.ArrayList;
import java.util.List;

public class PluginManager {
	
	private List<CoordinateSystem> recognizedCoordinateSystems = new ArrayList<CoordinateSystem>();
	private List<PLanguageOptions<?>> recognizedProgrammingLanguages = new ArrayList<PLanguageOptions<?>>();
	private List<ParameterMacro> recognizedMacros = new ArrayList<ParameterMacro>();
	private List<LayerDefinition<?, ?>> recognizedLayerDefs = new ArrayList<LayerDefinition<?, ?>>();
	private List<CompressionFormat> recognizedCompressions = new ArrayList<CompressionFormat>();
	private List<Serializer> recognizedSerializers = new ArrayList<Serializer>();
	private List<PropertyDefinition<?, ?>> recognizedProperties = new ArrayList<PropertyDefinition<?, ?>>();
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
	public List<PropertyDefinition<?, ?>> getRecognizedProperties() {
		return recognizedProperties;
	}
	public void addProperty(PropertyDefinition<?, ?> property) {
		recognizedProperties.add(property);
	}
	
	

}
