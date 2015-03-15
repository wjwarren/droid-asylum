package nl.ansuz.android.asylum.bus;

import com.squareup.otto.Bus;

/**
 * Wraps a Bus in a Singleton pattern.
 *
 * @author Wijnand
 */
public class SingletonBus extends Bus {

	/**
	 * Singleton holder a-la Bill Pugh.
	 */
	private static class Singleton {
		public static final SingletonBus INSTANCE = new SingletonBus();
	}

	/**
	 * CONSTRUCTOR
	 */
	private SingletonBus() {
		super();
	}

	/**
	 * @return The single instance of this class.
	 */
	public static SingletonBus getInstance() {
		return Singleton.INSTANCE;
	}
}
