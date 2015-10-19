package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Zeit_View extends JPanel {

	private JLabel zeitLabel;

	public Zeit_View() {
		initZeitView();
	}

	private void initZeitView() {
		this.setLayout(new GridLayout(1, 1));
		this.add(uhrzeitenPanel());
	}

	private JPanel uhrzeitenPanel() {
		JPanel uhrzeitenPanel = new JPanel();
		uhrzeitenPanel.setLayout(new GridLayout(30, 1));

		/*
		 * 8:00 - 19:00 Uhr
		 */
		for (int i = 8; i < 19; i++) {
			JLabel uhrzeitLabel = new JLabel(i + ":00" + " Uhr",
					SwingConstants.CENTER);
			uhrzeitLabel.setFont(new Font(i + ":00" + " Uhr", 0, 18));
			uhrzeitLabel.setPreferredSize(new Dimension(100, 50));
			uhrzeitenPanel.add(uhrzeitLabel);
			for (int j = 30; j < 31; j += 15) {
				JLabel uhrzeitLabel2 = new JLabel(i + ":" + j + " Uhr",
						SwingConstants.CENTER);
				uhrzeitLabel2.setFont(new Font(i + ":" + j + " Uhr", 0, 18));
				uhrzeitLabel2.setPreferredSize(new Dimension(100, 50));
				uhrzeitenPanel.add(uhrzeitLabel2);
			}
			if (i + 1 == 19) {
				JLabel uhrzeitLabel3 = new JLabel(i + 1 + ":00" + " Uhr",
						SwingConstants.CENTER);
				uhrzeitLabel3.setFont(new Font(i + 1 + ":00" + " Uhr", 0, 18));
				uhrzeitLabel3.setPreferredSize(new Dimension(100, 50));
				uhrzeitenPanel.add(uhrzeitLabel3);
			}
		}

		return uhrzeitenPanel;
	}

	public JLabel getZeitPanel() {
		zeitLabel = new JLabel("Zeit", SwingConstants.CENTER);
		zeitLabel.setPreferredSize(new Dimension(100, 30));

		return zeitLabel;
	}
}
