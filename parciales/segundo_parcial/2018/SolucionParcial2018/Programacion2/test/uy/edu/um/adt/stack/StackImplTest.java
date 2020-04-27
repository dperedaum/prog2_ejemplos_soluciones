package uy.edu.um.adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.Test;

public class StackImplTest {

	@Test
	public void testFlujoNormal() {
		Stack<Integer> colStack = new StackImpl<>();
		
		colStack.push(new Integer(2));
		colStack.push(new Integer(4));
		colStack.push(new Integer(7));

		assertEquals(new Integer(7), colStack.peek());
		
		colStack.pop();
		
		assertEquals(new Integer(4), colStack.peek());

		assertEquals(new Integer(4), colStack.pop());
		
		colStack.pop();
		
		try {
			
			colStack.pop();
			
			fail("El stack deberia estar vacio");

		} catch (EmptyStackException e) {
			
			assertTrue(true);

		}
	}

}
