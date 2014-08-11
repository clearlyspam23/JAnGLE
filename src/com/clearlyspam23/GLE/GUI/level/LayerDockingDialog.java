package com.clearlyspam23.GLE.GUI.level;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.intern.DefaultCDockable.Permissions;
import bibliothek.gui.dock.util.DirectWindowProvider;

import com.clearlyspam23.GLE.GUI.LayerEditManager;

public class LayerDockingDialog extends JDialog{
	
	public LayerDockingDialog(Frame frame, String layerName, LayerEditManager manager){
		super(frame, layerName);
		control = new CControl(new DirectWindowProvider(this));
		
//		addWindowListener( new WindowAdapter(){
//			public void windowClosing( WindowEvent e ){
//				dispose();
//			}
//			
//			public void windowClosed( WindowEvent e ){
//				control.destroy();
//			}
//		});
		add( control.getContentArea(), BorderLayout.CENTER );
        
        CGrid grid = new CGrid( control );
        int i = 0;
        LayerEditManager.ComponentData main = manager.getMainComponent();
        DefaultSingleCDockable dockable = new DefaultSingleCDockable(Integer.toString(i++), main.icon, main.name, null, Permissions.MAX);
        dockable.add(main.component);
        grid.add(0, 0, 1, 1, dockable);
        for(LayerEditManager.ComponentData c : manager.getSubComponents()){
        	dockable = new DefaultSingleCDockable(Integer.toString(i++), main.icon, main.name, null, Permissions.MAX_EXT_STACK);
            dockable.add(c.component);
            grid.add(0, 1, 1, 1, dockable);
        }
        control.getContentArea().deploy( grid );
        setSize(400, 800);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CControl control;
	
	public CControl getControl() {
		return control;
	}
	public void setControl(CControl control) {
		this.control = control;
	}

}
