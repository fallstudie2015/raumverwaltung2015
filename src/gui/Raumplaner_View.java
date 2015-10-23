package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import de.dhbw.java.Benutzer;
import de.dhbw.java.Buchung;
import de.dhbw.java.Raum;

/**
 * @author Tim
 * 
 *         Die Klasse liefert die Haupansicht f�r den Besteller, sowie die
 *         modifizierte Ansicht f�r den Verwalter
 *
 */
public class Raumplaner_View extends JFrame {

	private JCalendar calendar;
	private JPanel bvPanel, onScrollPanel, port;
	private JLabel nameLabel, bereichLabel, logoLabel, raumplanerLabel, raumLabel, benutzerLabel, ausstattungLabel;
	private JButton logoutButton, raumAddButton, raumDeleteButton, benutzerAddButton, benutzerDeleteButton,
			ausstattungAddButton, ausstattungDeleteButton, antragsButton;
	private JScrollPane scroller, formularScroller;
	private Raum_View rv;
	private ArrayList<Bestellformular_View> bvList;
	private ArrayList<Raum> raumList;
	private ArrayList<Buchung> buchungList;
	private ArrayList<Raum_View> raumViewList;
	private Date choosenDate;

	public Raumplaner_View() {
		initView();
	}

	public Raumplaner_View(ArrayList<Raum> raumList, ArrayList<Buchung> buchungList) {
		this.bvPanel = new JPanel(new FlowLayout());
		this.raumList = raumList;
		this.buchungList = buchungList;
		raumViewList = new ArrayList<Raum_View>();
		initView();
		this.choosenDate = new Date(calendar.getDate().getTime());
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if (new Date(calendar.getDate().getTime()) != choosenDate) {
					for (Raum_View rv : raumViewList) {
						rv.setBuchungenInCalendar(new Date(calendar.getDate().getTime()));
					}
					windowAktualisieren();
					choosenDate = new Date(calendar.getDate().getTime());
				}
			}
		});
	}

	/*
	 * Gr��e der Ansicht wird festgelegt und alle erforderlichen Panels werden
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

	private void setFormularScroller() {
		formularScroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		formularScroller.getVerticalScrollBar().setUnitIncrement(16);
		formularScroller.setPreferredSize(new Dimension(350, formularScroller.getPreferredSize().height));
		formularScroller.setVisible(false);
	}

	private void setRaum() {
		onScrollPanel = new JPanel();
		onScrollPanel.setLayout(new FlowLayout());

		port = new JPanel(new FlowLayout());

		try {
			if (raumList.size() > 0) {
				for (Raum raum : raumList) {

					// R�ume erstellen
					rv = new Raum_View(raum, this);
					raumViewList.add(rv);

					for (Buchung buchung : buchungList) {

						if (buchung.getRaumID() == raum.getRaumID()) {
							rv.getBuchung(buchung);
							rv.setBuchungenInCalendar(new Date(calendar.getDate().getTime()));
						}
					}
					windowAktualisieren();

					// Panel hinzuf�gen
					// bvList.add(bv);
					// bvPanel.add(bv);
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
	 * gef�llt
	 */
	private JPanel leftPanel() {
		calendar = new JCalendar();
		// calendar.setTodayButtonVisible(true);
		calendar.setPreferredSize(new Dimension(275, 300));
		calendar.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

		bvList = new ArrayList<Bestellformular_View>();

		setRaum();
		setFormularScroller();
		formularScroller.getViewport().add(bvPanel);

		nameLabel = new JLabel((Benutzer.getVorname() + " " + Benutzer.getNachname()));
		// nameLabel.setPreferredSize(new Dimension(150, 100));

		bereichLabel = new JLabel("Bereich");

		logoutButton = new JButton("Logout");
		logoutButton.setPreferredSize(new Dimension(275, 30));

		JPanel namenPanel = new JPanel();
		namenPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		namenPanel.add(nameLabel);

		JPanel bereichPanel = new JPanel();
		bereichPanel.add(bereichLabel);

		JPanel logoutPanel = new JPanel();
		logoutPanel.add(logoutButton);

		JPanel calendarPanel = new JPanel();
		calendarPanel.setLayout(new BorderLayout());

		// calendarPanel.add(Box.createVerticalGlue());
		calendarPanel.add(calendar, BorderLayout.NORTH);
		// calendarPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		calendarPanel.add(formularScroller, BorderLayout.CENTER);
		// calendarPanel.add(raumAddDelPanel());

		// JPanel infoPanel = new JPanel();
		// infoPanel.setLayout(new GridLayout(4, 1));
		//
		// infoPanel.add(antragPanel());
		// infoPanel.add(namenPanel);
		// infoPanel.add(bereichPanel);
		// infoPanel.add(logoutPanel);

		// JPanel leftPanel = new JPanel();
		// leftPanel.setLayout(new BorderLayout());
		//
		// leftPanel.add(calendarPanel, BorderLayout.NORTH);
		// leftPanel.add(infoPanel, BorderLayout.SOUTH);

		return calendarPanel;
	}

	/*
	 * Alle Panels werden zusammengesetzt
	 */
	private JPanel mainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// JScrollPane scrollPane = new JScrollPane(leftPanel(),
		// JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		// JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		JPanel left = new JPanel(new BorderLayout());

		left.add(logoPanel(), BorderLayout.NORTH);
		left.add(leftPanel(), BorderLayout.CENTER);

		// mainPanel.add(logoPanel(), BorderLayout.NORTH);
		mainPanel.add(left, BorderLayout.WEST);
		mainPanel.add(scrollPanel(), BorderLayout.CENTER);

		// mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		return mainPanel;
	}

	private void buchungenZuordnen() {
		for (Raum_View rv : raumViewList) {
			rv.deleteBuchungList();
			for (Buchung buchung : buchungList) {
				if (buchung.getRaumID() == rv.getRaumID()) {
					rv.setBuchungNeu(buchung);
					rv.setBuchungenInCalendar(new Date(calendar.getDate().getTime()));
				}
			}
		}
	}

	/*
	 * Die Komponenten werden zur�ckgegeben
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

	/*
	 * Methoden werden aufgerufen, wenn die Buchungen bzw. sich die R�ume �ndern
	 */
	public void setRaumArray(ArrayList<Raum> raumList) {
		this.raumList.clear();
		this.raumList = raumList;
		raumViewList.clear();
		bvPanel.removeAll();
		onScrollPanel.removeAll();
		port.removeAll();
		setRaum();
		scroller.getViewport().add(onScrollPanel);
		formularScroller.getViewport().add(bvPanel);
		windowAktualisieren();
	}

	public void setBuchungArray(ArrayList<Buchung> buchungList) {
		this.buchungList.clear();
		this.buchungList = buchungList;
		buchungenZuordnen();
		windowAktualisieren();
	}

	public JPanel getBVPanel() {
		return this.bvPanel;
	}

	public void setBVPanel(Bestellformular_View bv) {
		this.bvPanel.add(bv);
	}

	public void windowAktualisieren() {
		this.validate();
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

	public void setCalendarListener(MouseAdapter ml) {
		calendar.addMouseListener(ml);
	}

	public void setAntrafButtonListener(ActionListener al) {
		antragsButton.addActionListener(al);
	}
}
