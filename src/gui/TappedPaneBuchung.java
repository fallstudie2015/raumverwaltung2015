package gui;

import javax.swing.JTabbedPane;

import com.toedter.calendar.JCalendar;

import de.dhbw.java.Benutzer;

public class TappedPaneBuchung extends JTabbedPane {
	private PanelBuchung pb;
	private PanelMeineBuchung pmb;
	JCalendar jc;

	public TappedPaneBuchung(JCalendar jc) {
		this.jc = jc;
		pmb = new PanelMeineBuchung(jc);
		addTab("Meine Buchungen", pmb);
		if (Benutzer.getBenutzertyp() == 'v') {
			pb = new PanelBuchung(jc);
			addTab("Unbestätigte Buchungen", pb);
		}
	}

	public void reloadTableBuchung() {
		pmb.reloadTableBuchung();
		if (Benutzer.getBenutzertyp() == 'v') {
			pb.reloadTableBuchung();
		}
	}
}
