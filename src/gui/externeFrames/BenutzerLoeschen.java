package gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.java.SQL_Schnittstelle;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class BenutzerLoeschen extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Vorname;
	private JTextField textField_nachname;
	private JTextField textField_email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BenutzerLoeschen frame = new BenutzerLoeschen();
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
	public BenutzerLoeschen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BenutzerLoeschen.class.getResource("/ressources/menu_benutzer_loeschen_transp.png")));
		setResizable(false);
		setTitle("Benutzer loeschen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRaumAnlegen = new JLabel("Benutzer loeschen");
		lblRaumAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRaumAnlegen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRaumAnlegen, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);
		
		JButton btnLoeschen = new JButton("Loeschen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQL_Schnittstelle.deleteBenutzer(textField_email.getText(), textField_Vorname.getText(), textField_nachname.getText());
			}
		});
		btnLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnLoeschen);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		splitPane.setRightComponent(btnAbbrechen);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(5, 0, 0, 0));
		
		JLabel lblBenutzer = new JLabel("Vorname:");
		lblBenutzer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer);
		
		JLabel lblBenutzer_1 = new JLabel("Nachname:");
		lblBenutzer_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_1);
		
		JLabel lblBenutzer_2 = new JLabel("E-Mail - Adresse:");
		lblBenutzer_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_2);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		textField_Vorname = new JTextField();
		textField_Vorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5.add(textField_Vorname, BorderLayout.CENTER);
		textField_Vorname.setColumns(10);
		
		Component rigidArea = Box.createRigidArea(new Dimension(147, 11));
		panel_5.add(rigidArea, BorderLayout.NORTH);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(147, 11));
		panel_5.add(rigidArea_1, BorderLayout.SOUTH);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		textField_nachname = new JTextField();
		textField_nachname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6.add(textField_nachname, BorderLayout.CENTER);
		textField_nachname.setColumns(10);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(147, 11));
		panel_6.add(rigidArea_2, BorderLayout.NORTH);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(147, 11));
		panel_6.add(rigidArea_3, BorderLayout.SOUTH);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		textField_email = new JTextField();
		textField_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_7.add(textField_email, BorderLayout.CENTER);
		textField_email.setColumns(10);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(147, 11));
		panel_7.add(rigidArea_4, BorderLayout.NORTH);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(147, 11));
		panel_7.add(rigidArea_5, BorderLayout.SOUTH);
	}

}
