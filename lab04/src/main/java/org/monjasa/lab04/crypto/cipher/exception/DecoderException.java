package org.monjasa.lab04.crypto.cipher.exception;

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
