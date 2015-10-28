package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		ArrayList<Buchung> al = SQL_Schnittstelle
				.getAlleVorgemerktenBuchungen();

		String[][] tableData = new String[al.size()][3];
		for (int i = 0; i < al.size(); i++) {
			tableData[i][0] = al.get(i).getDatum().toString();
			tableData[i][1] = String.valueOf(SQL_Schnittstelle.getRaumName(al
					.get(i).getRaumID()));
			tableData[i][2] = String.valueOf(SQL_Schnittstelle
					.getBenutzerName(al.get(i).getBenutzerID()));
		}

		return tableData;

	}

	public void reloadTableBuchung() {
		dataBuchung = buchungBestellerListeToTableStringArray();
		buchungBestellerModel.fireTableDataChanged();
	}

	public void auswahlAnzeigen() {
		Date date = new Date();
		System.out.println(tableBuchung.getValueAt(
				tableBuchung.getSelectedRow(), 0));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {
			date = df.parse(String.valueOf(tableBuchung.getValueAt(
					tableBuchung.getSelectedRow(), 0)));
			System.out.println("erfolgreich");
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("nicht erfolgreich");
			e.printStackTrace();
		}

		jc.setDate(date);
	}
}