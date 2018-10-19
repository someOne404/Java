import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

//https://zh.scribd.com/doc/116995352/Gui-Class-With-Selection-And-Bubble-Sort

public class GUI extends JFrame{
	JButton Setzen;
	JButton BubbleSort;
	JButton SelectionSort;
	JTextField Eingabe;
	int Array[] = new int[5];
	Random rnd = new Random();
	
	public static void main(String[] args) {
        
        new GUI();
    }
	
	public GUI() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,300);
		setTitle("sorting");
		setLayout(null);
		
		Setzen = new JButton("Setzen");
		Setzen.setBounds(10,100,100,25);
		Setzen.addActionListener(new UnserListener());
		add(Setzen);
		
		BubbleSort = new JButton("BubbleSort");
		BubbleSort.setBounds(10,150,100,25);
		BubbleSort.addActionListener(new UnserListener());
		add(BubbleSort);
		
		SelectionSort = new JButton("SelectionSort");
		SelectionSort.setBounds(10,200,100,25);
		SelectionSort.addActionListener(new UnserListener());
		add(SelectionSort);
		
		Eingabe = new JTextField();
		Eingabe.setBounds(100,30,300,30);
		add(Eingabe);
	}
	
	public class UnserListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == Setzen) {
				Belege();
				String text ="";
				for (int i = 0; i < Array.length; i++) {
					Array[i] = rnd.nextInt(150);
					text += Array[i] + "  ";
					
				}
				Eingabe.setText(text);
			}
			else if (arg0.getSource() == BubbleSort) {
				BubbleSort();
				String text = "";
				for (int i = 0; i < Array.length; i++) {
					text += Array[i]+"  ";
				}
				Eingabe.setText(text);
			}
			else if (arg0.getSource() == SelectionSort) {
				SelectionSort();
				String text = "";
				for (int i = 0; i < Array.length; i++) {
					text += Array[i]+"  ";
				}
				Eingabe.setText(text);
			}
		}
	}
	
	
	public void Belege() {
		for (int i = 0; i<= Array.length-1; i++) {
			Array[i] = rnd.nextInt(100);
		}
	}
	
	public void BubbleSort() {
		for (int x1 = 0; x1 < Array.length-1; x1++) {
			for (int x = 0; x < Array.length -1; x++) {
				if (Array[x] > Array[x+1]) {
					int z = Array[x+1];
					Array[x+1] = Array[x];
					Array[x] = z;
				}
			}
		}
	}
	
	public void SelectionSort() {
		for (int i = 0; i<Array.length-1; i++) {
			int min = i;
			for (int j = i+1; j<Array.length; j++) {
				if (Array[min] > Array[j]) {
					min = j;
				}
			}
			if (min !=i) {
				int temp = Array[i];
				Array[i] = Array[min];
				Array[min] = temp;
			}
		}
	}
	
}