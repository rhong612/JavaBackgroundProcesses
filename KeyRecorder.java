import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyRecorder implements NativeKeyListener {

	private PrintWriter out;
	
	public KeyRecorder(String filename) {
		try {
			out = new PrintWriter(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		out.println(NativeKeyEvent.getKeyText(arg0.getKeyCode()));
		
		if (arg0.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.close();
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
		KeyRecorder recorder = new KeyRecorder("test.txt");
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.out.println("ERROR");
			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(recorder);
	}
}
