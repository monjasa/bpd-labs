package org.monjasa.lab04.crypto.cipher.exception;

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
