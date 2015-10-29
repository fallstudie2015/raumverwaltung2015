package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Tim
 * 
 *         Erstellt die linke seitliche Seite des Scrollpanes.
 *
 */
public class Zeit_View extends JPanel {

	private JLabel zeitLabel;

	public Zeit_View() {
		initZeitView();
	}

	private void initZeitView() {
		this.setLayout(new GridLayout(1, 1));
		this.add(uhrzeitenPanel());
	}

	/*
	 * erstellt Label mit den Uhrzeiten von 08:00 Uhr bis 19:00 Uhr im 30 min.
	 * Takt.
	 */
	private JPanel uhrzeitenPanel() {
		JPanel uhrzeitenPanel = new JPanel();
		uhrzeitenPanel.setLayout(new GridLayout(45, 1));

		/*
		 * 8:00 - 19:00 Uhr
		 */
		for (int i = 8; i < 19; i++) {
			JLabel uhrzeitLabel = new JLabel(i + ":00" + " Uhr", SwingConstants.CENTER);
			uhrzeitLabel.setFont(new Font(i + ":00" + " Uhr", 0, 18));
			uhrzeitLabel.setPreferredSize(new Dimension(100, 40));
			uhrzeitenPanel.add(uhrzeitLabel);
			// for (int k = 15; k < 16; k += 15) {
			// JLabel uhrzeitLabel2 = new JLabel(i + ":" + k + " Uhr",
			// SwingConstants.CENTER);
			// uhrzeitLabel2.setFont(new Font(i + ":" + k + " Uhr", 0, 18));
			// uhrzeitLabel2.setPreferredSize(new Dimension(100, 20));
			// uhrzeitenPanel.add(uhrzeitLabel2);

			for (int j = 30; j < 31; j += 15) {
				JLabel uhrzeitLabel3 = new JLabel(i + ":" + j + " Uhr", SwingConstants.CENTER);
				uhrzeitLabel3.setFont(new Font(i + ":" + j + " Uhr", 0, 18));
				uhrzeitLabel3.setPreferredSize(new Dimension(100, 40));
				uhrzeitenPanel.add(uhrzeitLabel3);

				// for (int l = 45; l < 46; l += 15) {
				// JLabel uhrzeitLabel4 = new JLabel(i + ":" + l + " Uhr",
				// SwingConstants.CENTER);
				// uhrzeitLabel4.setFont(new Font(i + ":" + l + " Uhr", 0, 18));
				// uhrzeitLabel4.setPreferredSize(new Dimension(100, 20));
				// uhrzeitenPanel.add(uhrzeitLabel4);
				// }
			}
			// }
			if (i + 1 == 19) {
				JLabel uhrzeitLabel5 = new JLabel(i + 1 + ":00" + " Uhr", SwingConstants.CENTER);
				uhrzeitLabel5.setFont(new Font(i + 1 + ":00" + " Uhr", 0, 18));
				uhrzeitLabel5.setPreferredSize(new Dimension(100, 40));
				uhrzeitenPanel.add(uhrzeitLabel5);
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
