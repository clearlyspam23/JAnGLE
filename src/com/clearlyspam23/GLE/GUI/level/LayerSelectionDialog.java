package com.clearlyspam23.GLE.GUI.level;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.level.Layer;

public class LayerSelectionDialog extends JDialog{
	
	private JList<String> list;
	
	public LayerSelectionDialog(Frame frame, @SuppressWarnings("rawtypes") List<Layer> layers, List<PNode> nodes, final LayerContainer container) {
		super(frame, "Layers");
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		list = new JList<String>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(Layer<?> l : layers){
			listModel.addElement(l.getName());
		}
		list.setModel(listModel);
		list.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				container.changeLayer(list.getSelectedIndex());
			}
			
		});
		scrollPane.setViewportView(list);
		list.setSelectedIndex(listModel.size()-1);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
