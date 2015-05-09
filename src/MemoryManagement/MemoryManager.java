package MemoryManagement;

import java.util.ArrayList;

/**
 * This is our highest level class in this project.  It controls 
 * how the memory manager acts.  A treeManager object
 * is created as well as an arrayList of leaves.
 */
public class MemoryManager {
	private TreeManager treeManager;
	private ArrayList<Tree> leaves;

	/**
	 * Constructor
	 */
	public MemoryManager()
	{
		leaves = new ArrayList<Tree>();
		treeManager = new TreeManager(64, leaves);
	}

	/**
	 * Allocates a space in memory for the process p
	 * @param p  A process that will be placed in memory
	 * unless there is not enough room left in memory
	 */
	public void allocate(Process p)
	{
		treeManager.allocate(p);
	}

	/**
	 * Removes the process p from memory.  If the location of the process
	 * has an empty buddy, then the two empty memory spaces are paired up.
	 * 
	 * @param p  The process in memory that is being removed
	 */
	public void deallocate(Process p)
	{
		treeManager.deallocate(p);

	}

	
	/**
	 * @return The leaves of processes
	 */
	public ArrayList<Tree> getProcesses()
	{
		return leaves;
	}

	/**
	 * Returns the information about the leaves
	 */
	public String toString()
	{
		return leaves.toString();
	}
}
