package uiApi.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Screenshots {

    private static final Logger logger = LogManager.getLogger(Screenshots.class);

    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            logger.warn("Screenshot skipped: WebDriver is null for test '{}'", testName);
            return null;
        }

        String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String folderPath = System.getProperty("user.dir") + "/screenshots";
        new File(folderPath).mkdirs();

        String destination = folderPath + "/" + testName + "_" + dateName + ".png";
        File finalDestination = new File(destination);

        try {
            FileUtils.copyFile(source, finalDestination);
            logger.info("Screenshot captured for test '{}' at: {}", testName, destination);
        } catch (IOException e) {
            logger.error("Failed to save screenshot for test '{}'", testName, e);
        }

        return destination;
    }
}