package MemoryManagement;

import java.util.ArrayList;

/**
 *	This class was created to perform the deallocation of a process. 
 *	When a process is deallocated, buddy empty spaces of memory
 *	are checked
 */
public class TreeManager {
	private Tree tree;
	private ArrayList<Tree> leaves;

	/**
	 * Constructor
	 * @param maxSize Maximum size of the memory
	 * @param leaves  The leaves of processes
	 */
	public TreeManager(int maxSize, ArrayList<Tree> leaves)
	{

		this.leaves = leaves;
		tree = new Tree(maxSize, null, 0, leaves);
	}

	/**
	 * Allocates space in memory for process p
	 * @param p The process trying to be put into memory
	 */
	public void allocate(Process p)
	{
		tree.allocate(p);
	}

	/**
	 * Removes the process p from memory.
	 * @param p  The process being removed
	 */
	public void deallocate(Process p)
	{
		Tree t = null;
		for(int i = 0; i < leaves.size(); i++)
		{
			if(leaves.get(i).getLp() == null)
				;
			else if(leaves.get(i).getLp().getName().equals(p.getName()))
				t = leaves.get(i);
		}

		if(t == null)
		{
			return;
		}

		maintainTree(t);

		System.out.println("done");

	}

	/**
	 * Checks for empty memory space buddies that can be combined
	 * @param t  The node that was removed
	 */
	protected void maintainTree(Tree t)
	{
		if(t.getParent() == null)
		{
			t.setLp(null);
			return;
		}
		//at this point, 
		//t is the tree node we need to remove the process from

		if(t.getSibling() == null)
		{
			System.out.println("Error. Look in the deallocate method in Memory Manager");
		}
		else if(t.getSibling().getLp() == null && t.getSibling().getLeft() == null)
		{

			leaves.add(t.getParent());
			leaves.remove(t.getSibling());
			leaves.remove(t);
			t.getParent().resetChildren();
			if(t.getParent() != null){
				t.updateParentSize(-1*t.getSize());
				maintainTree(t.getParent());
			}
		}
		else
		{
			t.setLp(null);
			t.updateParentSize(-1*t.getSize());

		}
	}

	/**
	 * @return The leaves of processes
	 */
	protected ArrayList<Tree> getLeaves()
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
