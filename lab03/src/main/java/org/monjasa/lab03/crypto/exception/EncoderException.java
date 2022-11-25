package org.monjasa.lab03.crypto.exception;

public class EncoderException extends IllegalStateException {
    private Throwable cause;

    public EncoderException(String msg, Throwable cause)
    {
        super(msg);

        this.cause = cause;
    }

    public Throwable getCause()
    {
        return cause;
    }
}
