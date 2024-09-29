
public class Calc {

	final static double tick = 0.1;

	public static double[][] position(Body a, Body b, int calcTick) {
		return new double[][] { a.position(calcTick), b.position(calcTick) };
	}

	public static double[][] velocity(Body a, Body b, int calcTick) {
		return new double[][] { a.velocity(calcTick), b.velocity(calcTick) };
	}

	public static double[][] acceleration(Body a, Body b, int calcTick) {
		return new double[][] { a.acceleration(calcTick), b.acceleration(calcTick) };
	}

	public static double distance(Body a, Body b, int calcTick) {
		double[][] position = position(a, b, calcTick);
		return Math.hypot(position[0][0] - position[1][0], position[0][1] - position[1][1]);
	}

	public static double[] displacement(Body a, Body b, int calcTick) {
		double[][] position = position(a, b, calcTick);
		return new double[] { position[1][0] - position[0][0], position[1][1] - position[0][1] };
	}

	public static void newAcceleration(Body a, Body b, int calcTick) {
		// g * m1 * m2 / r^3 * vector(r) / m
		double divider = Math.pow(distance(a, b, calcTick), 3);
		double[] r12 = displacement(a, b, calcTick);
		a.setAcceleration(new double[] { b.g_mass() * r12[0] / divider, b.g_mass() * r12[1] / divider });
		b.setAcceleration(new double[] { a.g_mass() * (-r12[0]) / divider, a.g_mass() * (-r12[1]) / divider });
		return;
	}

	public static double[][] initialAcceleration(double[] initA, double[] initB) {
		double distance = Math.hypot(initA[0] - initB[0], initA[1] - initB[1]);
		double divider = Math.pow(distance, 3);
		double[] r12 = new double[] { initB[0] - initA[0], initB[1] - initA[1] };

		double ag = initA[2] * 6.67384 * Math.pow(10, -11);
		double bg = initB[2] * 6.67384 * Math.pow(10, -11);

		return new double[][] { new double[] { bg * r12[0] / divider, bg * r12[1] / divider },
				new double[] { ag * (-r12[0]) / divider, ag * (-r12[1]) / divider } };
	}

	public static void newVelocity(Body a, Body b, int calcTick) {
		double[][] acceleration = acceleration(a, b, calcTick);
		double[][] velocity = velocity(a, b, calcTick);

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				velocity[i][j] += acceleration[i][j] * tick;

		a.setVelocity(velocity[0]);
		b.setVelocity(velocity[1]);

		return;
	}

	public static void newPosition(Body a, Body b, int calcTick) {
		double[][] velocity = velocity(a, b, calcTick);
		double[][] position = position(a, b, calcTick);

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				position[i][j] += velocity[i][j] * tick;

		a.setPosition(position[0]);
		b.setPosition(position[1]);

		return;
	}

	public static void calculate(Body a, Body b, int calcTick) {
		a.putHistory();
		b.putHistory();
		newAcceleration(a, b, calcTick);
		newVelocity(a, b, calcTick);
		newPosition(a, b, calcTick);

		return;
	}

	public static double[] centerMass(Body a, Body b, int calcTick) {
		double[][] position = position(a, b, calcTick);
		double[] mass = new double[] { a.mass(), b.mass() };
		double[] centermass = new double[2];

		centermass[0] = (position[0][0] * mass[0] + position[1][0] * mass[1]) / (mass[0] + mass[1]);
		centermass[1] = (position[0][1] * mass[0] + position[1][1] * mass[1]) / (mass[0] + mass[1]);

		return new double[] { centermass[0], centermass[1] };
	}
}
