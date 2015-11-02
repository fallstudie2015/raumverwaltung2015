/* Programmiert von: David Fankhänel
 * Programmiert für: Logout-Button auf der Hauptoberfläche
 * Beschreibung: Dient zum Logout, also zur Abmeldung als User um sich als neuer User einzuloggen
 */

package de.dhbw.gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.dhbw.gui.Raumplaner_View;

public class Logout extends JDialog {

	private Raumplaner_View rv;
	private JPanel contentPane;

	/*
	 * Fenster aufbauen
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
				de.dhbw.gui.Login_View lv = new de.dhbw.gui.Login_View();
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

	/* Oberfläche unsichtbar machen */
	private void setInvisible() {
		this.setVisible(false);
	}
}
