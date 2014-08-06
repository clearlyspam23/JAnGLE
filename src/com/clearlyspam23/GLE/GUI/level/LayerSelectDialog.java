package com.clearlyspam23.GLE.GUI.level;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.GUI.LayerContainer;

public class LayerSelectDialog extends JDialog {
	private final JList<String> list;
	private DefaultListModel<String> listModel;
	private LayerContainer container;
	public LayerSelectDialog() {
		setTitle("Layers");
		setAlwaysOnTop(true);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		list = new JList<String>();
		listModel = new DefaultListModel<String>();
		list.setModel(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				int index = lsm.getMinSelectionIndex();
				if(index>=0)
					container.changeLayer(index);
			}
		});
		scrollPane.setViewportView(list);
	}
	
	public void setToLayerContainer(LayerContainer cont){
		list.removeAll();
		for(Layer l : cont.getLayers()){
			listModel.addElement(l.getName());
		}
		container = cont;
	}

}
