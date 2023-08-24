package com.example.demosecond;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.time.Duration;
class SeleniumTestLogin {

    public static String API_URL = "https://q.524f.cc";
    public static void main(String[] args){
        Logger log = Logger.getLogger(DemoSecondApplicationTests.class);
        int maxWaitTime = 5;
        System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--no-sandbox");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
        // 测试登录
        String url = "https://q.524f.cc/pages/login/index.html";
        driver.get(url);
        driver.manage().window().maximize();
        // 获取登录用户名框
        driver.findElement(By.id("username")).sendKeys("admin");
        // 获取登录密码框
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.className("btn-primary")).click();
        log.info(">>登录测试成功");

//        创建项目
        driver.get(API_URL + "/pages/questionnaire/index.html");
        driver.findElement(By.className("btn-create-project")).click();
        driver.get(API_URL + "/pages/createProject/index.html");
        driver.findElement(By.id("projectName")).sendKeys("selenium项目");
        driver.findElement(By.id("projectDescribe")).sendKeys("这是一个selenium测试的项目描述");
        driver.findElement(By.className("btn-primary")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        log.info(">>创建项目测试成功");

//        创建问卷
        driver.get(API_URL + "/pages/questionnaire/index.html");

//        WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
        WebElement createQuestionnaireButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'selenium项目')]//following-sibling::div//button[contains(text(), '创建问卷')]")));
        createQuestionnaireButton.click();

//        driver.findElements(By.className("btn-link")).get(12).click();
        driver.get(API_URL + "/pages/createQuestionnaire/index.html");
        WebElement btn= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), '创 建')]")));
        btn.click();
//        driver.findElements(By.className("btn-default")).get(0).click();
        //createNew环节
        driver.get(API_URL + "/pages/createNewQuestionnaire/index.html");
        driver.findElement(By.id("surveyName")).sendKeys("selenium问卷");
        driver.findElement(By.id("surveyDescription")).sendKeys("这是针对selenium项目的selenium测试调查问卷描述");
        driver.findElement(By.id("start")).sendKeys("2023-6-19");
        driver.findElement(By.id("end")).sendKeys("2023-7-15");
        driver.findElement(By.className("btn-primary")).click();
        Alert alertt = wait.until(ExpectedConditions.alertIsPresent());
        alertt.accept();
        log.info(">>创建问卷测试成功");
        //design环节
        int problemNameCount = 0;
        int chooseTermCount = 0;
//        int editFinishCount = 0;
        int addOptions = 0;
        driver.findElements(By.className("question-type")).get(0).click();/*单选*/
        driver.findElements(By.id("problemName")).get(problemNameCount++).sendKeys("selenium单选");
        driver.findElements(By.id("chooseTerm")).get(chooseTermCount++).sendKeys("选项1");
        driver.findElements(By.className("btn-add-option")).get(addOptions++).click();
        driver.findElements(By.id("chooseTerm")).get(chooseTermCount++).sendKeys("选项2");
        driver.findElement(By.id("editFinish")).click();
        driver.findElement(By.className("btn-success")).click();
        Alert alerttt = wait.until(ExpectedConditions.alertIsPresent());
        alerttt.accept();
        log.info(">>设计问卷测试成功");
//        driver.findElements(By.className("question-type")).get(1).click();/*多选*/
//        driver.findElements(By.className("question-type")).get(2).click();/*填空*/
//        driver.findElements(By.className("question-type")).get(3).click();/*矩阵*/
//        driver.findElements(By.className("question-type")).get(4).click();/*量表*/
    }
}
