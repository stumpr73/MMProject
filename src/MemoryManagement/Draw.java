package MemoryManagement;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Controls the colors of the GUI to show the corresponding
 * processes in memory.
 */
public class Draw extends JPanel {

	MemoryManager mm;
	ArrayList<Color> colors = new ArrayList<Color>();

	/**
	 * Sets the memory manager to the GUI
	 * @param mm  The memory manager
	 */
	public void setMM(MemoryManager mm)
	{
		this.mm = mm;
		setColors();
	}

	/**
	 * Sets the colors that processes will be displayed as on the GUI
	 */
	protected void setColors(){
		colors.add(Color.BLUE);
		colors.add(Color.CYAN);
		colors.add(Color.DARK_GRAY);
		colors.add(Color.GRAY);
		colors.add(Color.GREEN);
		colors.add(Color.LIGHT_GRAY);
		colors.add(Color.MAGENTA);
		colors.add(Color.ORANGE);
		colors.add(Color.PINK);
		colors.add(Color.YELLOW);
	}

	/**
	 * Paints the colors on the GUI corresponding to processes in memory.
	 * Processes are displayed as one of the colors in the colors ArrayList.
	 * Red coloring defines memory that was allocated for a process but is not
	 * being used by the process.  For example, if a process of size
	 * 15 is allocated into a memory block of size 16, the 1 unit of extra
	 * space will be red.  Black coloring defines unallocated memory.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i =0; i<mm.getProcesses().size(); i++)
		{
			if(mm.getProcesses().get(i).getLp() != null)
			{
				g.setColor(colors.get(i));
				//**************************************
				//this is where the code must be modified
				g.fillRect(getWidth() / 4,
						(mm.getProcesses().get(i).getLowEnd()*5 + getHeight()/16),//where the box starts vertically
						getWidth() / 2, 
						(mm.getProcesses().get(i).getLp().getSize())*5 + getHeight()/16); //where the box ends vertically


				g.setColor(Color.RED);
				g.fillRect(getWidth() / 4,
						((mm.getProcesses().get(i).getLp().getSize() + mm.getProcesses().get(i).getLowEnd())*5 + getHeight()/16),//where the box starts vertically
						getWidth() / 2, 
						((mm.getProcesses().get(i).getSize()- mm.getProcesses().get(i).getLp().getSize())*5 + getHeight()/16)); //where the box ends vertical 		 
			}
			else
			{
				g.setColor(Color.BLACK);

				g.fillRect(getWidth() / 4,
						(mm.getProcesses().get(i).getLowEnd()*5 + getHeight()/16),//where the box starts vertically
						getWidth() / 2, 
						(mm.getProcesses().get(i).getSize())*5 + getHeight()/16); //where the box ends vertically
			}
		}
		System.out.println("Leaves: " + mm.toString());
	}
}