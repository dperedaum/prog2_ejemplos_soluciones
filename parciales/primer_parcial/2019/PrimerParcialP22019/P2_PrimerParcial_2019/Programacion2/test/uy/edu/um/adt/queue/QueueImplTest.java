package uy.edu.um.adt.queue;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueImplTest {

	@Test
	public void testFlujoNormal() {
		Queue<Integer> queue = new QueueImpl<>();
		
		queue.enqueue(new Integer(21));
		queue.enqueue(new Integer(34));
		queue.enqueue(new Integer(3));
		
		try {
			
			assertEquals(new Integer(21), queue.dequeue());
			
		} catch (EmptyQueue e) {
			
			fail(e.getMessage());
			
		}
		
		assertEquals(2, queue.size());
		
		assertTrue(queue.contains(34));
		
		assertFalse(queue.contains(18));
		
	}

}
