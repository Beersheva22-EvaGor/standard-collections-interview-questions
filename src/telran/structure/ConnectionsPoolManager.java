package telran.structure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/*
 * NB. Order is reversed
 */
public class ConnectionsPoolManager implements ConnectionsPool {

	private int maxConnections;
	LinkedHashMap<Integer, Connection> map;
	
	
	public ConnectionsPoolManager(int... maxConnections) {
		this.maxConnections = maxConnections.length==0? 3: maxConnections[0];
		map = new LinkedHashMap<>();
	}
	
	public boolean addConnection(Connection connection) {
		Integer id = connection.id;
		boolean res =  !map.containsKey(id);
		if (res) {
			if (map.size() == maxConnections) {			
				map.remove(map.keySet().iterator().next());
			} 			
			map.put(id, connection);
		}
		return res;
	}
	

	@Override
	public Connection getConnection(int id) {
		boolean contains =  map.containsKey(id);
		Connection connectionTemp = null;
		if (contains) {
			connectionTemp = map.get(id);
			map.remove(id);
			map.put(id, connectionTemp);
		}
		return connectionTemp;
	}
	
	
	public Connection getLastConnection() {
		return map.get(map.keySet().iterator().next());
	}

	
	@Override
    public String toString() {		
        Iterator<Entry<Integer, Connection>> i = map.entrySet().iterator();
        String[] ar = new String[map.size()];
        int j = ar.length-1;
        while (i.hasNext()) {
        	Entry<Integer, Connection> e = i.next();
        	Connection eValue = e.getValue();
        	ar[j--] = "id = "+ eValue.getId() + ", ip = " + eValue.getIpAddress() + ", port = " + eValue.getPort() + "\n";
        }
        StringBuilder res = new StringBuilder();
        Arrays.stream(ar).forEach(a -> res.append(a));
         return res.toString();
    }

}
