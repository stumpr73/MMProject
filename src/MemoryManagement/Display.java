package MemoryManagement;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//
/**
 *  Sets up the GUI to display the contents of the memory manager
 *  as well as performs the actions that the user inputs to the GUI.
 */
public class Display extends JFrame
implements ActionListener {

	private MemoryManager mm;
	private JButton allocate;
	private JButton deallocate;
	private JTextField processName;
	private JTextField processSize;
	private JPanel buttonPanel;
	private JPanel textPanel;
	private JList list;
	private JPanel listPanel;
	private Draw drawPanel = new Draw();
	private DefaultListModel listModel;

	/**
	 * Constructor
	 */
	public Display() {
		super("Memory Manager");
		setUpGUI();
	}

	/**
	 * Creates the GUI
	 */
	protected void setUpGUI(){
		mm = new MemoryManager();
		drawPanel.setMM(mm);
		setSize(512, 512);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//buttons***************************************
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));

		allocate = new JButton("Allocate");
		allocate.addActionListener(this);
		buttonPanel.add(allocate);

		deallocate = new JButton("Deallocate");
		deallocate.addActionListener(this);
		buttonPanel.add(deallocate);
		//***********************************************

		//textFields
		textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(2,1));

		processName = new JTextField("Process Name Here");
		processName.addActionListener(this);
		textPanel.add(processName);

		processSize = new JTextField("Process Size Here");
		processSize.addActionListener(this);
		textPanel.add(processSize);
		//***********************************

		//list of processes
		listPanel = new JPanel();
		listModel = new DefaultListModel();
		list = new JList(listModel);

		list.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String name = (String) list.getSelectedValue();
					//Font font = new Font(name, Font.PLAIN, 12);
					//label.setFont(font);
				}
			}
		});

		JScrollPane pane = new JScrollPane();
		pane.getViewport().add(list);
		pane.setPreferredSize(new Dimension(250, 200));

		//**********************************************

		Container contentPane = this.getContentPane();
		contentPane.add(textPanel, BorderLayout.SOUTH);
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		contentPane.add(pane, BorderLayout.WEST);
		add(drawPanel);
	}

	/**
	 * Either allocates or deallocates memory for a process, when
	 * the corresponding JButton is pressed on the GUI
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == allocate) {
			try{
				int remainingSize = 64;
				for(int i = 0; i<mm.getProcesses().size(); i++)
				{
					if(mm.getProcesses().get(i).getLp() != null)
						remainingSize -= mm.getProcesses().get(i).getSize();
				}
				mm.allocate(new Process(Integer.parseInt(processSize.getText()), processName.getText()));

				if(remainingSize >= Integer.parseInt(processSize.getText()))
					listModel.addElement(processName.getText() + ", " + Integer.parseInt(processSize.getText()));
				repaint();}
			catch(NumberFormatException nfe){
				System.err.println("Invalid input please enter an integer");}


		} 
		else if (source == deallocate) {

			try{mm.deallocate(new Process(Integer.parseInt(processSize.getText()), processName.getText()));
			listModel.removeElement(processName.getText() + ", " + Integer.parseInt(processSize.getText()));
			repaint();}
			catch(NumberFormatException nfe){
				System.err.println("Invalid input please enter an integer");}
		} 
	}
}
//
