package com.clearlyspam23.GLE.basic.layers.tile.gui.tileset;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetGroupNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTileNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode.Type;
import com.clearlyspam23.GLE.basic.layers.tile.resources.BasicTilesetHandle;

public class TilesetLoadPanel extends JPanel implements TilesetViewListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TilesetTreeViewPanel tilesetViewPanel;
	private TilesetEditPanel tilesetEditPanel;
	private TilesetGroupNode root;
	
	private TilesetTileNode currentTilesetNode;
	
	private final JPopupMenu tilePopUp;
	
	private final JPopupMenu groupPopUp;
	
	private static final String newLine = System.getProperty("line.separator");
	
	private int lastId;

	/**
	 * Create the panel.
	 */
	public TilesetLoadPanel() {
		setLayout(new BorderLayout(0, 0));
		tilePopUp = new JPopupMenu();
		JMenuItem button = new JMenuItem("New Tileset");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				BasicTilesetHandle h = new BasicTilesetHandle("New Tileset");
				h.setID(++lastId);
				TilesetTileNode tileNode = new TilesetTileNode(h);
				for(int i = 1; !root.getTilesetsByName(tileNode.getName()).isEmpty(); i++){
					tileNode.setName("New Tileset " + i);
				}
				tilesetViewPanel.insertNode(tileNode, true);
				changeToTileset(tileNode);
			}
			
		});
		tilePopUp.add(button);
		button = new JMenuItem("New Group");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tilesetViewPanel.insertNode(new TilesetGroupNode("New Group"));
			}
			
		});
		tilePopUp.add(button);
		
		tilePopUp.add(button);
		button = new JMenuItem("Remove");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(TilesetLoadPanel.this, "Are you sure you want to Remove this Tileset?", "Confirm Removal", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
					tilesetViewPanel.removeNode();
			}
			
		});
		tilePopUp.add(button);
		
		groupPopUp = new JPopupMenu();
		button = new JMenuItem("New Tileset");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				BasicTilesetHandle h = new BasicTilesetHandle("New Tileset");
				h.setID(++lastId);
				TilesetTileNode tileNode = new TilesetTileNode(h);
				for(int i = 1; !root.getTilesetsByName(tileNode.getName()).isEmpty(); i++){
					tileNode.setName("New Tileset " + i);
				}
				tilesetViewPanel.insertNode(tileNode, true);
				changeToTileset(tileNode);
			}
			
		});
		groupPopUp.add(button);
		button = new JMenuItem("New Group");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tilesetViewPanel.insertNode(new TilesetGroupNode("New Group"));
			}
			
		});
		groupPopUp.add(button);
		
		button = new JMenuItem("Remove");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(TilesetLoadPanel.this, "Are you sure you want to Remove this group? This will also Remove any children of this group", "Confirm Removal", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
					tilesetViewPanel.removeNode();
			}
			
		});
		groupPopUp.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.WEST);
		
		tilesetViewPanel = new TilesetTreeViewPanel();
		tilesetViewPanel.setPreferredSize(new Dimension(160, 0));
		scrollPane.setViewportView(tilesetViewPanel);
		
		tilesetEditPanel = new TilesetEditPanel();
		add(tilesetEditPanel, BorderLayout.CENTER);

	}
	
