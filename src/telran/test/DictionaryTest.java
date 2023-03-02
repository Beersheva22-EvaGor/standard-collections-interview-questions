package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.structure.Dictionary;
import telran.structure.Word;

class DictionaryTest {
	Word dict;
	@BeforeEach
	void setUp() throws Exception {
		dict = new Dictionary();
		dict.addWord("abcdef");
		dict.addWord("abcdef");
		dict.addWord("lmn");
		dict.addWord("lmn");
		
	}

	@Test
	void addWordTest() {
		assertEquals(2, ((Dictionary)dict).size());
	}
	
	@Test 
	void getWordsByPrefixTest() {
		ArrayList<String> expected = new ArrayList<>();
		expected.add("abcdef");
		assertEquals(expected, dict.getWordsByPrefix("abc"));
	}
}
