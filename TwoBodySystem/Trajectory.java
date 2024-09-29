import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Trajectory extends JPanel implements ActionListener {
	final int WIDTH = 900;
	final int HEIGHT = 900;
	final double mass1 = 8.6 * Math.pow(10, 14);
	final double mass2 = 1.3 * Math.pow(10, 15);
	final double[] r1 = { -35, -70 };
	final double[] r2 = { 100 , 150 };
	final double[] v1 = { 10, -7 };
	final double[] v2 = { -13, 2 };
	final double[][] initAcceleration = Calc.initialAcceleration(new double[] { r1[0], r1[1], mass1 },
			new double[] { r2[0], r2[1], mass2 });

	final int SIZE = 10;
	final int RADIUS = 5;
	final double SCALE = 1;

	Body body1 = new Body(r1, mass1, v1, initAcceleration[0]);
	Body body2 = new Body(r2, mass2, v2, initAcceleration[1]);
	LinkedList<double[]> centerMass = new LinkedList<double[]>();

	int calcTick = 0;

	private Timer timer;

	double[] delta = new double[] { 0, 0 };

	public Trajectory() {
		timer = new Timer(10, this);
		timer.start();
		setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int centerX = WIDTH / 2;
		int centerY = HEIGHT / 2;

		LinkedList<double[]> points = body1.positionData();
		double[] point1 = new double[2];
		double[] point2 = new double[2];

		g.setColor(Color.CYAN);
		for (int i = 1; i < points.size(); i += 1) {
			point1 = points.get(i - 1);
			point2 = points.get(i);

//			System.out.println(a + " " + c);

			g.drawLine(centerX + (int) ((point1[0] - delta[0]) / SCALE),
					centerY - (int) ((point1[1] - delta[1]) / SCALE), centerX + (int) ((point2[0] - delta[0]) / SCALE),
					centerY - (int) ((point2[1] - delta[1]) / SCALE));
		}

		g.setColor(Color.BLUE);
		g.fillOval(centerX + (int) ((point2[0] - delta[0]) / SCALE) - RADIUS,
				centerY - (int) ((point2[1] - delta[1]) / SCALE) - RADIUS, SIZE, SIZE);

		points = body2.positionData();
		g.setColor(Color.MAGENTA);
		for (int i = 1; i < points.size(); i++) {
			point1 = points.get(i - 1);
			point2 = points.get(i);

			g.drawLine(centerX + (int) ((point1[0] - delta[0]) / SCALE),
					centerY - (int) ((point1[1] - delta[1]) / SCALE), centerX + (int) ((point2[0] - delta[0]) / SCALE),
					centerY - (int) ((point2[1] - delta[1]) / SCALE));
		}

		g.setColor(Color.RED);
		g.fillOval(centerX + (int) ((point2[0] - delta[0]) / SCALE) - RADIUS,
				centerY - (int) ((point2[1] - delta[1]) / SCALE) - RADIUS, SIZE, SIZE);

		points = centerMass;
		g.setColor(Color.ORANGE);
		for (int i = 1; i < points.size(); i++) {
			point1 = points.get(i - 1);
			point2 = points.get(i);

			g.drawLine(centerX + (int) ((point1[0] - delta[0]) / SCALE),
					centerY - (int) ((point1[1] - delta[1]) / SCALE), centerX + (int) ((point2[0] - delta[0]) / SCALE),
					centerY - (int) ((point2[1] - delta[1]) / SCALE));
		}
		
		g.setColor(Color.WHITE);
		g.drawString("tick = " + calcTick, WIDTH-150, 70);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Calc.calculate(body1, body2, calcTick);
		delta = Calc.centerMass(body1, body2, calcTick);
		centerMass.add(new double[] { delta[0], delta[1] });

		calcTick++;
		repaint();
	}
}
