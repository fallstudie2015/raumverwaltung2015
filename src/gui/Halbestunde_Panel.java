package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Halbestunde_Panel extends JPanel {

	private JPanel upperPanel, downerPanel;
	private Raum_View_Label oberesLabel, unteresLabel;

	public Halbestunde_Panel(Raum_View_Label label, Raum_View_Label label2) {
		this.oberesLabel = label;
		this.unteresLabel = label2;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(200, 40));

		label.setPanel(upperPanel);
		label2.setPanel(downerPanel);

		initPanel();
	}

	Halbestunde_Panel(Raum_View_Label label) {
		this.oberesLabel = label;
		upperPanel = new JPanel(new BorderLayout());
		upperPanel.setBackground(Color.WHITE);
		upperPanel.add(label, BorderLayout.CENTER);
		downerPanel = new JPanel(new BorderLayout());
		downerPanel.setBackground(Color.WHITE);
		downerPanel.add(new JLabel(), BorderLayout.CENTER);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(200, 40));
		this.add(upperPanel);
		this.add(downerPanel);
		label.setPanel(upperPanel);
	}

	private void initPanel() {
		upperPanel = new JPanel(new BorderLayout());

		downerPanel = new JPanel(new BorderLayout());

		upperPanel.setBackground(Color.WHITE);

		upperPanel.setBackground(Color.WHITE);

		upperPanel.add(oberesLabel, BorderLayout.CENTER);
		downerPanel.add(unteresLabel, BorderLayout.CENTER);

		this.add(Box.createVerticalGlue());
		this.add(upperPanel);
		this.add(Box.createRigidArea(new Dimension(0, 0)));
		this.add(downerPanel);
	}

	public Time getLabelObenTime() {
		return oberesLabel.getTime();
	}

	public Time getLabelUntenTime() {
		return unteresLabel.getTime();
	}

	public Raum_View_Label getRaumViewLabelOben() {
		return oberesLabel;
	}

	public Raum_View_Label getRaumViewLabelUnten() {
		return unteresLabel;
	}
}
