package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Bestellformular_View extends JSplitPane {

	private JLabel raumLabel, namelabel, bereichLabel, telLabel, DatumLabel,
			zeitVonLabel, zeitBisLabel, personenLabel, technikLabel,
			bestuhlungLabel;
	private JTextField telField, personenField;
	private JComboBox datumTagCB, datumMonatCB, datumJahrCB, zeitVonStundeCB,
			zeitVonMinuteCB, zeitBisStundeCB, zeitBisMinuteCB;
	private JComboBox<String> bestuhlungCB, ausstattungCB;
	private JRadioButton internRB, externRB;
	private JButton vormerkenButton, abbrechenButton, addButton;
	private JTextArea sonstigeArea;
	private String stunde[] = { "8", "9", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19" };
	private String minute[] = { "00", "05", "10", "15", "20", "25", "30", "35",
			"40", "45", "50", "55" };

	public Bestellformular_View() {
		initView();
	}

	private void initView() {

	}

	private JPanel raumUndNamePanel() {
		raumLabel = new JLabel();
		raumLabel.setPreferredSize(new Dimension(100, 30));

		namelabel = new JLabel();
		namelabel.setPreferredSize(new Dimension(50, 30));

		bereichLabel = new JLabel();
		bereichLabel.setPreferredSize(new Dimension(50, 30));

		JPanel nameBereichPanel = new JPanel();
		nameBereichPanel.setLayout(new GridLayout(1, 2));

		nameBereichPanel.add(namelabel);
		nameBereichPanel.add(bereichLabel);

		JPanel raumNamePanel = new JPanel();
		raumNamePanel.setLayout(new GridLayout(2, 1));

		raumNamePanel.add(raumLabel);
		raumNamePanel.add(nameBereichPanel);

		return raumNamePanel;
	}

	private JPanel telPanel() {
		telLabel = new JLabel();
		telLabel.setPreferredSize(new Dimension(50, 30));

		telField = new JTextField();
		telField.setPreferredSize(new Dimension(100, 30));

		JPanel telPanel = new JPanel();
		telPanel.setLayout(new GridLayout(1, 2));

		telPanel.add(telLabel);
		telPanel.add(telField);

		return telPanel;
	}

	private JPanel datumPanel() {

	}

	private JPanel zeitVonPanel() {
		zeitVonLabel = new JLabel("Zeit von:");
		zeitVonLabel.setPreferredSize(new Dimension(100, 30));

		zeitVonStundeCB = new JComboBox(stunde);
		zeitVonMinuteCB = new JComboBox(minute);

		JPanel minUndStdPanel = new JPanel();
		minUndStdPanel.setLayout(new GridLayout(1, 2));

		minUndStdPanel.add(zeitVonStundeCB);
		minUndStdPanel.add(zeitVonMinuteCB);

		JPanel zeitLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		zeitLabelPanel.add(zeitVonLabel);

		JPanel zeitVonPanel = new JPanel();
		zeitVonPanel.setLayout(new BorderLayout());

		zeitVonPanel.add(zeitLabelPanel);
		zeitVonPanel.add(zeitLabelPanel);

		return zeitVonPanel;
	}

	private JPanel zeitBisPanel() {
		zeitBisLabel = new JLabel("bis:");
		zeitBisLabel.setPreferredSize(new Dimension(100, 30));

		zeitBisStundeCB = new JComboBox(stunde);
		zeitBisMinuteCB = new JComboBox(minute);

		JPanel minUndStdPanel = new JPanel();
		minUndStdPanel.setLayout(new GridLayout(1, 2));

		minUndStdPanel.add(zeitBisStundeCB);
		minUndStdPanel.add(zeitBisMinuteCB);

		JPanel zeitLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		zeitLabelPanel.add(zeitBisLabel);

		JPanel zeitBisPanel = new JPanel();
		zeitBisPanel.setLayout(new BorderLayout());

		zeitBisPanel.add(zeitLabelPanel);
		zeitBisPanel.add(zeitLabelPanel);

		return zeitBisPanel;
	}
}
