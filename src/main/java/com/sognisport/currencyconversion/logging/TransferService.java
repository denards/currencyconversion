package com.sognisport.currencyconversion.logging;

public abstract class TransferService {

    public boolean convert(long amount) {
        return true;
    }

    abstract protected void beforeTransfer(long amount);

    abstract protected void afterTransfer(long amount, boolean outcome);
}
