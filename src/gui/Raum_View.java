package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.dhbw.java.Benutzer;
import de.dhbw.java.Buchung;
import de.dhbw.java.Raum;
import de.dhbw.java.SQL_Schnittstelle;

public class Raum_View extends JPanel implements MouseListener {

	private JLabel raumLabel;
	private String raumName;
	private Bestellformular_View bv;
	private Raumplaner_View frame;
	private ArrayList<Buchung> buchungList;
	private Raum raum;
	private ArrayList<Raum_View_Label> labelList;

	public Raum_View(Raum raum, Raumplaner_View frame) {
		this.raum = raum;
		this.raumName = raum.getName();
		this.frame = frame;
		setBestellformularView();
		labelList = new ArrayList<Raum_View_Label>();
		buchungList = new ArrayList<Buchung>();
		initView();
	}

	private void initView() {
		this.setLayout(new GridLayout(1, 1));
		this.add(raumzeitenPanel());
	}

	private void setBestellformularView() {
		// Bestellformular view erstellen
		bv = new Bestellformular_View(frame, Benutzer.getVorname(), Benutzer.getNachname(), raum.getRaumID());
		bv.setRaumName(raumName);
		frame.setBVList(bv);
		frame.setBVPanel(bv);
		bv.initView();
	}

	private JPanel raumzeitenPanel() {
		JPanel raumzeitenPanel = new JPanel();
		raumzeitenPanel.setLayout(new GridLayout(23, 1));

		for (int i = 8; i < 19; i++) {
			// Halbestunde_Panel panel = new Halbestunde_Panel(new
			// Raum_View_Label(Time.valueOf("0" + i + ":00:00")));
			// raumzeitenPanel.add(panel);
			// labelList.add(panel.getRaumViewLabel());
			for (int k = 15; k < 16; k += 15) {
				Halbestunde_Panel panel2 = new Halbestunde_Panel(new Raum_View_Label(Time.valueOf("0" + i + ":00:00")),
						new Raum_View_Label(Time.valueOf("0" + i + ":" + k + ":00")));
				raumzeitenPanel.add(panel2);
				labelList.add(panel2.getRaumViewLabelOben());
				labelList.add(panel2.getRaumViewLabelUnten());
				for (int j = 30; j < 31; j += 15) {
					// Halbestunde_Panel panel3 = new Halbestunde_Panel(
					// , true);
					// raumzeitenPanel.add(panel3);
					// labelList.add(panel3.getRaumViewLabel());
					for (int l = 45; l < 46; l += 15) {
						Halbestunde_Panel panel4 = new Halbestunde_Panel(
								new Raum_View_Label(Time.valueOf("0" + i + ":" + j + ":00")),
								new Raum_View_Label(Time.valueOf("0" + i + ":" + l + ":00")));
						raumzeitenPanel.add(panel4);
						labelList.add(panel4.getRaumViewLabelOben());
						labelList.add(panel4.getRaumViewLabelUnten());
					}
				}
			}
			if (i + 1 == 19) {
				Halbestunde_Panel panel5 = new Halbestunde_Panel(
						new Raum_View_Label(Time.valueOf("0" + (i + 1) + ":00:00")),
						new Raum_View_Label(Time.valueOf("0" + (i + 1) + ":15:00")));
				raumzeitenPanel.add(panel5);
				labelList.add(panel5.getRaumViewLabelOben());
				labelList.add(panel5.getRaumViewLabelUnten());
			}
		}

		return raumzeitenPanel;
	}

	public JLabel getRaumLabel() {
		raumLabel = new JLabel(raumName, SwingConstants.CENTER);
		raumLabel.setFont(new Font(raumName, 0, 18));
		raumLabel.setPreferredSize(new Dimension(200, 50));
		// raumLabel.addMouseListener(this);

		return raumLabel;
	}

	public void setBuchungenInCalendar(Date today) {
		labelLeeren();

		Color farbe;

		for (Buchung buchung : buchungList) {
			if (buchung.getBenutzerID() == Benutzer.getBenutzerID()) {
				farbe = Color.GREEN;
			} else {
				farbe = Color.RED;
			}
			if (today.toString().compareTo(buchung.getDatum().toString()) == 0) {
				for (Raum_View_Label label : labelList) {
					if (buchung.getZeitVon().equals(label.getTime()) || buchung.getZeitBis().equals(label.getTime())
							|| (label.getTime().before(buchung.getZeitBis())
									&& label.getTime().after(buchung.getZeitVon()))) {
						if (Benutzer.getBenutzertyp() == 'v') {
							if (buchung.getZeitVon().equals(label.getTime())) {
								label.setText(SQL_Schnittstelle.getBenutzerName(buchung.getBenutzerID()));
								label.setHorizontalTextPosition(SwingConstants.CENTER);
							}
							label.setToolTipText(
									"<html>" + raum.getName() + "<br>" + raum.getStrasse() + "<br>" + raum.getStock()
											+ "<br>" + SQL_Schnittstelle.getBenutzerName(buchung.getBenutzerID())
											+ "<br>" + buchung.getTelefon() + "<br>" + "</html>");
						}
						if (buchung.getStatus().equals("v")) {
							ImageIcon ii = new ImageIcon(
									getClass().getClassLoader().getResource("ressources/muster.jpg"));
							ImageIcon imageIcon = new ImageIcon(
									ii.getImage().getScaledInstance(200, 20, Image.SCALE_DEFAULT));
							label.setIcon(imageIcon);
						} else {
							label.setBackground(farbe);
							label.setBuchung(buchung);
						}
					}
				}

			}
		}
	}

	public void labelLeeren() {
		for (Raum_View_Label label : labelList) {
			label.setIcon(null);
			label.setText(null);
			label.setBackground(label.getParent().getBackground());
			label.setMouseListener(this);
		}
	}

	public void getBuchung(Buchung buchung) {
		this.buchungList.add(buchung);
	}

	public void setBuchungNeu(Buchung buchung) {
		this.buchungList.add(buchung);
	}

	public void deleteBuchungList() {
		this.buchungList.clear();
	}

	public int getRaumID() {
		return raum.getRaumID();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Raum_View_Label label = (Raum_View_Label) e.getSource();

		int i = 0;
		Bestellformular_View aktiv = null;
		for (Bestellformular_View view : frame.getBVList()) {
			if (view.isVisible()) {
				aktiv = view;
				i++;

			}
		}
		if (i == 0) {
			bv.setVisible(true);
			bv.setScrollPane(frame.getformularScrollPane());
			bv.setDate(frame.getCalendar());
			bv.setMaxPersonen(raum.getAnzPersonen());
			bv.setZeitCB(label.getTime().toString().substring(0, 2), label.getTime().toString().substring(3, 5));

			frame.getformularScrollPane().setVisible(true);

			frame.validate();
		} else {
			aktiv.setVisible(false);
			i = 0;
			bv.setVisible(true);
			bv.setScrollPane(frame.getformularScrollPane());
			bv.setDate(frame.getCalendar());
			bv.setZeitCB(label.getTime().toString().substring(0, 2), label.getTime().toString().substring(3, 5));
			frame.getformularScrollPane().setVisible(true);

			frame.validate();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public String getRaumName() {
		return raumName;
	}
}
