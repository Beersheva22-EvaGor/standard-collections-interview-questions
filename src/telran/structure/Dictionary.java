package telran.structure;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class Dictionary implements Word {
	private HashMap<String, String> mapFull = new HashMap<>();
	private HashMap<Character,  TreeSet<String>> map = new HashMap<>();
	@Override
	public boolean addWord(String word) {
		String wordLower = word.toLowerCase();
		boolean res = mapFull.containsKey(wordLower);
		if (!res) {
			mapFull.put(wordLower, word);
			Character firstLetter = word.charAt(0);

			TreeSet<String> tempSet = map.getOrDefault(firstLetter, new TreeSet<>());
			tempSet.add(wordLower);
			map.put(firstLetter, tempSet);			
		}
		return res;
	}


	@Override
	public List<String> getWordsByPrefix(String prefix) {
		List<String> res = new ArrayList<String>();
		prefix = prefix.toLowerCase();
		Character firstLetter = prefix.charAt(0);
		TreeSet<String> tempSet = map.get(firstLetter);
		
		if (tempSet != null) {
			tempSet.subSet(prefix, prefix + Character.MAX_VALUE).stream().forEach(el -> res.add(el));
		}
		
		return res.size() == 0 ? null : res;
	};
	
	public int size() {
		return map.size();
	}
}
