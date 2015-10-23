package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Halbestunde_Panel extends JPanel {

	private JPanel upperPanel, downerPanel;
	private Raum_View_Label label;

	public Halbestunde_Panel(Raum_View_Label label, boolean farbe) {
		this.label = label;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(200, 20));

		initPanel(farbe);
	}

	private void initPanel(boolean farbe) {
		upperPanel = new JPanel();

		// upperPanel.add(label);

		downerPanel = new JPanel();

		if (farbe) {
			upperPanel.setBackground(Color.WHITE);
			downerPanel.setBackground(Color.WHITE);
		} else {
			upperPanel.setBackground(Color.LIGHT_GRAY);
			downerPanel.setBackground(Color.LIGHT_GRAY);
		}

		this.add(Box.createVerticalGlue());
		this.add(upperPanel);
		this.add(Box.createRigidArea(new Dimension(0, 0)));
		this.add(downerPanel);
	}

	public Time getLabelTime() {
		return label.getTime();
	}

	public Raum_View_Label getRaumViewLabel() {
		return label;
	}

}
