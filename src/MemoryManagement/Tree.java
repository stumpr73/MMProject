package MemoryManagement;

import java.util.ArrayList;

/**
 * This class does the majority of the work in our project.
 * The grunt work of allocating a space in memory for a new process
 * is in this class.  Efficient ways to traverse through this tree
 * are used.
 */
public class Tree{

	private Tree left;
	private Tree right;
	private int size;
	private int sizeLeft; //the available space underneath this node
	private Process lp;
	private Tree parent;
	private int lowEnd;
	private ArrayList<Tree> leaves;

	/**
	 * Constructor
	 * 
	 * @param s Size of the node
	 * @param p  Parent of the node
	 * @param lowEnd The number in memory where this node will begin
	 * @param leaves  The leaves of processes
	 */
	public Tree(int s, Tree p, int lowEnd, ArrayList<Tree> leaves){
		size = s;
		left = null;
		right = null;
		lp = null;
		this.lowEnd=lowEnd;
		sizeLeft = size;
		parent = p;
		this.leaves = leaves;
		leaves.add(this);
	}

	/**
	 * This method allocates a place in memory for the process P.
	 * If the size of the process is larger than the amount of
	 * memory left, an error message will be displayed.
	 * 
	 * @param p The process being placed in memory
	 */
	protected void allocate(Process p){
		Tree c = null;
		if(p.getSize()<= size/2){
			if(left==null && right == null){
				generateChildren();
			}
			c = findSuitableChild(p);
			if(c != null)
				c.allocate(p);
			else
				System.out.println("There is no more room in the tree");
		}
		else if(lp==null)
			assignProcess(p);
		else
			System.out.println("Cannot fit");
	}


	/**
	 * Generates the children of this node
	 */
	protected void generateChildren(){
		leaves.remove(this);
		left = new Tree(size/2,this, lowEnd, leaves);
		right = new Tree(size/2,this, (lowEnd +(size/2)), leaves);
	}

	/**
	 * Finds a suitable node that a process can be placed
	 * @param p The process trying to be placed in memory
	 * @return  Either the left child or right child
	 */
	protected Tree findSuitableChild(Process p){
		if(left.getSizeLeft() >= p.getSize() && left.getLp() == null)
			return left;
		else if(right.getSizeLeft() >= p.getSize() && right.getLp() ==null)
			return right;
		else
		{
			System.out.println("There is no more room in the tree");
			return null;
		}
	}

	/**
	 * Assigns the process to this node
	 * @param p
	 */
	protected void assignProcess(Process p){
		int adjustedSize = (int)Math.pow(2,Math.ceil(Math.log(p.getSize())/Math.log(2)));
		lp = p;
		updateParentSize(adjustedSize);
	}

	/**
	 * When a process is either added or removed in memory
	 * The size left underneath this parent needs to be updated
	 * @param s The size of the process that was either removed
	 * or added to memory
	 */
	protected void updateParentSize(int s)
	{
		if(parent != null)
		{
			sizeLeft = sizeLeft - s;
			parent.updateParentSize(s);
		}
		else
		{
			sizeLeft = sizeLeft - s;
		}
	}

	/**
	 * @return The sibling of the node
	 */
	protected Tree getSibling()
	{
		if(parent.getLeft().equals(this))
			return parent.getRight();
		else if(parent.getRight().equals(this))
			return parent.getLeft();
		else
			return null;

	}

	/**
	 * Sets the size left under the node
	 * @param size The new size left under the node
	 */
	protected int getSizeLeft(){
		return sizeLeft;
	}

	/**
	 * @return Where this node begins in memory
	 */
	protected int getLowEnd()
	{
		return lowEnd;
	}

	/**
	 * Resets the children of this node
	 */
	protected void resetChildren()
	{
		left = null;
		right = null;
	}

	/**
	 * 
	 * @return  If this node has a parent
	 */
	protected boolean hasParent()
	{
		if(parent == null)
			return false;
		return true;
	}

	/**
	 * @return The parent of the node
	 */
	protected Tree getParent()
	{
		return parent;
	}

	/**
	 * @return If there are no children
	 */
	protected boolean areNoChildren()
	{
		return left == null;
	}

	/**
	 * @return The left child
	 */
	protected Tree getLeft() {
		return left;
	}

	/**
	 * Sets the left child
	 * @param left
	 */
	protected void setLeft(Tree left) {
		this.left = left;
	}

	/**
	 * @return The right child
	 */
	protected Tree getRight() {
		return right;
	}

	/**
	 * Sets the right child to the parameter right
	 * @param right
	 */
	protected void setRight(Tree right) {
		this.right = right;
	}

	/**
	 * @return The size of this node
	 */
	protected int getSize() {
		return size;
	}

	/**
	 * Sets the size of this node
	 * @param size
	 */
	protected void setSize(int size) {
		this.size = size;
	}

	/**
	 * Sets the size left under the node
	 * @param size The new size left under the node
	 */
	protected void setSizeLeft(int size){
		sizeLeft = size;
	}

	/**
	 * Sets where the node begins in memory
	 * @param n	Where the node will begin in memory
	 */
	protected void setLowEnd(int n){
		lowEnd = n;
	}

	/**
	 * Returns the process occupying this node
	 * @return
	 */
	protected Process getLp() {
		return lp;
	}

	/**
	 * Sets the process occupying this node
	 * @param lp
	 */
	protected void setLp(Process lp) {
		this.lp = lp;
	}

	/**
	 * Sets the parent occupying this node
	 * @param parent
	 */
	protected void setParent(Tree parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tree other = (Tree) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} 
		if (lp == null) {
			if (other.lp != null)
				return false;
		} else if (!lp.equals(other.lp))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (size != other.size)
			return false;
		if (sizeLeft != other.sizeLeft)
			return false;
		return true;
	}

	/**
	 * Returns the information about the node
	 */
	public String toString()
	{
		if(lp != null)
			return lp.getName() + " of size " + size + " Range: " + lowEnd + " to "+ (lowEnd + size -1) + "\n";
		return "Size: " + size + " Range: " + lowEnd + " to "+ (lowEnd + size -1) + "\n";
	}
}