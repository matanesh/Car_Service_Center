package CarServiceCenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener 
{ 
	public void actionPerformed(ActionEvent e) 
    {   
		OptionPane.option = e.getActionCommand();
    } 
}
