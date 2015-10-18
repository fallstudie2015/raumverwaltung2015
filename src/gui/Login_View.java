package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_View extends JFrame {

	/*
	 * Hier wird das Login Fenster erzeugt, es muss nach dem erzeugen auf
	 * visible gesetzt werden
	 * 
	 * Alle Elemente sind auf einem eigenen Panel implementiert, um deren
	 * spezifische Größe zu ändern.
	 */

	private JLabel loginWrongLabel, logoLabel;
	private JTextField userIDField;
	private JPasswordField passwordField;
	private JButton loginButton, cancelButton;

	public Login_View() {
		initLogin();
	}

	/*
	 * Rahmenbedingungen setzen
	 * 
	 * Auflösung: 786 * 500 als feste und unveränderbare Größe
	 */
	private void initLogin() {
		setLayout(new BorderLayout());
		getContentPane().add(loginPanel(), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Raumplaner v.1.0");
		setSize(new Dimension(786, 500));
		setResizable(false);
		setLocationRelativeTo(this);
	}

	/*
	 * Alle erforderlichen Panel werden zusammengefügt, bevor sie auf das Frame
	 * gesetzt werden
	 */
	private JPanel loginPanel() {
		JPanel mainFrame = new JPanel();
		mainFrame.setLayout(new BorderLayout());

		JPanel felderPanel = new JPanel();
		felderPanel.setLayout(new GridLayout(3, 1));

		felderPanel.add(labelPanel());
		felderPanel.add(insertFieldPanel());
		felderPanel.add(buttonPanel());

		felderPanel.setBorder(BorderFactory.createEmptyBorder(50, 75, 10, 75));

		mainFrame.add(logoPanel(), BorderLayout.NORTH);
		mainFrame.add(felderPanel, BorderLayout.CENTER);

		return mainFrame;
	}

	/*
	 * Panel für das Label, welches erscheint, wenn die Daten falsch sind
	 */
	private JPanel labelPanel() {
		loginWrongLabel = new JLabel("Test");
		loginWrongLabel.setBorder(BorderFactory.createEmptyBorder(70, 5, 5, 5));

		JPanel labelPanel = new JPanel();
		labelPanel.add(loginWrongLabel);

		return labelPanel;
	}

	/*
	 * Panel enthält die zwei eingabe Felder
	 * 
	 * Die ActionListener müssen noch entfernt werden
	 */
	private JPanel insertFieldPanel() {
		userIDField = new JTextField("eMail-Adresse");
		userIDField.setBorder(BorderFactory.createEtchedBorder());
		userIDField.setPreferredSize(new Dimension(355, 30));

		passwordField = new JPasswordField("Passwort");
		passwordField.setBorder(BorderFactory.createEtchedBorder());
		passwordField.setPreferredSize(new Dimension(355, 30));
		passwordField.setEchoChar((char) 0);

		// userId Feld wird gedrückt
		MouseListener ml1 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				userIDField.setText("");
			}
		};
		userIDField.addMouseListener(ml1);

		// ich weiß noch nciht ob man es braucht
		ActionListener al1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		};
		userIDField.addActionListener(al1);

		// Passwort Feld frei machen und Zeichen setzen
		MouseListener ml2 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				passwordField.setText("");
				passwordField.setEchoChar('•');
			}
		};
		passwordField.addMouseListener(ml2);

		// ich weiß noch nciht ob man es braucht
		ActionListener al2 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		};
		passwordField.addActionListener(al2);

		JPanel userPanel = new JPanel();
		userPanel.add(userIDField);

		JPanel passwordPanel = new JPanel();
		passwordPanel.add(passwordField);

		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(2, 1));

		fieldPanel.add(userPanel);
		fieldPanel.add(passwordPanel);

		return fieldPanel;
	}

	/*
	 * Panel enthält die zwei Buttons
	 * 
	 * ActionListener sind nur zu Testzwecken implementier, müssen noch entfernt
	 * werden
	 */
	private JPanel buttonPanel() {
		loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(150, 30));

		cancelButton = new JButton("Abbrechen");
		cancelButton.setPreferredSize(new Dimension(150, 30));

		// Login Daten werden weitergegeben
		ActionListener al1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		};
		loginButton.addActionListener(al1);

		// Systemabbruch
		ActionListener al2 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		cancelButton.addActionListener(al2);

		JPanel loginPanel = new JPanel();
		loginPanel.add(loginButton);
		loginPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

		JPanel cancelPanel = new JPanel();
		cancelPanel.add(cancelButton);
		cancelPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		buttonPanel.add(loginPanel);
		buttonPanel.add(cancelPanel);

		return buttonPanel;
	}

	/*
	 * Panel enthält ein Firmenlogo o.ä.
	 */
	private JPanel logoPanel() {
		logoLabel = new JLabel("Firmenlogo");
		logoLabel.setPreferredSize(new Dimension(100, 100));

		JPanel logoPanel = new JPanel();
		logoPanel.add(logoLabel);

		return logoPanel;
	}

	/*
	 * Methoden um ActionListener und andere Listener hinzuzufügen
	 */
	public void setUserIDListener(ActionListener al) {
		this.userIDField.addActionListener(al);
	}

	public void setUserIDListener(MouseAdapter ml) {
		this.userIDField.addMouseListener(ml);
	}

	public void setPasswortListener(ActionListener al) {
		this.passwordField.addActionListener(al);
	}

	public void setPasswortListener(MouseAdapter ml) {
		this.passwordField.addMouseListener(ml);
	}

	public void setLoginButtonListener(ActionListener al) {
		this.loginButton.addActionListener(al);
	}

	public void setCancelButtonListener(ActionListener al) {
		this.cancelButton.addActionListener(al);
	}

	/*
	 * Setter Methoden für die einzelnen Elemente auf dem JFrame
	 */
	public JLabel getLoginWrongLabel() {
		return loginWrongLabel;
	}

	public JTextField getUserIDField() {
		return userIDField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
