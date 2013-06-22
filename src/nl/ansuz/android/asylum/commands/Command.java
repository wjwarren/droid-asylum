package nl.ansuz.android.asylum.commands;

/**
 * Command interface.
 *
 * @author Wijnand
 */
public interface Command {

	/**
	 * Executes this Command.
	 *
	 * @return T - Result of the execution.
	 */
	public <T> T execute() throws Exception;

	/**
	 * Cancels Command execution.
	 */
	public void cancel();

}
