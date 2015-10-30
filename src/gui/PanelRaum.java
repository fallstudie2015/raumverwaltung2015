package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import listener.TableBuchungs_Listener;
import de.dhbw.java.SQL_Schnittstelle;
import de.dhbw.java.TableBuchung;

public class PanelRaum extends JPanel {
	private String[] tableHeader = getStringTableHeader();
	private String[][] dataBuchung = buchungBestellerListeToTableStringArray();
	private DefaultTableModel buchungBestellerModel = new DefaultTableModel(
			dataBuchung, tableHeader);

	private TableBuchung tableRaum = new TableBuchung(buchungBestellerModel);
	private TableBuchungs_Listener tbl;

	private JScrollPane scrollPane;;

	private JLabel lblHeader = new JLabel("Unbestätigte Buchungen",
			SwingConstants.CENTER);

	public PanelRaum() {
		tableRaum.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		tableRaum.getSelectionModel().addListSelectionListener(tbl);
		tableRaum.setAutoCreateRowSorter(true);
		setLayout(new BorderLayout());
		lblHeader.setFont(new Font("header", 0, 20));
		// add(lblHeader, BorderLayout.NORTH);
		scrollPane = new JScrollPane(tableRaum);
		add(scrollPane, BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private String[] getStringTableHeader() {
		ResultSet rs = null;
		rs = SQL_Schnittstelle.getAllRooms();
		int anzahlSpalten = 0;
		try {
			anzahlSpalten = rs.getMetaData().getColumnCount();
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelBuchung.getStringTableHeader");
		}

		String[] stringTableHeader = new String[anzahlSpalten];
		try {
			for (int i = 1; i < anzahlSpalten + 1; i++) {
				stringTableHeader[i - 1] = rs.getMetaData().getColumnLabel(i);
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelBuchung.getStringTableHeader");
		}

		return stringTableHeader;
	}

	private String[][] buchungBestellerListeToTableStringArray() {
		ResultSet rs = null;
		rs = SQL_Schnittstelle.getAllRooms();
		String[][] tableData = null;
		int anzahlSpalten = 0;
		int anzahlZeilen = 0;

		try {
			rs.last();
			tableData = new String[rs.getRow()][3];
			rs.beforeFirst();
			if (rs.next()) {
				rs.last();
				anzahlSpalten = rs.getMetaData().getColumnCount();
				anzahlZeilen = rs.getRow();
				tableData = new String[anzahlZeilen][anzahlSpalten];
				rs.beforeFirst();

				while (rs.next()) {
					for (int i = 1; i < anzahlSpalten + 1; i++) {
						tableData[rs.getRow() - 1][i - 1] = rs.getString(i);
					}
				}

				// int i = 0;
				//
				// while (rs.next()) {
				// tableData[i][0] = rs.getDate("datum").toString();
				// tableData[i][1] = rs.getString("raumName");
				// tableData[i][2] = rs.getString("benutzerName");
				// i++;
				// }
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelBuchung.buchungBestellerListeToTableStringArray");
		}

		return tableData;
	}

	public int getSelectedRaumID() {
		int raumid = (int) tableRaum.getValueAt(tableRaum.getSelectedRow(), 1);
		return raumid;
	}
}