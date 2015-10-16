package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Raum_View extends JPanel implements MouseListener {

	private JLabel raumLabel;
	private String raumName;
	private Raum_View_Label label;

	public Raum_View(String name) {
		this.raumName = name;
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
		System.out.println("hey");
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
}
