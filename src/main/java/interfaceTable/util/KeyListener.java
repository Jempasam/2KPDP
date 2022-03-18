package interfaceTable.util;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyListener {
	public static KeyCode key;
	public static boolean pressed;
	public static void init(Scene scene) {
		pressed=false;
		scene.setOnKeyPressed((e)->{
			key=e.getCode();
			pressed=true;
		});
		scene.setOnKeyReleased((e)->{
			pressed=false;
		});
	}
	public static boolean isKeyPressed(KeyCode key) {
		return pressed && key == KeyListener.key;
	}
	public static KeyCode getKeyPressed() {
		return pressed ? key : null;
	}
	public static boolean anyKeyPressed() {
		return pressed;
	}
	public static KeyCode waitNewKeyPress() {
		while(pressed) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(!pressed) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return key;
	}
	public static KeyCode waitNewKeyPressBetween(int min, int max) {
		KeyCode ret;
		do {
			ret=waitNewKeyPress();
		}
		while(min>ret.getCode() || ret.getCode()>max);
		return ret;
		
	}
	public static KeyCode waitNewKeyPressBetween(KeyCode min, KeyCode max) {
		return waitNewKeyPressBetween(min.getCode(), max.getCode());
	}
}
