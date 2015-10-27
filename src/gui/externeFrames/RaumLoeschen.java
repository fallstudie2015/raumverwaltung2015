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

import de.dhbw.java.SQL_Schnittstelle;

import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class RaumLoeschen extends JDialog {

	private JPanel contentPane;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RaumLoeschen frame = new RaumLoeschen();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public RaumLoeschen() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RaumLoeschen.class.getResource("/ressources/menu_raum_loeschen_transp.png")));
		setResizable(false);
		setTitle("Raum loeschen");
		setLocationRelativeTo(this);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRaumAnlegen = new JLabel("Raum loeschen");
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
				boolean feedback = SQL_Schnittstelle.setDeleteFlagRaum(textField_1.getText());
				if (feedback == true)
				{
					setVisible();
					Erfolg("Raum wurde gelöscht");
				}
				else
				{
					Erfolg("Raum konnte nicht gelöscht werden");
				}
			}
		});
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
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(8, 0, 0, 0));
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(label);
		
		JLabel label_1 = new JLabel("Raumname:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(label_1);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(8, 0, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_4.add(textField_1);
	}
	
	private void setVisible()
	{
		this.setVisible(false);
	}
	
	public static void Erfolg(String nachricht) {
		JOptionPane.showMessageDialog(null, nachricht, "Information", JOptionPane.INFORMATION_MESSAGE);

	}
}
