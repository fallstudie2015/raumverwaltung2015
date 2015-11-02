/* Programmiert von: Kai Kleefisch
 * Programmiert für: Alle Panels mit Tabellen
 * Beschreibung: Ausgelagerter Code der mehrfach vorgekommen ist
 */
package de.dhbw.java;

import java.sql.ResultSet;

import de.dhbw.gui.Error_Message_Box;

public abstract class TabellenWerkzeug {

	// Erstellung der Spaltenbezeichnung aus einem ResultSet
	public static String[] getStringTableHeader(ResultSet rs) {
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

	// Erstellung der Tabellendaten aus einem ResultSet
	public static String[][] resultSetToTableStringArray(ResultSet rs) {
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
						if (rs.getString(i).contains(":")) {
							tableData[rs.getRow() - 1][i - 1] = rs.getString(i)
									.substring(0, rs.getString(i).length() - 3);
						} else {
							tableData[rs.getRow() - 1][i - 1] = rs.getString(i);
						}
					}
				}
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e,
					"gui.PanelBuchung.buchungBestellerListeToTableStringArray");
		}

		return tableData;
	}

}
