package gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import de.dhbw.java.SQL_Schnittstelle;
import gui.Raumplaner_View;
import gui.externeFrames.BenutzerAnlegen.KeyListenerESC;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JTextField;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class RaumAnlegen extends JDialog {

	private JPanel contentPane;
	private JTextField textField_name;
	private JFormattedTextField textField_personen;
	private ArrayList<String> ausstattungsList = new ArrayList<String>();
	private JTextField textField_strasse;
	private JTextField textField_stock;
	private JTextField textField_a1;
	private JTextField textField_a2;
	private JTextField textField_a3;
	private JTextField textField_a4;
	private JTextField textField_a5;
	private JTextField textField_a6;
	private JTextField textField_a7;
	private JTextField textField_a8;
	private JLabel lblRaumname;
	private JLabel lblStrasse;
	private JLabel lblStockwerk;
	private JLabel lblAnzahlPersonen;
	private JLabel lblAusstattung;
	public Raumplaner_View rv;
	private MeinActionListener mal = new MeinActionListener();
	private KeyListenerESC esc = new KeyListenerESC();

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// RaumAnlegen frame = new RaumAnlegen();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public RaumAnlegen(final Raumplaner_View rv) {
		setModal(true); // Fenster wird aufgebaut
		setIconImage(Toolkit.getDefaultToolkit().getImage(RaumAnlegen.class
				.getResource("/ressources/menu_raum_anlegen_transp.png")));
		setResizable(false);
		this.rv = rv;
		setTitle("Raum anlegen");
		setLocationRelativeTo(this);
		setBounds(100, 100, 310, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblRaumAnlegen = new JLabel("Raum anlegen");
		lblRaumAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRaumAnlegen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRaumAnlegen, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane);

		JButton btnAnlegen = new JButton("Anlegen");

		btnAnlegen.addActionListener(mal); // Aktion Listener

		btnAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnAnlegen);

		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInvisible();// Beim Klicken auf Abbrechen wird Fenster
								// unsichtbar
			}
		});
		btnAbbrechen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setRightComponent(btnAbbrechen);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(12, 0, 0, 0));

		lblRaumname = new JLabel("Raumname");
		lblRaumname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblRaumname);

		lblStrasse = new JLabel("Straße");
		lblStrasse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblStrasse);

		lblStockwerk = new JLabel("Stockwerk");
		lblStockwerk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblStockwerk);

		lblAnzahlPersonen = new JLabel("Max. Anzahl Personen");
		lblAnzahlPersonen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAnzahlPersonen);

		lblAusstattung = new JLabel("Ausstattung");
		lblAusstattung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAusstattung);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(12, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		textField_name = new JTextField();
		textField_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5.add(textField_name, BorderLayout.CENTER);
		textField_name.setColumns(10);
		textField_name.addActionListener(mal);
		textField_name.addKeyListener(esc);

		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		textField_strasse = new JTextField();
		textField_strasse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9.add(textField_strasse, BorderLayout.CENTER);
		textField_strasse.setColumns(10);
		textField_strasse.addActionListener(mal);
		textField_strasse.addKeyListener(esc);

		JPanel panel_10 = new JPanel();
		panel_4.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));

		textField_stock = new JTextField();
		textField_stock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_10.add(textField_stock, BorderLayout.CENTER);
		textField_stock.setColumns(10);
		textField_stock.addActionListener(mal);
		textField_stock.addKeyListener(esc);

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setAllowsInvalid(false);
		textField_personen = new JFormattedTextField(formatter);
		textField_personen.addActionListener(mal);
		textField_personen.addKeyListener(esc);

		textField_personen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6.add(textField_personen, BorderLayout.CENTER);
		textField_personen.setColumns(10);

		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		textField_a1 = new JTextField();
		textField_a1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_a1.setColumns(10);
		panel_7.add(textField_a1, BorderLayout.CENTER);
		textField_a1.addActionListener(mal);
		textField_a1.addKeyListener(esc);

		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));

		textField_a2 = new JTextField();
		textField_a2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(textField_a2, BorderLayout.CENTER);
		textField_a2.setColumns(10);
		textField_a2.addActionListener(mal);
		textField_a2.addKeyListener(esc);

		JPanel panel_11 = new JPanel();
		panel_4.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));

		textField_a3 = new JTextField();
		textField_a3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_11.add(textField_a3, BorderLayout.CENTER);
		textField_a3.setColumns(10);
		textField_a3.addActionListener(mal);
		textField_a3.addKeyListener(esc);

		JPanel panel_12 = new JPanel();
		panel_4.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));

		textField_a4 = new JTextField();
		textField_a4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_12.add(textField_a4, BorderLayout.CENTER);
		textField_a4.setColumns(10);
		textField_a4.addActionListener(mal);
		textField_a4.addKeyListener(esc);

		JPanel panel_13 = new JPanel();
		panel_4.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		textField_a5 = new JTextField();
		textField_a5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_13.add(textField_a5, BorderLayout.CENTER);
		textField_a5.setColumns(10);
		textField_a5.addActionListener(mal);
		textField_a5.addKeyListener(esc);

		JPanel panel_14 = new JPanel();
		panel_4.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));

		textField_a6 = new JTextField();
		textField_a6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_14.add(textField_a6, BorderLayout.CENTER);
		textField_a6.setColumns(10);
		textField_a6.addActionListener(mal);
		textField_a6.addKeyListener(esc);

		JPanel panel_15 = new JPanel();
		panel_4.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));

		textField_a7 = new JTextField();
		textField_a7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_15.add(textField_a7, BorderLayout.CENTER);
		textField_a7.setColumns(10);
		textField_a7.addActionListener(mal);
		textField_a7.addKeyListener(esc);

		JPanel panel_16 = new JPanel();
		panel_4.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));

		textField_a8 = new JTextField();
		textField_a8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_16.add(textField_a8, BorderLayout.CENTER);
		textField_a8.setColumns(10);
		textField_a8.addActionListener(mal);
		textField_a8.addActionListener(mal);
		textField_a8.addKeyListener(esc);
	}

	private boolean PflichtfelderPruefen() // Prüft, ob Pflichtfelder gefüllt
											// sind
	{
		boolean gefuellt = true;

		if (textField_name.getText().isEmpty()) {
			lblRaumname.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblRaumname.setForeground(Color.black);
		}

		if (textField_personen.getText().isEmpty()) {
			lblAnzahlPersonen.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblAnzahlPersonen.setForeground(Color.black);
		}

		if (textField_stock.getText().isEmpty()) {
			lblStockwerk.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblStockwerk.setForeground(Color.black);
		}

		if (textField_strasse.getText().isEmpty()) {
			lblStrasse.setForeground(Color.red);
			gefuellt = false;
		} else {
			lblStrasse.setForeground(Color.black);
		}

		return gefuellt;
	}

	private ArrayList<String> Ausstattung() { // eingegebene Ausstattung in
												// Array List laden, und
		if (!textField_a1.getText().isEmpty()) {
			ausstattungsList.add(textField_a1.getText());
		}

		if (!textField_a2.getText().isEmpty()) {
			ausstattungsList.add(textField_a2.getText());
		}

		if (!textField_a3.getText().isEmpty()) {
			ausstattungsList.add(textField_a3.getText());
		}

		if (!textField_a4.getText().isEmpty()) {
			ausstattungsList.add(textField_a4.getText());
		}

		if (!textField_a5.getText().isEmpty()) {
			ausstattungsList.add(textField_a5.getText());
		}

		if (!textField_a6.getText().isEmpty()) {
			ausstattungsList.add(textField_a6.getText());
		}

		if (!textField_a7.getText().isEmpty()) {
			ausstattungsList.add(textField_a7.getText());
		}

		if (!textField_a8.getText().isEmpty()) {
			ausstattungsList.add(textField_a8.getText());
		}

		return ausstattungsList;
	}

	private void setInvisible() { // Fenster unsichtbar machen
		this.setVisible(false);
	}

	public static void erfolg(String nachricht) { // MessageBox für Rückgabewert
		JOptionPane.showMessageDialog(null, nachricht, "Information",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public class MeinActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			boolean pflicht = PflichtfelderPruefen();

			if (pflicht) {

				boolean feedback = SQL_Schnittstelle.insertRaum( // Aufruf
																	// RaumAnlegen
																	// Methode
						textField_name.getText(), textField_strasse.getText(),
						textField_stock.getText(),
						Integer.parseInt(textField_personen.getText()),
						Ausstattung());

				if (feedback == true) // Rückgabewert der Methode
										// Ausstattung anlegen
				{
					setInvisible();
					erfolg("Raum wurde angelegt");
					System.out.println("Raum wurde angelegt");
					rv.setRaumArray(SQL_Schnittstelle.getRooms()); // aktualisiert
																	// die
																	// Räume
				} else {
					erfolg("Raum konnte nicht angelegt werden");
					System.out.println("Raum konnte nicht angelegt werden");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						" Bitte fuellen Sie die Pflichtfelder aus", "Achtung!",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	public class KeyListenerESC implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				setInvisible();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
