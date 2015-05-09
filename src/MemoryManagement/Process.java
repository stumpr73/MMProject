package MemoryManagement;

public class Process {
	int size;
	String name;

	/**
	 * Constructor
	 * @param size Size of the process
	 * @param name Name of the process
	 */
	public Process(int size, String name) {
		super();
		this.size = size;
		this.name = name;
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
		Process other = (Process) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	/**
	 * @return Size of the process
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size of the process
	 * @param size Size of the process
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return The name of the process
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the process
	 * @param name The name of the process
	 */
	public void setName(String name) {
		this.name = name;
	}
}
