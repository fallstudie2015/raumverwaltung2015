package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.dhbw.java.Buchung;
import de.dhbw.java.SQL_Schnittstelle;
import mail.MailConnection;
import mail.MailTexte;

public class Bestaetigungs_View extends JDialog {

	private Buchung buchung;

	private String pufferStart;

	private JLabel buchungsID;
	private JLabel raum;
	private JLabel benutzer;
	private JLabel telefon;
	private JLabel datum;
	private JLabel zeit;
	private JLabel bestuhlung;
	private JLabel kommentar;
	private JLabel ausstattung;
	private JLabel puffer;

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

	private JComboBox<String> cbPuffer;
	private String minute[] = { "keinen", "15", "30", "45" };

	private JButton btnBestaetigen;
	private JButton btnAblehnen;
	private JButton btnAbbrechen;

	private bestaetigungsViewListener meinBVL;

	private Raumplaner_View mutterFenster;

	/*
	 * Konstruktor zum Erzeugen der Bestätigungs_View mit Einstellungen zu
	 * Größe, Formatierung und Lage
	 */
	public Bestaetigungs_View(Raumplaner_View mutterView,
			Buchung uebergabeBuchung) {

		mutterFenster = mutterView;
		buchung = uebergabeBuchung;
		this.setModal(true);
		this.setLayout(new BorderLayout());
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);

		this.befuelleMainPanel();
		this.add(this.createMainPanel(), BorderLayout.CENTER);

		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	/* Panel mit den Button für den unteren Teil der Oberfläche erstellen. */
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

	/*
	 * Erzeugung eines kleinen Panels für die Zeitangabe erstellen, da dies eine
	 * andere Formatierung besitzt.
	 */
	private JPanel createZeitPanel() {

		JPanel zeitPanel = new JPanel();
		try {
			zeitPanel.setLayout(new GridLayout(1, 3));

			txtZeitVon = new JTextField("" + buchung.getZeitVon() + "");
			txtZeitBis = new JTextField("" + buchung.getZeitBis() + "");

			zeitPanel.add(txtZeitVon);
			txtZeitVon.setEditable(false);
			zeitPanel.add(new JLabel(" - ", SwingConstants.CENTER));
			zeitPanel.add(txtZeitBis);
			txtZeitBis.setEditable(false);
		} catch (Exception ex) {
			Error_Message_Box.laufzeitfehler(ex,
					"gui.Bestaetigungs_View.createZeitPanel()");
		}

		return zeitPanel;
	}

