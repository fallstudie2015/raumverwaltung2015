package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import de.dhbw.java.Buchung;
import de.dhbw.java.Raum;

public class Raum_View extends JPanel implements MouseListener {

	private JLabel raumLabel;
	private String raumName;
	private Raum_View_Label label;
	private Bestellformular_View bv;
	private Raumplaner_View frame;
	private Buchung buchung;
	private ArrayList<Raum_View_Label> labelList;

	public Raum_View(Raum raum, Bestellformular_View bv, Raumplaner_View frame) {
		this.raumName = raum.getName();
		this.bv = bv;
		this.frame = frame;
		labelList = new ArrayList<Raum_View_Label>();
		initView();
	}

	private void initView() {
		this.setLayout(new GridLayout(1, 1));
		this.add(raumzeitenPanel());
	}

	private JPanel raumzeitenPanel() {
		JPanel raumzeitenPanel = new JPanel();
		raumzeitenPanel.setLayout(new GridLayout(23, 1));

		// raumzeitenPanel.add(getRaumLabel());

		// for (int i = 0; i < 23; i++) {
		// label = new Raum_View_Label();
		// label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// label.setPreferredSize(new Dimension(200, 50));
		// raumzeitenPanel.add(label);
		// }

		for (int i = 8; i < 19; i++) {
			label = new Raum_View_Label(Time.valueOf(i + ":00"));
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			label.setPreferredSize(new Dimension(200, 50));
			raumzeitenPanel.add(label);
			labelList.add(label);
			for (int j = 30; j < 31; j += 15) {
				label = new Raum_View_Label(Time.valueOf(i + ":" + j));
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label.setPreferredSize(new Dimension(200, 50));
				raumzeitenPanel.add(label);
				labelList.add(label);
			}
			if (i + 1 == 19) {
				label = new Raum_View_Label(Time.valueOf((i + 1) + ":00"));
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label.setPreferredSize(new Dimension(200, 50));
				raumzeitenPanel.add(label);
				labelList.add(label);
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

	public void setBuchungen(Date today) {
		long diff = buchung.getZeitVon().getTime()
				- buchung.getZeitBis().getTime();
		if (today == buchung.getDatum()) {
			for (Raum_View_Label label : labelList) {
				if (buchung.getZeitVon() == label.getTime()
						|| buchung.getZeitBis() == label.getTime()
						|| ((buchung.getZeitBis().getTime() - label.getTime()
								.getTime()) <= diff && (buchung.getZeitBis()
								.getTime() - label.getTime().getTime() >= 0))) {
					label.setBackground(Color.RED);
				}
			}
		}
	}

	public void getBuchung(Buchung buchung) {
		this.buchung = buchung;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int i = 0;
		Bestellformular_View aktiv = null;
		for (Bestellformular_View view : frame.getList()) {
			if (view.isVisible()) {
				aktiv = view;
				i++;

			}
		}
		if (i == 0) {
			bv.setVisible(true);
			bv.setScrollPane(frame.getformularScrollPane());
			bv.setDate(frame.getCalendar());
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
