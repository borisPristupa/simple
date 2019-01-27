package com.boris.simple.springaop;

import com.boris.simple.springaop.logging.Logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Business {
    private Logger logger = LoggerFactory.getLogger(Business.class);

    private boolean conditionalLoggingEnabled;

    @Logging
    void logic1() {
        logger.info("Executing Business.logic1()");
        logger.info("Calling Business.logic2() from Business.logic1()");
        logic2();
    }

    @Logging
    void logic2() {
        logger.info("Executing Business.logic2()");
    }

    @Logging(needed = "isConditionalLoggingEnabled")
    void logic3() {
        logger.info("Executing Business.logic3()");
    }

    public boolean isConditionalLoggingEnabled() {
        return conditionalLoggingEnabled;
    }

    void setConditionalLoggingEnabled(boolean conditionalLoggingEnabled) {
        this.conditionalLoggingEnabled = conditionalLoggingEnabled;
    }

    @PostConstruct
    public void init() {
        setConditionalLoggingEnabled(false);
    }
}
