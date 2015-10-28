package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import de.dhbw.java.Ausstattung;
import de.dhbw.java.Benutzer;
import de.dhbw.java.SQL_Schnittstelle;

public class Bestellformular_View extends JPanel {

	private JLabel raumLabel, nameLabel, bereichLabel, telLabel, datumLabel, zeitVonLabel, zeitBisLabel, personenLabel,
			technikLabel, ausstattungLabel, bestuhlungLabel;
	private JTextField telField;
	private JSpinField persField;
	private JDateChooser dateChooser;
	private java.util.Date oldDate;
	private JComboBox<String> bestuhlungCB, zeitVonStundeCB, zeitVonMinuteCB, zeitBisStundeCB, zeitBisMinuteCB;
	private JButton reservierenButton, abbrechenButton;
	private JTextArea sonstigeArea;
	private JCheckBox externCheck;
	private final String stundeVon[] = { "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18" };
	private final String stundeBis[] = { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" };
	private final String minute[] = { "00", "15", "30", "45" };
	private Ausstattung ausstattung[];
	private final String bestuhulung[] = { "", "U-Form", "Blockbildung", "Schulbanksystem/parlamentarische Bestuhlung",
			"Sonderbestuhlung" };
	private Ausstattung technik[];
	private String raumName;
	private JScrollPane sonstigeScroller, pane;
	private JFrame frame;
	private String nutzerVorname, nutzerNachname, nutzerBereich;
	private int raumId;
	private ArrayList<String> ausstattungList;
	private PanelBuchung panelBuchung;
	private Raum_View raum;

	public Bestellformular_View(JFrame frame, String name, String nachname, int raumId, PanelBuchung panel,
			String bereich, Raum_View rv) {
		// initView();
		this.raumId = raumId;
		this.raum = rv;
		this.panelBuchung = panel;
		this.nutzerVorname = name;
		this.nutzerNachname = nachname;
		this.nutzerBereich = bereich;
		this.frame = frame;
		ausstattungList = new ArrayList<String>();
		this.setVisible(false);
	}

	public void initView() {
		this.setLayout(new GridLayout(1, 1));
		this.add(mainPanel());
		setChooser();
	}

	private JPanel mainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(raumUndNamePanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(telPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(datumPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(zeitVonPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(zeitBisPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(personenPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(inExPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(ausstattungPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(technikPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(bestuhulungPanel());
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		mainPanel.add(buttonPanel());

		return mainPanel;
	}

	private JPanel raumUndNamePanel() {
		raumLabel = new JLabel(raumName);
		raumLabel.setPreferredSize(new Dimension(100, 30));

		nameLabel = new JLabel(nutzerVorname + " " + nutzerNachname);
		nameLabel.setPreferredSize(new Dimension(125, 30));

		bereichLabel = new JLabel(nutzerBereich);
		bereichLabel.setPreferredSize(new Dimension(50, 30));

		JPanel raumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		raumPanel.add(raumLabel);

		JPanel namenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namenPanel.add(nameLabel);

		JPanel bereichPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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

		JPanel feldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		feldPanel.add(telField);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(telPanel, BorderLayout.WEST);
		panel.add(feldPanel, BorderLayout.EAST);

		return panel;
	}

	private JPanel datumPanel() {
		datumLabel = new JLabel("Datum: ");
		datumLabel.setPreferredSize(new Dimension(50, 30));

		dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(200, 30));

		JPanel chooserPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		chooserPanel.add(dateChooser);

		JPanel datumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		datumPanel.add(datumLabel);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(datumPanel, BorderLayout.WEST);
		panel.add(chooserPanel, BorderLayout.EAST);

		return panel;
	}

	private JPanel zeitVonPanel() {
		zeitVonLabel = new JLabel("Zeit von:");
		zeitVonLabel.setPreferredSize(new Dimension(100, 30));

		ItemListener il = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (!SQL_Schnittstelle.pruefeBuchungskonflikt(raumName, new Date(dateChooser.getDate().getTime()),
						Time.valueOf(
								zeitVonStundeCB.getSelectedItem() + ":" + zeitVonMinuteCB.getSelectedItem() + ":00"),
						Time.valueOf(
								zeitBisStundeCB.getSelectedItem() + ":" + zeitBisMinuteCB.getSelectedItem() + ":00"))) {
					reservierenButton.setEnabled(false);
				} else {
					reservierenButton.setEnabled(true);
				}
			}
		};

		zeitVonStundeCB = new JComboBox<String>(stundeVon);
		zeitVonStundeCB.setPreferredSize(new Dimension(50, 25));
		zeitVonStundeCB.addItemListener(il);

		zeitVonMinuteCB = new JComboBox<String>(minute);
		zeitVonMinuteCB.setPreferredSize(new Dimension(50, 25));
		zeitVonMinuteCB.addItemListener(il);

		JPanel minUndStdPanel = new JPanel();
		minUndStdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		minUndStdPanel.add(zeitVonStundeCB);
		minUndStdPanel.add(zeitVonMinuteCB);

		JPanel zeitLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		zeitLabelPanel.add(zeitVonLabel);

		JPanel zeitVonPanel = new JPanel();
		zeitVonPanel.setLayout(new BorderLayout());

		zeitVonPanel.add(zeitLabelPanel, BorderLayout.WEST);
		zeitVonPanel.add(minUndStdPanel, BorderLayout.EAST);

		return zeitVonPanel;
	}

	private JPanel zeitBisPanel() {
		zeitBisLabel = new JLabel("bis:");
		zeitBisLabel.setPreferredSize(new Dimension(100, 30));

		ItemListener il = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (!SQL_Schnittstelle.pruefeBuchungskonflikt(raumName, new Date(dateChooser.getDate().getTime()),
						Time.valueOf(
								zeitVonStundeCB.getSelectedItem() + ":" + zeitVonMinuteCB.getSelectedItem() + ":00"),
						Time.valueOf(
								zeitBisStundeCB.getSelectedItem() + ":" + zeitBisMinuteCB.getSelectedItem() + ":00"))) {
					reservierenButton.setEnabled(false);
				} else {
					reservierenButton.setEnabled(true);
				}
			}
		};

		zeitBisStundeCB = new JComboBox<String>(stundeBis);
		zeitBisStundeCB.setPreferredSize(new Dimension(50, 25));
		zeitBisStundeCB.addItemListener(il);

		zeitBisMinuteCB = new JComboBox<String>(minute);
		zeitBisMinuteCB.setPreferredSize(new Dimension(50, 25));
		zeitBisMinuteCB.addItemListener(il);

		JPanel minUndStdPanel = new JPanel();
		minUndStdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		minUndStdPanel.add(zeitBisStundeCB);
		minUndStdPanel.add(zeitBisMinuteCB);

		JPanel zeitLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		zeitLabelPanel.add(zeitBisLabel);

		JPanel zeitBisPanel = new JPanel();
		zeitBisPanel.setLayout(new BorderLayout());

		zeitBisPanel.add(zeitLabelPanel, BorderLayout.WEST);
		zeitBisPanel.add(minUndStdPanel, BorderLayout.EAST);

		return zeitBisPanel;
	}

	private JPanel personenPanel() {
		personenLabel = new JLabel("Anzahl Personen: ");
		personenLabel.setPreferredSize(new Dimension(155, 30));

		persField = new JSpinField();
		persField.setMinimum(0);
		persField.setPreferredSize(new Dimension(105, 30));

		JPanel personenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		personenPanel.add(personenLabel);

		JPanel feldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		feldPanel.add(persField);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(personenPanel, BorderLayout.WEST);
		panel.add(feldPanel, BorderLayout.EAST);

		return panel;
	}

	private JPanel inExPanel() {
		externCheck = new JCheckBox("mit G\u00E4sten/Externen");
		externCheck.setPreferredSize(new Dimension(140, 30));

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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

		technikLabel = new JLabel("Zusatzausstattung: ");
		technikLabel.setPreferredSize(new Dimension(150, 30));
		labelPanel.add(technikLabel);

		for (Ausstattung string : technik) {
			JCheckBox check = new JCheckBox(string.getBezeichnung());
			check.setPreferredSize(new Dimension(140, 30));
			check.setToolTipText(string.getBezeichnung());
			check.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					JCheckBox check = (JCheckBox) e.getSource();
					String name = check.getText();

					if (name == "Sonstige" && check.isSelected()) {
						sonstigeArea.setVisible(true);
						sonstigeScroller.setVisible(true);
						frame.validate();
					} else if (name == "Sonstige" && !check.isSelected()) {
						sonstigeArea.setVisible(false);
						sonstigeScroller.setVisible(false);
						frame.validate();
					}

					if (check.isSelected()) {
						ausstattungList.add(name);
					} else {
						ausstattungList.remove(name);
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
		sonstigeScroller.setPreferredSize(new Dimension(200, 100));
		sonstigeScroller.setVisible(false);

		JPanel sonstigePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		sonstigePanel.add(sonstigeScroller);

		checkPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(checkPanel);

		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel2.add(sonstigePanel);

		technikPanel.add(labelPanel, BorderLayout.NORTH);
		technikPanel.add(panel, BorderLayout.CENTER);
		technikPanel.add(panel2, BorderLayout.PAGE_END);

		return technikPanel;
	}

	private JPanel ausstattungPanel() {
		JPanel ausstattungPanel = new JPanel(new BorderLayout());

		JPanel checkPanel = new JPanel(new GridLayout(ausstattung.length, 1));

		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		ausstattungLabel = new JLabel("Raumausstattung:");
		ausstattungLabel.setPreferredSize(new Dimension(110, 30));
		labelPanel.add(ausstattungLabel);

		for (Ausstattung string : ausstattung) {
			JCheckBox check = new JCheckBox(string.getBezeichnung(), true);
			check.setPreferredSize(new Dimension(140, 30));
			check.setEnabled(false);
			check.setToolTipText(string.getBezeichnung());
			checkPanel.add(check);
		}

		checkPanel.setBorder(BorderFactory.createEmptyBorder(0, 110, 0, 0));

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		panel.add(checkPanel);

		ausstattungPanel.add(labelPanel, BorderLayout.NORTH);
		ausstattungPanel.add(panel, BorderLayout.CENTER);

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

		JPanel cbPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cbPanel.add(bestuhlungCB);
		// cbPanel.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0));

		bestuhlungPanel.add(labelPanel, BorderLayout.WEST);
		bestuhlungPanel.add(cbPanel, BorderLayout.EAST);

		return bestuhlungPanel;
	}

	private JPanel buttonPanel() {
		reservierenButton = new JButton("reservieren");
		reservierenButton.setPreferredSize(new Dimension(100, 30));
		reservierenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (setBuchung()) {
					raum.setBuchungArray(SQL_Schnittstelle.getBuchung());
				} else {
					JOptionPane.showMessageDialog(null, "Es ist ein Fehler bei der Reservierung aufgerteten!");
				}
				ausstattungList.clear();
				getBestellformular().setVisible(false);
				pane.setVisible(false);

				frame.validate();
			}
		});

		JPanel rbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rbPanel.add(reservierenButton);

		abbrechenButton = new JButton("abbrechen");
		abbrechenButton.setPreferredSize(new Dimension(100, 30));
		abbrechenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getBestellformular().setVisible(false);
				pane.setVisible(false);
				if (Benutzer.getBenutzertyp() == 'v') {
					panelBuchung.setVisible(true);
				}
				frame.validate();
			}
		});

		JPanel abPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		abPanel.add(abbrechenButton);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());

