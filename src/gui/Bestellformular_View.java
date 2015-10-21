package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

public class Bestellformular_View extends JPanel {

	private JLabel raumLabel, nameLabel, bereichLabel, telLabel, datumLabel, zeitVonLabel, zeitBisLabel, personenLabel,
			technikLabel, ausstattungLabel, bestuhlungLabel;
	private JTextField telField;
	private JSpinField persField;
	private JDateChooser dateChooser;
	private JComboBox<String> bestuhlungCB, zeitVonStundeCB, zeitVonMinuteCB, zeitBisStundeCB, zeitBisMinuteCB;
	private JButton reservierenButton, abbrechenButton;
	private JTextArea sonstigeArea;
	private JCheckBox externCheck;
	private final String stunde[] = { "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" };
	private final String minute[] = { "00", "30" };
	private final String ausstattung[] = { "Flipchart", "Metaplanwand", "Leinwand" };
	private final String bestuhulung[] = { "", "U-Form", "Blockbildung", "Schulbanksystem/parlamentarische Bestuhlung",
			"Sonderbestuhlung" };
	private final String technik[] = { "Netzwerk (LAN)", "Beamer", "Moderatoren-Koffer", "Sonstige" };
	private String raumName;
	private JScrollPane sonstigeScroller, pane;
	private JFrame frame;

	public Bestellformular_View(JFrame frame) {
		// initView();
		this.frame = frame;
		this.setVisible(false);
	}

	public void initView() {
		this.setLayout(new GridLayout(1, 1));
		this.add(mainPanel());
	}

	private JPanel mainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(raumUndNamePanel());
		mainPanel.add(telPanel());
		mainPanel.add(datumPanel());
		mainPanel.add(zeitVonPanel());
		mainPanel.add(zeitBisPanel());
		mainPanel.add(personenPanel());
		mainPanel.add(inExPanel());
		mainPanel.add(technikPanel());
		mainPanel.add(ausstattungPanel());
		mainPanel.add(bestuhulungPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		mainPanel.add(buttonPanel());

		return mainPanel;
	}

	private JPanel raumUndNamePanel() {
		raumLabel = new JLabel(raumName);
		raumLabel.setPreferredSize(new Dimension(100, 30));

		nameLabel = new JLabel("Name");
		nameLabel.setPreferredSize(new Dimension(50, 30));

		bereichLabel = new JLabel("Bereich");
		bereichLabel.setPreferredSize(new Dimension(50, 30));

		JPanel raumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		raumPanel.add(raumLabel);

		JPanel namenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namenPanel.add(nameLabel);

		JPanel bereichPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bereichPanel.add(bereichLabel);

		JPanel nameBereichPanel = new JPanel();
		nameBereichPanel.setLayout(new GridLayout(1, 2));

		nameBereichPanel.add(namenPanel);
		nameBereichPanel.add(bereichPanel);

		JPanel raumNamePanel = new JPanel();
		raumNamePanel.setLayout(new GridLayout(2, 1));

		raumNamePanel.add(raumPanel);
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
		personenLabel.setPreferredSize(new Dimension(155, 30));

		persField = new JSpinField();
		persField.setMinimum(0);
		persField.setPreferredSize(new Dimension(100, 30));

		JPanel personenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		personenPanel.add(personenLabel);
		personenPanel.add(persField);

		return personenPanel;
	}

	private JPanel inExPanel() {
		externCheck = new JCheckBox("mit Gästen/Externen");

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(externCheck);

		return panel;
	}

