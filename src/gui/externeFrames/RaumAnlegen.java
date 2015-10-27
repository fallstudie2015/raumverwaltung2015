package gui.externeFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.dhbw.java.SQL_Schnittstelle;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class RaumAnlegen extends JDialog {

	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_personen;
	private ArrayList<String> testArrayList = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaumAnlegen frame = new RaumAnlegen();
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
	public RaumAnlegen() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(RaumAnlegen.class.getResource("/ressources/menu_raum_anlegen_transp.png")));
		setResizable(false);

		ArrayFuellen(); //Bestehende Ausstattung wird in Array geladen

		setTitle("Raum anlegen");
		setLocationRelativeTo(this);
		setBounds(100, 100, 310, 363);
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
		btnAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	SQL_Schnittstelle.insertRaum(name, strasse, stock, maxAnzPersonen, grundAusstattungList)
			}
		});
		btnAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane.setLeftComponent(btnAnlegen);

		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible();
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
		panel_3.setLayout(new GridLayout(10, 0, 0, 0));

		JLabel lblRaumname = new JLabel("Raumname");
		lblRaumname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblRaumname);

		JLabel lblAnzahlPersonen_1 = new JLabel("Anzahl Personen");
		lblAnzahlPersonen_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAnzahlPersonen_1);

		JLabel lblAnzahlPersonen = new JLabel("Ausstattung");
		lblAnzahlPersonen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblAnzahlPersonen);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(10, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		textField_name = new JTextField();
		textField_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5.add(textField_name, BorderLayout.CENTER);
		textField_name.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		textField_personen = new JTextField();
		textField_personen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6.add(textField_personen, BorderLayout.CENTER);
		textField_personen.setColumns(10);

		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		Object testArray[] = testArrayList.toArray();
		JComboBox comboBox_0 = new JComboBox(testArray);
		comboBox_0.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_7.add(comboBox_0, BorderLayout.CENTER);

		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));

		JComboBox comboBox = new JComboBox(testArray);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setEnabled(false);
		panel_8.add(comboBox, BorderLayout.CENTER);

		JComboBox comboBox_1 = new JComboBox(testArray);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_1.setEnabled(false);
		panel_4.add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox(testArray);
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_2.setEnabled(false);
		panel_4.add(comboBox_2);

		JComboBox comboBox_3 = new JComboBox(testArray);
		comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_3.setEnabled(false);
		panel_4.add(comboBox_3);

		JComboBox comboBox_4 = new JComboBox(testArray);
		comboBox_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_4.setEnabled(false);
		panel_4.add(comboBox_4);

		JComboBox comboBox_5 = new JComboBox(testArray);
		comboBox_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_5.setEnabled(false);
		panel_4.add(comboBox_5);

		JComboBox comboBox_6 = new JComboBox(testArray);
		comboBox_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_6.setEnabled(false);
		panel_4.add(comboBox_6);

	}

	private void ArrayFuellen() {
		testArrayList.add("");
		testArrayList.add("Ausstattung 1");
		testArrayList.add("Ausstattung 2");
		testArrayList.add("Ausstattung 3");
		testArrayList.add("Ausstattung 4");
		testArrayList.add("Ausstattung 5");
		
	}
	
	private void setVisible()
	{
		this.setVisible(false);
	}
}
