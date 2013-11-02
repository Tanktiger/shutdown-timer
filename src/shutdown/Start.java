package shutdown;

import java.io.IOException;

public class Start {

	public static void main(String[] args) {
		try {
			
			Frame frame = new Frame();
			frame.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void shutdown() throws IOException {
	    Runtime.getRuntime().exec("cmd /c shutdown -s");
	}
	
	public static void cancel() throws IOException  {
	    Runtime.getRuntime().exec("cmd /c shutdown -a");
	}
}
