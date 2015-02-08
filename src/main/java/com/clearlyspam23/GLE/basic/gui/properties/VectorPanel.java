package com.clearlyspam23.GLE.basic.gui.properties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.util.Vector2;

public class VectorPanel extends SubPanel {
	
	private VectorComponent min;
	private VectorComponent max;
	private VectorComponent def;
	public VectorPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{32, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblMin = new JLabel("Min");
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.anchor = GridBagConstraints.WEST;
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 0;
		gbc_lblMin.gridy = 0;
		add(lblMin, gbc_lblMin);
		
		min = new VectorComponent();
		GridBagConstraints gbc_vectorComponent = new GridBagConstraints();
		gbc_vectorComponent.insets = new Insets(0, 0, 5, 0);
		gbc_vectorComponent.gridx = 1;
		gbc_vectorComponent.gridy = 0;
		add(min, gbc_vectorComponent);
		
		JLabel lblMax = new JLabel("Max");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.anchor = GridBagConstraints.WEST;
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 0;
		gbc_lblMax.gridy = 1;
		add(lblMax, gbc_lblMax);
		
		max = new VectorComponent();
		GridBagConstraints gbc_vectorComponent_1 = new GridBagConstraints();
		gbc_vectorComponent_1.insets = new Insets(0, 0, 5, 0);
		gbc_vectorComponent_1.gridx = 1;
		gbc_vectorComponent_1.gridy = 1;
		add(max, gbc_vectorComponent_1);
		
		JLabel lblDefault = new JLabel("Default");
		GridBagConstraints gbc_lblDefault = new GridBagConstraints();
		gbc_lblDefault.insets = new Insets(0, 0, 0, 5);
		gbc_lblDefault.gridx = 0;
		gbc_lblDefault.gridy = 2;
		add(lblDefault, gbc_lblDefault);
		
		def = new VectorComponent();
		GridBagConstraints gbc_vectorComponent_2 = new GridBagConstraints();
		gbc_vectorComponent_2.gridx = 1;
		gbc_vectorComponent_2.gridy = 2;
		add(def, gbc_vectorComponent_2);
	}

	@Override
	public void reset() {
		setMinVector(new Vector2(0, 0));
		setMaxVector(new Vector2(1000, 1000));
		setDefaultVector(new Vector2(0, 0));
	}
	
	public Vector2 getMinVector(){
		return min.getVector();
	}
	
	public Vector2 getMaxVector(){
		return max.getVector();
	}
	
	public Vector2 getDefaultVector(){
		return def.getVector();
	}
	
	public void setMinVector(Vector2 vec){
		min.setToVector(vec);
	}
	
	public void setMaxVector(Vector2 vec){
		max.setToVector(vec);
	}
	
	public void setDefaultVector(Vector2 vec){
		def.setToVector(vec);
	}
}
