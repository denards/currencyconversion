package com.sognisport.currencyconversion.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4TransferService extends TransferService {
    private static final Logger logger = LoggerFactory.getLogger(Slf4TransferService.class);

    @Override
    protected void beforeTransfer(long amount) {
        logger.info("Preparing the amount to convert {}$.", amount);
    }

    @Override
    protected void afterTransfer(long amount, boolean outcome) {
        logger.info("The amount ${} were converted successfully ? {}.", amount, outcome);
    }
}

