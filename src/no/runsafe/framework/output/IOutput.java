package no.runsafe.framework.output;

import java.util.logging.Level;

public interface IOutput {

	// Sends the supplied string to the console/log the output handler has
	public void outputToConsole(String message);

	// Sends the supplied string with the supplied logging level to the console/log the output handler has
	public void outputToConsole(String message, Level level);

	// Translates ChatColors to TerminalColors and outputs string to server console
	public void outputColoredToConsole(String message, Level level);

	// Sends the supplied string to the console/log the output handler has if the debug level is high enough
	public void outputDebugToConsole(String message, Level messageLevel);

	// Broadcasts the supplied string to all players on the event the output handler has
	public void outputToServer(String message);

	// Gets the current debug output level
	public Level getDebugLevel();
	
	// Sets the debug output level
	public void setDebugLevel(Level level);
}