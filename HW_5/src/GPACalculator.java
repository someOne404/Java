// Burgard Lu (jl4nq)
// HW 5
// Source:
// TA's OH
// https://stackoverflow.com/questions/1117888/how-to-remove-a-row-from-jtable
// https://1bestcsharp.blogspot.com/2015/02/java-how-to-remove-selected-row-from.html
// https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
// https://stackoverflow.com/questions/18309113/jtable-how-to-force-user-to-select-exactly-one-row
// http://www.java2s.com/Tutorial/Java/0240__Swing/Setthegridcolor.htm
// https://stackoverflow.com/questions/6232355/deleting-all-the-rows-in-a-jtable
// https://stackoverflow.com/questions/18179701/how-to-find-index-of-int-array-which-match-specific-value
// https://stackoverflow.com/questions/7861724/is-there-a-word-wrap-property-for-jlabel/7861833#7861833

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GPACalculator extends JFrame{
    final String html1 = "<html><body style='width: ";
    final String html2 = "px'>";
	final int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(); // the height of the device being used
	final int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(); // the width of the device being used
	
	// Panels
	JPanel coursePanel;
	JPanel buttonPanel;
	JPanel summaryPanel;
	JPanel rightPanel;
	
	// Course Panel
	DefaultTableModel dtm;
	JTable table;
		
	// Button Panel
	JButton addCourse;
	JButton removeCourse;
	JButton clear;
	JButton addCredits;
	
	// Summary Panel
	double currentGPA = 0.0;
	int currentCredits = 0;
	int untakenCredits = 0;
	int totalCredits = 0;
	double requiredGPA = 0.0;
	double currentTotalGrades = 0.0;
	JLabel target;
	JTextField targetGPA;
	JLabel current;
	JLabel required;
	JLabel suggestion;
	JButton calculate;
	JButton enter;

	
    public static void main(String[] args) {
        
        new GPACalculator();
    }
    
    

    public GPACalculator()
    {
        // Window Setting
    		this.setTitle("GPA Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(width/2, height/2);
		this.setLayout(new GridLayout(1,2));

		
		// Initializing Panels
		coursePanel = new JPanel();
		buttonPanel = new JPanel();
		summaryPanel = new JPanel();
		rightPanel = new JPanel();
     
		
		// Course Panel
		// Assume users can directly edit the table where all the courses are being displayed
		// Assume there is a default row that displays an example course
		// Assume the courses without grades are current and anticipated courses 
		// Assume the default credit hours of the new course being added is 3
		// Assume users will enter appropriate letter grade and credit hours for the corresponding blocks
	    String[] columnNames = {"Name","Grade","Credits"};
	    Object[][] data = {{"Ex. Course","A",3},{"","","3"},{"","","3"}};
	    dtm = new DefaultTableModel(data,columnNames);
	    table = new JTable(dtm);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setGridColor(Color.black);
	    coursePanel.setLayout(new BorderLayout());
	    coursePanel.add(new JScrollPane(table), BorderLayout.CENTER);
	    
	    
	    
	    
		// Button Panel
	    // Assume there are 4 buttons that allow users to add a course, remove a course, add 15 credits, and clear all the courses
	    // add a course
		addCourse = new JButton("Add a Course");
		addCourse.addActionListener(new addCourseButtonListener());
		    
		// remove a course
		removeCourse = new JButton("Remove");
		removeCourse.addActionListener(new removeCourseButtonListener());
		    
		// add 15 credits
		addCredits = new JButton("Add 15 Credits");
		addCredits.addActionListener(new addBlankCreditsButtonListener());
		    
		// reset courses
		clear = new JButton("Clear");
		clear.addActionListener(new resetButtonListener());
	    
	    buttonPanel.setLayout(new GridLayout(2,2));
	    buttonPanel.add(addCourse);
	    buttonPanel.add(removeCourse);
	    buttonPanel.add(addCredits);
	    buttonPanel.add(clear);
	   

	    // Summary Panel
		current = new JLabel("Current GPA: ");
		calculate = new JButton("Calculate");
		calculate.addActionListener(new calculateButtonListener());
		
		target = new JLabel("Target GPA: ");
		targetGPA = new JTextField("");
	    required = new JLabel("Required GPA: ");
	    enter = new JButton("Enter");
	    enter.addActionListener(new enterButtonListener());
	    
	    suggestion = new JLabel("");

	    summaryPanel.setLayout(new GridLayout(4,2));
	    summaryPanel.setMaximumSize(new Dimension(width/4,height/4));
		
		summaryPanel.add(current);
		summaryPanel.add(calculate);
		summaryPanel.add(target);
		summaryPanel.add(targetGPA);
		summaryPanel.add(required);
		summaryPanel.add(enter);


	    rightPanel.setLayout(new GridLayout(3,1));
	    rightPanel.setMaximumSize(new Dimension(width/4, height/2));
		rightPanel.add(buttonPanel);
		rightPanel.add(summaryPanel);
		rightPanel.add(suggestion);
		
		
	    this.add(coursePanel);
	    this.add(rightPanel);
	    this.pack();
	    this.setVisible(true);
		
  
    }  
    
    
    // Button Listeners
    class addCourseButtonListener implements ActionListener {	// Button to add a course
		@Override
		public void actionPerformed(ActionEvent arg0) {
			insertRow();
		}
	}
    
    
    class calculateButtonListener implements ActionListener {	// Button to calculate currentGPA
		@Override
		public void actionPerformed(ActionEvent arg0) {
			calculate();
			current.setText("Current GPA: " + String.format ("%.3f", currentGPA));
			
		}
	}
    
    class resetButtonListener implements ActionListener {	// Button to clear the course list
		@Override
		public void actionPerformed(ActionEvent arg0) {
			clear();
			current.setText("Current GPA: ");
			required.setText("Required GPA: ");
			targetGPA.setText("");
			suggestion.setText(""); // set the summary to their initial states
		}
	}
    
    
    class addBlankCreditsButtonListener implements ActionListener {// Button to add 15 credit hours. 
		@Override
		public void actionPerformed(ActionEvent arg0) {
			insertRow();
			insertRow();
			insertRow();
			insertRow();
			insertRow(); // Assume the default credit hours is 3, so adding 15 credit hours means that 5 new courses are added at one time


		}
	}
    
    class enterButtonListener implements ActionListener {	// Button to calculate the requiredGPA
		@Override
		public void actionPerformed(ActionEvent arg0) {
			calculateRequired();
			required.setText("Required GPA: " + String.format ("%.3f", requiredGPA));
		}
	}
    
    class removeCourseButtonListener implements ActionListener {// Button to remove a single course
		@Override
		public void actionPerformed(ActionEvent arg0) {
			removeRow();
		}
	}
    
    
    // methods
	public void insertRow() { // Add a row to the JTable to represent a new course being added. Assume the default credit hours is 3 
		dtm.addRow(new Object[] { "", "", 3});
	}
	
	public void removeRow() { // Remove a single selected row from the JTable to represent a course being removed.
		try {
			dtm.removeRow(table.getSelectedRow());
		} catch (Exception ex){
			JOptionPane.showMessageDialog(null, "Please Select A Course!"); //  If users do not select any course but clicked the Remove button, a window will show up
		}
		
	}
	
	public void clear() { // Clear all the rows from the JTable to represent a refreshed course page.
		int rowCount = dtm.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			dtm.removeRow(i);
		}
		currentGPA = 0.0;
		currentCredits = 0;
		untakenCredits = 0;
		totalCredits = 0;
		requiredGPA = 0.0;
		currentTotalGrades = 0.0; // All the values are set back to their initial values
		
	}
	
	public void calculate() { // Calculate currentGPA all over again
		double[] grade = {4.0, 4.0, 3.7, 3.3, 3.0, 2.7, 2.3, 2.0, 1.7, 1.3, 1,0, 0.0, 0.0};
	    String[] letters = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "E", "F"};

	    currentTotalGrades = 0.0;
	    currentCredits = 0;
	    untakenCredits = 0;
	    totalCredits = 0;
	    int row = dtm.getRowCount(); 

		for (int i = 0; i < row; i++) {
			totalCredits += Integer.parseInt(dtm.getValueAt(i, 2).toString());
		} // Calculate all the credits being displayed in the course list
		
		for (int i = 0; i < row; i++) {
			if (dtm.getValueAt(i, 1).toString() != "") {
				currentCredits += Integer.parseInt(dtm.getValueAt(i, 2).toString()); // Calculate the credits for the courses that have letter grades, which represent taken courses
			}
			else
				untakenCredits += Integer.parseInt(dtm.getValueAt(i, 2).toString()); // Calculate the credits for the courses that do not have letter grades, which represent current courses and anticipated courses
		} 
		
		for (int i = 0; i < row; i++) { // Calculate the weighted grade points that are earned
		
			if (dtm.getValueAt(i, 1).toString() != "") {
				String letter = dtm.getValueAt(i, 1).toString();
				currentTotalGrades +=  grade[Arrays.asList(letters).indexOf(letter)] * Double.parseDouble(dtm.getValueAt(i, 2).toString());
			}
			else
				currentTotalGrades += 0.0;
			
		}
		
		currentGPA = currentTotalGrades/currentCredits; 
		
	}
	
	public void calculateRequired() { // Calculate requiredGPA
		calculate();
		try {
			requiredGPA= (Double.parseDouble(targetGPA.getText().trim())* totalCredits - (currentGPA * currentCredits)) / untakenCredits; // Based on the equation given on Piazza
			if (requiredGPA > 4.0) { // Display the warning message if requiredGPA exceeds 4.0
				suggestion.setText(html1 + width/4 + html2 + "Because the required GPA exceeds 4.0, try to add more credit hours and recalculate.");
			}	
			else if (requiredGPA < 2.0) { // Display the warning message if requiredGPA below 4.0
				suggestion.setText(html1 + width/4 + html2 + "Because the required GPA is lower than 2.0, take fewer credit hours if you wish.");
			}
			else
				suggestion.setText(""); // When the requiredGPA is in the normal range, no warning message
		} catch (NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "Please Enter the Value in Double"); // The warning message is displayed when users do not enter double value for targetGPA
		}
	}
	
    
}

    