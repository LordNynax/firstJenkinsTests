package com.jenkinsTests.selenium.developertests;

import com.jenkinsTests.selenium.drivers.WebdriverConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.configuration.CompositeConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class firstJenkinsTest {

    private static Log log = LogFactory.getLog(firstJenkinsTest.class);
    private static CompositeConfiguration config = WebdriverConfiguration.getConfig();

    @Test
    public void firefoxTest(){
        System.out.println("Starting test");
        log.debug("Starting Chrome driver ...");
        System.setProperty("webdriver.chrome.driver", getConfigProperty("se.chromedriver"));
        WebDriver driver = new ChromeDriver();
        log.info("Chrome driver correctly set up");

        driver.navigate().to("http://www.eurofins-digitaltesting.com/");
        log.info("navigated to website");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"home\"]/div/footer/div/div[2]/p"));
        log.info("Checking element");
        assertThat(element.getText()).contains("Eurofins Digital Testing 2016");
        log.info("Test OK!");

        driver.close();
        log.info("driver closed");
        driver.quit();
        log.info("driver quit");
        System.out.println("Test OK!");
    }

    private static String getConfigProperty(String propertyName) {
        return config.getString(propertyName);
    }
}
