package com.phonebook.core;

import com.phonebook.utils.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

   // public static String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibGVub0BnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTc1NzU3MjMwMSwiaWF0IjoxNzU2OTcyMzAxfQ.Jf12HYvhCi_ZGFVdx_1rj35ICcaw8aZVYHjRSfpL-Nk";
    public static String AUTH = PropertiesLoader.loadProperty("auth");
    protected AppManager app = new AppManager();
    public static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void init(Method method, Object[] p) {
        logger.info("Start test: {} with data {}",method.getName(), Arrays.asList(p));
        app.start();
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED: {}",result.getMethod().getMethodName() );
        } else {
            logger.error("FAILED: {}", result.getMethod().getMethodName());
        }
        logger.info("==================================");
    }
}
