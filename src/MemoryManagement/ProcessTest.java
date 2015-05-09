package MemoryManagement;

import static org.junit.Assert.*;
import org.junit.*;


/**
 * The test class ProcessTest.
 *
 * @author  David Ferrara
 * @version 3.31.2015
 */
public class ProcessTest
{
	private Process p1;

	/**
	 * Default constructor for test class ProcessTest
	 */
	public ProcessTest()
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
		p1 = new Process(16, "Process A");
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
	public void testGetSize()
	{
		assertEquals(16, p1.getSize());
	}

	@Test
	public void testSetSize()
	{
		assertEquals(16, p1.getSize());
		p1.setSize(32);
		assertEquals(32, p1.getSize());
	}

	@Test
	public void testGetName()
	{
		assertEquals("Process A", p1.getName());
	}

	@Test
	public void testSetName()
	{
		assertEquals("Process A", p1.getName());
		p1.setName("Process B");
		assertEquals("Process B", p1.getName());
	}
}


