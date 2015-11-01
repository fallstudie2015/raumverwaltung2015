/* Programmiert von: Kai Kleefisch
 * Programmiert für: TeilBereich der GUI im Raumplaner_View
 * Beschreibung: Beinhaltet die Tabellen meineBuchungen und nicht bestätigte Buchungen zur Navigation
 */
package gui;

import javax.swing.JTabbedPane;

import com.toedter.calendar.JCalendar;

import de.dhbw.java.Benutzer;

public class TappedPaneBuchung extends JTabbedPane {
	// Panel mit Tabelle mit unbestätigten Buchungen für den Verwalter
	private PanelBuchung pb;
	// Panel mit Tabelle mit eigenen Buchungen des Benutzers
	private PanelMeineBuchung pmb;
	JCalendar jc;

	/*
	 * Konstruktor mit Übergabeparameter JCalendar, um auf den Kalendar vom
	 * Mutterfenster zugriff zu haben und auf ein bestimmtes Datum springen zu
	 * können
	 */
	public TappedPaneBuchung(JCalendar jc) {
		try {
			this.jc = jc;
			pmb = new PanelMeineBuchung(jc);
			addTab("Meine Buchungen", pmb);
			/*
			 * Wenn BenutzerTyp gleich Verwalter, dann bekommt er auch die
			 * Tabelle mit den unbestätigten Buchungen angezeigt
			 */
			if (Benutzer.getBenutzertyp() == 'v') {
				pb = new PanelBuchung(jc);
				addTab("Unbestätigte Buchungen", pb);
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.TappedPaneBuchung.TappedPaneBuchung(JCalendar jc)");
		}

	}

	// Method um vom Parent ein Update der Tabellen auf dem Unterfenster
	// auszulösen
	public void reloadTableBuchung() {
		try {
			pmb.reloadTableBuchung();
			if (Benutzer.getBenutzertyp() == 'v') {
				pb.reloadTableBuchung();
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.TappedPaneBuchung.reloadTableBuchung");
		}
	}
}
