/* Programmiert von: Kai Kleefisch
 * Programmiert für: Ansicht Raumplaner_View
 * Beschreibung: Zeigt eine Tabelle mit allen unbestätigten Buchungen an
 */
package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import de.dhbw.java.MeineTabelle;
import de.dhbw.java.SQL_Schnittstelle;
import de.dhbw.java.TabellenWerkzeug;
import de.dhbw.listener.TableBuchungs_Listener;

public class PanelBuchung extends JPanel {
	private String[] tableHeader;
	private String[][] tableData;
	private DefaultTableModel tableModel;

	private MeineTabelle tableBuchung;
	private TableBuchungs_Listener tbl;

	private JScrollPane scrollPane;;

	private JCalendar jc;

	/*
	 * Konstruktor benötigt den JCalendar vom Raumplanerview damit die
	 * möglichkeit besteht nach auswahl einer Zeile zum benötigten Datum zu
	 * springen
	 */
	public PanelBuchung(JCalendar jc) {
		try {
			this.jc = jc;
			ResultSet rs = SQL_Schnittstelle.getBuchungenZuGenehmigung();
			tableHeader = TabellenWerkzeug.getStringTableHeader(rs);
			tableData = TabellenWerkzeug.resultSetToTableStringArray(rs);
			tableModel = new DefaultTableModel(tableData, tableHeader);
			tableBuchung = new MeineTabelle(tableModel);

			tbl = new TableBuchungs_Listener(this);
			tableBuchung.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);
			tableBuchung.getSelectionModel().addListSelectionListener(tbl);
			setLayout(new BorderLayout());
			scrollPane = new JScrollPane(tableBuchung);
			add(scrollPane, BorderLayout.CENTER);
			setBorder(BorderFactory.createLineBorder(Color.black));
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelBuchung.PanelBuchung(JCalendar jc");

		}
	}

	// Methode um die Tabellendaten nach einer Veränderung neu zu laden
	public void reloadTableBuchung() {
		try {
			tableBuchung.getSelectionModel().removeListSelectionListener(tbl);
			remove(scrollPane);
			ResultSet rs = SQL_Schnittstelle.getBuchungenZuGenehmigung();
			tableData = TabellenWerkzeug.resultSetToTableStringArray(rs);

			tableModel = new DefaultTableModel(tableData, tableHeader);
			tableBuchung = new MeineTabelle(tableModel);

			tableModel.fireTableDataChanged();
			tableBuchung.getSelectionModel().addListSelectionListener(tbl);
			scrollPane = new JScrollPane(tableBuchung);
			add(scrollPane);
			validate();
			repaint();
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelBuchung.reloadTableBuchung()");

		}
	}

	/*
	 * Nach einer Auswahl in der Tabelle springt der Raumplaner_View automatisch
	 * zum ausgewählten Datum
	 */
	public void auswahlAnzeigenImRaumplaner_View() {
		Date date = new Date();
		try {
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
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelBuchung.auswahlAnzeigenImRaumplaner_View()");

		}
	}
}
