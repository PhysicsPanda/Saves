import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		final int WIDTH = 900;
		final int HEIGHT = 900;
		
		JFrame frame = new JFrame("Trajectory of two body");
		Trajectory trajectory = new Trajectory();
		frame.add(trajectory);
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}