package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.dhbw.java.Buchung;
import de.dhbw.java.SQL_Schnittstelle;

public class Bestaetigungs_View extends JFrame {

	private Buchung buchung;

	private JLabel buchungsID;
	private JLabel raum;
	private JLabel benutzer;
	private JLabel telefon;
	private JLabel datum;
	private JLabel zeit;
	private JLabel bestuhlung;
	private JLabel kommentar;
	private JLabel ausstattung;

	private JTextField txtBuchungsID;
	private JTextField txtRaum;
	private JTextField txtBenutzer;
	private JTextField txtTelefon;
	private JTextField txtDatum;
	private JTextField txtZeitVon;
	private JTextField txtZeitBis;
	private JTextField txtBestuhlung;
	private JTextField txtKommentar;
	private JTextField txtAusstattung;

	private JButton btnBestaetigen;
	private JButton btnAblehnen;
	private JButton btnAbbrechen;

	private bestaetigungsViewListener meinBVL;

	public Bestaetigungs_View(Buchung uebergabeBuchung) {

		buchung = uebergabeBuchung;
		this.setLayout(new BorderLayout());
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);

		this.befuelleMainPanel();
		this.add(this.createMainPanel(), BorderLayout.CENTER);

		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private JPanel createButtonPanel() {

		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new FlowLayout());
		btnBestaetigen = new JButton("Genehmigen");
		btnAblehnen = new JButton("Ablehnen");
		btnAbbrechen = new JButton("Abbrechen");

		meinBVL = new bestaetigungsViewListener(this);

		btnBestaetigen.addActionListener(meinBVL);
		btnAblehnen.addActionListener(meinBVL);
		btnAbbrechen.addActionListener(meinBVL);
		buttonPanel.add(btnBestaetigen);
		buttonPanel.add(btnAblehnen);
		buttonPanel.add(btnAbbrechen);

		return buttonPanel;
	}

	private JPanel createZeitPanel() {

		JPanel zeitPanel = new JPanel();
		zeitPanel.setLayout(new FlowLayout());

		txtZeitVon = new JTextField("" + buchung.getZeitVon() + "");
		txtZeitBis = new JTextField("" + buchung.getZeitBis() + "");

		zeitPanel.add(txtZeitVon);
		txtZeitVon.setEditable(false);
		zeitPanel.add(new JLabel(" - "));
		zeitPanel.add(txtZeitBis);
		txtZeitBis.setEditable(false);

		return zeitPanel;
	}

	private JPanel createMainPanel() {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(9, 2));

		buchungsID = new JLabel("Buchungs-ID");
		raum = new JLabel("Raum");
		benutzer = new JLabel("Benutzer");
		telefon = new JLabel("Telefonnummer");
		datum = new JLabel("Datum");
		zeit = new JLabel("Zeit");
		bestuhlung = new JLabel("Bestuhlung");
		kommentar = new JLabel("Kommentar");
		ausstattung = new JLabel("Ausstattung");

		mainPanel.add(buchungsID);
		mainPanel.add(txtBuchungsID);
		txtBuchungsID.setEditable(false);
		mainPanel.add(raum);
		mainPanel.add(txtRaum);
		txtRaum.setEditable(false);
		mainPanel.add(benutzer);
		mainPanel.add(txtBenutzer);
		txtBenutzer.setEditable(false);
		mainPanel.add(telefon);
		mainPanel.add(txtTelefon);
		txtTelefon.setEditable(false);
		mainPanel.add(datum);
		mainPanel.add(txtDatum);
		txtDatum.setEditable(false);
		mainPanel.add(zeit);
		mainPanel.add(createZeitPanel());
		mainPanel.add(bestuhlung);
		mainPanel.add(txtBestuhlung);
		txtBestuhlung.setEditable(false);
		mainPanel.add(kommentar);
		mainPanel.add(txtKommentar);
		txtKommentar.setEditable(false);
		mainPanel.add(ausstattung);
		mainPanel.add(txtAusstattung);
		txtAusstattung.setEditable(false);

		return mainPanel;

	}

	private void befuelleMainPanel() {

		txtBuchungsID = new JTextField("" + buchung.getBuchungsID());
		txtRaum = new JTextField(
				"" + SQL_Schnittstelle.getRaumName(buchung.getRaumID()));
		txtBenutzer = new JTextField(""
				+ SQL_Schnittstelle.getBenutzerName(buchung.getBenutzerID()));
		txtTelefon = new JTextField("" + buchung.getTelefon());
		txtDatum = new JTextField("" + buchung.getDatum());
		txtZeitVon = new JTextField("" + buchung.getZeitVon());
		txtZeitBis = new JTextField("" + buchung.getZeitBis());
		txtBestuhlung = new JTextField("" + buchung.getBestuhlung());
		txtKommentar = new JTextField("" + buchung.getKommentar());
		txtAusstattung = new JTextField("Hier fehlt die Ausstattung");

	}

	class bestaetigungsViewListener implements ActionListener {

		JFrame mbv;

		public bestaetigungsViewListener(JFrame meineBestaetigungsView) {
			mbv = meineBestaetigungsView;
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnBestaetigen) {
				SQL_Schnittstelle.upadteBuchungStatus(buchung.getBuchungsID(),
						'g');
				System.out.println("Bestätigt");

			} else if (e.getSource() == btnAblehnen) {

				SQL_Schnittstelle.upadteBuchungStatus(buchung.getBuchungsID(),
						's');
				System.out.println("Abgelehnt");

			} else if (e.getSource() == btnAbbrechen) {
				mbv.dispose();
			}

		}

	}

}
