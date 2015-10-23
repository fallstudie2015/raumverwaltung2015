package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Viertelstunde_Panel extends JPanel {

	private JPanel upperPanel, downerPanel;
	private Raum_View_Label label;

	public Viertelstunde_Panel(Raum_View_Label label) {
		this.label = label;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(200, 20));
		initPanel();
	}

	private void initPanel() {
		upperPanel = new JPanel();
		// upperPanel.add(label);

		downerPanel = new JPanel();

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
