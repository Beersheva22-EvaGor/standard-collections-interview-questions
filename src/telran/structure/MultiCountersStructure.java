package telran.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MultiCountersStructure implements MultiCounters {

	HashMap<Object, Integer> structObjects;
	ArrayList<HashSet<Object>> listFrequency;

	
	public MultiCountersStructure() {
		structObjects = new HashMap<> ();
		listFrequency = new ArrayList<>();
	}
	
	@Override
	public Integer addItem(Object item) {
		// define frequency of occurrence + 1
		Integer counter = getValue(item);
		if (counter == null) {
			counter = 0;
		}
		structObjects.put(item, ++counter);
				
		if (counter > listFrequency.size()) {
			listFrequency.add(new HashSet<>());
		}
		resettleObjectInList(item, counter, counter - 1);
		return counter;
	}

	private void resettleObjectInList(Object item, Integer counterNew, Integer counterOld) {
		HashSet<Object> setNew = listFrequency.get(counterNew - 1); // counter - 1 e.g. indexing starts from 0
		setNew.add(item);
		if (counterOld > 0) {
			HashSet<Object> setOld = listFrequency.get(counterOld - 1);
			setOld.remove(item); 	// remove the object with old frequency
		}
	}
	
	@Override
	public Integer getValue(Object item) {
		return structObjects.get(item);
	}

	@Override
	public boolean remove(Object item) {
		Integer counter = getValue(item);
		if (counter != null) {
			structObjects.remove(item);
			HashSet<Object> set = listFrequency.get(counter - 1);
			set.remove(item);
			if (counter > 0) {
				listFrequency.get(counter - 2).add(item);
			}
			if (counter == listFrequency.size() && set.isEmpty()) {
				listFrequency.remove(counter - 1);
			}
		}
		return counter == null ? false : true;
	}

	@Override
	public Set<Object> getMaxItems() {
		return listFrequency.get(listFrequency.size()-1);
	}

}
