package telran.structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class MultiCountersStructure implements MultiCounters {

	HashMap<Object, Integer> struct;
	TreeMap<Integer, HashSet<Object>> rbtree;

	public MultiCountersStructure() {
		struct = new HashMap<>();
		rbtree = new TreeMap<>();
	}
	

	@Override
	public Integer addItem(Object item) {
		// define frequency of occurrence + 1
		Integer counter = getValue(item);
		if (counter == null) {
			counter = 0;
		}
		struct.put(item, ++counter);

		// remove object from the tree's node with old counter
		removeObjFromTree(item, counter - 1);

		// put with a new counter
		HashSet<Object> set = rbtree.get(counter);
		if (set == null) {
			set = new HashSet<>();
		}
		set.add(item);
		rbtree.put(counter, set);

		return counter;
	}

	private void removeObjFromTree(Object item, Integer counter) {
		if (counter > 0) {
			HashSet<Object> set = rbtree.get(counter);
			set.remove(item);
			if (set.size() == 0) {
				rbtree.remove(counter);
			} else {
				rbtree.put(counter, set);
			}
		}
	}

	@Override
	public Integer getValue(Object item) {
		return struct.get(item);
	}

	@Override
	public boolean remove(Object item) {
		Integer counter = getValue(item);
		struct.remove(item);
		
		if (counter != null) {
			removeObjFromTree(item, counter);
		}
		return counter == null ? false : true;
	}

	@Override
	public Set<Object> getMaxItems() {
		return rbtree.lastEntry().getValue();
	}

}
