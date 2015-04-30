package nl.ansuz.android.asylum.storage;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Write to and read from Android internal storage.
 * @author Wijnand
 */
public class InternalStorage {

    private final Context mContext;

    /**
     * Creates a new {@link InternalStorage} instance.
     * @param context {@link Context} - The application's context for file manipulation.
     */
    public InternalStorage(Context context) {
        mContext = context;
    }

    /**
     * Writes an {@link Object} to the internal file storage.
     * @param fileName String - File name.
     * @param object {@link Object} - The Object to save. Should implement the {@link Serializable} interface.
     * @throws IOException
     */
    public void writeObject(String fileName, Object object) throws IOException {
        FileOutputStream fileOutputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        try {
            objectOutputStream.writeObject(object);
        } catch (IOException ioe) {
            // Do nothing.
        } finally {
            objectOutputStream.close();
            fileOutputStream.close();
        }
    }

    /**
     * @param fileName String - Name of the file to read the Object from.
     * @return {@link Object} - The deserialized Object.
     * @throws IOException
     */
    public <T> T readObject(String fileName, Class<T> type) throws IOException {
        FileInputStream fis = mContext.openFileInput(fileName);
        ObjectInputStream is = new ObjectInputStream(fis);

        Object result = null;

        try {
            result = is.readObject();
        } catch (ClassNotFoundException | IOException e) {
            // Do nothing.
        } finally {
            is.close();
            fis.close();
        }

        return type.cast(result);
    }

    /**
     * @param fileName String - The file to delete.
     * @return boolean - Whether the file was successfully deleted.
     */
    public boolean deleteFile(String fileName) {
        return mContext.deleteFile(fileName);
    }

}
