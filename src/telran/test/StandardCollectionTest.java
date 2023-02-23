package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.StackInt;

class StandardCollectionTest {
	@BeforeEach
	void setUp() throws Exception{
		
	}
	
	
	@Test
	@Disabled
	void subListTest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,70,-20));	
		list.add(5);
		List<Integer> listSub = list.subList(6, 9);

		System.out.println(listSub);
		listSub.add(1, -2);
		listSub.sort(Integer::compare);
		listSub.clear();
		System.out.println(list);		
		
	}
	
	@Test
	@Disabled
	void displayOccurencesCount(){
		String[] strings = {"lmn", "abc", "abc", "lmn", "a", "lmn"};
		Arrays.stream(strings)
			.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
			.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
			.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));

	}
	
	@Test
	void displayDigitStatistics() {
		// solution in 1! line
		// generate 10^6 random integers in range [-1, Integer.MAX_VALUE)
		// display digit and frequency of every number in descending order
		// using flatMap 4 getting many from 1
		
		
		new Random().ints(-1, Integer.MAX_VALUE)
		.boxed()
		.limit(1_000_000)
		.flatMap(n -> 
					Arrays.stream(String.valueOf(n).split("\\s*"))
					.map(d-> Integer.parseInt(d))
				)
		.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
		.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
		.forEach(e -> System.out.printf("Digit %s:   %d\n", e.getKey(), e.getValue()));
		
	}

	@Test
	void stackIntTest() {
		StackInt stack = new StackInt();
		assertTrue(stack.isEmpty());
		assertThrows(NoSuchElementException.class, stack::pop);
		stack.push(111);
		assertEquals(111, stack.getMax());
		stack.push(222);
		assertEquals(222, stack.getMax());
		stack.push(312);
		assertEquals(312, stack.getMax());
		assertFalse(stack.isEmpty());
		assertEquals(312, stack.pop());
		assertEquals(222, stack.getMax());
		stack.push(0);
		assertEquals(222, stack.getMax());		
		stack.pop();
		stack.pop();
		stack.pop();
		assertTrue(stack.isEmpty());
		assertThrows(NoSuchElementException.class, stack::pop);
	}
}