		buttonPanel.add(rbPanel, BorderLayout.WEST);
		buttonPanel.add(abPanel, BorderLayout.EAST);

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
		oldDate = dateChooser.getDate();
	}

	public void setMaxPersonen(int max) {
		persField.setMaximum(max);
	}

	public void setZeitCB(String hr, String min) {
		zeitVonMinuteCB.setSelectedItem(min);
		zeitVonStundeCB.setSelectedItem(hr);

		if (hr.equals("19")) {
			zeitVonMinuteCB.setSelectedItem("45");
			zeitVonStundeCB.setSelectedItem("18");

			zeitBisMinuteCB.setSelectedIndex(0);
			zeitBisStundeCB.setSelectedItem(hr);
		} else {
			if (min.equals("45")) {
				zeitBisMinuteCB.setSelectedIndex(0);
				zeitBisStundeCB.setSelectedIndex(zeitVonStundeCB.getSelectedIndex());
			} else {
				zeitBisMinuteCB.setSelectedIndex(zeitVonMinuteCB.getSelectedIndex() + 1);
				zeitBisStundeCB.setSelectedItem(hr);
			}
		}
	}

	public void setTechnik(ArrayList<Ausstattung> list) {
		list.add(new Ausstattung(100, "Sonstige"));
		technik = new Ausstattung[list.size()];
		technik = list.toArray(technik);
	}

	public void setGrundausstattung(ArrayList<Ausstattung> list) {
		ausstattung = new Ausstattung[list.size()];
		ausstattung = list.toArray(ausstattung);
	}

	private boolean setBuchung() {
		String telefon = telField.getText();
		Date datum = new Date(dateChooser.getDate().getTime());
		Time zeitVon = Time
				.valueOf(zeitVonStundeCB.getSelectedItem() + ":" + zeitVonMinuteCB.getSelectedItem() + ":00");
		Time zeitBis = Time
				.valueOf(zeitBisStundeCB.getSelectedItem() + ":" + zeitBisMinuteCB.getSelectedItem() + ":00");
		String kommentar = sonstigeArea.getText();
		String bestuhlung = String.valueOf(bestuhlungCB.getSelectedItem());
		int benutzerId = Benutzer.getBenutzerID();
		int anzPersonen = persField.getValue();
		boolean externeTeilnehmer = externCheck.isSelected();

		return SQL_Schnittstelle.insertBuchung(telefon, datum, zeitVon, zeitBis, kommentar, bestuhlung, benutzerId,
				raumId, 'v', anzPersonen, ausstattungList, externeTeilnehmer);
	}

	private void setChooser() {
		PropertyChangeListener pc = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (oldDate != dateChooser.getDate()) {
					if (!SQL_Schnittstelle.pruefeBuchungskonflikt(raumName, new Date(dateChooser.getDate().getTime()),
							Time.valueOf(zeitVonStundeCB.getSelectedItem() + ":" + zeitVonMinuteCB.getSelectedItem()
									+ ":00"),
							Time.valueOf(zeitBisStundeCB.getSelectedItem() + ":" + zeitBisMinuteCB.getSelectedItem()
									+ ":00"))) {
						reservierenButton.setEnabled(false);
					} else {
						reservierenButton.setEnabled(true);
					}
				}
			}
		};

		dateChooser.addPropertyChangeListener(pc);
	}
}
