/* Programmiert von: Kai Kleefisch
 * Programmiert für: Löschdialog der Ausstattung
 * Beschreibung: Zeigt eine Tabelle mit allen aktiven Ausstattungen, um so die zu löschende Ausstattung auswählen zu können
 */
package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import de.dhbw.java.MeineTabelle;
import de.dhbw.java.SQL_Schnittstelle;
import de.dhbw.java.TabellenWerkzeug;

public class PanelAusstattung extends JPanel {
	private String[] tableHeader;
	private String[][] tableData;
	// TabellenModel wird zur erstellung einer Tabelle benötigt
	private DefaultTableModel tableModel;

	private MeineTabelle tableAusstattung;
	private JScrollPane scrollPane;;

	public PanelAusstattung() {
		try {
			ResultSet rs = SQL_Schnittstelle.getAllAusstattung();
			tableHeader = TabellenWerkzeug.getStringTableHeader(rs);
			tableData = TabellenWerkzeug.resultSetToTableStringArray(rs);
			tableModel = new DefaultTableModel(tableData, tableHeader);

			tableAusstattung = new MeineTabelle(tableModel);
			tableAusstattung.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);
			setLayout(new BorderLayout());
			scrollPane = new JScrollPane(tableAusstattung);
			add(scrollPane, BorderLayout.CENTER);
			setBorder(BorderFactory.createLineBorder(Color.black));
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelAusstattung.PanelAusstattung()");
		}
	}

	// Gibt die ID der Ausstattung zurück die gelöscht werden soll
	public int getSelectedAusstattungsID() {
		int ausid = 0;
		try {
			if (tableAusstattung.getSelectedRow() == -1) {

			} else {
				ausid = Integer.parseInt(String.valueOf(tableAusstattung
						.getValueAt(tableAusstattung.getSelectedRow(), 0)));
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelAusstattung.getSelectedAusstattungsID()");
		}
		return ausid;
	}
}