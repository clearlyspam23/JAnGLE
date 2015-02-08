package com.clearlyspam23.GLE.GUI;

public abstract class PLangSubPanel<T> extends SubPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract void setToData(T data);
	
	public abstract T getData();

}