	private JPanel technikPanel() {
		JPanel technikPanel = new JPanel(new BorderLayout());

		JPanel checkPanel = new JPanel();
		checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.PAGE_AXIS));

		checkPanel.add(Box.createVerticalGlue());
		checkPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		technikLabel = new JLabel("Technik: ");
		technikLabel.setPreferredSize(new Dimension(100, 30));
		labelPanel.add(technikLabel);

		for (String string : technik) {
			JCheckBox check = new JCheckBox(string);
			check.setPreferredSize(new Dimension(100, 30));
			check.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					JCheckBox check = (JCheckBox) e.getSource();
					String name = check.getText();

					if (name == "Sonstige" && check.isSelected()) {
						sonstigeArea.setVisible(true);
						sonstigeScroller.setVisible(true);
						frame.validate();
					} else {
						sonstigeArea.setVisible(false);
						sonstigeScroller.setVisible(false);
						frame.validate();
					}
				}
			});
			checkPanel.add(check);
		}

		sonstigeArea = new JTextArea(5, 20);
		sonstigeArea.setPreferredSize(new Dimension(100, 100));
		sonstigeArea.setLineWrap(true);
		sonstigeArea.setVisible(true);

		sonstigeScroller = new JScrollPane(sonstigeArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sonstigeScroller.setVisible(false);

		JPanel sonstigePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		sonstigePanel.add(sonstigeScroller);

		checkPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));

		technikPanel.add(labelPanel, BorderLayout.NORTH);
		technikPanel.add(checkPanel, BorderLayout.CENTER);
		technikPanel.add(sonstigePanel, BorderLayout.SOUTH);

		return technikPanel;
	}

	private JPanel ausstattungPanel() {
		JPanel ausstattungPanel = new JPanel(new BorderLayout());

		JPanel checkPanel = new JPanel(new GridLayout(ausstattung.length, 1));

		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		ausstattungLabel = new JLabel("Grundausstattung:");
		ausstattungLabel.setPreferredSize(new Dimension(110, 30));
		labelPanel.add(ausstattungLabel);

		for (String string : ausstattung) {
			JCheckBox check = new JCheckBox(string);
			check.setPreferredSize(new Dimension(100, 30));
			checkPanel.add(check);
		}

		checkPanel.setBorder(BorderFactory.createEmptyBorder(0, 110, 0, 0));

		ausstattungPanel.add(labelPanel, BorderLayout.NORTH);
		ausstattungPanel.add(checkPanel, BorderLayout.CENTER);

		return ausstattungPanel;
	}

	private JPanel bestuhulungPanel() {
		JPanel bestuhlungPanel = new JPanel(new BorderLayout());

		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		bestuhlungLabel = new JLabel("Bestuhlung:");
		bestuhlungLabel.setPreferredSize(new Dimension(100, 30));

		labelPanel.add(bestuhlungLabel);

		bestuhlungCB = new JComboBox<>(bestuhulung);
		bestuhlungCB.setPreferredSize(new Dimension(200, 30));

		JPanel cbPanel = new JPanel(new FlowLayout());
		cbPanel.add(bestuhlungCB);
		cbPanel.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0));

		bestuhlungPanel.add(labelPanel, BorderLayout.NORTH);
		bestuhlungPanel.add(cbPanel, BorderLayout.CENTER);

		return bestuhlungPanel;
	}

	private JPanel buttonPanel() {
		reservierenButton = new JButton("reservieren");
		reservierenButton.setPreferredSize(new Dimension(100, 30));
		reservierenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane option = new JOptionPane("Der Raum: " + raumName
				// + " wurde reserviert!", "Der Raum: " + raumName
				// + " wurde reserviert!", JOptionPane.OK_OPTION);

				getBestellformular().setVisible(false);
				pane.setVisible(false);
				frame.validate();
			}
		});

		abbrechenButton = new JButton("abbrechen");
		abbrechenButton.setPreferredSize(new Dimension(100, 30));
		abbrechenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getBestellformular().setVisible(false);
				pane.setVisible(false);
				frame.validate();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(reservierenButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(100, 0)));
		buttonPanel.add(abbrechenButton);

		return buttonPanel;
	}

	public void setRaumName(String name) {
		this.raumName = name;
	}

	private JPanel getBestellformular() {
		return this;
	}

	public void setScrollPane(JScrollPane pane) {
		this.pane = pane;
	}

	public void setDate(JCalendar cal) {
		dateChooser.setDate(cal.getDate());
	}

	public void setMaxPersonen(int max) {
		persField.setMaximum(max);
	}

	private void setBuchung() {
		String telefon = telField.getText();
		Date datum = new Date(dateChooser.getDate().getTime());
		Time zeitVon = Time
				.valueOf(zeitVonStundeCB.getSelectedItem() + ":" + zeitVonMinuteCB.getSelectedItem() + ":00");
		Time zeitBis = Time
				.valueOf(zeitBisStundeCB.getSelectedItem() + ":" + zeitBisMinuteCB.getSelectedItem() + ":00");
		String kommentar = sonstigeArea.getText();
		String bestuhlung = String.valueOf(bestuhlungCB.getSelectedItem());
	}
}
