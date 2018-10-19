import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

 
public class DefaultTableExample extends JFrame
{
	
	DefaultTableModel dtm;
	JButton button;
	JPanel panel;
	JTable table;
	
	public void insertRow() {
		dtm.addRow(new Object[] { "", "", 3});
	}
	
	
    public DefaultTableExample()
    {	
    		this.setLayout(new BorderLayout());
    		panel = new JPanel();
         
        //create the model
        String[] columnNames = {"Name","Grade","Credits"};
        
        
        Object[][] data = {{"Example Course","",3},{"","",""},{"","",""}};
        dtm = new DefaultTableModel(data,columnNames);
        
        
        //create the table
        table = new JTable(dtm);
        button = new JButton("add");
        dtm.addRow(new Object[] { "", "", 3});
        dtm.setColumnIdentifiers(columnNames);
        button.addActionListener( new addCourseButtonListener());
        panel.setLayout(new BorderLayout());
        panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        panel.add(table, BorderLayout.CENTER);
     
        //add the table to the frame
        this.add(button,BorderLayout.EAST);
        this.add(panel,BorderLayout.WEST);
         
        this.setTitle("Editable Table Example");
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DefaultTableExample();
            }
        });
    }
    
    
    class addCourseButtonListener implements ActionListener {	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			insertRow();
			
			
			
		}
	}
}