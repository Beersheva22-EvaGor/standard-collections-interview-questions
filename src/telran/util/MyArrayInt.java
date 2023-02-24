package telran.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//all methods of the class MyArrayInt should have complexity o[1]
public class MyArrayInt {
	HashMap<Integer, Integer> map;
	int value;
	int size;
	
	public MyArrayInt(int size, int value) {
		this.value = value;
		this.size = size;
	}
	
	public void set(int index, int value){
		checkIndex(index);
		if (map == null) {
			map = new HashMap<>();
		}
		map.put(index, value);
	}
	
	public int get(int index) {
		checkIndex(index);
		int res = value;
		if (map != null) {
			res = map.getOrDefault(index, value);
		}
		return res;
	}
	
	public void setAll(int value) {
		//sets a given value for all array's elements
		this.value = value;
		map = null;
	}
	
	private void checkIndex(int index) {
		if (index<0 || index> size-1) {
			throw new IndexOutOfBoundsException();
		}
	}
}
