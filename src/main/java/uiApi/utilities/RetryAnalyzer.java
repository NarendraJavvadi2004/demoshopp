package uiApi.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);

    private int retryCount = 0;
    private static final int MAX_RETRY = 1;  // retry once

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY) {
            retryCount++;
            logger.warn("Retrying test '{}' ({} of {}) due to failure",
                        result.getMethod().getMethodName(), retryCount, MAX_RETRY);
            return true;
        }
        logger.error("Test '{}' failed after {} retries",
                     result.getMethod().getMethodName(), MAX_RETRY);
        return false;
    }
}