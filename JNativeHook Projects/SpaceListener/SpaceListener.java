import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class SpaceListener implements NativeKeyListener {
	private Robot robotWriter;
	
	public SpaceListener() {
		try {
			this.robotWriter = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		if (arg0.getKeyCode() == NativeKeyEvent.VC_SPACE) {
	        robotWriter.keyPress(KeyEvent.VK_BACK_SPACE);
	        robotWriter.keyPress(KeyEvent.VK_S);
	        robotWriter.keyPress(KeyEvent.VK_P);
	        robotWriter.keyPress(KeyEvent.VK_A);
	        robotWriter.keyPress(KeyEvent.VK_C);
	        robotWriter.keyPress(KeyEvent.VK_E);
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		SpaceListener listener = new SpaceListener();
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.out.println("ERROR");
			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(listener);
	}
}
