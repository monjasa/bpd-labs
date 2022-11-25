package org.monjasa.lab03.crypto.exception;

public class DecoderException extends IllegalStateException {
    private Throwable cause;

    public DecoderException(String msg, Throwable cause)
    {
        super(msg);

        this.cause = cause;
    }

    public Throwable getCause()
    {
        return cause;
    }
}
