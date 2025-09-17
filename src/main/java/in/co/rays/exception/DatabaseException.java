package in.co.rays.exception;

/**
 * DatabaseException is used to indicate exceptions occurring in the database layer.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class DatabaseException extends Exception {

    /**
     * Constructs a new DatabaseException with the specified detail message.
     *
     * @param msg the detail message
     */
    public DatabaseException(String msg) {
        super(msg);
    }
}
