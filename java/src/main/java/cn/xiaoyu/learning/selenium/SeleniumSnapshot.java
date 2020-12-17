package cn.xiaoyu.learning.selenium;


import com.google.common.io.Files;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * selenium截全屏
 *
 * @link https://zhuanlan.zhihu.com/p/73255362
 */
public class SeleniumSnapshot {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        new SeleniumSnapshot().doSomething();
    }

    public void doSomething() {
        FirefoxDriver driver = null;
        try {
            String os = System.getProperty("os.name").toLowerCase();
            logger.info(String.format("*******************%s***************************", os));
            String filePath;
            if (os.contains("linux")) {
                System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox");
                System.setProperty("webdriver.gecko.driver", "/home/mwopr/geckodriver");
                filePath = "/usr/screenfile.png";
            } else {
                System.setProperty("webdriver.firefox.bin", "D:/Program Files/Mozilla Firefox/firefox.exe");
                System.setProperty("webdriver.gecko.driver", "D:/geckodriver.exe");
                filePath = "d:/screenfile.png";
            }
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

            // 设置火狐的无头模式
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-headless");
            firefoxOptions.setHeadless(true);

            //新建一个WebDriver的对象，但是new的是FirefoxDriver的驱动
            driver = new FirefoxDriver(firefoxOptions);
            driver.get("https://news.baidu.com/");

            long width = (long) driver.executeScript("return document.documentElement.scrollWidth");
            long height = (long) driver.executeScript("return document.documentElement.scrollHeight");
            logger.info(width + "-" + height);

            // 火狐浏览器设置缩放
//            driver.executeScript("document.body.style.cssText = document.body.style.cssText + '; -moz-transform:scale(1);-moz-transform-origin:0% 50%; ';");

            driver.manage().window().setSize(new Dimension((int) width * 1, (int) height * 1));

            TimeUnit.MILLISECONDS.sleep(1000);
            File scrFile = driver.getScreenshotAs(OutputType.FILE);

            //利用FileUtils工具类的copy()方法保存getScreenshotAs()返回的文件对象。
            //看到网上有使用File.copyFile()方法，我这里测试的结果需要使用copy()方法
            Files.copy(scrFile, new File(filePath));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            driver.quit();
            logger.info("执行结束，关闭浏览器");
        }
    }
}
