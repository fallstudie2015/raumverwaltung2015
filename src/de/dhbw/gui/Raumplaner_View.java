/* Programmiert von: Tim Deisser
 * Programmiert für: Die Hauptoberfläche
 * Beschreibung: Dient zur Anzeige der Hauptansicht für den Besteller, sowie die modifizierte Ansicht für den Verwalter
 */

package de.dhbw.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import de.dhbw.gui.externeFrames.AusstattungAnlegen;
import de.dhbw.gui.externeFrames.AusstattungLoeschen;
import de.dhbw.gui.externeFrames.BenutzerAnlegen;
import de.dhbw.gui.externeFrames.BenutzerLoeschen;
import de.dhbw.gui.externeFrames.Logout;
import de.dhbw.gui.externeFrames.PasswortAendern;
import de.dhbw.gui.externeFrames.RaumAnlegen;
import de.dhbw.gui.externeFrames.RaumLoeschen;
import de.dhbw.java.Ausstattung;
import de.dhbw.java.Benutzer;
import de.dhbw.java.Buchung;
import de.dhbw.java.BuchungPlus;
import de.dhbw.java.Raum;
import de.dhbw.java.SQL_Schnittstelle;

public class Raumplaner_View extends JFrame {

	public Raumplaner_View diese = this;
	private JCalendar calendar;
	private JPanel bvPanel, onScrollPanel, port;
	private JLabel nameLabel, bereichLabel, logoLabel, raumplanerLabel, raumLabel, benutzerLabel, ausstattungLabel;
	private JButton refreshButton, logoutButton, passwortChangeButton, raumAddButton, raumDeleteButton,
			benutzerAddButton, benutzerDeleteButton, ausstattungAddButton, ausstattungDeleteButton, antragsButton;
	private JScrollPane scroller, formularScroller;
	private ArrayList<Bestellformular_View> bvList;
	private ArrayList<Raum> raumList;
	private ArrayList<BuchungPlus> buchungList;
	private ArrayList<Raum_View> raumViewList;
	private Date choosenDate;
	private TappedPaneBuchung panelBuchung;

	public Raumplaner_View() {
		initView();
	}

