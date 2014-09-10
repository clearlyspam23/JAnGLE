package com.clearlyspam23.GLE;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clearlyspam23.GLE.level.LayerDefinition;
import com.clearlyspam23.GLE.level.LayerTemplate;
import com.clearlyspam23.GLE.level.Level;
import com.clearlyspam23.GLE.template.CompressionFormat;
import com.clearlyspam23.GLE.template.CoordinateSystem;
import com.clearlyspam23.GLE.template.LevelSerializer;
import com.clearlyspam23.GLE.template.PLanguageOptions;
import com.clearlyspam23.GLE.template.ParameterMacro;
import com.clearlyspam23.GLE.util.Vector2;

public class Template {
	
	//probably should change this later
	public static final String defaultLocation = System.getProperty("user.dir");
	
	//meta data
	private String name = "";
	private File templateFile = new File(defaultLocation);
	
	//Runtime data
	private List<ParameterMacro> runtimeCommand = new ArrayList<ParameterMacro>();
	@SuppressWarnings("rawtypes")
	private PLanguageOptions usedPLanguage;
	private Object pLanguageData;
	
	//General Data
	private CoordinateSystem coordinateSystem;
	private LevelSerializer serializer;
	private CompressionFormat compression;
	private String extension;
	private boolean useCustomExtension = false;
	private boolean useDefaultDirectory = true;
	private Vector2 defaultSize = new Vector2();
	
	//Layer data
	private List<LayerTemplate> layerTemplates = new ArrayList<LayerTemplate>();
	@SuppressWarnings("rawtypes")
	private List<PropertyTemplate> activeProperties = new ArrayList<PropertyTemplate>();
	
	private Map<LayerDefinition, Map<String, Object>> templateData = new HashMap<LayerDefinition, Map<String, Object>>();
	
	public void addParameter(ParameterMacro macro)
	{
		runtimeCommand.add(macro); 
	}
	
	public List<String> getParameterMacros()
	{
		List<String> ans = new ArrayList<String>();
		for(ParameterMacro m : runtimeCommand)
			ans.add(m.getRawText());
		return ans;
	}
	
	public void clearParameters(){
		
	}
	
	public void addLayerTemplate(LayerTemplate temp)
	{
		temp.setTemplate(this);
		layerTemplates.add(temp);
	}
	
	public List<LayerTemplate> getLayers(){
		return layerTemplates;
	}
	
	public void setLayers(List<LayerTemplate> list){
		layerTemplates = list;
	}
	
	public String getRuntimeCall(JAnGLEData data)
	{
		if(runtimeCommand.isEmpty())
			return "";
		String ans = runtimeCommand.get(0).getRuntimeText(data);
		for(int i = 1; i < runtimeCommand.size(); i++)
			ans += " " + runtimeCommand.get(1).getRuntimeText(data);
		return ans;
	}

	public CoordinateSystem getCoordinateSystem() {
		return coordinateSystem;
	}

	public void setCoordinateSystem(CoordinateSystem coordinateSystem) {
		this.coordinateSystem = coordinateSystem;
	}
	
	public Level generateLevel()
	{
		Level l = new Level(this);
		return l;
	}

	public LevelSerializer getSerializer() {
		return serializer;
	}

	public void setSerializer(LevelSerializer serializer) {
		this.serializer = serializer;
	}

	@SuppressWarnings("rawtypes")
	public List<PropertyTemplate> getActiveProperties() {
		return activeProperties;
	}

	@SuppressWarnings("rawtypes")
	public void addProperty(PropertyTemplate tmp) {
		activeProperties.add(tmp);
	}
	
	public void setActiveProperties(List<PropertyTemplate> props){
		activeProperties = props;
	}

	public String getTemplateName() {
		return name;
	}

	public void setTemplateName(String templateName) {
		this.name = templateName;
	}

	public File getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(File templateFile) {
		this.templateFile = templateFile;
	}

	public PLanguageOptions<?> getUsedPLanguage() {
		return usedPLanguage;
	}

	public void setUsedPLanguage(PLanguageOptions<?> usedPLanguage) {
		this.usedPLanguage = usedPLanguage;
	}

	public Object getpLanguageData() {
		return pLanguageData;
	}

	public void setpLanguageData(Object pLanguageData) {
		this.pLanguageData = pLanguageData;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public CompressionFormat getCompression() {
		return compression;
	}

	public void setCompression(CompressionFormat compression) {
		this.compression = compression;
	}

	public boolean isUsingCustomExtension() {
		return useCustomExtension;
	}

	public void useCustomExtension(boolean useCustom) {
		useCustomExtension = useCustom;
	}

	public boolean isUsingDefaultDirectory() {
		return useDefaultDirectory;
	}

	public void useDefaultDirectory(boolean useDefaultDirectory) {
		this.useDefaultDirectory = useDefaultDirectory;
	}
	
	public LayerTemplate getLayerTemplate(String name){
		for(LayerTemplate t : layerTemplates)
			if(t.getName().equals(name))
				return t;
		return null;
	}

	@SuppressWarnings("rawtypes")
	public PropertyTemplate getPropertyTemplate(String key) {
		for(PropertyTemplate t : activeProperties)
			if(t.getName().equals(key))
				return t;
		return null;
	}
	
	public void putTemplateData(LayerDefinition def, String name, Object data){
		if(!templateData.containsKey(def)){
			templateData.put(def, new HashMap<String, Object>());
		}
		templateData.get(def).put(name, data);
	}
	
	public Object getTemplateData(LayerDefinition def, String name){
		return templateData.get(def).get(name);
	}

	public Vector2 getDefaultSize() {
		return defaultSize;
	}

	public void setDefaultSize(Vector2 defaultSize) {
		this.defaultSize = defaultSize;
	}

	public Map<LayerDefinition, Map<String, Object>> getTemplateData() {
		return templateData;
	}

	public void setTemplateData(
			Map<LayerDefinition, Map<String, Object>> templateData) {
		this.templateData = templateData;
	}

}
