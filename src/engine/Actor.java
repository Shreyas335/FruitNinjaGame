package engine;

import javafx.geometry.Bounds;
/*
 * engine.Actor is an abstract base class for general sprites in an arcade style game. Because engine.Actor extends ImageView, you have access to all the ImageView commands such as:
  	- getX(), getY(), setX(), setY()
  	- setImage()
  	- getFitHeight(), getFitWidth()
 */


public abstract class Actor extends javafx.scene.image.ImageView implements javafx.css.Styleable, javafx.event.EventTarget {
	public abstract void act(long now);

	public World getWorld() {
		if (this.getParent() == null) {
			return null;
		} else {
			return (World) this.getParent();
		}
	}
		
	
	public void move(double dx, double dy) {
//		this.setTranslateX(dx);
//		this.setTranslateY(dy);
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}
	
	public double getWidth() {
		//Note there are 3 different functions to get the Bounds, not sure if this is right.
		Bounds temp = this.getBoundsInParent();
		return temp.getWidth();
	}
	
	public double getHeight() {
		Bounds temp = this.getBoundsInParent();
		return temp.getHeight();
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		World temp  = this.getWorld();
		java.util.List<A> arr = new java.util.ArrayList<A>();
		for (A obj : temp.getObjects(cls)) {
			if (this != obj && this.intersects(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
				arr.add(obj);
			}
		}
		return arr; 
	}
	
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		World temp  = this.getWorld();

		for (A obj : temp.getObjects(cls)) {
			/*
			if (getX() >= obj.getX()  && getX() <= obj.getX() + obj.getImage().getWidth() && getY() >= obj.getY() - obj.getImage().getHeight() && getY() <= obj.getY() + obj.getImage().getHeight() && obj != this && obj.getClass().equals(cls)) {
					return obj;

			}
			*/

			if (this != obj && this.intersects(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
				return obj;
			}
		}
		return null;
	}

	public void addedToWorld() {

	}
}
