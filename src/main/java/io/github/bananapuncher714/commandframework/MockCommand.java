package io.github.bananapuncher714.commandframework;

public class MockCommand {
	protected String cmd;
	protected String[] args;
	
	public MockCommand( String command, String... args ) {
		this.cmd = command;
		this.args = args;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd( String cmd ) {
		this.cmd = cmd;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs( String[] args ) {
		this.args = args;
	}
	
	@Override
	public String toString() {
		String str = cmd;
		for ( String arg : args ) {
			str += " " + arg;
		}
		return str;
	}
}
