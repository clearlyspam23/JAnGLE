package com.clearlyspam23.GLE.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListRenderTest extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListRenderTest frame = new ListRenderTest();
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
	public ListRenderTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Icon", "Label"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(32);
		table.getColumnModel().getColumn(0).setMinWidth(32);
		table.getColumnModel().getColumn(0).setMaxWidth(32);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		contentPane.add(table, BorderLayout.CENTER);
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addElement("hello");
		model.addElement("world");
		try{
			Image img = ImageIO.read(new File("images/TilesetIcon.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static class ButtonListCellRenderer extends DefaultListCellRenderer {

	    private static final long serialVersionUID = -7799441088157759804L;
	    private static final Color textSelectionColor = Color.BLACK;
	    private static final Color backgroundSelectionColor = Color.CYAN;
	    private static final Color textNonSelectionColor = Color.BLACK;
	    private static final Color backgroundNonSelectionColor = Color.WHITE;
	    
	    
	    private JPanel panel;
	    private JLabel label;
	    private Icon icon;
	    private JButton button;

	    public ButtonListCellRenderer(Image img) {
	    	
	    	icon = new ImageIcon(img);
	        panel = new JPanel();
	        panel.setLayout(new FlowLayout());
	        button = new JButton();
	        button.setText("V");
	        button.setPreferredSize(new Dimension(64, 64));
	        button.setContentAreaFilled(false);
	        button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					button.setSelected(!button.isSelected());
				}
	        	
	        });
	        button.setSelected(false);
	        panel.add(button);
	        label = new JLabel();
	        panel.add(label);
	    }

	    @Override
	    public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean expanded) {
	    	
	    	//panel.setSize(list.getPreferredSize());
	        label.setText(value.toString());

	        if (selected) {
	            panel.setBackground(backgroundSelectionColor);
	            panel.setForeground(textSelectionColor);
	        } else {
	            panel.setBackground(backgroundNonSelectionColor);
	            panel.setForeground(textNonSelectionColor);
	        }
	        if(button.isSelected()){
	        	button.setIcon(icon);
	        }

	        return panel;
	    }
	}

}
