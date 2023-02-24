package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collectors;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.*;

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
	@Disabled
	void displayDigitStatistics() {
		// solution in 1! line
		// generate 10^6 random integers in range [-1, Integer.MAX_VALUE)
		// display digit and frequency of every number in descending order
		// using flatMap 4 getting many from 1
		

		new Random().ints(1_000_000, -1, Integer.MAX_VALUE)
			.flatMap(n -> Integer.toString(n).chars()).boxed().collect(Collectors.groupingBy(d -> d, Collectors.counting()))
			.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
			.forEach(e -> System.out.printf("Digit %c:   %d\n", e.getKey(), e.getValue()));
		
	}

	@Test
	@Disabled
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
	
	@Test
	void myArrayIntTest() {
		MyArrayInt ar = new MyArrayInt(10, 5);
		ar.set(2, 10);
		assertEquals(10, ar.get(2));
		
		ar.setAll(30);
		ar.set(2, 10);
		assertEquals(10, ar.get(2));
		
		ar.setAll(100);
		ar.set(2, 10);
		assertEquals(10, ar.get(2));
		assertEquals(100, ar.get(1));
		
	}
	
	@Test
	void maxNumberWithNegativeImageTest() {
		int ar[] = {10000000, 3, -2, -200, 200, -3,2}; // 200
		int ar1[] = {10000000, -1000000000,3,-4}; //-1
		assertEquals(200, maxNumberWithNegativeImage(ar));
		assertEquals(-1, maxNumberWithNegativeImage(ar1));
	}
	
	int maxNumberWithNegativeImage(int[] ar) {
		HashSet<Integer> set = new HashSet<>();
		Integer[] max = {-1};
		Arrays.stream(ar).filter(el -> {
			if ( set.contains(el*(-1))) {
				if (max[0] < Math.abs(el)) {
					max[0] = Math.abs(el);
				}
				return true;
			} else {
				set.add(el);
				return false;
			}
		}).toArray();

		return max[0];
	}
	
	@Test
	void treeIteratingTest() {
		int array[] = {1, 11, 111, 32, 9, 1234, 99, 992};
		TreeSet<Integer> tree = createAndIterateTreeInOrder(array);
		
		Iterator<Integer> it = tree.iterator();
		int i = 0;
		while (it.hasNext()) {
			assertEquals(array[i++], it.next());
		}
	}
	
	private TreeSet<Integer> createAndIterateTreeInOrder(int[] array) {
		// create tree, add in tree numbers from a given array
		// and iterate in the order of array defined in test
		
		TreeSet<Integer> tree = new TreeSet<>((e1, e2) ->
				getSumDigitsInNumber(e1) - getSumDigitsInNumber(e2)
				);
		Arrays.stream(array).forEach(el -> tree.add(el));
		return tree;		
	}
	
	private int getSumDigitsInNumber(int el) {
		return Integer.toString(el).chars().mapToObj(s->(char)s).mapToInt(s->Integer.parseInt(String.valueOf(s))).sum();
	}

	
	
}
