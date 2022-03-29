import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test case succcess and test case details are "+result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test case Failed and Test case details are "+result);
    }

    @Override
    public void onFinish(ITestContext context) {
        PositiveTests.driver.quit();
        NegativeTest.driver.quit();
        PositiveTest2.driver.quit();
    }
}

