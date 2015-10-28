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
			pb.auswahlAnzeigen();
		}

	}
}
