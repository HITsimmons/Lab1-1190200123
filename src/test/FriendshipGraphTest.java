package socialnetwork;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FriendshipGraphTest {

	FriendshipGraph graph=new FriendshipGraph();
	
	@Test
	/**
	 * create three Persons
	 */
	public void addVertextest() {
		Person a = new Person("a");
		Person b = new Person("b");
		Person c = new Person("c");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		assertTrue(graph.Men.contains(a));
		assertTrue(graph.Men.contains(b));
		assertTrue(graph.Men.contains(c));
	}
	
	@Test
	/**
	 * test no direction graph
	 */
	public void addEdgetest() 
	{
		Person a = new Person("a");
		Person b = new Person("b");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addEdge(a, b);
		graph.addEdge(b, a);
		assertTrue(a.getfriend().contains(b));
		assertTrue(b.getfriend().contains(a));
	}
	@Test
	/**
	 * assume a graph abc两两相连，d的度为0
	 */
	public void getDistancetest() {
		Person a = new Person("a");
		Person b = new Person("b");
		Person c = new Person("c");
		Person d = new Person("d");
		Person e = new Person("e");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addEdge(a, b);
		graph.addEdge(b, c);
		graph.addEdge(d, e);
		assertEquals(0, graph.getDistance(a, a));
		assertEquals(1, graph.getDistance(a, b));
		assertEquals(2, graph.getDistance(a, c));
		assertEquals(-1, graph.getDistance(a, d));
		assertEquals(-1, graph.getDistance(a, e));
		assertEquals(1, graph.getDistance(d, e));
	}

}
