package com.clearlyspam23.GLE.test;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;

import bibliothek.gui.DockStation;
import bibliothek.gui.dock.ScreenDockStation;
import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.behavior.ExternalizingCGridAreaConfiguration;
import bibliothek.gui.dock.title.NullTitleFactory;
import bibliothek.gui.dock.util.DirectWindowProvider;

public class DockingFramesTest {
    public static void main( String[] args ){
        /* In this example we will replace the default behavior of externalized CDockables: instead of being stacked,
         * we will allow them to be split (as if they would be children of a CGridArea or CWorkingArea). */
        
        /* Like in every example, we need a JFrame... */
    	JFrame actualFrame = new JFrame();
    	actualFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JDialog frame = new JDialog( actualFrame );
        
        /* ... and a controller */
        CControl control = new CControl( new  DirectWindowProvider(frame));
        
        /* Setting up the new behavior is quite simple, this piece of code will do it for us. 
         * 
         * The configuration will add a listener to the ScreenDockStation, and every time a Dockable is added to
         * it, the listener will insert a new ExternalizingCGridArea. */
        ExternalizingCGridAreaConfiguration config = ExternalizingCGridAreaConfiguration.installOn( control );
        DockStation station = control.getStation( CControl.EXTERNALIZED_STATION_ID ).getStation();
        
        /* We do not want the floating SplitDockStations to have a title. Since a ScreenDockStation does only
         * have floating SplitDockStations as children, we can safely disable all titles. */
        control.getController().getDockTitleManager().registerClient( ScreenDockStation.TITLE_ID, new NullTitleFactory() );
        
        /* And the remaining part of the initialization is like in most of the other examples */
        frame.add( control.getContentArea(), BorderLayout.CENTER );
        
        CGrid grid = new CGrid( control );
        grid.add( 0, 0, 1, 1, new ColorSingleCDockable( "red", Color.RED ) );
//        grid.add( 1, 0, 1, 1, new ColorSingleCDockable( "green", Color.GREEN ) );
        grid.add( 0, 1, 1, 1, new ColorSingleCDockable( "blue", Color.BLUE ) );
        grid.add( 0, 1, 1, 1, new ColorSingleCDockable( "yellow", Color.YELLOW ) );
        control.getContentArea().deploy( grid );
        
        actualFrame.setVisible(true);
        frame.setVisible( true );
    }    
}