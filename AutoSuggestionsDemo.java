import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AutoSuggestionsDemo {

	public static void main(String args[]) {

		final JFrame frame = new JFrame("AUTO SUGGESTIONS DEMO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextField textField = new JTextField();
		frame.add(textField, BorderLayout.NORTH);

		JComboBox<String> comboBox = new JComboBox<String>();
		frame.add(comboBox);

		JLabel result = new JLabel();
		frame.add(result, BorderLayout.SOUTH);

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

				for (String item : matchingList) {
					comboBox.addItem(item);
				}

				result.setText(matchingList.toString());
			}

			//3. METHOD TO FILTER SUGGESTIONS FROM THE ENTIRE FILE
			private ArrayList<String> getMatchingList(String key) {
				
				//3.1 LOAD FILE AS AN IN MEMORY ARRAY 
				ArrayList<String> values = new ArrayList<String>();
				values.add("A1");
				values.add("A2");
				values.add("A3");
				values.add("B");
				values.add("1B");
				values.add("AB");
				values.add("AB1");
				values.add("Hydrogen, 1, H2");
				values.add("Helium, 2, He");

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
}