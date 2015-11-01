/* Programmiert von: Kai Kleefisch
 * Programmiert für: Festellung von Änderungen in PanelBuchung
 * Beschreibung: Wenn der Benutzer einen Wert in der Tabelle ausgewählt hat wird automatisch die Ansicht aktualisiert
 */

package listener;
import gui.PanelBuchung;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class TableBuchungs_Listener implements ListSelectionListener {
	PanelBuchung pb;

	public TableBuchungs_Listener(PanelBuchung pb) {
		this.pb = pb;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == true) {
			pb.auswahlAnzeigenImRaumplaner_View();
		}

	}
}
