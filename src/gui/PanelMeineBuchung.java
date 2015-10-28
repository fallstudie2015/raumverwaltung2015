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

import listener.TableMeineBuchungs_Listener;

import com.toedter.calendar.JCalendar;

import de.dhbw.java.Benutzer;
import de.dhbw.java.SQL_Schnittstelle;
import de.dhbw.java.TableBuchung;

public class PanelMeineBuchung extends JPanel {
	private String[] tableHeader = getStringTableHeader();
	private String[][] dataBuchung = buchungBestellerListeToTableStringArray();
	private DefaultTableModel buchungBestellerModel = new DefaultTableModel(
			dataBuchung, tableHeader);

	private TableBuchung tableBuchung = new TableBuchung(buchungBestellerModel);
	private TableMeineBuchungs_Listener tbl;

	private JScrollPane scrollPane;;

	private JLabel lblHeader = new JLabel("Meine Buchung",
			SwingConstants.CENTER);
	private JCalendar jc;

	public PanelMeineBuchung(JCalendar jc) {
		this.jc = jc;
		tbl = new TableMeineBuchungs_Listener(this);
		tableBuchung.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		tableBuchung.getSelectionModel().addListSelectionListener(tbl);
		tableBuchung.setAutoCreateRowSorter(true);
		setLayout(new BorderLayout());
		lblHeader.setFont(new Font("header", 0, 20));
		add(lblHeader, BorderLayout.NORTH);
		scrollPane = new JScrollPane(tableBuchung);
		add(scrollPane, BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private String[] getStringTableHeader() {
		ResultSet rs = null;
		rs = SQL_Schnittstelle.getMyBuchungen(Benutzer.getBenutzerID());
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
		rs = SQL_Schnittstelle.getMyBuchungen(Benutzer.getBenutzerID());
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
		remove(scrollPane);
		dataBuchung = buchungBestellerListeToTableStringArray();

		buchungBestellerModel = new DefaultTableModel(dataBuchung, tableHeader);
		tableBuchung = new TableBuchung(buchungBestellerModel);

		buchungBestellerModel.fireTableDataChanged();
		tableBuchung.getSelectionModel().addListSelectionListener(tbl);
		scrollPane = new JScrollPane(tableBuchung);
		add(scrollPane);
		validate();
		repaint();
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
