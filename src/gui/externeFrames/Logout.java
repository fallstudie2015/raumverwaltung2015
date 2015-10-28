package gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.java.SQL_Schnittstelle;
import gui.Raumplaner_View;
import startpunkt.Start;

public class Logout extends JDialog {

	private Raumplaner_View rv;
	private JPanel contentPane;

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
	public Logout(Raumplaner_View rv) {
		this.rv = rv;
		setModal(true);
		setType(Type.UTILITY);
		setTitle("Wirklich ausloggen?");
		setResizable(false);
		setLocationRelativeTo(this);
		setBounds(100, 100, 310, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);

		JButton btnAusloggen = new JButton("Logout");
		btnAusloggen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rv.dispose();
				gui.Login_View lv = new gui.Login_View();
				lv.setVisible(true);
				dispose();
			}
		});
		btnAusloggen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnAusloggen);

		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setInvisible();
			}
		});
		splitPane.setRightComponent(btnAbbrechen);
		
		JLabel lblAusloggen = new JLabel("Logout?");
		lblAusloggen.setHorizontalAlignment(SwingConstants.CENTER);
		lblAusloggen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblAusloggen, BorderLayout.CENTER);
	}

	private void setInvisible() {
		this.setVisible(false);
	}
}
