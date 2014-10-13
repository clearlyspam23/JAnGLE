package com.clearlyspam23.GLE;

import java.util.ArrayList;
import java.util.List;

import com.clearlyspam23.GLE.GUI.template.TemplateSubPanel;
import com.clearlyspam23.GLE.level.LayerDefinition;
import com.clearlyspam23.GLE.template.CompressionFormat;
import com.clearlyspam23.GLE.template.CoordinateSystem;
import com.clearlyspam23.GLE.template.LevelSerializer;
import com.clearlyspam23.GLE.template.PLanguageOptions;
import com.clearlyspam23.GLE.template.ParameterMacro;

public class PluginManager {
	
	private List<CoordinateSystem> recognizedCoordinateSystems = new ArrayList<CoordinateSystem>();
	@SuppressWarnings("rawtypes")
	private List<PLanguageOptions> recognizedProgrammingLanguages = new ArrayList<PLanguageOptions>();
	private List<ParameterMacro> recognizedMacros = new ArrayList<ParameterMacro>();
	@SuppressWarnings("rawtypes")
	private List<LayerDefinition> recognizedLayerDefs = new ArrayList<LayerDefinition>();
	private List<CompressionFormat> recognizedCompressions = new ArrayList<CompressionFormat>();
	private List<LevelSerializer> recognizedSerializers = new ArrayList<LevelSerializer>();
	@SuppressWarnings("rawtypes")
	private List<PropertyDefinition> recognizedProperties = new ArrayList<PropertyDefinition>();
	private List<TemplateSubPanel> templatePanels = new ArrayList<TemplateSubPanel>();
	private List<TemplateSubPanel> advancedTemplatePanels = new ArrayList<TemplateSubPanel>();
	
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
	public void addLayerDefinition(LayerDefinition<?, ?> layerDef) {
		recognizedLayerDefs.add(layerDef);
	}
	public List<CompressionFormat> getRecognizedCompressions() {
		return recognizedCompressions;
	}
	public void addCompression(CompressionFormat compression) {
		recognizedCompressions.add(compression);
	}
	public List<LevelSerializer> getRecognizedSerializers() {
		return recognizedSerializers;
	}
	public void addSerializer(LevelSerializer serializer) {
		recognizedSerializers.add(serializer);
	}
	@SuppressWarnings("rawtypes")
	public List<PropertyDefinition> getRecognizedProperties() {
		return recognizedProperties;
	}
	public void addProperty(PropertyDefinition<?, ?> property) {
		recognizedProperties.add(property);
	}
	public List<TemplateSubPanel> getTemplatePanels() {
		return templatePanels;
	}
	public void addTemplatePanel(TemplateSubPanel templatePanel) {
		templatePanels.add(templatePanel);
	}
	public List<TemplateSubPanel> getAdvancedTemplatePanels() {
		return advancedTemplatePanels;
	}
	public void addAdvancedTemplatePanel(TemplateSubPanel advancedTemplatePanel) {
		advancedTemplatePanels.add(advancedTemplatePanel);
	}
	
	

}