//	public TilesetHandle generateTilesetHandle(){
//		
//	}
	
	private boolean finalizeCurrentTileset(boolean silent){
		if(currentTilesetNode!=null){
			String errorMsg;
			BasicTilesetHandle tileset = tilesetEditPanel.getTileset();
			if(!silent&&!(errorMsg=isValidTileset(tileset)).isEmpty()){
				String msg = "The Tileset currently being edited is invalid for the following reason(s) : " + newLine + newLine + errorMsg + newLine + newLine
						+ "If you change Tilesets, this Tileset will not be saved and any changes will be lost." + newLine + newLine + "Change Tilesets Anyways?";
				return JOptionPane.showConfirmDialog(this, msg, "Confirm Tileset Change", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION;
			}
			currentTilesetNode.setTileset(tileset);
		}
		return true;
	}
	
	private void changeToTileset(TilesetTileNode handle){
		if(!finalizeCurrentTileset(false)){
			return;
		}
		currentTilesetNode = handle;
		tilesetEditPanel.setToTileset(handle.getTileset());
	}
	
	public void setToTileset(TilesetGroupNode tilesetTree, int startId){
		lastId = startId;
		root = tilesetTree.cloneAsBasic().getAsGroup();
		currentTilesetNode = null;
		tilesetEditPanel.setToTileset(null);
		tilesetViewPanel.setToTilesets(root, this);
	}
	
	private boolean isNameUnique(String name){
		return isNameUnique(name, currentTilesetNode.getTileset());
	}
	
	private boolean isNameUnique(String name, TilesetHandle handle){
		List<TilesetHandle> h = root.getTilesetsByName(name);
		if(h.size()==1){
			return h.get(0)==handle;
		}
		return h.isEmpty();
	}
	
	private String isValidTileset(TilesetHandle current){
		StringBuilder out = new StringBuilder("");
		if(current.getName()==null||"".equals(current.getName()))
			out.append("name cannot be empty").append(newLine);
		else{
			if(!isNameUnique(current.getName()))
				out.append("name is not unique").append(newLine);
		}
        if(!isImageFileValid(current.getFilename()))
        	out.append("the image file is not valid").append(newLine);
        else{
        	
        }
        return out.toString();
	}
	
	private boolean isImageFileValid(String s){
		return new BasicTilesetHandle("", s, Integer.MAX_VALUE, Integer.MAX_VALUE).isValid();
	}
	
	@Override
	public void onTilesetDoubleClick(TilesetTreeViewPanel panel,
			TilesetTileNode tileNode, MouseEvent e) {
		if(tileNode!=currentTilesetNode)
			changeToTileset(tileNode);
	}

	@Override
	public void onGroupDoubleClick(TilesetTreeViewPanel panel,
			TilesetGroupNode tileNode, MouseEvent e) {
		
	}

	@Override
	public void onTilesetRightClick(TilesetTreeViewPanel panel,
			TilesetTileNode tileNode, MouseEvent e) {
		tilePopUp.show(e.getComponent(), e.getX(), e.getY());
	}

	@Override
	public void onGroupRightClick(TilesetTreeViewPanel panel,
			TilesetGroupNode tileNode, MouseEvent e) {
		groupPopUp.show(e.getComponent(), e.getX(), e.getY());
	}

	@Override
	public void onTilesetRenamed(TilesetTreeViewPanel panel, TilesetTileNode node) {
		if(node==currentTilesetNode)
			tilesetEditPanel.updateName(node.getName());
	}

	@Override
	public void onGroupRenamed(TilesetTreeViewPanel panel, TilesetGroupNode node) {
		
	}
	
	public void removeInvalidTilesets(){
		removeInvalidTilesets(root);
	}
	
	private void removeInvalidTilesets(TilesetGroupNode parent){
		for(int i = 0; i < parent.getChildren().size(); i++){
			TilesetTreeNode node = parent.getChildren().get(i);
			if(node.getType()==Type.TILE){
				if(!isValidTileset(node.getAsTiles().getTileset()).isEmpty())
					parent.getChildren().remove(i--);
			}
			else
				removeInvalidTilesets(node.getAsGroup());
		}
	}
	
	private void accumulate(TilesetGroupNode node, StringBuilder err){
		for(TilesetTreeNode n : node.getChildren()){
			if(n.getType()==TilesetTreeNode.Type.TILE){
				currentTilesetNode = n.getAsTiles();
				if(!isValidTileset(currentTilesetNode.getTileset()).isEmpty()){
					err.append(currentTilesetNode.getTileset().getName()).append(newLine);
				}
			}
			else{
				accumulate(n.getAsGroup(), err);
			}
		}
	}
	
	/**
	 * attempts to validate the existing tilesets
	 * @return a string, either the empty string if all tilesets were validated successfully, or a string with the name of every failed string, followed by a new line
	 */
	public String validateTilesets(){
		StringBuilder err = new StringBuilder("");
		accumulate(root, err);
//		for(TilesetHandle h : root.getTilesets()){
//			if(!isValidTileset(h).isEmpty()){
//				System.out.println(isValidTileset(h));
//				err.append(h.getName()).append(newLine);
//			}
//		}
		return err.toString();
	}
	
	public boolean finish(){
		return finish(false);
	}
	
	public boolean finish(boolean silentFinalize){
		if(!finalizeCurrentTileset(silentFinalize))
			return false;
		String err = validateTilesets();
		//tommorow, split this up so itll fit better into more than just a dialog
		if(!err.isEmpty()){
			String msg = "The following Tilesets are invalid:" + newLine + newLine + err + newLine + 
					"If you continue, these tilesets will be removed" + newLine + newLine + 
					" Do you want to Continue?";
			if(JOptionPane.showConfirmDialog(this, msg, "Confirm", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				removeInvalidTilesets(root);
				return true;
			}
			return false;
		}
		return true;
	}
	
	private void replicate(TilesetGroupNode node, TilesetGroupNode replicate){
		for(TilesetTreeNode n : node.getChildren()){
			if(n.getType()==Type.TILE){
				TilesetHandle h = n.getAsTiles().getTileset();
				replicate.addNode(new TilesetTileNode(new BasicTilesetHandle(h.getName(), h.getFilename(), h.getTileWidth(), h.getTileHeight(),
						h.getTileXSpacing(), h.getTileYSpacing(), h.getID())));
			}
			else{
				TilesetGroupNode g = new TilesetGroupNode(n.getName());
				replicate.addNode(g);
				replicate(n.getAsGroup(), g);
			}
		}
	}
	
	public TilesetGroupNode getTilesets(){
		TilesetGroupNode output = new TilesetGroupNode(root.getName());
		replicate(root, output);
		return output;
	}

}
