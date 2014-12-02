package hotciv.command;

import hotciv.framework.command.Command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCommand implements Command {

	private FileWriter fileWriter;
	private BufferedWriter bufferWriter;
	private boolean toggleTranscription;

	public WriteCommand(String name) {
		toggleTranscription = false;
		try{
			File file = new File(name);
			fileWriter = new FileWriter(file);
			bufferWriter = new BufferedWriter(fileWriter);
		}
		catch(IOException e ){

		}
	}

	public void writeTranscript(String action) {
		try {
			if(toggleTranscription){
				bufferWriter.write(action);
			}else{

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void closeTranscript(){
		try {
			bufferWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setTranscription(boolean transcripe) {
		toggleTranscription = transcripe;

	}

}
