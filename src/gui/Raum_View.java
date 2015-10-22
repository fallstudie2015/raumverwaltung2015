package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import de.dhbw.java.Benutzer;
import de.dhbw.java.Buchung;
import de.dhbw.java.Raum;

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
		bv = new Bestellformular_View(frame, Benutzer.getVorname(), Benutzer.getNachname());
		bv.setRaumName(raumName);
		frame.setBVList(bv);
		frame.setBVPanel(bv);
		bv.initView();
	}

	private JPanel raumzeitenPanel() {
		JPanel raumzeitenPanel = new JPanel();
		raumzeitenPanel.setLayout(new GridLayout(23, 1));

		// raumzeitenPanel.add(getRaumLabel());

		// for (int i = 0; i < 23; i++) {
		// Raum_View_Label label = new Raum_View_Label();
		// label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// label.setPreferredSize(new Dimension(200, 50));
		// raumzeitenPanel.add(label);
		// }

		for (int i = 8; i < 19; i++) {
			Raum_View_Label label = new Raum_View_Label(Time.valueOf("0" + i + ":00:00"));
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			label.setPreferredSize(new Dimension(200, 50));
			label.setOpaque(true);
			raumzeitenPanel.add(label);
			labelList.add(label);
			for (int j = 30; j < 31; j += 15) {
				Raum_View_Label label2 = new Raum_View_Label(Time.valueOf("0" + i + ":" + j + ":00"));
				label2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label2.setPreferredSize(new Dimension(200, 50));
				label2.setOpaque(true);
				raumzeitenPanel.add(label2);
				labelList.add(label2);
			}
			if (i + 1 == 19) {
				Raum_View_Label label3 = new Raum_View_Label(Time.valueOf("0" + (i + 1) + ":00:00"));
				label3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label3.setPreferredSize(new Dimension(200, 50));
				raumzeitenPanel.add(label3);
				label3.setOpaque(true);
				labelList.add(label3);
			}
		}

		return raumzeitenPanel;
	}

	public JLabel getRaumLabel() {
		raumLabel = new JLabel(raumName, SwingConstants.CENTER);
		raumLabel.setFont(new Font(raumName, 0, 18));
		raumLabel.setPreferredSize(new Dimension(200, 50));
		raumLabel.addMouseListener(this);

		return raumLabel;
	}

	public void setBuchungenInCalendar(Date today) {
		labelLeeren();

		for (Buchung buchung : buchungList) {
			if (today.toString().compareTo(buchung.getDatum().toString()) == 0) {
				for (Raum_View_Label label : labelList) {
					if (buchung.getZeitVon().equals(label.getTime()) || buchung.getZeitBis().equals(label.getTime())
							|| (label.getTime().before(buchung.getZeitBis())
									&& label.getTime().after(buchung.getZeitVon()))) {
						label.setBackground(Color.RED);
						// label.setBorder(BorderFactory.createEmptyBorder());
					}
				}

			}
		}
	}

	public void labelLeeren() {
		for (Raum_View_Label label : labelList) {
			label.setBackground(Color.WHITE);
			// label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
	}

	public void getBuchung(Buchung buchung) {
		this.buchungList.add(buchung);
	}

	public void setBuchungNeu(Buchung buchung) {
		this.buchungList.clear();
		this.buchungList.add(buchung);
	}

	public int getRaumID() {
		return raum.getRaumID();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
			frame.getformularScrollPane().setVisible(true);

			frame.validate();
		} else {
			aktiv.setVisible(false);
			i = 0;
			bv.setVisible(true);
			bv.setScrollPane(frame.getformularScrollPane());
			bv.setDate(frame.getCalendar());
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
