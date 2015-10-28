package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import listener.TableBuchungs_Listener;

import com.toedter.calendar.JCalendar;

import de.dhbw.java.SQL_Schnittstelle;
import de.dhbw.java.TableBuchung;

public class PanelBuchung extends JPanel {
	private String[] tableHeader = new String[] { "ID", "Datum", "Raum",
			"Besteller" };
	private String[][] dataBuchung = buchungBestellerListeToTableStringArray();
	private DefaultTableModel buchungBestellerModel = new DefaultTableModel(
			dataBuchung, tableHeader);

	private TableBuchung tableBuchung = new TableBuchung(buchungBestellerModel);
	private TableBuchungs_Listener tbl;

	private JLabel lblHeader = new JLabel("Unbestätigte Buchungen",
			SwingConstants.CENTER);
	private JCalendar jc;

	public PanelBuchung(JCalendar jc) {
		this.jc = jc;
		tbl = new TableBuchungs_Listener(this);
		tableBuchung.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		tableBuchung.getSelectionModel().addListSelectionListener(tbl);
		tableBuchung.setAutoCreateRowSorter(true);
		setLayout(new BorderLayout());
		lblHeader.setFont(new Font("header", 0, 20));
		add(lblHeader, BorderLayout.NORTH);
		add(new JScrollPane(tableBuchung), BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private String[][] buchungBestellerListeToTableStringArray() {
		ResultSet rs = null;
		rs = SQL_Schnittstelle.getBuchungenZuGenehmigung();
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

	public void reloadTableBuchung() {
		tableBuchung.getSelectionModel().removeListSelectionListener(tbl);
		dataBuchung = buchungBestellerListeToTableStringArray();
		buchungBestellerModel.fireTableDataChanged();
		tableBuchung.getSelectionModel().addListSelectionListener(tbl);
	}

	public void auswahlAnzeigenImRaumplaner_View() {
		Date date = new Date();
		System.out.println(tableBuchung.getValueAt(
				tableBuchung.getSelectedRow(), 3));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {
			date = df.parse(String.valueOf(tableBuchung.getValueAt(
					tableBuchung.getSelectedRow(), 3)));
			System.out.println("erfolgreich");
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("nicht erfolgreich");
			e.printStackTrace();
		}
		jc.setDate(date);
	}
}
