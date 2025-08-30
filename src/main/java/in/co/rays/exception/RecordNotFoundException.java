package in.co.rays.exception;

/**
 * RecordNotFoundException is thrown when a requested record is not found in the database.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class RecordNotFoundException extends Exception {

    /**
     * Constructs a new RecordNotFoundException with the specified detail message.
     *
     * @param msg the detail message
     */
    public RecordNotFoundException(String msg) {
        super(msg);
    }
}
