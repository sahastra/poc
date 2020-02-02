import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DetailView extends JFrame {
	
	static JLabel name = new JLabel();
	static JLabel symbol = new JLabel();
	static JLabel atomicNumber = new JLabel();
	static JLabel massNumber = new JLabel();
	static JLabel imagePath = new JLabel();
	
	public DetailView(ElementInfo info){
		  super();
	        
	        setTitle("Element Details");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        name.setText(info.getName());
	        symbol.setText(info.getSymbol());
	        atomicNumber.setText(info.getAtomicNumber());
	        massNumber.setText(info.getAtomicWeight());
	        imagePath.setText(info.getImagePath());
	        
	        add(name);
	        add(symbol);
	        add(atomicNumber);
	        add(massNumber);
	        add(imagePath);
	        
	        setLayout(new GridLayout(5, 2));
	        //setBackground(Color.blue);
	        
	        setVisible(true);
			setSize(450, 350);
	}

}
