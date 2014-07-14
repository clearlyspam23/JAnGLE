package com.clearlyspam23.GLE;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Template {
	
	//meta data
	private String templateName;
	private File templateFile;
	
	//Runtime data
	private List<ParameterMacro> runtimeCommand = new ArrayList<ParameterMacro>();
	@SuppressWarnings("rawtypes")
	private PLanguageOptions usedPLanguage;
	private Object pLanguageData;
	
	//Level Data
	private CoordinateSystem coordinateSystem;
	private Serializer serializer;
	private String extension;
	private CompressionFormat compression;
	
	//Layer data
	private List<LayerTemplate> layerTemplates = new ArrayList<LayerTemplate>();
	private Map<String, Class<?>> recognizedProperties = new HashMap<String, Class<?>>();
	private boolean allowArbitraryProperties = false;
	
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
		layerTemplates.add(temp);
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
		for(LayerTemplate t : layerTemplates)
		{
			l.addLayer(t.createLayer(l));
		}
		return l;
	}

	public boolean allowsArbitraryProperties() {
		return allowArbitraryProperties;
	}

	public void allowArbitraryProperties(boolean allowArbitraryProperties) {
		this.allowArbitraryProperties = allowArbitraryProperties;
	}

	public Serializer getSerializer() {
		return serializer;
	}

	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	public Set<Entry<String, Class<?>>> getProperties() {
		return recognizedProperties.entrySet();
	}

	public void addProperty(String name, Class<?> cls) {
		recognizedProperties.put(name, cls);
	}
	
	public boolean isValidProperty(String name, Class<?> cls){
		return allowArbitraryProperties||(cls!=null&&cls.equals(recognizedProperties.get(name)));
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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

}
