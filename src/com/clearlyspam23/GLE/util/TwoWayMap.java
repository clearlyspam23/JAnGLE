package com.clearlyspam23.GLE.util;

import java.util.HashMap;
import java.util.Map;

public class TwoWayMap<T, E> {
	
	private Map<T, E> normMap;
	private Map<E, T> reverseMap;
	
	public TwoWayMap(){
		normMap = new HashMap<T, E>();
		reverseMap = new HashMap<E, T>();
	}
	
	public void put(T t, E e){
		normMap.put(t, e);
		reverseMap.put(e, t);
	}
	
	public E getNormal(T t){
		return normMap.get(t);
	}
	
	public T getReverse(E e){
		return reverseMap.get(e);
	}

}
