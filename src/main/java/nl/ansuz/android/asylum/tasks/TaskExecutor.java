package nl.ansuz.android.asylum.tasks;

import android.os.AsyncTask;
import android.os.Build;

import javax.inject.Inject;

/**
 * Helps executing {@link DataTask} implementations in compatibility mode.
 *
 * @author Wijnand Warren
 */
public class TaskExecutor {

	@Inject
	public TaskExecutor() {
		// Do nothing, just making Dagger happy. :)
	}

	/**
	 * Executes a task based on its {@link DataTask.RunType}.
	 *
	 * @param task DataTask - The task to execute.
	 */
	public void execute(DataTask task) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			task.execute();
		} else {
			if (task.getType() == DataTask.RunType.SEQUENTIAL) {
				task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
			} else {
				task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}
		}
	}

}
