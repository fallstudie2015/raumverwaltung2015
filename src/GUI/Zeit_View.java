package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
		// zeitLabel = new JLabel("Zeit", SwingConstants.CENTER);
		// zeitLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// zeitLabel.setPreferredSize(new Dimension(100, 25));

		JPanel uhrzeitenPanel = new JPanel();
		uhrzeitenPanel.setLayout(new GridLayout(46, 1));
		
		uhrzeitenPanel.add(getZeitPanel());
				
		// uhrzeitenPanel.add(zeitLabel);

		/*
		 * 8:00 - 19:00 Uhr
		 */
		for (int i = 8; i < 19; i++) {
			JLabel uhrzeitLabel = new JLabel(i + ":00" + " Uhr", SwingConstants.CENTER);
			uhrzeitLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			uhrzeitLabel.setPreferredSize(new Dimension(100, 30));
			uhrzeitenPanel.add(uhrzeitLabel);
			for (int j = 15; j < 46; j += 15) {
				JLabel uhrzeitLabel2 = new JLabel(i + ":" + j + " Uhr", SwingConstants.CENTER);
				uhrzeitLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				uhrzeitLabel2.setPreferredSize(new Dimension(100, 30));
				uhrzeitenPanel.add(uhrzeitLabel2);
			}
			if (i + 1 == 19) {
				JLabel uhrzeitLabel3 = new JLabel(i + 1 + ":00" + " Uhr", SwingConstants.CENTER);
				uhrzeitLabel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				uhrzeitLabel3.setPreferredSize(new Dimension(100, 30));
				uhrzeitenPanel.add(uhrzeitLabel3);
			}
		}

		return uhrzeitenPanel;
	}

	public JLayeredPane getZeitPanel() {
		zeitLabel = new JLabel("Zeit", SwingConstants.CENTER);
		// zeitLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		zeitLabel.setPreferredSize(new Dimension(100, 30));

		JLayeredPane jlp = new JLayeredPane();
		jlp.add(zeitLabel);

		return jlp;
	}
}
