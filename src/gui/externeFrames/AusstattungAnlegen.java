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
import java.awt.Toolkit;

public class AusstattungAnlegen extends JDialog {

	private JPanel contentPane;
	private JTextField textField_1;

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
		setModal(true); 					//Fenster wird aufgebaut
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AusstattungAnlegen.class.getResource(
						"/ressources/menu_ausstattung_anlegen_transp.png")));
		setResizable(false);
		setTitle("Ausstattung anlegen");
		setLocationRelativeTo(null);
		setBounds(100, 100, 310, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblAusstattungAnlegen = new JLabel("Ausstattung anlegen");
		lblAusstattungAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAusstattungAnlegen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAusstattungAnlegen, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);

		JButton btnAnlegen = new JButton("Anlegen"); 
		btnAnlegen.addActionListener(new ActionListener() { //Aktion Listener und Anbindung Methode für Ausstattung Anlegen
			public void actionPerformed(ActionEvent e) {
				boolean feedback = SQL_Schnittstelle
						.insertAusstattungArt(textField_1.getText());

				if (feedback == true) {						//Rückgabewert der Methode Ausstattung anlegen
					setVisible();
					Erfolg("Ausstattung wurde erstellt!");
				} else {
					Erfolg("Ausstattung konnte nicht erstellt werden!");
				}
			}
		});
		btnAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnAnlegen);

		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible();//Beim Klicken auf Abbrechen wird Fenster unsichtbar
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

		JLabel lblAusstattung = new JLabel("");
		lblAusstattung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung);

		JLabel lblAusstattung_1 = new JLabel("Ausstattungsname:");
		lblAusstattung_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung_1);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(8, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);

		textField_1 = new JTextField();
		panel_4.add(textField_1);
		textField_1.setColumns(10);
	}

	private void setVisible() { //Fenster unsichtbar machen 
		this.setVisible(false);
	}

	public static void Erfolg(String nachricht) { //MessageBox für Rückgabewert
		JOptionPane.showMessageDialog(null, nachricht, "Information",
				JOptionPane.INFORMATION_MESSAGE);

	}
}
