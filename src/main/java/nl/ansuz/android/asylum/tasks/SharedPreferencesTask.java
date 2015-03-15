package nl.ansuz.android.asylum.tasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/**
 * Base {@link SharedPreferences} Task implementation. Extends this class to perform a specific task.
 *
 * @author Wijnand
 */
public abstract class SharedPreferencesTask<Params, Progress, Result> extends DataTaskBase<Params, Progress, Result> {

    protected SharedPreferences mPreferences;

    /**
     * Creates a new {@link SharedPreferencesTask} instance.
     *
     * @param type See {@link DataTaskBase#DataTaskBase(RunType)}.
     * @param context {@link Context} - The {@link Context} to use to open the preference file.
     * @param fileName String - Desired preferences file.
     * @param mode int - Operating mode. See also {@link Context#MODE_PRIVATE} etc.
     */
    public SharedPreferencesTask(RunType type, Context context, String fileName, int mode) {
        super(type);

        mPreferences = context.getSharedPreferences(fileName, mode);
    }

    /**
     * Performs a {@link SharedPreferences} action, i.e. store some settings.
     *
     * @return {@link Result} - Result of the action.
     */
    protected abstract Result doPreferencesAction();

    /**
     * Saves all changes the {@link SharedPreferences.Editor} made to the {@link SharedPreferences}.
     *
     * @param editor {@link SharedPreferences.Editor} - Editor used to make the changes.
     */
    protected void saveChanges(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected Result doInBackground(Params... params) {
        return doPreferencesAction();
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
