package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SlotLabel extends ColoredLabel {
	private String address;
    private SlotLabels slotlabels;
	
    public SlotLabel(String address, SlotLabels sl) {
        super("                    ", Color.WHITE, RIGHT);
        this.address = address;
        this.slotlabels = sl;
        addMouseListener(new MouseA());
    }
    
    class MouseA extends MouseAdapter {    	
    	public void mousePressed(MouseEvent e){
    		System.out.println("Clicked: " + address + "!");
			slotlabels.getCurrent().setAddress(address);
		}	
    }
}