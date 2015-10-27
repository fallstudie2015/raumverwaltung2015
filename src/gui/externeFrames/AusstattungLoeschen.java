package gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AusstattungLoeschen extends JDialog {

	private JPanel contentPane;
	private JTextField textField_name;

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
	public AusstattungLoeschen() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AusstattungLoeschen.class.getResource("/ressources/menu_ausstattung_loeschen_transp.png")));
		setResizable(false);
		setTitle("Ausstattung loeschen");
		setLocationRelativeTo(this);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRaumAnlegen = new JLabel("Ausstattung loeschen");
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
				setVisible();
			}
		});
		splitPane.setRightComponent(btnAbbrechen);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(9, 0, 0, 0));
		
		JLabel lblRaumname = new JLabel("Raumname");
		lblRaumname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(lblRaumname);
		
		JLabel lblAusstattung = new JLabel("Ausstattung");
		lblAusstattung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(lblAusstattung);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(9, 0, 0, 0));
		
		textField_name = new JTextField();
		textField_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(textField_name);
		textField_name.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_1.setEnabled(false);
		panel_3.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_2.setEnabled(false);
		panel_3.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_3.setEnabled(false);
		panel_3.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_4.setEnabled(false);
		panel_3.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_5.setEnabled(false);
		panel_3.add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_6.setEnabled(false);
		panel_3.add(comboBox_6);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_7.setEnabled(false);
		panel_3.add(comboBox_7);
	}
	
	private void setVisible()
	{
		this.setVisible(false);
	}
	public static void Erfolg(String nachricht) {
		JOptionPane.showMessageDialog(null, nachricht, "Information", JOptionPane.INFORMATION_MESSAGE);

	}
}
