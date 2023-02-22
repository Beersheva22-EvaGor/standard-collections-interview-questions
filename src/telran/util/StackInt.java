package telran.util;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class StackInt {
	private ArrayList<Integer> stack;
	private Integer max;
	private int size;
	
	public StackInt() {
		stack = new ArrayList<>();
		size = 0;
	}
	
	//Write the following methods with complexity O(1)
	public void push(int num) {
		// adds number into top of stack
		stack.add(num);
		size++;
	}
	
	public int pop() {
		// returns a number from top of stack or throws NoSuchElementException if stack is empty
		checkIfEmpty();
		int lastIndex = stack.size()-1;
		Integer res = stack.get(lastIndex);
		stack.remove(lastIndex);
		size--;
		return res;
	}
	
	public boolean isEmpty() {
		return size == 0;		
	}
	
	public int getMax() {
		// returns max value of the stack or throws NoSuchElementException if stack is empty
		checkIfEmpty();
		return max;
	}

	private void checkIfEmpty() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
	}
}
