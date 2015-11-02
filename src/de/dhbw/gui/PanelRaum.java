/* Programmiert von: Kai Kleefisch
 * Programmiert für: Den Löschdialog der Räume
 * Beschreibung: Zeigt eine Tabelle mit allen aktiven Räumen, um so den zu löschenden Raum auswählen zu können
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

public class PanelRaum extends JPanel {
	private String[] tableHeader;
	private String[][] tableData;
	// TabellenModel wird zur erstellung einer Tabelle benötigt
	private DefaultTableModel tableModel;

	private MeineTabelle tableRaum;
	private JScrollPane scrollPane;

	public PanelRaum() {
		try {
			ResultSet rs = SQL_Schnittstelle.getAllRooms();
			tableHeader = TabellenWerkzeug.getStringTableHeader(rs);
			tableData = TabellenWerkzeug.resultSetToTableStringArray(rs);
			tableModel = new DefaultTableModel(tableData, tableHeader);

			tableRaum = new MeineTabelle(tableModel);
			tableRaum.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);
			tableRaum.setAutoCreateRowSorter(true);
			setLayout(new BorderLayout());
			scrollPane = new JScrollPane(tableRaum);
			add(scrollPane, BorderLayout.CENTER);
			setBorder(BorderFactory.createLineBorder(Color.black));
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e, "gui.PanelRaum.PanelRaum()");
		}

	}

	// Gibt die ID des Raumes zurück der gelöscht werden soll
	public int getSelectedRaumID() {
		int raumid = 0;
		try {
			if (tableRaum.getSelectedRow() == -1) {

			} else {
				raumid = Integer.parseInt(String.valueOf(tableRaum.getValueAt(
						tableRaum.getSelectedRow(), 0)));
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelRaum.getSelectedRaumId");
		}

		return raumid;
	}
}