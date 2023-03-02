package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.structure.Connection;
import telran.structure.ConnectionsPoolManager;
import telran.structure.ConnectionsPool;

class ConnectionPoolTest {

	ConnectionsPool pool;
	@BeforeEach
	void setUp() throws Exception {
		pool = new ConnectionsPoolManager();
		pool.addConnection(new Connection(1, "1.1.1.1", 111111));
		pool.addConnection(new Connection(2, "2.2.2.2", 222222));
		pool.addConnection(new Connection(3, "3.3.3.3", 333333));
	}
	
	@Test
	void addConnectionTest() {
		display();
		assertFalse(pool.addConnection(new Connection(1, "1.1.1.1", 111111)));
		assertFalse(pool.addConnection(new Connection(3, "3.3.3.3", 333333)));
		assertTrue(pool.addConnection(new Connection(4, "4.4.4.4", 444444)));
		display();
	}
	
	@Test
	void getConnectionTest() {
		assertEquals("1.1.1.1", pool.getConnection(1).getIpAddress());
		display();
	}

	private void display() {
		System.out.println(pool.toString());
	}


}
