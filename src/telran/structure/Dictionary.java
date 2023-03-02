package telran.structure;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class Dictionary implements Word {
	private HashMap<String, String> mapFull = new HashMap<>();
	private TreeSet<String> tree = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	@Override
	public boolean addWord(String word) {
		boolean res = false;
		if (!(res = mapFull.containsKey(word.toLowerCase()))) {
			tree.add(word);
		}
		return res;
	}


	@Override
	public List<String> getWordsByPrefix(String prefix) {
		List<String> res = new ArrayList<String>();
		prefix = prefix.toLowerCase();
		tree.subSet(prefix, prefix + Character.MAX_VALUE).stream().forEach(el -> res.add(el));
				
		return res.size() == 0 ? null : res;
	}
	
	public int size() {
		return tree.size();
	}
}
