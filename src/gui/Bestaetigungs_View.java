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
import mail.MailConnection;
import mail.MailTexte;

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

	private Raumplaner_View mutterFenster;

	public Bestaetigungs_View(Raumplaner_View mutterView,
			Buchung uebergabeBuchung) {

		mutterFenster = mutterView;
		buchung = uebergabeBuchung;
		this.setLayout(new BorderLayout());
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);

		this.befuelleMainPanel();
		this.add(this.createMainPanel(), BorderLayout.CENTER);

		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	private JPanel createButtonPanel() {

		JPanel buttonPanel = new JPanel();

		try {

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
		} catch (Exception ex) {
			Error_Message_Box.laufzeitfehler(ex,
					"gui.Bestaetigungs_View.createButtonPanel");
		}
		return buttonPanel;
	}

	private JPanel createZeitPanel() {

		JPanel zeitPanel = new JPanel();
		try {
			zeitPanel.setLayout(new FlowLayout());

			txtZeitVon = new JTextField("" + buchung.getZeitVon() + "");
			txtZeitBis = new JTextField("" + buchung.getZeitBis() + "");

			zeitPanel.add(txtZeitVon);
			txtZeitVon.setEditable(false);
			zeitPanel.add(new JLabel(" - "));
			zeitPanel.add(txtZeitBis);
			txtZeitBis.setEditable(false);
		} catch (Exception ex) {
			Error_Message_Box.laufzeitfehler(ex,
					"gui.Bestaetigungs_View.createZeitPanel()");
		}

		return zeitPanel;
	}

	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel();
		try {

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

		} catch (Exception ex) {
			Error_Message_Box.laufzeitfehler(ex,
					"gui.Bestaetigungs_View.createMainPanel");
		}
		return mainPanel;
	}

	private void befuelleMainPanel() {
		try {
			txtBuchungsID = new JTextField("" + buchung.getBuchungsID());
			txtRaum = new JTextField(
					"" + SQL_Schnittstelle.getRaumName(buchung.getRaumID()));
			txtBenutzer = new JTextField("" + SQL_Schnittstelle
					.getBenutzerName(buchung.getBenutzerID()));
			txtTelefon = new JTextField("" + buchung.getTelefon());
			txtDatum = new JTextField("" + buchung.getDatum());
			txtZeitVon = new JTextField("" + buchung.getZeitVon());
			txtZeitBis = new JTextField("" + buchung.getZeitBis());
			txtBestuhlung = new JTextField("" + buchung.getBestuhlung());
			txtKommentar = new JTextField("" + buchung.getKommentar());
			txtAusstattung = new JTextField("Hier fehlt die Ausstattung");
		} catch (Exception ex) {
			Error_Message_Box.laufzeitfehler(ex,
					"gui.Bestaetigungs_View.befuelleMainPanel");
		}

	}

	public Raumplaner_View getRaumView() {

		return mutterFenster;
	}

	public Buchung getBuchung() {

		return buchung;
	}

	class bestaetigungsViewListener implements ActionListener {

		Bestaetigungs_View mbv;

		public bestaetigungsViewListener(
				Bestaetigungs_View meineBestaetigungsView) {
			mbv = meineBestaetigungsView;
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnBestaetigen) {
				SQL_Schnittstelle.upadteBuchungStatus(buchung.getBuchungsID(),
						'g');

				mbv.getRaumView()
						.setBuchungArray(SQL_Schnittstelle.getBuchung());
				MailConnection mail = new MailConnection();
				mail.sendMail(
						SQL_Schnittstelle.getBenutzerEmail(
								mbv.getBuchung().getBenutzerID()),
						MailTexte.getBetreffBestaetigen(mbv.getBuchung()),
						MailTexte.getTextBestaetigen(mbv.getBuchung()));
				mbv.getRaumView().getPanelBuchung().reloadTableBuchung();
				mbv.dispose();

			} else if (e.getSource() == btnAblehnen) {

				SQL_Schnittstelle.upadteBuchungStatus(buchung.getBuchungsID(),
						'a');
				mbv.getRaumView()
						.setBuchungArray(SQL_Schnittstelle.getBuchung());
				MailConnection mail = new MailConnection();
				mail.sendMail(
						SQL_Schnittstelle.getBenutzerEmail(
								mbv.getBuchung().getBenutzerID()),
						MailTexte.getBetreffAbgelehnt(mbv.getBuchung()),
						MailTexte.getTextAbgelehnt(mbv.getBuchung()));
				mbv.getRaumView().getPanelBuchung().reloadTableBuchung();
				mbv.dispose();

			} else if (e.getSource() == btnAbbrechen) {
				mbv.dispose();
			}

		}

	}

}
