package uiApi.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private   int retrycount =0;
    private static final  int  MaxRetry =1;
	@Override
	public boolean retry(ITestResult result) {
		if(retrycount<MaxRetry) {
			retrycount++;
			return true;
		}
		
		return false;
	}

}
