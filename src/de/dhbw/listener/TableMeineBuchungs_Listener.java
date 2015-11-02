/* Programmiert von: Kai Kleefisch
 * Programmiert für: Festellung von Änderungen in PanelBuchung
 * Beschreibung: Wenn der Benutzer einen Wert in der Tabelle ausgewählt hat wird automatisch die Ansicht aktualisiert
 */
package de.dhbw.listener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.dhbw.gui.PanelMeineBuchung;

public class TableMeineBuchungs_Listener implements ListSelectionListener {
	PanelMeineBuchung pb;

	public TableMeineBuchungs_Listener(PanelMeineBuchung pb) {
		this.pb = pb;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == true) {
			pb.auswahlAnzeigenImRaumplaner_View();
		}

	}
}