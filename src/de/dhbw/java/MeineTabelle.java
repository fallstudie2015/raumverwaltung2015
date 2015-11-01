package de.dhbw.java;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MeineTabelle extends JTable {

	public MeineTabelle(DefaultTableModel model) {
		super(model);
	}

	// Methode um festzulegen welche Zellen bearbeitet werden können
	public boolean isCellEditable(int row, int col) {
		// if (col == 0 || col == 1 || col == 2) {
		// return true;
		// }
		return false;
	}
};