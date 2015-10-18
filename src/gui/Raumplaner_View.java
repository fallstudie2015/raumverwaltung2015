package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

/**
 * @author Tim
 * 
 *         Die Klasse liefert die Haupansicht für den Besteller, sowie die
 *         modifizierte Ansicht für den Verwalter
 *
 */
public class Raumplaner_View extends JFrame {

	private JCalendar calendar;
	private JLabel nameLabel, bereichLabel, logoLabel, raumplanerLabel;
	private JButton logoutButton;
	private JScrollPane scroller, formularScroller;
	private Raum_View rv;

	public Raumplaner_View() {
		initView();
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
	}

	/*
	 * Die obersete Leiste mit den zwei Logos werden initialisiert und
	 * angeordnet
	 */
	private JPanel logoPanel() {
		ImageIcon ii1 = new ImageIcon(getClass().getClassLoader().getResource(
				"ressources/logo_2.png"));
		ImageIcon imageIcon = new ImageIcon(ii1.getImage().getScaledInstance(
				300, 150, Image.SCALE_DEFAULT));
		logoLabel = new JLabel(imageIcon, SwingConstants.LEFT);
		logoLabel.setPreferredSize(new Dimension(300, 150));

		ImageIcon ii2 = new ImageIcon(getClass().getClassLoader().getResource(
				"ressources/Schrift.png"));
		ImageIcon imageIcon2 = new ImageIcon(ii2.getImage().getScaledInstance(
				700, 150, Image.SCALE_DEFAULT));
		raumplanerLabel = new JLabel(imageIcon2, SwingConstants.LEFT);
		raumplanerLabel.setPreferredSize(new Dimension(700, 150));

		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(new BorderLayout());
		logoPanel.add(logoLabel, BorderLayout.EAST);
		logoPanel.add(raumplanerLabel, BorderLayout.WEST);

		return logoPanel;
	}

	/*
	 * Beide ScrollPanel werden geladen, jedoch das Bestellformular ist nicht
	 * sichtbar, erst nach dem klicken auf dem gewünschten raum
	 */
	private JPanel scrollPanel() {
		JPanel onScrollPanel = new JPanel();
		onScrollPanel.setLayout(new FlowLayout());

		Zeit_View zv = new Zeit_View();

		JPanel bvPanel = new JPanel(new FlowLayout());

		JPanel port = new JPanel(new FlowLayout());

		for (int i = 0; i < 20; i++) {
			// Bestellformular view erstellen
			Bestellformular_View bv = new Bestellformular_View();

			// Räume erstellen
			rv = new Raum_View("Raum XY" + i, bv, this);

			// Raumnamen übergeben
			bv.setRaumName(rv.getRaumName());
			bv.initView();

			// Panel hinzufügen
			bvPanel.add(bv);
			onScrollPanel.add(rv);

			port.add(rv.getRaumLabel());
		}

		scroller = new JScrollPane(onScrollPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.getVerticalScrollBar().setUnitIncrement(16);
		scroller.setColumnHeaderView(port);
		scroller.setRowHeaderView(zv);

		formularScroller = new JScrollPane(bvPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		formularScroller.getVerticalScrollBar().setUnitIncrement(16);
		formularScroller.setPreferredSize(new Dimension(350, formularScroller
				.getPreferredSize().height));
		formularScroller.setVisible(false);

		JPanel scrollPane = new JPanel(new BorderLayout());
		scrollPane.add(formularScroller, BorderLayout.EAST);
		scrollPane.add(scroller, BorderLayout.CENTER);

		return scrollPane;
	}

	/*
	 * Das seitlich linke Panel wird mit Kalender und Daten des Benutzers
	 * gefüllt
	 */
	private JPanel leftPanel() {
		calendar = new JCalendar();
		calendar.setTodayButtonVisible(true);
		calendar.setPreferredSize(new Dimension(275, 300));

		nameLabel = new JLabel("Name");
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
		calendarPanel.add(calendar);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(3, 1));

		infoPanel.add(namenPanel);
		infoPanel.add(bereichPanel);
		infoPanel.add(logoutPanel);

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		leftPanel.add(calendarPanel, BorderLayout.NORTH);
		leftPanel.add(infoPanel, BorderLayout.SOUTH);

		return leftPanel;
	}

	/*
	 * Alle Panels werden zusammengesetzt
	 */
	private JPanel mainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(logoPanel(), BorderLayout.NORTH);
		mainPanel.add(leftPanel(), BorderLayout.WEST);
		mainPanel.add(scrollPanel(), BorderLayout.CENTER);

		// mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		return mainPanel;
	}

	/*
	 * Die zwei ScrollPanes werden zurückgegeben
	 */
	public JScrollPane getScrollPane() {
		return scroller;
	}

	public JScrollPane getformularScrollPane() {
		return formularScroller;
	}
}
