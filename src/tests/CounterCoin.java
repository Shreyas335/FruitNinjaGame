package tests;

import javafx.scene.image.Image;

public class CounterCoin extends TestActor {
	
	private static final Image COIN_IMAGE = new Image("file:src/testresources/Coin.png");

	public CounterCoin() {
		setImage(COIN_IMAGE);
	}

}
