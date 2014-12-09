package hotciv.decorator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DecoratorWriter {

	private BufferedWriter bufferWriter;
	private boolean toggleTranscription;

	public DecoratorWriter(String name) throws IOException {
		toggleTranscription = false;
			bufferWriter = new BufferedWriter(new FileWriter(new File(name), true));
	}
	
	public boolean getToggle(){
		return toggleTranscription;
	}
	
	public void writeTranscript(String action){
			if(toggleTranscription){
				try {
					bufferWriter.write(action + "\n");
				} catch (IOException e) { e.printStackTrace(); }
			}else{}
	}

	public void closeTranscript() throws IOException{
			bufferWriter.close();
	}

	public void setTranscription() {
		if(toggleTranscription){
			toggleTranscription = false;
		}else{
			toggleTranscription = true;
		}
	}
}

