package utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int maxRetries = 2;
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (retryCount < maxRetries) {
            retryCount++;
            return true;
        }
        return false;
    }
}
