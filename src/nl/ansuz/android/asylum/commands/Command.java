package nl.ansuz.android.asylum.commands;

/**
 * Command interface.
 *
 * @author Wijnand
 */
public interface Command<ResponseType> {

	/**
	 * Executes this Command.
	 *
	 * @return ResponseType - Result of the execution.
	 */
	public ResponseType execute() throws Exception;

	/**
	 * Cancels Command execution.
	 */
	public void cancel();

}
