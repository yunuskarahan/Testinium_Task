package com.testinium.Utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {


    private Driver() {

    }


    private static WebDriver driver;


    public static WebDriver getDriver() {

        if (driver == null) {

            String browser = ConfigurationReader.getProperty("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<>();
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    chromeOptions.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    break;

            }

        }

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }


    }


}


























