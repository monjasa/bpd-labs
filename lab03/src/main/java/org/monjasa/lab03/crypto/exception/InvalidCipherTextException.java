package org.monjasa.lab03.crypto.exception;

public class InvalidCipherTextException
        extends Exception
{
    /**
     * base constructor.
     */
    public InvalidCipherTextException()
    {
    }

    /**
     * create a InvalidCipherTextException with the given message.
     *
     * @param message the message to be carried with the exception.
     */
    public InvalidCipherTextException(
            String  message)
    {
        super(message);
    }

    /**
     * create a InvalidCipherTextException with the given message.
     *
     * @param message the message to be carried with the exception.
     * @param cause the root cause of the exception.
     */
    public InvalidCipherTextException(
            String  message,
            Throwable cause)
    {
        super(message, cause);
    }
}
