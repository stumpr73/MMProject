package MemoryManagement;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *  This class is used to control the animation of random processes being allocated
 *  and deallocated from memory
 */
public class Animation extends JFrame {
	private MemoryManager mm;
	private JList list;
	private JPanel listPanel;
	private Draw drawPanel = new Draw();
	private DefaultListModel listModel;
	Random r = new Random();
	ArrayList<String> names = new ArrayList<>();
	int processName=0; // the index of the next process name
	int remainingSize=64; // size left in memory

	/**
	 * Constructor
	 */
	public Animation(){
		setUpGUI();
		setUpProcessNames();
		animate();
	}

	/**
	 * Sets up the GUI
	 */
	private void setUpGUI(){
		mm = new MemoryManager();
		drawPanel.setMM(mm);
		setSize(512, 512);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//list of processes
		listPanel = new JPanel();
		listModel = new DefaultListModel();
		list = new JList(listModel);

		JScrollPane pane = new JScrollPane();
		pane.getViewport().add(list);
		pane.setPreferredSize(new Dimension(250, 200));

		//**********************************************

		Container contentPane = this.getContentPane();
		contentPane.add(pane, BorderLayout.WEST);
		add(drawPanel);
	}

	/**
	 * Adds the process names to an ArrayList.
	 * Each new process will get the next letter in the list
	 */
	private void setUpProcessNames(){
		names.add("Process A");
		names.add("Process B");
		names.add("Process C");
		names.add("Process D");
		names.add("Process E");
		names.add("Process F");
	}

	/**
	 * Performs the animation of allocating and deallocating
	 * random processes.
	 */
	private void animate(){
		//can be set lower, but more waiting will have to happen
		//due to random size processes being created
		while(remainingSize >= 4){
			Process process = createRandomProcess();

			mm.allocate(process);
			for(int i = 0; i<mm.getProcesses().size(); i++)
			{
				if(mm.getProcesses().get(i).getLp() != null)
					remainingSize -= mm.getProcesses().get(i).getSize();
			}
			listModel.addElement(process.getName() + ", " + process.getSize());
			repaint();
			
			setVisible(true);
			
			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * Creates a random process.  
	 * @return A process whose size is small enough to fit into 
	 * the current memory.
	 */
	private Process createRandomProcess(){
		int adjustedSize=0;
		Process p=null;

		do{
			p = new Process(1 + r.nextInt(31),names.get(processName));
			processName++;
			adjustedSize = (int)Math.pow(2,Math.ceil(Math.log(p.getSize())/Math.log(2)));
		} while(remainingSize < adjustedSize);

			return p;
	}
}