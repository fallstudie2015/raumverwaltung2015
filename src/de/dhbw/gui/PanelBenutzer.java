/* Programmiert von: Kai Kleefisch
 * Programmiert für: Löschdialog der Benutzer
 * Beschreibung: Zeigt eine Tabelle mit allen aktiven Benutzern, um so den zu löschenden Benutzer auswählen zu können
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

public class PanelBenutzer extends JPanel {
	private String[] tableHeader;
	private String[][] tableData;
	// TabellenModel wird zur erstellung einer Tabelle benötigt
	private DefaultTableModel tableModel;

	private MeineTabelle tableBenutzer;
	private JScrollPane scrollPane;;

	public PanelBenutzer() {
		try {
			ResultSet rs = SQL_Schnittstelle.getAllAusstattung();
			tableHeader = TabellenWerkzeug.getStringTableHeader(rs);
			tableData = TabellenWerkzeug.resultSetToTableStringArray(rs);
			tableModel = new DefaultTableModel(tableData, tableHeader);

			tableBenutzer = new MeineTabelle(tableModel);
			tableBenutzer.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);
			setLayout(new BorderLayout());
			scrollPane = new JScrollPane(tableBenutzer);
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
			if (tableBenutzer.getSelectedRow() == -1) {

			} else {
				ausid = Integer.parseInt(String.valueOf(tableBenutzer
						.getValueAt(tableBenutzer.getSelectedRow(), 0)));
			}

		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelAusstattung.getSelectedAusstattungsID()");
		}
		return ausid;
	}
}