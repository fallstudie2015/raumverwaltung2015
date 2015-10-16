package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

public class Raumplaner_View extends JFrame {

	private JCalendar calendar;
	private JLabel nameLabel, bereichLabel, logoLabel, raumplanerLabel;
	private JButton logoutButton;
	private JScrollPane scroller;
	private Raum_View rv;

	public Raumplaner_View() {
		initView();
	}

	private void initView() {
		setLayout(new BorderLayout());
		getContentPane().add(mainPanel(), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Raumplaner v.1.0");
		setMinimumSize(new Dimension(1300, 786));
		setMaximumSize(new Dimension(1920, 1080));
		setExtendedState(MAXIMIZED_BOTH);
	}

	private JPanel logoPanel() {
		ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("ressources/logo.png"));
		logoLabel = new JLabel(imageIcon, SwingConstants.LEFT);
		logoLabel.setPreferredSize(new Dimension(300, 150));

		ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("C:\\Users\\Tim\\Schrift.png").getImage().getScaledInstance(
				900, 150, Image.SCALE_DEFAULT));
		raumplanerLabel = new JLabel(imageIcon2, SwingConstants.LEFT);
		raumplanerLabel.setPreferredSize(new Dimension(900, 150));

		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(new BorderLayout());
		logoPanel.add(logoLabel, BorderLayout.EAST);
		logoPanel.add(raumplanerLabel, BorderLayout.WEST);

		return logoPanel;
	}

	private JPanel scrollPanel() {
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		upperPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JPanel onScrollPanel = new JPanel();
		onScrollPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		Zeit_View zv = new Zeit_View();
		upperPanel.add(zv.getZeitPanel());
		onScrollPanel.add(zv);

		for (int i = 0; i < 20; i++) {
			rv = new Raum_View("Raum XY" + i);
			upperPanel.add(rv.getRaumLabel());
			onScrollPanel.add(rv);

		}

		scroller = new JScrollPane(onScrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroller.getVerticalScrollBar().setUnitIncrement(16);

		JPanel scrollPane = new JPanel(new BorderLayout());
		// scrollPane.add(upperPanel, BorderLayout.NORTH);
		scrollPane.add(scroller, BorderLayout.CENTER);

		return scrollPane;
	}

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

	private JPanel mainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(logoPanel(), BorderLayout.NORTH);
		mainPanel.add(leftPanel(), BorderLayout.WEST);
		mainPanel.add(scrollPanel(), BorderLayout.CENTER);

		// mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		return mainPanel;
	}

}
