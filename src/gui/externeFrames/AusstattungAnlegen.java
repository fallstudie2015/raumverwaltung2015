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

public class AusstattungAnlegen extends JFrame {

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
					AusstattungLoeschen frame = new AusstattungLoeschen();
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
	public AusstattungAnlegen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AusstattungAnlegen.class.getResource("/ressources/menu_ausstattung_anlegen_transp.png")));
		setResizable(false);
		setTitle("Ausstattung anlegen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRaumAnlegen = new JLabel("Ausstattung anlegen");
		lblRaumAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRaumAnlegen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRaumAnlegen, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);
		
		JButton btnAnlegen = new JButton("Anlegen");
		btnAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnAnlegen);
		
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
		
		JLabel lblAusstattung = new JLabel("Ausstattung 1");
		lblAusstattung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung);
		
		JLabel lblAusstattung_1 = new JLabel("Ausstattung 2");
		lblAusstattung_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung_1);
		
		JLabel lblAusstattung_2 = new JLabel("Ausstattung 3");
		lblAusstattung_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung_2);
		
		JLabel lblAusstattung_3 = new JLabel("Ausstattung 4");
		lblAusstattung_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung_3);
		
		JLabel lblAusstattung_4 = new JLabel("Ausstattung 5");
		lblAusstattung_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung_4);
		
		JLabel lblAusstattung_5 = new JLabel("Ausstattung 6");
		lblAusstattung_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung_5);
		
		JLabel lblAusstattung_6 = new JLabel("Ausstattung 7");
		lblAusstattung_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung_6);
		
		JLabel lblAusstattung_7 = new JLabel("Ausstattung 8");
		lblAusstattung_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung_7);
		
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
