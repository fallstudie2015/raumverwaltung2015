package gui;

import java.sql.Date;
import java.sql.Time;

import javax.swing.JLabel;

public class Raum_View_Label extends JLabel {

	private Time time;

	public Raum_View_Label(Time time) {
		this.time = time;
	}

	public Time getTime() {
		return time;
	}

}
