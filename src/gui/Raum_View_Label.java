package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhbw.java.Buchung;

public class Raum_View_Label extends JLabel {

	private Time time;
	private Buchung buchung;
	private JPanel parent;
	public boolean buchungGesetzt = false;

	public Raum_View_Label(Time time) {
		this.time = time;
		this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		this.setPreferredSize(new Dimension(200, 20));
		this.setOpaque(true);
	}

	public JPanel getPanel() {
		return parent;
	}

	public void setPanel(JPanel panel) {
		parent = panel;
	}

	public Time getTime() {
		return time;
	}

	public void setBuchung(Buchung buchung) {
		this.buchung = buchung;
		if (this.buchung != null) {
			buchungGesetzt = true;
		}
	}

	public Buchung getBuchung() {
		return buchung;
	}

	public void setMouseListener(MouseListener ml) {
		this.addMouseListener(ml);
	}
}
