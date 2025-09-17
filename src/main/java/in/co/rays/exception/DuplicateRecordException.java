package in.co.rays.exception;

/**
 * DuplicateRecordException is thrown when an attempt is made to create a record
 * that already exists in the database.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class DuplicateRecordException extends Exception {

    /**
     * Constructs a new DuplicateRecordException with the specified detail message.
     *
     * @param msg the detail message
     */
    public DuplicateRecordException(String msg) {
        super(msg);
    }
}
