package MemoryManagement;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * The test class TreeManagerTest.
 */
public class TreeManagerTest
{
	/**
	 * Default constructor for test class TreeManagerTest
	 */
	public TreeManagerTest()
	{
	}

	/**
	 * Sets up the test fixture.
	 *
	 * Called before every test case method.
	 */
	@Before
	public void setUp()
	{
	}

	/**
	 * Tears down the test fixture.
	 *
	 * Called after every test case method.
	 */
	@After
	public void tearDown()
	{
	}

	@Test
	public void testDeallocate()
	{
		ArrayList<Tree> leaves = new ArrayList<Tree>();
		TreeManager t1 = new TreeManager(64, leaves);
		Process p1 = new Process(16, "Process A");
		t1.allocate(p1);
		assertNotNull(leaves.get(0));
		t1.deallocate(p1);
		assertEquals(1, leaves.size());
	}
}