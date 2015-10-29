package listener;

import gui.PanelMeineBuchung;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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