	/*
	 * Hauptpanel anlegen mit Labeln und Textfeldern. Hier wird auch das
	 * Zeitpanel eingefügt
	 */
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel();
		try {

			mainPanel.setLayout(new GridLayout(10, 2));

			buchungsID = new JLabel("Buchungs-ID");
			raum = new JLabel("Raum");
			benutzer = new JLabel("Benutzer");
			telefon = new JLabel("Telefonnummer");
			datum = new JLabel("Datum");
			zeit = new JLabel("Zeit");
			bestuhlung = new JLabel("Bestuhlung");
			kommentar = new JLabel("Kommentar");
			ausstattung = new JLabel("Ausstattung");
			puffer = new JLabel("Puffer eintragen");

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
			mainPanel.add(puffer);
			mainPanel.add(cbPuffer);

		} catch (Exception ex) {
			Error_Message_Box.laufzeitfehler(ex,
					"gui.Bestaetigungs_View.createMainPanel");
		}
		return mainPanel;
	}

	/*
	 * Daten werden aus der Buchungsinstanz ausgelesen und in die Textfelder
	 * geschrieben
	 */
	private void befuelleMainPanel() {
		try {
			txtBuchungsID = new JTextField("" + buchung.getBuchungsID());
			txtRaum = new JTextField(
					"" + SQL_Schnittstelle.getRaumName(buchung.getRaumID()));
			txtBenutzer = new JTextField("" + SQL_Schnittstelle
					.getBenutzerName(buchung.getBenutzerID()));
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
			befuelleCbPuffer(buchung.getZeitVon());
			txtAusstattung = new JTextField(SQL_Schnittstelle
					.getAusstattungBuchung(buchung.getBuchungsID()));
			cbPuffer = new JComboBox<String>(minute);
		} catch (Exception ex) {
			Error_Message_Box.laufzeitfehler(ex,
					"gui.Bestaetigungs_View.befuelleMainPanel");
		}

	}

	private boolean setPuffer() {
		if ("keinen".compareTo((String) cbPuffer.getSelectedItem()) != 0) {
			System.out.println();
			System.out.println("Zeit von: " + buchung.getZeitVon()
					+ " Zeit bis: " + buchung.getZeitBis());
			System.out.println("Zeit abziehen: "
					+ Time.valueOf("00:" + cbPuffer.getSelectedItem() + ":00"));
			long diff = buchung.getZeitVon().getTime()
					- Time.valueOf("01:" + cbPuffer.getSelectedItem() + ":00")
							.getTime();
			System.out.println("Pufferzeit start: " + new Time(diff));
			pufferStart = new Time(diff).toString();
			Time zeitVonPuffer = Time.valueOf(pufferStart);
			System.out.println(zeitVonPuffer);

			SQL_Schnittstelle.insertBuchung("", buchung.getDatum(),
					zeitVonPuffer, buchung.getZeitVon(), "", "", 1021,
					buchung.getRaumID(), 'p', 0, null, false);
		}
		return true;
	}

	public Raumplaner_View getRaumView() {

		return mutterFenster;
	}

	public Buchung getBuchung() {

		return buchung;
	}

	public void befuelleCbPuffer(Time zeitVon) {
		ArrayList<Raum_View_Label> labelListe = mutterFenster
				.getLabellist(buchung.getRaumID());
		int startInt = 0;

		for (Raum_View_Label label : labelListe) {
			if (label.getTime().equals(zeitVon)) {
				System.out.println();
				System.out.println(
						"Aktuelles Label: " + labelListe.indexOf(label));
				startInt = labelListe.indexOf(label);
				if (startInt > 2) {
					if (labelListe.get(startInt - 1).buchungGesetzt == false
							&& labelListe
									.get(startInt - 2).buchungGesetzt == false
							&& labelListe.get(
									startInt - 3).buchungGesetzt == false) {
						minute = new String[4];
						minute[0] = "keinen";
						minute[1] = "15";
						minute[2] = "30";
						minute[3] = "45";
					} else if (labelListe
							.get(startInt - 1).buchungGesetzt == false
							&& labelListe.get(
									startInt - 2).buchungGesetzt == false) {
						minute = new String[3];
						minute[0] = "keinen";
						minute[1] = "15";
						minute[2] = "30";
					} else if (labelListe
							.get(startInt - 1).buchungGesetzt == false) {
						minute = new String[2];
						minute[0] = "keinen";
						minute[1] = "15";
					} else {
						minute = new String[1];
						minute[0] = "keinen";
					}
				} else if (startInt > 1) {
					if (labelListe.get(startInt - 1).buchungGesetzt == false
							&& labelListe.get(
									startInt - 2).buchungGesetzt == false) {
						minute = new String[3];
						minute[0] = "keinen";
						minute[1] = "15";
						minute[2] = "30";
					} else if (labelListe
							.get(startInt - 1).buchungGesetzt == false) {
						minute = new String[2];
						minute[0] = "keinen";
						minute[1] = "15";
					} else {
						minute = new String[1];
						minute[0] = "keinen";
					}
				} else if (startInt > 0) {
					if (labelListe.get(startInt - 1).buchungGesetzt == false) {
						minute = new String[2];
						minute[0] = "keinen";
						minute[1] = "15";
					} else {
						minute = new String[1];
						minute[0] = "keinen";
					}
				} else if (startInt == 0) {
					minute = new String[1];
					minute[0] = "keinen";
				}
			}
		}

	}

	/* Listener für die drei Button erstellen */
	class bestaetigungsViewListener implements ActionListener {

		Bestaetigungs_View mbv;

		public bestaetigungsViewListener(
				Bestaetigungs_View meineBestaetigungsView) {
			mbv = meineBestaetigungsView;
		}

		public void actionPerformed(ActionEvent e) {

			/*
			 * Die Buchung bekommt das Flag 'g' für genehmigt, eine Mail wird an
			 * den Ersteller gesendet und die Tabelle mit den Anträgen wird neu
			 * geladen.
			 */
			if (e.getSource() == btnBestaetigen) {
				SQL_Schnittstelle.upadteBuchungStatus(buchung.getBuchungsID(),
						'g');

				setPuffer();

				mbv.getRaumView()
						.setBuchungArray(SQL_Schnittstelle.getBuchungPlus());

				MailConnection mail = new MailConnection();

				mail.sendMail(
						SQL_Schnittstelle.getBenutzerEmail(
								mbv.getBuchung().getBenutzerID()),
						MailTexte.getBetreffBestaetigen(mbv.getBuchung()),
						MailTexte.getTextBestaetigen(mbv.getBuchung()));

				if (pufferStart == null) {
					mail.sendMail(MailTexte.hausmeisterPostfach,
							MailTexte.getBetreffBestaetigenHausmeister(
									mbv.getBuchung()),
							MailTexte.getTextBestaetigenHausmeister(
									mbv.getBuchung()));
				} else {
					mail.sendMail(MailTexte.hausmeisterPostfach,
							MailTexte.getBetreffBestaetigenHausmeister(
									mbv.getBuchung()),
							MailTexte.getTextBestaetigenHausmeisterMitPuffer(
									mbv.getBuchung(), pufferStart));
				}

				mbv.getRaumView().getPanelBuchung().reloadTableBuchung();
				mbv.dispose();

			}

			/*
			 * Die Buchung bekommt das Flag 'a' für abgelehnt, eine Mail wird an
			 * den Ersteller gesendet und die Tabelle mit den Anträgen wird neu
			 * geladen.
			 */
			else if (e.getSource() == btnAblehnen) {

				SQL_Schnittstelle.upadteBuchungStatus(buchung.getBuchungsID(),
						'a');

				mbv.getRaumView()
						.setBuchungArray(SQL_Schnittstelle.getBuchungPlus());
				MailConnection mail = new MailConnection();

				mail.sendMail(
						SQL_Schnittstelle.getBenutzerEmail(
								mbv.getBuchung().getBenutzerID()),
						MailTexte.getBetreffAbgelehnt(mbv.getBuchung()),
						MailTexte.getTextAbgelehnt(mbv.getBuchung()));

				mbv.getRaumView().getPanelBuchung().reloadTableBuchung();

				mbv.dispose();

			} else if (e.getSource() == btnAbbrechen) {
				// befuelleCbPuffer(buchung.getZeitVon());
				mbv.dispose();
			}

		}
	}

}
