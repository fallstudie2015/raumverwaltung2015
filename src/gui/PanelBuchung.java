package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import listener.TableBuchungs_Listener;
import de.dhbw.java.Buchung;
import de.dhbw.java.SQL_Schnittstelle;
import de.dhbw.java.TableBuchung;

public class PanelBuchung extends JPanel {
	private String[] tableHeader = new String[] { "Datum", "Raum", "Besteller" };
	private String[][] dataBuchung = buchungBestellerListeToTableStringArray();
	private DefaultTableModel buchungBestellerModel = new DefaultTableModel(
			dataBuchung, tableHeader);

	private TableBuchung tableBuchung = new TableBuchung(buchungBestellerModel);
	private TableBuchungs_Listener tbl;

	public PanelBuchung() {
		tbl = new TableBuchungs_Listener(this);
		tableBuchung.getSelectionModel().addListSelectionListener(tbl);
		setLayout(new BorderLayout());
		add(new JScrollPane(tableBuchung), BorderLayout.CENTER);
	}

	private String[][] buchungBestellerListeToTableStringArray() {
		ArrayList<Buchung> al = SQL_Schnittstelle
				.getAlleVorgemerktenBuchungen();

		String[][] tableData = new String[al.size()][3];
		for (int i = 0; i < al.size(); i++) {
			tableData[i][0] = al.get(i).getDatum().toString();
			tableData[i][1] = String.valueOf(al.get(i).getRaumID());
			tableData[i][2] = String.valueOf(al.get(i).getBenutzerID());
		}

		return tableData;

	}

	public String getDatum() {
		System.out.println(tableBuchung.getValueAt(
				tableBuchung.getSelectedRow(), 0));
		String date = String.valueOf(tableBuchung.getValueAt(
				tableBuchung.getSelectedRow(), 0));
		return date;
	}
}