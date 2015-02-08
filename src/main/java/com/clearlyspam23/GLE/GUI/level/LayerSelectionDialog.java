package com.clearlyspam23.GLE.GUI.level;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.GUI.util.ColorIcon;
import com.clearlyspam23.GLE.GUI.util.EmptyIcon;
import com.clearlyspam23.GLE.GUI.util.PanelList;
import com.clearlyspam23.GLE.GUI.util.PushButton;
import com.clearlyspam23.GLE.level.Layer;

public class LayerSelectionDialog extends JDialog{
	
	private PanelList list;
	private static final Font font = new Font("Tahoma", Font.PLAIN, 14);
	private Icon icon;
	private Icon colorIcon;
	
	public LayerSelectionDialog(Frame frame, @SuppressWarnings("rawtypes") List<Layer> layers, List<PNode> nodes, final LayerContainer container) {
		super(frame, "Layers");
		
		try{
			Image image = ImageIO.read(new File("images/VisibilityIcon.png"));
			icon = new ImageIcon(image);
		}
		catch(Exception e){
			//do something better here
			icon = new EmptyIcon();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		list = new PanelList(0, 0, 0, 0);
		colorIcon = new ColorIcon(list.getPanelColor(), icon.getIconWidth(), icon.getIconHeight());
//		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		for(int i = 0; i < layers.size(); i++){
			list.addPanel(makePanel(layers.get(i), nodes.get(i)));
		}
		list.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(!arg0.getValueIsAdjusting()&&list.getSelectedIndex()>=0)
					container.changeLayer(list.getSelectedIndex());
			}
		});
		scrollPane.setViewportView(list);
		list.setSelection(layers.size()-1);
		add(list);
	}
	
	public void setToLevelPanel(LevelPanel panel){
		list.removeAll();
//		for(int i = 0; i < layers.size(); i++){
//			list.addPanel(makePanel(layers.get(i), nodes.get(i)));
//		}
	}
	
	@SuppressWarnings("rawtypes")
	private JPanel makePanel(final Layer layer, final PNode node){
		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(5, 0));
		final JToggleButton button = new PushButton(colorIcon, icon);
		button.setPreferredSize(new Dimension(32, 32));
		panel.add(button, BorderLayout.WEST);
		button.setSelected(node.getVisible());
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				node.setVisible(button.isSelected());
			}
			
		});
		JLabel label = new JLabel(layer.getName());
		label.setFont(font);
		label.addMouseListener(list.getMouseListener(panel));
		label.setBackground(new Color(0, 0, 0, 0));
		panel.add(label, BorderLayout.CENTER);
		return panel;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
