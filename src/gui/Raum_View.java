package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Raum_View extends JPanel implements MouseListener {

	private JLabel raumLabel;
	private String raumName;
	private Raum_View_Label label;
	private Bestellformular_View bv;
	private JFrame frame;

	public Raum_View(String name, Bestellformular_View bv, JFrame frame) {
		this.raumName = name;
		this.bv = bv;
		this.frame = frame;
		initView();
	}

	private void initView() {
		this.setLayout(new GridLayout(1, 1));
		this.add(raumzeitenPanel());
	}

	private JPanel raumzeitenPanel() {
		JPanel raumzeitenPanel = new JPanel();
		raumzeitenPanel.setLayout(new GridLayout(46, 1));

		raumzeitenPanel.add(getRaumLabel());

		for (int i = 0; i < 45; i++) {
			label = new Raum_View_Label();
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			label.setPreferredSize(new Dimension(100, 30));
			raumzeitenPanel.add(label);
		}

		return raumzeitenPanel;
	}

	public JLabel getRaumLabel() {
		raumLabel = new JLabel(raumName, SwingConstants.CENTER);
		raumLabel.setPreferredSize(new Dimension(100, 30));
		raumLabel.addMouseListener(this);

		return raumLabel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		bv.setVisible(true);
		// SwingUtilities.updateComponentTreeUI(frame);
		frame.validate();
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
