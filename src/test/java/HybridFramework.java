import hybridframeworkutils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Maruf on 8/28/2014.
 */
public class HybridFramework extends Utilities{
    static WebDriver driver;

    @BeforeMethod
    public void setup() throws Exception {
        // read data from excel
        testCases = readXL("C:\\Users\\Maruf\\Desktop\\HDF.xlsx", "Test Cases");
        testData = readXL("C:\\Users\\Maruf\\Desktop\\HDF.xlsx", "Test Data");
        testSteps = readXL("C:\\Users\\Maruf\\Desktop\\HDF.xlsx", "Test Steps");
    }

    @Test
    public void mainTest() {


        // loop through Test Data Sheet
        for (int i = 1; i < testData.length; i++) {
            // // check which sets of data will be executed
            if (testData[i][3].equalsIgnoreCase("y")) {
                // get data from rows that need to be executed and assign them into variables
                tDID =testData[i][0];
                browser =testData[i][4];
                url =testData[i][5];
                email = testData[i][6];
                password =testData[i][7];
                welcomeMessage=testData[i][8];
                deptCity=testData[i][9];
                destCity=testData[i][10];
                deptDate=testData[i][11];
                retDate=testData[i][12];
                dayTime=testData[i][13];
                resultTestData =testData[i][14];
                System.out.println("                       " + testData[i][0]+" "+testData[i][1]);
                // loop through Test Cases Sheet and check which test cases will be executed
                for (int j = 1; j < testCases.length; j++) {
                    // get the Test Case ID (TCID) of the test case that need to be executed
                    if (testCases[j][2].equalsIgnoreCase("y")) {
                        // get TCID
                        System.out.println("                      " + testCases[j][0]+ testCases[j][1]);
                        tCID = testCases[j][0];
                        driver = getDriver(browser);
                        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        errorAt = " ";
                        // loop through Test Steps Sheet
                        for (int k = 1; k < testSteps.length; k++) {
                            // go the Test Steps matching the TCID
                            if (tCID.equalsIgnoreCase(testSteps[k][0])) {
                                // assign Test Steps variables
                                System.out.println("******* " + testSteps[k][2]+" "+testSteps[k][3] + " *********");
                                // assign Test Steps variables
                                tSID = testSteps[k][2];
                                keyword = testSteps[k][4];
                                input= testSteps[k][5];
                                path = testSteps[k][6];
                                resultTestStep = testSteps[k][7];
                                // assign input params
                                assignInputVars();

                                // execute tests using keywords
                                try {
                                    //            run tests
                                    //            assign results
                                    runTests();
                                    resultTestData = "Pass";
                                } catch (Exception e) {
                                    // get errors
                                    System.out.println("++++++ "+ e.getClass().toString().substring(26)+" ++++++++++++");
                                    // assign error
                                    tError = e.getClass().toString().substring(26);
                                    // assign results

                                    resultTestData = "Fail";
                                    errorAt = errorAt + tCID + tSID + testSteps[k][3];
                                    testSteps[k][7]=tError;
                                    testData [i][15]=errorAt;

                                }
                                // assign to errorReports
                                testData[i][14]=resultTestData;
                            }// test steps if
                        }// test steps for loop
                        driver.close();
                    } // test case if
                } // test cases for loop

            } // test data if


        } // test data for loop




    }

    @AfterMethod
    public void teardown() throws Exception {
        writeXLSX("C:\\Users\\Maruf\\Desktop\\Test Data Results.xlsx", "Test Cases", testData);
        writeXLSX("C:\\Users\\Maruf\\Desktop\\Test Steps Results.xlsx", "Test Cases", testSteps);

    }
}
