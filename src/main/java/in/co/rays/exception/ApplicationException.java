package in.co.rays.exception;

/**
 * ApplicationException is used to indicate exceptions occurring in the application layer.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
public class ApplicationException extends Exception {

    /**
     * Constructs a new ApplicationException with the specified detail message.
     *
     * @param msg the detail message
     */
    public ApplicationException(String msg) {
        super(msg);
    }
}
