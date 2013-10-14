package nl.ansuz.android.asylum.tasks;

import java.util.concurrent.Executor;

/**
 * Task responsible for loading data.
 * Works nicely together with an {@link AsyncTask}.
 *
 * @author Wijnand
 */
public interface DataTask {

	/**
	 * Determines how this Task should be run, either sequential or in parallel.
	 *
	 * @author Wijnand
	 */
	public enum RunType {
		SEQUENTIAL,
		PARALLEL;
	}

	public interface OnCompleteListener<Result> {
		/**
		 * Called when the DataTask has finished successfully.
		 *
		 * @param result Result - Result of the task execution.
		 */
		public void onComplete(Result result);
	}

	public interface OnExceptionListener {
		/**
		 * Called when the DataTask has encountered an error.
		 * TODO: Pass a task enum to identify faulty task when multiple are run in parallel?
		 *
		 * @param exception Exception - The exception that was encountered.
		 */
		public void onException(Exception exception);
	}

	/**
	 * Executes this task.
	 */
	public void execute();

	/**
	 * Executes this task on a specific {@link Executor}.
	 */
	public void executeOnExecutor(Executor executor);

	/**
	 * Aborts this task.
	 */
	public void abort();

	/**
	 * Registers an {@link OnCompleteListener}.
	 *
	 * @param listener OnCompleteListener - The listener to register.
	 */
	public void setOnCompleteListener(OnCompleteListener listener);

	/**
	 * Registers an {@link OnExceptionListener}
	 *
	 * @param listener OnExceptionListener - The listener to register.
	 */
	public void setOnExceptionListener(OnExceptionListener listener);

	/**
	 * @return RunType - How this Task should be run.
	 */
	public RunType getType();

}
