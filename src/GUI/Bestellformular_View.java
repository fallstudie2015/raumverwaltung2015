package GUI;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Bestellformular_View extends JSplitPane {

	private JLabel raumLabel, namelabel, bereichLabel, telLabel, DatumLabel, zeitVonLabel, zeitBisLabel, personenLabel,
			technikLabel, bestuhlungLabel;
	private JTextField telField, personenField;
	private JComboBox<Integer> datumTagCB, datumMonatCB, datumJahrCB, zeitVonStundeCB, zeitVonMinuteCB,
			zeitBisStundeCB, zeitBisMinuteCB;
	private JComboBox<String> bestuhlungCB, ausstattungCB;
	private JRadioButton internRB, externRB;
	private JButton vormerkenButton, abbrechenButton, addButton;
	private JTextArea sonstigeArea;

	public Bestellformular_View() {
		initView();
	}

	private void initView() {

	}

}
