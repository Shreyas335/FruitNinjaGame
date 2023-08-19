import engine.Actor;
import engine.World;
import javafx.scene.image.Image;

public class Ball extends Actor {
	double dx;
	double dy;
	double speed = 3;
	boolean inContact = false;
	double paddleX = -1000000;
	boolean alrBrickTouching = false;

	public Ball() {
		setImage(new Image("ball.png"));
		dx = speed;
		dy = speed;
	}

	@Override
	public void act(long now) {
		// Bounds bounds = getScene().getRoot().getBoundsInLocal();
		// System.out.println(getX());
		// System.out.println(getY());

		// Getting info of the paddle
		World world = getWorld();
		Paddle p = world.getObjects(Paddle.class).get(0);
		if (paddleX == -1000000) {
			paddleX = p.getX();
		}

		if (getOneIntersectingObject(Paddle.class) == null) {
			inContact = false;

		}
		if (getX() > getScene().getWidth() - getWidth() / 2) {
			// setX(getScene().getWidth() - getWidth() / 2);
			dx = -dx;
		}
		if (getX() < getWidth() / 2) {
			// setX(getWidth() /2);
			dx = -dx;
		}
		if (getY() > getScene().getHeight() - getHeight() / 2) {
			((BallWorld) (getWorld())).getScore().setValue(((BallWorld) (getWorld())).getScore().getValue() - 1000);
			// setY(getScene().getHeight() - getHeight() /2);
			dy = -dy;
		}
		if (getY() < getHeight() / 2) {

			// setY(getHeight() /2);
			dy = -dy;
		}
		if (getOneIntersectingObject(Paddle.class) != null) {
			if (!inContact) {
				inContact = true;
				// dy++;
				// dx++;
				double paddleWidth = p.getWidth();
				double paddleThird = paddleWidth / 3;
				// not moving
				if (paddleX == p.getX()) {
					dy = -dy;
				} else if (paddleX > p.getX()) { // moving right
					if (p.getX() + paddleThird <= getX() && p.getX() + 2 * paddleThird >= getX()) { // middle third
						dy = -dy;
					} else if (p.getX() + 2 * paddleThird <= getX() && p.getX() + paddleWidth >= getX()) { // right
																											// third
						dy = -dy;
						if (dx <= 0) {
							dx = -dx;
						}
					} else if (p.getX() + paddleWidth < getX()) { // hit past the right side
						dy = -dy / 2;
						if (dx <= 0) {
							dx = -dx;
						}
					} else { // opposite third
						dy = -dy;
					}
				} else if (paddleX < p.getX()) { // moving left
					if (p.getX() + paddleThird <= getX() && p.getX() + 2 * paddleThird >= getX()) { // middle third
						dy = -dy;
					} else if (p.getX() <= getX() && p.getX() + paddleThird >= getX()) { // left third
						dy = -dy;
						if (dx >= 0) {
							dx = -dx;
						}
					} else if (p.getX() > getX()) { // hit past the left side
						dy = -dy / 2;
						if (dx >= 0) {
							dx = -dx;
						}
					} else { // opposite third
						dy = -dy;
					}
				}

			} else {
				// nothing, collision detection
			}

		}

		// Update paddleX for next act()
		paddleX = p.getX();

		// Brick
		Brick brick = getOneIntersectingObject(Brick.class);
		if (brick != null) {
			if (!alrBrickTouching) {
				alrBrickTouching = true;
				((BallWorld) (getWorld())).getScore().setValue(((BallWorld) (getWorld())).getScore().getValue() + 100);
				if (getX() >= brick.getX() && getX() <= brick.getX() + brick.getWidth() && (getY() >= brick.getY() || getY() <= brick.getY() + brick.getHeight())) {
					dy = -dy;
				}
				else if (getY() >= brick.getY() && getY() <= brick.getY() + brick.getHeight() && (getX() <= brick.getX()|| getX() >= brick.getX() + brick.getWidth())) {
					dx = -dx;
				}

				else {
					dx = -dx;
					dy = -dx;
				}


			}

		} else {
			alrBrickTouching = false;
		}

		move(dx, dy);

	}

}
