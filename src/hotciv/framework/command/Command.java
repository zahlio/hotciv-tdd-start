package hotciv.framework.command;

public interface Command {

	public void writeTranscript(String action);
	
	public void setTranscription(boolean transcripe);
	
	public void closeTranscript();
}
