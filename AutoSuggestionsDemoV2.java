import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AutoSuggestionsDemoV2 {
	
	static JComboBox<String> comboBox = new JComboBox<String>();
	static JLabel result = new JLabel();
	static JTextField textField = new JTextField();
	static JFrame frame;
	
	public AutoSuggestionsDemoV2() {
		frame = new JFrame("AUTO SUGGESTIONS DEMO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		frame.add(textField, BorderLayout.NORTH);
		comboBox.addActionListener(new ComboListener());
		
		frame.add(comboBox);

		frame.add(result, BorderLayout.SOUTH);

	}
	
	public static void main(String args[]) {
		new AutoSuggestionsDemoV2();

		// 1. ADD LISTENER TO YOUR SEARCH TEXT BOX
		DocumentListener documentListener = new DocumentListener() { //BEGIN OF LISTENER

			public void changedUpdate(DocumentEvent documentEvent) {
				changeSuggestions(documentEvent);
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				changeSuggestions(documentEvent);
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				changeSuggestions(documentEvent);
			}

			//2. METHOD TO BUILD SUGGESTIONS COMBO BOX BASED ON THE 
			private void changeSuggestions(DocumentEvent documentEvent) {
				System.out.println("textField: " + textField.getText());
				ArrayList<String> matchingList = getMatchingList(textField.getText());
				System.out.println(matchingList);

				comboBox.removeAllItems(); //reset combo every time, else keep appending old suggestions
				comboBox.addItem("---Please select---");
				
				for (String item : matchingList) {
					comboBox.addItem(item);
				}
				
				comboBox.showPopup(); //To open the Combo List without Clicking 
				    
				result.setText(matchingList.toString());
			}

			//3. METHOD TO FILTER SUGGESTIONS FROM THE ENTIRE FILE
			private ArrayList<String> getMatchingList(String key) {
				
				//3.1 LOAD FILE AS IN-MEMORY ARRAY 
				ArrayList<String> values = new ArrayList<String>();
				values.add("Hydrogen, H, 1, 1.008");
				values.add("Helium, He, 2, 4.0026");
				values.add("Lithium, Li, 3, 6.94");
				values.add("Beryllium, Be, 4, 9.0122");
				values.add("Boron, B, 5, 10.81");

				ArrayList<String> suggestionList = new ArrayList<String>();
				
				for (String element : values) {
				    //3.2 ADD ONLY MATCHING VALUES TO THE SUGGESTION LIST
					if (element.toUpperCase().contains(key.toUpperCase())) {
						suggestionList.add(element);
					}
				}

				return suggestionList;
			}

		}; //END OF THE LISTENER
		
		textField.getDocument().addDocumentListener(documentListener);

		frame.setSize(250, 150);
		frame.setVisible(true);
	}

	class ComboListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedItem = comboBox.getItemAt(comboBox.getSelectedIndex());
			System.out.println("Combo Selected : " + selectedItem);		
			
			if (selectedItem.equalsIgnoreCase("---Please select---")) {
				//DO NOT DO ANYTHING !
				return;
			}
			
			String[] parts = selectedItem.split(",");
			
					
			//Show details of the selected item from the combo
			ElementInfo info = new ElementInfo();
			info.setName(parts[0]);
			info.setSymbol(parts[1]);
			info.setAtomicNumber(parts[2]);
			info.setAtomicWeight(parts[3]);
			info.setImagePath("na");
			new DetailView(info);
		}
	}
}