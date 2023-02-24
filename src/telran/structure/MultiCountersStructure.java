package telran.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MultiCountersStructure implements MultiCounters {
/*
 * structObjects - HashMap that stores objects as a key and frequency of their occurrence as a value
 * structFrequency - HashMap that stores occurrence as a key and objects as a value
 * listFrequency - stores ordered sequence of frequencies that exists in HashMaps
 */
	HashMap<Object, Integer> structObjects;
	HashMap<Integer, HashSet<Object>> structFrequency;
	ArrayList<Integer> listFrequency;

	
	public MultiCountersStructure() {
		structObjects = new HashMap<> ();
		structFrequency = new HashMap<>();
		listFrequency = new ArrayList<>();
	}
	
	@Override
	public Integer addItem(Object item) {
		// define frequency of occurrence + 1
		Integer counter = getValue(item);
		if (counter == null) {
			counter = 0;
		}
		structObjects.remove(item);
		counter++;
		structObjects.put(item, counter);
				
		// add, if not exists, frequency to the listFrequency
		int insertionPoint = getInsertionPoint(counter);

		// looking for objects with the same frequency and delete previous frequency
		addWithNewFrequency(item, counter);
		deleteFromFrequencies(item, counter - 1, insertionPoint);		
		
		return counter;
	}

	private int getInsertionPoint(Integer counter) {
		int insertionPoint = Collections.binarySearch(listFrequency, counter);
		if (insertionPoint < 0) {
			insertionPoint = -1-insertionPoint;
			listFrequency.add(insertionPoint, counter);
		}
		return insertionPoint;
	}

	private void deleteFromFrequencies(Object item, Integer counter, int insertionPoint) {
		HashSet<Object> set = structFrequency.get(counter);
		if (set != null) {
			set.remove(item);
			if (set.size() == 0) {
				structFrequency.remove(counter);
				listFrequency.remove(insertionPoint-1);
			}
		}
		
	}

	private void addWithNewFrequency(Object item, Integer counter) {
		HashSet<Object> set = structFrequency.get(counter);
		if (set == null) {
			HashSet<Object> newSet = new HashSet<Object>();
			newSet.add(item);
			structFrequency.put(counter, newSet);
		} else {
			set.add(item);
		}
	}
	
	@Override
	public Integer getValue(Object item) {
		return structObjects.get(item);
	}

	@Override
	public boolean remove(Object item) {
		Integer counter = getValue(item);
		structObjects.remove(item);
		if (counter != null) {
			Integer position = getInsertionPoint(counter);
			deleteFromFrequencies(item, counter, position);			
		}
		return counter == null ? false : true;
	}

	@Override
	public Set<Object> getMaxItems() {
		return structFrequency.get(listFrequency.get(listFrequency.size()-1));
	}

}
