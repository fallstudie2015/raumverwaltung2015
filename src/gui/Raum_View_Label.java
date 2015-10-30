package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhbw.java.Benutzer;
import de.dhbw.java.Buchung;

/**
 * 
 * @author Tim
 * 
 *         Erstellt die Labels auf dem Hauptfeld und speichert ihre Zeit und die
 *         dazugehörige Buchung
 *
 */
public class Raum_View_Label extends JLabel {

	private Time time;
	private Buchung buchung;
	private JPanel parent;
	Raumplaner_View frame;
	public boolean buchungGesetzt = false;

	public Raum_View_Label(Time time) {
		this.time = time;
		this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		this.setPreferredSize(new Dimension(200, 20));
		this.setOpaque(true);
		this.setEnabled(true);

		/*
		 * Listener öffnet Benutzer abhängig die zu Bestätigenansicht oder
		 * Stornierenansicht
		 */
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (Benutzer.getBenutzertyp() == 'v' && buchung != null && !buchung.getStatus().equalsIgnoreCase("g")) {
					Bestaetigungs_View beV = new Bestaetigungs_View(frame, buchung);
				} else if (Benutzer.getBenutzertyp() == 'b' && buchung != null
						&& Benutzer.getBenutzerID() == buchung.getBenutzerID()) {
					Stornieren_View sv = new Stornieren_View(frame, buchung);
				} else if (Benutzer.getBenutzertyp() == 'v' && buchung != null
						&& buchung.getStatus().equalsIgnoreCase("g")) {
					Stornieren_View sv = new Stornieren_View(frame, buchung);
				}
			}
		});
	}

	/*
	 * Methoden zum setzten oder entfernen der Attribute auf dem Label
	 */
	public JPanel getPanel() {
		return parent;
	}

	public void setPanel(JPanel panel) {
		parent = panel;
	}

	public void setFrame(Raumplaner_View rv) {
		this.frame = rv;
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

	public void removeBuchung() {
		buchung = null;
		buchungGesetzt = false;
	}
}