	/*
	 * Konstruktor initialisiert die Ansicht
	 */
	public Raumplaner_View(ArrayList<Raum> raumList, ArrayList<Buchung> buchungList) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Raumplaner_View.class.getResource("/ressources/Desktop_Statusbar_icon.png")));
		this.bvPanel = new JPanel(new FlowLayout());
		this.onScrollPanel = new JPanel(new FlowLayout());
		this.port = new JPanel(new FlowLayout());
		this.raumList = raumList;
		this.buchungList = SQL_Schnittstelle.getBuchungPlus();
		raumViewList = new ArrayList<Raum_View>();
		initView();
		this.choosenDate = new Date(calendar.getDate().getTime());

		/*
		 * Listener ändert die Ansicht, sobald ein neues Datum im Kalendar
		 * ausgewählt wurde
		 */
		calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				lookAfterCalendar();
			}
		});
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				lookAfterCalendar();
			}
		});
	}

	/*
	 * Größe der Ansicht wird festgelegt und alle erforderlichen Panels werden
	 * geladen
	 */
	private void initView() {
		setLayout(new BorderLayout());
		getContentPane().add(mainPanel(), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Raumplaner v.1.0");
		setMinimumSize(new Dimension(1024, 786));
		setMaximumSize(new Dimension(1920, 1080));
		setExtendedState(MAXIMIZED_BOTH);
		setBestellerView();
	}

	/*
	 * Die obersete Leiste mit den zwei Logos werden initialisiert und
	 * angeordnet
	 */
	private JPanel logoPanel() {
		ImageIcon ii1 = new ImageIcon(getClass().getClassLoader().getResource("ressources/logo_2.png"));
		ImageIcon imageIcon = new ImageIcon(ii1.getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT));
		logoLabel = new JLabel(imageIcon, SwingConstants.LEFT);
		logoLabel.setPreferredSize(new Dimension(300, 150));

		ImageIcon ii2 = new ImageIcon(getClass().getClassLoader().getResource("ressources/Schrift.png"));
		ImageIcon imageIcon2 = new ImageIcon(ii2.getImage().getScaledInstance(700, 150, Image.SCALE_DEFAULT));
		raumplanerLabel = new JLabel(imageIcon2, SwingConstants.LEFT);
		raumplanerLabel.setPreferredSize(new Dimension(700, 150));

		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(new BorderLayout());
		// logoPanel.add(raumplanerLabel, BorderLayout.EAST);
		logoPanel.add(logoLabel, BorderLayout.WEST);

		return logoPanel;
	}

	/*
	 * Panel enthält alle Buttons, welche der Verwalter zusätzlich hat.
	 */
	private JPanel buttonPanel() {
		ImageIcon iiAddButton = new ImageIcon(
				getClass().getClassLoader().getResource("ressources/menu_benutzer_anlegen_transp.png"));
		ImageIcon imageIconAddButton = new ImageIcon(
				iiAddButton.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		benutzerAddButton = new JButton(imageIconAddButton);
		benutzerAddButton.setPreferredSize(new Dimension(50, 50));
		benutzerAddButton.setToolTipText("Benutzer anlegen");

		// Öffnet die Ansicht um einen Benutzer anzulegen
		benutzerAddButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BenutzerAnlegen ba = new BenutzerAnlegen();
				ba.setVisible(true);

			}
		});

		ImageIcon iiDeleteButton = new ImageIcon(
				getClass().getClassLoader().getResource("ressources/menu_benutzer_loeschen_transp.png"));
		ImageIcon imageIconDeleteButton = new ImageIcon(
				iiDeleteButton.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		benutzerDeleteButton = new JButton(imageIconDeleteButton);
		benutzerDeleteButton.setPreferredSize(new Dimension(50, 50));
		benutzerDeleteButton.setToolTipText("Benutzer löschen");

		// Öffnet die Ansicht um einen Benutzer zu löschen
		benutzerDeleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BenutzerLoeschen bl = new BenutzerLoeschen();
				bl.setVisible(true);
			}
		});

		ImageIcon iiRaumButton = new ImageIcon(
				getClass().getClassLoader().getResource("ressources/menu_raum_anlegen_transp.png"));
		ImageIcon imageIconRaumButton = new ImageIcon(
				iiRaumButton.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		raumAddButton = new JButton(imageIconRaumButton);
		raumAddButton.setPreferredSize(new Dimension(50, 50));
		raumAddButton.setToolTipText("Raum anlegen");

		// Öffnet die Ansicht um einen Raum anzulegen
		raumAddButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RaumAnlegen ra = new RaumAnlegen(getRV());
				ra.setVisible(true);
			}
		});

		ImageIcon iiRaumDeleteButton = new ImageIcon(
				getClass().getClassLoader().getResource("ressources/menu_raum_loeschen_transp.png"));
		ImageIcon imageIconRaumDeleteButton = new ImageIcon(
				iiRaumDeleteButton.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		raumDeleteButton = new JButton(imageIconRaumDeleteButton);
		raumDeleteButton.setPreferredSize(new Dimension(50, 50));
		raumDeleteButton.setToolTipText("Raum löschen");

		// Öffnet die Ansicht um einen Raum zu löschen
		raumDeleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RaumLoeschen rl = new RaumLoeschen(getRV());
				rl.setVisible(true);
			}
		});

		ImageIcon iiAusButton = new ImageIcon(
				getClass().getClassLoader().getResource("ressources/menu_ausstattung_anlegen_transp.png"));
		ImageIcon imageIconAusButton = new ImageIcon(
				iiAusButton.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ausstattungAddButton = new JButton(imageIconAusButton);
		ausstattungAddButton.setPreferredSize(new Dimension(50, 50));
		ausstattungAddButton.setToolTipText("Ausstattung anlegen");

		// Öffnet die Ansicht um eine Ausstattung anzulegen
		ausstattungAddButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AusstattungAnlegen aa = new AusstattungAnlegen(diese);
				aa.setVisible(true);
			}
		});

		ImageIcon iiAusDeleteButton = new ImageIcon(
				getClass().getClassLoader().getResource("ressources/menu_ausstattung_loeschen_transp.png"));
		ImageIcon imageIconAusDeleteButton = new ImageIcon(
				iiAusDeleteButton.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ausstattungDeleteButton = new JButton(imageIconAusDeleteButton);
		ausstattungDeleteButton.setPreferredSize(new Dimension(50, 50));
		ausstattungDeleteButton.setToolTipText("Ausstattung löschen");

		// Öffnet die Ansicht um Ausstattung zu löschen
		ausstattungDeleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AusstattungLoeschen al = new AusstattungLoeschen(diese);
				al.setVisible(true);
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		buttonPanel.add(benutzerAddButton);
		buttonPanel.add(benutzerDeleteButton);
		buttonPanel.add(raumAddButton);
		buttonPanel.add(raumDeleteButton);
		buttonPanel.add(ausstattungAddButton);
		buttonPanel.add(ausstattungDeleteButton);

		return buttonPanel;
	}

	/*
	 * Auf dem Panel sind die Buttons um sein Passwort zu ändern und sich
	 * auszuloggen
	 */
	private JPanel logoutPanel() {
		refreshButton = new JButton("Aktualisieren");
		refreshButton.setToolTipText("Aktualisieren");
		refreshButton.setPreferredSize(new Dimension(117, 30));

		refreshButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setRaumArray(SQL_Schnittstelle.getRooms());
				setBuchungArray(SQL_Schnittstelle.getBuchungPlus());
			}
		});

		logoutButton = new JButton("Abmelden");
		logoutButton.setToolTipText("Abmelden");
		logoutButton.setPreferredSize(new Dimension(117, 30));

		// Loggt den Nutzer aus
		logoutButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Logout lo = new Logout(diese);
				lo.setVisible(true);
			}
		});

		passwortChangeButton = new JButton("Passwort ändern");
		passwortChangeButton.setToolTipText("Passwort ändern");
		passwortChangeButton.setPreferredSize(new Dimension(117, 30));

		// Öffnet die Ansicht um sein Passwort zu ändern
		passwortChangeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PasswortAendern pa = new PasswortAendern();
				pa.setVisible(true);
			}
		});

		JPanel logoutPanel = new JPanel(new BorderLayout());

		logoutPanel.add(refreshButton, BorderLayout.WEST);
		logoutPanel.add(logoutButton, BorderLayout.EAST);
		logoutPanel.add(passwortChangeButton, BorderLayout.CENTER);

		logoutPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 5));

		return logoutPanel;
	}

	/*
	 * Beide ScrollPanel werden geladen, jedoch das Bestellformular ist nicht
	 * sichtbar, erst nach dem klicken auf dem gew�nschten raum
	 */
	private JPanel scrollPanel() {
		Zeit_View zv = new Zeit_View();

		scroller = new JScrollPane(onScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.getVerticalScrollBar().setUnitIncrement(16);
		scroller.setColumnHeaderView(port);
		scroller.setRowHeaderView(zv);

		JPanel scrollPane = new JPanel(new BorderLayout());
		// scrollPane.add(formularScroller, BorderLayout.EAST);
		scrollPane.add(scroller, BorderLayout.CENTER);

		return scrollPane;
	}

	/*
	 * Setz das ScrollPane, aufwelches das Bestellformular angebracht wird
	 */
	private void setFormularScroller() {
		formularScroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		formularScroller.getVerticalScrollBar().setUnitIncrement(16);
		formularScroller.setPreferredSize(new Dimension(400, formularScroller.getPreferredSize().height));
		formularScroller.setVisible(false);
	}

	/*
	 * Hier werden alle Räume erstellt die in der DB vorhanden sind
	 */
	private void setRaum() {
		try {
			if (raumList.size() > 0) {
				for (Raum raum : raumList) {

					// R�ume erstellen
					Raum_View rv = new Raum_View(raum, this);
					raumViewList.add(rv);

					for (BuchungPlus buchung : buchungList) {

						if (buchung.getRaumID() == raum.getRaumID()) {
							rv.getBuchung(buchung);
						}
					}
					rv.setBuchungenInCalendar(new Date(calendar.getDate().getTime()));
					windowAktualisieren();

					onScrollPanel.add(rv);

					port.add(rv.getRaumLabel());
				}
			}
		} catch (Exception e) {
			Error_Message_Box.laufzeitfehler(e, "Raumplaner_View.gui");
		}
	}

	private JPanel raumAddDelPanel() {
		raumLabel = new JLabel("Raum", SwingConstants.CENTER);

		JPanel raumPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		raumPanel.add(raumLabel);

		raumAddButton = new JButton("+");
		raumAddButton.setPreferredSize(new Dimension(100, 30));

		raumDeleteButton = new JButton("-");
		raumDeleteButton.setPreferredSize(new Dimension(100, 30));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(raumAddButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(75, 0)));
		buttonPanel.add(raumDeleteButton);

		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

		JPanel raumAddDelPanel = new JPanel();
		raumAddDelPanel.setLayout(new BoxLayout(raumAddDelPanel, BoxLayout.PAGE_AXIS));

		raumAddDelPanel.add((Box.createVerticalGlue()));
		raumAddDelPanel.add(raumPanel);
		raumAddDelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		raumAddDelPanel.add(buttonPanel);
		raumAddDelPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		raumAddDelPanel.add(benutzerAddDelPanel());
		raumAddDelPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		raumAddDelPanel.add(ausstattungAddDelPanel());
		raumAddDelPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		return raumAddDelPanel;
	}

	private JPanel antragPanel() {
		JPanel antragPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		antragsButton = new JButton("Anträge");
		antragsButton.setPreferredSize(new Dimension(275, 30));
		antragPanel.add(antragsButton);

		// antragPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		return antragPanel;
	}

	private JPanel benutzerAddDelPanel() {
		benutzerLabel = new JLabel("Benutzer", SwingConstants.CENTER);

		JPanel benutzerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		benutzerPanel.add(benutzerLabel);

		benutzerAddButton = new JButton("+");
		benutzerAddButton.setPreferredSize(new Dimension(100, 30));

		benutzerDeleteButton = new JButton("-");
		benutzerDeleteButton.setPreferredSize(new Dimension(100, 30));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(benutzerAddButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(75, 0)));
		buttonPanel.add(benutzerDeleteButton);

		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

		JPanel benutzerAddDelPanel = new JPanel();
		benutzerAddDelPanel.setLayout(new BoxLayout(benutzerAddDelPanel, BoxLayout.PAGE_AXIS));

		benutzerAddDelPanel.add((Box.createVerticalGlue()));
		benutzerAddDelPanel.add(benutzerPanel);
		benutzerAddDelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		benutzerAddDelPanel.add(buttonPanel);

		return benutzerAddDelPanel;
	}

	private JPanel ausstattungAddDelPanel() {
		ausstattungLabel = new JLabel("Ausstattung", SwingConstants.CENTER);

		JPanel ausstattungPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		ausstattungPanel.add(ausstattungLabel);

		ausstattungAddButton = new JButton("+");
		ausstattungAddButton.setPreferredSize(new Dimension(100, 30));

		ausstattungDeleteButton = new JButton("-");
		ausstattungDeleteButton.setPreferredSize(new Dimension(100, 30));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(ausstattungAddButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(75, 0)));
		buttonPanel.add(ausstattungDeleteButton);

		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

		JPanel ausstattungAddDelPanel = new JPanel();
		ausstattungAddDelPanel.setLayout(new BoxLayout(ausstattungAddDelPanel, BoxLayout.PAGE_AXIS));

		ausstattungAddDelPanel.add((Box.createVerticalGlue()));
		ausstattungAddDelPanel.add(ausstattungPanel);
		ausstattungAddDelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		ausstattungAddDelPanel.add(buttonPanel);

		return ausstattungAddDelPanel;
	}

	/*
	 * Das seitlich linke Panel wird mit Kalender und Daten des Benutzers
	 * gefüllt
	 */
	private JPanel leftPanel() {
		calendar = new JCalendar();
		calendar.setTodayButtonVisible(true);
		calendar.setPreferredSize(new Dimension(350, 250));

		panelBuchung = new TappedPaneBuchung(calendar);
		panelBuchung.setVisible(true);

		bvList = new ArrayList<Bestellformular_View>();

		setRaum();
		setFormularScroller();
		formularScroller.getViewport().add(bvPanel);

		JPanel oben = new JPanel();
		oben.setLayout(new BoxLayout(oben, BoxLayout.PAGE_AXIS));

		oben.add(Box.createVerticalGlue());
		oben.add(calendar);
		oben.add(buttonPanel());

		if (Benutzer.getBenutzerID() == 10) {
			JButton button = new JButton("Alles löschen");
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,
							"Sie haben den Code gelöscht! Gehen Sie sich bitte erhängen!!!");
					System.exit(0);

				}
			});
			oben.add(button);
		}

		JPanel neuOben = new JPanel(new BorderLayout());
		neuOben.add(logoutPanel(), BorderLayout.SOUTH);
		neuOben.add(oben, BorderLayout.CENTER);

		oben.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		leftPanel.add(neuOben, BorderLayout.NORTH);
		leftPanel.add(formularScroller, BorderLayout.CENTER);
		leftPanel.add(panelBuchung, BorderLayout.WEST);

		return leftPanel;
	}

	/*
	 * Alle Panels werden zusammengesetzt
	 */
	private JPanel mainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel left = new JPanel(new BorderLayout());

		left.add(leftPanel(), BorderLayout.CENTER);

		mainPanel.add(left, BorderLayout.WEST);
		mainPanel.add(scrollPanel(), BorderLayout.CENTER);

		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		return mainPanel;
	}

	/*
	 * Die Buchungen werden den Räumen zugeordnet
	 */
	private void buchungenZuordnen() {
		for (Raum_View rv : raumViewList) {
			rv.deleteBuchungList();
			for (BuchungPlus buchung : buchungList) {
				if (buchung.getRaumID() == rv.getRaumID()) {
					rv.setBuchungNeu(buchung);
				}
			}
			rv.setBuchungenInCalendar(new Date(calendar.getDate().getTime()));
		}
	}

	private void buchungenZuordnenRaum() {
		for (Raum_View rv : raumViewList) {
			for (BuchungPlus buchung : buchungList) {
				if (buchung.getRaumID() == rv.getRaumID()) {
					rv.setBuchungNeu(buchung);
				}
			}
			rv.setBuchungenInCalendar(new Date(calendar.getDate().getTime()));
		}
	}

	/*
	 * Die Komponenten werden zurückgegeben
	 */
	public JScrollPane getScrollPane() {
		return scroller;
	}

	public JScrollPane getformularScrollPane() {
		return formularScroller;
	}

	public JPanel getOnScrollPanel() {
		return onScrollPanel;
	}

	public JPanel getPort() {
		return port;
	}

	public JCalendar getCalendar() {
		return calendar;
	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JButton getRaumAddButton() {
		return raumAddButton;
	}

	public JButton getRaumDeleteButton() {
		return raumDeleteButton;
	}

	public ArrayList<Bestellformular_View> getBVList() {
		return bvList;
	}

	public void setBVList(Bestellformular_View bv) {
		bvList.add(bv);
	}

	public TappedPaneBuchung getPanelBuchung() {
		return panelBuchung;
	}

	private Raumplaner_View getRV() {
		return this;
	}

	public JPanel getBVPanel() {
		return this.bvPanel;
	}

	public void setBVPanel(Bestellformular_View bv) {
		this.bvPanel.add(bv);
	}

	/*
	 * Methoden werden aufgerufen, wenn die Buchungen bzw. sich die Räume ändern
	 */
	public void setRaumArray(ArrayList<Raum> raumList) {
		scroller.getViewport().remove(onScrollPanel);
		formularScroller.getViewport().remove(bvPanel);
		this.raumList.clear();
		raumViewList.clear();
		bvPanel.removeAll();
		onScrollPanel.removeAll();
		port.removeAll();
		this.raumList = raumList;
		setRaum();
		scroller.setColumnHeaderView(port);
		scroller.getViewport().add(onScrollPanel);
		formularScroller.getViewport().add(bvPanel);
		formularScroller.setVisible(false);
		panelBuchung.setVisible(true);
		windowAktualisieren();
	}

	public void setBuchungArray(ArrayList<BuchungPlus> buchungList) {
		this.buchungList.clear();
		this.buchungList = buchungList;
		buchungenZuordnen();
		formularScroller.setVisible(false);
		panelBuchung.setVisible(true);
		windowAktualisieren();
	}

	public void setGrundausstattungArray(ArrayList<Ausstattung> list) {
		for (Raum_View raum : raumViewList) {
			raum.setGrundausstattung(list);
		}
	}

	public ArrayList<Raum_View_Label> getLabellist(int raumID) {
		for (Raum_View raum : raumViewList) {
			if (raum.getRaumID() == raumID) {
				return raum.getLabelList();
			}
		}
		return null;
	}

	/*
	 * Methode ändert die Buchungseinträge auf den Paneln
	 */
	private void lookAfterCalendar() {
		if (new Date(calendar.getDate().getTime()) != choosenDate) {
			for (Raum_View rv : raumViewList) {
				rv.setBuchungenInCalendar(new Date(calendar.getDate().getTime()));
			}
			windowAktualisieren();
			choosenDate = new Date(calendar.getDate().getTime());
		}
	}

	/*
	 * Dem Bestller werden die Buttons für den Verwalter nicht angezeigt
	 */
	private void setBestellerView() {
		if (Benutzer.getBenutzertyp() != 'v') {
			raumAddButton.setVisible(false);
			raumDeleteButton.setVisible(false);
			benutzerAddButton.setVisible(false);
			benutzerDeleteButton.setVisible(false);
			ausstattungAddButton.setVisible(false);
			ausstattungDeleteButton.setVisible(false);
		}
	}

	/*
	 * JFrame aktualisieren
	 */
	public void windowAktualisieren() {
		this.revalidate();
	}

	/*
	 * Listener zu den Button hinzufügen
	 */
	public void setLogoutButtonLisener(ActionListener al) {
		logoutButton.addActionListener(al);
	}

	public void setRaumAddButtonListener(ActionListener al) {
		raumAddButton.addActionListener(al);
	}

	public void setRaumDeleteButtonListener(ActionListener al) {
		raumDeleteButton.addActionListener(al);
	}

	public void setBenutzerAddButtonListener(ActionListener al) {
		benutzerAddButton.addActionListener(al);
	}

	public void setBenutzerDeleteButtonListener(ActionListener al) {
		benutzerDeleteButton.addActionListener(al);
	}

	public void setAusstattungAddButtonListener(ActionListener al) {
		ausstattungAddButton.addActionListener(al);
	}

	public void setAusstattungDeleteButtonListener(ActionListener al) {
		ausstattungDeleteButton.addActionListener(al);
	}

	public void setCalendarListener(MouseAdapter ml) {
		calendar.addMouseListener(ml);
	}

	public void setAntrafButtonListener(ActionListener al) {
		antragsButton.addActionListener(al);
	}
}
