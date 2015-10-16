package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

public class Bestellformular_View extends JSplitPane {

	private JLabel raumLabel, namelabel, bereichLabel, telLabel, datumLabel,
			zeitVonLabel, zeitBisLabel, personenLabel, technikLabel,
			bestuhlungLabel;
	private JTextField telField;
	private JSpinField persField;
	private JDateChooser dateChooser;
	private JComboBox datumTagCB, datumMonatCB, datumJahrCB;
	private JComboBox<String> bestuhlungCB, ausstattungCB, zeitVonStundeCB,
			zeitVonMinuteCB, zeitBisStundeCB, zeitBisMinuteCB;
	private JRadioButton internRB, externRB;
	private JButton vormerkenButton, abbrechenButton, addButton;
	private JTextArea sonstigeArea;
	private final String stunde[] = { "8", "9", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19" };
	private final String minute[] = { "00", "05", "10", "15", "20", "25", "30",
			"35", "40", "45", "50", "55" };
	private JScrollPane scroller;
	private String raumName;

	public Bestellformular_View() {
		this.setDividerLocation(JSplitPane.VERTICAL_SPLIT);
		// initView();
		setVisible(false);
		setPreferredSize(new Dimension(325, 1000));
	}

	public void initView() {
		this.setLayout(new BorderLayout());
		this.add(mainPanel(), BorderLayout.CENTER);
	}

	private JPanel mainPanel() {
		JPanel mainpanel = new JPanel(new GridLayout(6, 1));

		mainpanel.add(raumUndNamePanel());
		mainpanel.add(telPanel());
		mainpanel.add(datumPanel());
		mainpanel.add(zeitVonPanel());
		mainpanel.add(zeitBisPanel());
		mainpanel.add(personenPanel());

		scroller = new JScrollPane(mainpanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.getVerticalScrollBar().setUnitIncrement(16);
		scroller.setBorder(BorderFactory.createEmptyBorder());

		JPanel scroll = new JPanel();
		scroll.add(scroller);

		return scroll;
	}

	private JPanel raumUndNamePanel() {
		raumLabel = new JLabel(raumName);
		raumLabel.setPreferredSize(new Dimension(100, 30));

		namelabel = new JLabel("Name");
		namelabel.setPreferredSize(new Dimension(50, 30));

		bereichLabel = new JLabel("Bereich");
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
		telLabel = new JLabel("Tel. :");
		telLabel.setPreferredSize(new Dimension(50, 30));

		telField = new JTextField();
		telField.setPreferredSize(new Dimension(200, 30));

		JPanel telPanel = new JPanel();
		telPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		telPanel.add(telLabel);
		telPanel.add(telField);

		return telPanel;
	}

	private JPanel datumPanel() {
		datumLabel = new JLabel("Datum: ");
		datumLabel.setPreferredSize(new Dimension(50, 30));

		dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(200, 30));

		JPanel datumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		datumPanel.add(datumLabel);
		datumPanel.add(dateChooser);

		return datumPanel;
	}

	private JPanel zeitVonPanel() {
		zeitVonLabel = new JLabel("Zeit von:");
		zeitVonLabel.setPreferredSize(new Dimension(100, 30));

		zeitVonStundeCB = new JComboBox<String>(stunde);
		zeitVonStundeCB.setPreferredSize(new Dimension(50, 25));

		zeitVonMinuteCB = new JComboBox<String>(minute);
		zeitVonMinuteCB.setPreferredSize(new Dimension(50, 25));

		JPanel minUndStdPanel = new JPanel();
		minUndStdPanel.setLayout(new FlowLayout());

		minUndStdPanel.add(zeitVonStundeCB);
		minUndStdPanel.add(zeitVonMinuteCB);

		JPanel zeitLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		zeitLabelPanel.add(zeitVonLabel);

		JPanel zeitVonPanel = new JPanel();
		zeitVonPanel.setLayout(new BorderLayout());

		zeitVonPanel.add(zeitLabelPanel, BorderLayout.NORTH);
		zeitVonPanel.add(minUndStdPanel, BorderLayout.CENTER);

		return zeitVonPanel;
	}

	private JPanel zeitBisPanel() {
		zeitBisLabel = new JLabel("bis:");
		zeitBisLabel.setPreferredSize(new Dimension(100, 30));

		zeitBisStundeCB = new JComboBox<String>(stunde);
		zeitBisStundeCB.setPreferredSize(new Dimension(50, 25));

		zeitBisMinuteCB = new JComboBox<String>(minute);
		zeitBisMinuteCB.setPreferredSize(new Dimension(50, 25));
		zeitBisMinuteCB.setEnabled(true);

		JPanel minUndStdPanel = new JPanel();
		minUndStdPanel.setLayout(new FlowLayout());

		minUndStdPanel.add(zeitBisStundeCB);
		minUndStdPanel.add(zeitBisMinuteCB);

		JPanel zeitLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		zeitLabelPanel.add(zeitBisLabel);

		JPanel zeitBisPanel = new JPanel();
		zeitBisPanel.setLayout(new BorderLayout());

		zeitBisPanel.add(zeitLabelPanel, BorderLayout.NORTH);
		zeitBisPanel.add(minUndStdPanel, BorderLayout.CENTER);

		return zeitBisPanel;
	}

	private JPanel personenPanel() {
		personenLabel = new JLabel("Anzahl Personen: ");
		personenLabel.setPreferredSize(new Dimension(205, 30));

		persField = new JSpinField();
		persField.setMinimum(0);
		persField.setPreferredSize(new Dimension(50, 30));

		JPanel personenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		personenPanel.add(personenLabel);
		personenPanel.add(persField);

		return personenPanel;
	}

	public void setRaumName(String name) {
		this.raumName = name;
	}
}
