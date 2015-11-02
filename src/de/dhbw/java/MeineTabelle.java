/* Programmiert von: Kai Kleefisch
 * Programmiert für: Alle in der gui angezeigten Tabellen
 * Beschreibung: Die Klasse kann verwendet werden, um allen in der gui angezeigten Tabellen funktionen hinzuzufügen oder zu entfernen 
 */

package de.dhbw.java;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MeineTabelle extends JTable {

	// Konstruktor bekommt das Model übergeben und gibt es weiter an
	// Mutter-Klasse
	public MeineTabelle(DefaultTableModel model) {
		super(model);
		getTableHeader().setReorderingAllowed(false);
		setAutoCreateRowSorter(true);
		getColumnModel().getColumn(0).setMinWidth(0);
		getColumnModel().getColumn(0).setMaxWidth(0);
		getColumnModel().getColumn(0).setWidth(0);

	}

	// Methode um festzulegen welche Zellen bearbeitet werden können
	public boolean isCellEditable(int row, int col) {
		// if (col == 0 || col == 1 || col == 2) {
		// return true;
		// }
		return false;
	}
};
