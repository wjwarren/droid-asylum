package nl.ansuz.android.asylum.tasks;

import nl.ansuz.android.asylum.net.UrlLoadException;
import nl.ansuz.android.asylum.utils.NetworkUtil;

import javax.inject.Inject;

/**
 * Base network Task implementation. Extend to perform a specific task.
 *
 * This class is based on a blog post by Joshua Musselwhite.
 * @see http://www.therealjoshua.com/2012/09/android-architecture-structuring-network-calls-part-2/
 * @author Wijnand
 */
public abstract class NetworkTask<Params, Progress, Result> extends DataTaskBase<Params, Progress, Result> {

	@Inject
	protected NetworkUtil networkUtil;

	private UrlLoadException urlLoadException;

	/**
	 * @see DataTaskBase#DataTaskBase(RunType)
	 */
	public NetworkTask(RunType type) {
		super(type);
	}

	/**
	 * Performs a network action, i.e. gets data from the web.
	 */
	protected abstract Result doNetworkAction() throws UrlLoadException;

	/** {@inheritDoc} */
	@Override
	protected void init() {
		super.init();

		urlLoadException = null;
	}

	/** {@inheritDoc} */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		urlLoadException = null;

		// Check if network is available.
		if (!networkUtil.hasInternetAccess()) {
			abort();
		}
	}

	/** {@inheritDoc} */
	@Override
	protected Result doInBackground(Params... params) {
		if(!isCancelled()) {
			try {
				return doNetworkAction();
			} catch (UrlLoadException e) {
				urlLoadException = e;
			}
		}

		return null;
	}

	/** {@inheritDoc} */
	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);

		if (isCancelled() || isAborted()) {
			return;
		}

		if (urlLoadException != null) {
			onPostException(urlLoadException);
			if (onExceptionListener != null) {
				onExceptionListener.onException(urlLoadException);
			}
		} else {
			onPostSuccess(result);
			if (onCompleteListener != null) {
				onCompleteListener.onComplete(result);
			}
		}
	}
}
