package com.clearlyspam23.GLE.GUI.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class ConfirmationFileChooser extends JFileChooser {

	public ConfirmationFileChooser() {
		super();
	}

	public ConfirmationFileChooser(File currentDirectory, FileSystemView fsv) {
		super(currentDirectory, fsv);
	}

	public ConfirmationFileChooser(File currentDirectory) {
		super(currentDirectory);
	}

	public ConfirmationFileChooser(FileSystemView fsv) {
		super(fsv);
	}

	public ConfirmationFileChooser(String currentDirectoryPath,
			FileSystemView fsv) {
		super(currentDirectoryPath, fsv);
	}

	public ConfirmationFileChooser(String currentDirectoryPath) {
		super(currentDirectoryPath);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
    public void approveSelection(){
        File f = getSelectedFile();
        if(f.exists() && getDialogType() == SAVE_DIALOG){
            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
            switch(result){
                case JOptionPane.YES_OPTION:
                    super.approveSelection();
                    return;
                case JOptionPane.NO_OPTION:
                    return;
                case JOptionPane.CLOSED_OPTION:
                    return;
                case JOptionPane.CANCEL_OPTION:
                    cancelSelection();
                    return;
            }
        }
        super.approveSelection();
    }        

}
