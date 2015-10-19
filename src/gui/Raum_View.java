package gui;

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
	private Bestellformular_View bv;
	private Raumplaner_View frame;

	public Raum_View(String name, Bestellformular_View bv, Raumplaner_View frame) {
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
		raumzeitenPanel.setLayout(new GridLayout(45, 1));

		// raumzeitenPanel.add(getRaumLabel());

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
		bv.setVisible(true);
		frame.getformularScrollPane().setVisible(true);
		// frame.getScrollPane().getColumnHeader().setEnabled(false);

		// frame.getScrollPane().getHorizontalScrollBar().setEnabled(false);
		// frame.getScrollPane().getVerticalScrollBar().setEnabled(false);
		// frame.getScrollPane().getViewport().getView().setEnabled(false);
		// frame.getScrollPane().getVerticalScrollBar().setUnitIncrement(0);
		// frame.getScrollPane().getComponent(0).setVisible(false);

		// frame.getScrollPane().getViewport().add(bv, null);

		// for (Component a : frame.getScrollPane().getComponents()) {
		// a.setVisible(false);
		// }

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
