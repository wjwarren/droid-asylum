package nl.ansuz.android.asylum.tasks;

/**
 * Base Database task implementation. Extends thi class to perform a specific task.
 *
 * @author Wijnand
 */
public abstract class DatabaseTask<Params, Progress, Result> extends DataTaskBase<Params, Progress, Result> {

	/**
	 * @see DataTaskBase#DataTaskBase(RunType)
	 */
	public DatabaseTask(RunType type) {
		super(type);
	}

	/**
	 * Performs a database action, i.e. retrieve some rows.
	 */
	protected abstract Result doDatabaseAction();

	/** {@inheritDoc} */
	@Override
	protected void init() {
		super.init();
	}

	/** {@inheritDoc} */
	@Override
	protected Result doInBackground(Params... params) {
		return doDatabaseAction();
	}

	/** {@inheritDoc} */
	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);

		if (isCancelled() || isAborted()) {
			return;
		}

		onPostSuccess(result);
		if (onCompleteListener != null) {
			onCompleteListener.onComplete(result);
		}
	}
}
