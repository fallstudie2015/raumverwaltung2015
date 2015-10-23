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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

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
		panel_3.setLayout(new GridLayout(8, 0, 0, 0));
		
		JLabel lblBenutzer = new JLabel("Benutzer 1");
		lblBenutzer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer);
		
		JLabel lblBenutzer_1 = new JLabel("Benutzer 2");
		lblBenutzer_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_1);
		
		JLabel lblBenutzer_2 = new JLabel("Benutzer 3");
		lblBenutzer_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_2);
		
		JLabel lblBenutzer_3 = new JLabel("Benutzer 4");
		lblBenutzer_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_3);
		
		JLabel lblBenutzer_4 = new JLabel("Benutzer 5");
		lblBenutzer_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_4);
		
		JLabel lblBenutzer_5 = new JLabel("Benutzer 6");
		lblBenutzer_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_5);
		
		JLabel lblBenutzer_6 = new JLabel("Benutzer 7");
		lblBenutzer_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_6);
		
		JLabel lblBenutzer_7 = new JLabel("Benutzer 8");
		lblBenutzer_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblBenutzer_7);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(8, 0, 0, 0));
		
		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		panel_4.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		panel_4.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		panel_4.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		panel_4.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		panel_4.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		panel_4.add(textField_7);
		textField_7.setColumns(10);
	}

}
