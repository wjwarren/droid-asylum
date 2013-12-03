package nl.ansuz.android.asylum.bus;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;

/**
 * An Otto {@link Bus} that always posts to the main thread.<br/>
 * Code taken from: <a href="https://github.com/square/otto/issues/38">github.com/square/otto/issues/38</a>
 *
 * @author Wijnand
 */
public class MainThreadBus extends Bus {

    private final Handler mainThread = new Handler(Looper.getMainLooper());

    /**
     * {@inheritDoc}
     *
     * @param event See {@link Bus#post(Object)}.
     */
    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    MainThreadBus.super.post(event);
                }
            });
        }
    }

}
