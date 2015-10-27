package gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.java.SQL_Schnittstelle;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import java.awt.Window.Type;
import java.awt.Color;

public class PasswortAendern extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordFieldAlt;
	private JPasswordField passwordFieldNeu1;
	private JPasswordField passwordFieldNeu2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswortAendern frame = new PasswortAendern();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PasswortAendern() {
		setType(Type.UTILITY);
		setTitle("Passwort aendern");
		setResizable(false);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRaumAnlegen = new JLabel("Passwort aendern");
		lblRaumAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRaumAnlegen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRaumAnlegen, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);
		
		JButton btnNewButton = new JButton("Aendern");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String feedback = SQL_Schnittstelle.passwortAendern(GetPasswortAlt(), GetPasswortNeu1(), GetPasswortNeu2());
				PwGeaendert(feedback);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Abbrechen");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible();
			}
		});
		splitPane.setRightComponent(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(5, 0, 0, 0));
		
		JLabel lblAltesPasswort = new JLabel("Altes Passwort:");
		lblAltesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAltesPasswort);
		
		JLabel lblNeuesPasswort = new JLabel("Neues Passwort:");
		lblNeuesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblNeuesPasswort);
		
		JLabel lblPasswortBesttigen = new JLabel("Passwort bestätigen:");
		lblPasswortBesttigen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblPasswortBesttigen);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		Component rigidArea = Box.createRigidArea(new Dimension(147, 11));
		panel_5.add(rigidArea, BorderLayout.NORTH);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(147, 11));
		panel_5.add(rigidArea_1, BorderLayout.SOUTH);
		
		passwordFieldAlt = new JPasswordField();
		panel_5.add(passwordFieldAlt, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(147, 11));
		panel_6.add(rigidArea_2, BorderLayout.NORTH);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(147, 11));
		panel_6.add(rigidArea_3, BorderLayout.SOUTH);
		
		passwordFieldNeu1 = new JPasswordField();
		panel_6.add(passwordFieldNeu1, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(147, 11));
		panel_7.add(rigidArea_4, BorderLayout.NORTH);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(147, 11));
		panel_7.add(rigidArea_5, BorderLayout.SOUTH);
		
		passwordFieldNeu2 = new JPasswordField();
		panel_7.add(passwordFieldNeu2, BorderLayout.CENTER);
	}
	
	private String GetPasswortAlt() //gibt den Wert des Passwortfeldes zurück
	{
		String pw = "";
		char[] charArray = passwordFieldAlt.getPassword(); //wandelt char Array von passwordField in String um
		for (int i = 0; i < charArray.length; i++) 
		{
			pw += charArray[i];
		}
		return pw;
	}
	
	private String GetPasswortNeu1() //gibt den Wert des Passwortfeldes zurück
	{
		String pw = "";
		char[] charArray = passwordFieldNeu1.getPassword(); //wandelt char Array von passwordField in String um
		for (int i = 0; i < charArray.length; i++) 
		{
			pw += charArray[i];
		}
		return pw;
	}
	
	private String GetPasswortNeu2() //gibt den Wert des Passwortfeldes zurück
	{
		String pw = "";
		char[] charArray = passwordFieldNeu2.getPassword(); //wandelt char Array von passwordField in String um
		for (int i = 0; i < charArray.length; i++) 
		{
			pw += charArray[i];
		}
		return pw;
	}
	
	private void setVisible()
	{
		this.setVisible(false);
	}

	public static void PwGeaendert(String nachricht)
    {
	 JOptionPane.showMessageDialog(null, nachricht, "Information", JOptionPane.INFORMATION_MESSAGE);
	 
    }
}
