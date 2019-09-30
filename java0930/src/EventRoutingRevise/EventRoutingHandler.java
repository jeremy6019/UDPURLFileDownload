package EventRoutingRevise;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventRoutingHandler implements ActionListener {

	Frame frame;
	Button blueBtn, greenBtn;
    
	
	
	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public EventRoutingHandler(Button blueBtn, Button greenBtn) {
		super();
		this.blueBtn = blueBtn;
		this.greenBtn = greenBtn;
	}


	@Override
	public void actionPerformed(ActionEvent e) {		
    
		if(e.getSource()==blueBtn) {
			frame.setBackground(Color.BLUE);			
		} else if(e.getSource()==greenBtn) {
			frame.setBackground(Color.GREEN);
		}
	}

}
