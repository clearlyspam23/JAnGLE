package com.clearlyspam23.GLE.GUI;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LayerSelectDialog extends JDialog {
	public LayerSelectDialog(final LayerContainer container) {
		setTitle("Layers");
		setAlwaysOnTop(true);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				int index = lsm.getMinSelectionIndex();
				if(index>=0)
					container.changeToLayer(index);
			}
		});
		scrollPane.setViewportView(list);
	}

}
