package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login_View extends JFrame {

	/*
	 * Hier wird das Login Fenster erzeugt, es muss nach dem erzeugen auf
	 * visible gesetzt werden
	 * 
	 * Alle Elemente sind auf einem eigenen Panel implementiert, um deren
	 * spezifische Gr��e zu �ndern.
	 */

	private JLabel loginWrongLabel, logoLabel;
	private JTextField userIDField;
	private JPasswordField passwordField;
	private JButton loginButton, cancelButton;
	private ActionListener action;
	private static int wait = 0;

	public Login_View(ActionListener action) {
		setLoginButtonListener(action);
		initLogin();
	}

	public Login_View() {
		initLogin();
		setLoginButtonListener(new listener.Login_Listener(this));
	}

	/*
	 * Rahmenbedingungen setzen
	 * 
	 * Aufl�sung: 786 * 500 als feste und unver�nderbare Gr��e
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
	 * Alle erforderlichen Panel werden zusammengef�gt, bevor sie auf das Frame
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
	 * Panel f�r das Label, welches erscheint, wenn die Daten falsch sind
	 */
	private JPanel labelPanel() {
		loginWrongLabel = new JLabel(
				"Benutzerdaten sind nicht korrekt angegeben!");
		loginWrongLabel.setForeground(Color.RED);
		loginWrongLabel.setBorder(BorderFactory.createEmptyBorder(40, 5, 5, 5));
		loginWrongLabel.setVisible(false);

		JPanel labelPanel = new JPanel();
		labelPanel.add(loginWrongLabel);

		return labelPanel;
	}

	/*
	 * Panel enth�lt die zwei eingabe Felder
	 * 
	 * Die ActionListener m�ssen noch entfernt werden
	 */
	private JPanel insertFieldPanel() {
		userIDField = new JTextField("eMail-Adresse");
		userIDField.selectAll();
		userIDField.setBorder(BorderFactory.createEtchedBorder());
		userIDField.setPreferredSize(new Dimension(355, 30));

		passwordField = new JPasswordField("Passwort");
		passwordField.setBorder(BorderFactory.createEtchedBorder());
		passwordField.setPreferredSize(new Dimension(355, 30));
		passwordField.setEchoChar((char) 0);

		// userId Feld wird gedr�ckt
		MouseListener ml1 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				userIDField.setText("");
			}
		};
		userIDField.addMouseListener(ml1);

		// Passwort Feld frei machen und Zeichen setzen
		MouseListener ml2 = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				passwordField.setText("");
				passwordField.setEchoChar('\u2022');
			}
		};
		passwordField.addMouseListener(ml2);
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				if (wait == 0) {
					passwordField.setText("");
					passwordField.setEchoChar('\u2022');
					wait++;
				}
			}
		});

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
	 * Panel enth�lt die zwei Buttons
	 * 
	 * ActionListener sind nur zu Testzwecken implementier, m�ssen noch entfernt
	 * werden
	 */
	private JPanel buttonPanel() {
		loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(150, 30));

		cancelButton = new JButton("Abbrechen");
		cancelButton.setPreferredSize(new Dimension(150, 30));

		loginButton.addActionListener(action);

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
	 * Panel enth�lt ein Firmenlogo o.�.
	 */
	private JPanel logoPanel() {
		ImageIcon ii1 = new ImageIcon(getClass().getClassLoader().getResource(
				"ressources/logo_2.png"));
		ImageIcon imageIcon = new ImageIcon(ii1.getImage().getScaledInstance(
				300, 150, Image.SCALE_DEFAULT));
		logoLabel = new JLabel(imageIcon, SwingConstants.CENTER);
		logoLabel.setPreferredSize(new Dimension(300, 150));

		JPanel logoPanel = new JPanel();
		logoPanel.add(logoLabel);

		return logoPanel;
	}

	/*
	 * Methoden um ActionListener und andere Listener hinzuzuf�gen
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

	public void setLoginButtonKeyListener(KeyAdapter kl) {
		this.loginButton.addKeyListener(kl);
	}

	public void setCancelButtonListener(ActionListener al) {
		this.cancelButton.addActionListener(al);
	}

	/*
	 * Setter Methoden f�r die einzelnen Elemente auf dem JFrame
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
