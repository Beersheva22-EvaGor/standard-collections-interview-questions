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
		dict.addWord("ABcdef");
		dict.addWord("lmn");		
	}

	@Test
	void addWordTest() {
		dict.addWord("abcdef");
		dict.addWord("AbcdeF");
		
		dict.addWord("LMN");
		assertEquals(2, ((Dictionary)dict).size());
	}
	
	@Test 
	void getWordsByPrefixTest() {
		ArrayList<String> expected = new ArrayList<>();
		expected.add("ABcdef");
		assertEquals(expected, dict.getWordsByPrefix("abc"));
	}
}
