package com.clearlyspam23.GLE.debug;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class TestInternal extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestInternal frame = new TestInternal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestInternal() {
		setBounds(100, 100, 450, 300);

	}

}
