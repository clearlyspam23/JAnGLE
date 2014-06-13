package com.clearlyspam23.GLE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {
	
	private List<ParameterMacro> runtimeCommand = new ArrayList<ParameterMacro>();
	
	private CoordinateSystem coordinateSystem;
	
	private List<LayerTemplate> layerTemplates = new ArrayList<LayerTemplate>();
	
	private static class StringMacro extends ParameterMacro
	{
		
		private String text;
		
		public StringMacro(String text)
		{
			this.text = text;
		}

		@Override
		protected String getMacroText() {
			return "";
		}

		@Override
		public String getDescription() {
			return "";
		}

		@Override
		public String getRuntimeText(JAnGLEData info) {
			return text;
		}
		
	}
	
	public void addParameter(ParameterMacro macro)
	{
		runtimeCommand.add(macro);
	}
	
	public void addParameter(String text)
	{
		runtimeCommand.add(new StringMacro(text));
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
			l.addLayer(t.createLayer());
		}
		return l;
	}

}
