package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import telran.structure.MultiCountersStructure;

class MultiCountersStructureTest {

	@Test
	void multiCountersTest() {
		MultiCountersStructure struct = new MultiCountersStructure();
		HashSet<Object> set = new HashSet<>();
		set.add("Pardon");
		
		assertEquals(1, struct.addItem("Pardon"));
		assertEquals(1, struct.addItem("Sliha"));
		assertEquals(2, struct.addItem("Pardon"));
		assertEquals(3, struct.addItem("Pardon"));
		assertEquals(1, struct.addItem("sliha"));
		assertEquals(2, struct.addItem("Sliha"));
		assertEquals(null, struct.getValue(1000));
		assertFalse(struct.remove(1000));
		assertEquals(set, struct.getMaxItems());
		assertEquals(3, struct.addItem("Sliha"));
		set.add("Sliha");
		assertEquals(set, struct.getMaxItems());
		assertTrue(struct.remove("Sliha"));
		assertEquals(null, struct.getValue("Sliha"));
		
	}

}
