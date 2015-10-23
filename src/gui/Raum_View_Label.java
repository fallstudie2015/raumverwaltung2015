package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Raum_View_Label extends JLabel {

	private Time time;

	public Raum_View_Label(Time time) {
		this.time = time;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(new Dimension(200, 20));
		this.setOpaque(true);
	}

	public Raum_View_Label() {
		// TODO Auto-generated constructor stub
	}

	public Time getTime() {
		return time;
	}

}
