package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SlotLabel extends ColoredLabel {
	private String address;
	
    public SlotLabel(String address) {
        super("                    ", Color.WHITE, RIGHT);
        this.address = address;
        addMouseListener(new MouseA());
    }
    
    class MouseA extends MouseAdapter {    	
    	public void mousePressed(MouseEvent e){
    		System.out.println("Clicked: " + address + "!");
			// TODO
		}	
    }
}