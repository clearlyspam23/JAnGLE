package com.clearlyspam23.GLE.util;

public class Vector2 {
	
	public double x;
	public double y;
	
	public Vector2(){
		
	}
	
	public Vector2(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2 vec){
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public void set(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Vector2 copy(){
		return new Vector2(x, y);
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Vector2))
			return false;
		return equals((Vector2)o);
	}
	
	public boolean equals(Vector2 other){
		return x==other.x&&y==other.y;
	}

}
