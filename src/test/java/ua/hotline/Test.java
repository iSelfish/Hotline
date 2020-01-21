package ua.hotline;

import core.Driver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ua.hotline.PageObjects.StartPage;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test {

    @Before
    public void basicConfigure(){
        BasicConfigurator.configure();
    }
    @After
    public void killDriver() {
        Driver.kill();
    }

    //1. вводим в поиск телевизор, открываем первый товар, в характеристиках проверяем что в графе тип есть слово телевизор (Тип: LCD телевизор (LED))
    @org.junit.Test
    public void firstTest() {
        String testType;
        testType = new StartPage()
                .navigateTo("https://hotline.ua/")
                .closePopUp()
                .textToSearchField("телевизор")
                .clickSearchButton()
                .waitForProductsListPage()
                .openFirstItem()
                .waitForSpecificationPage()
                .getType();
        /*скриншотик
        File screenshotAs = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenshotAs, new File("C:/Users/Dmytro_Kolos/Desktop/image.png"));
        }catch (IOException e){
            e.printStackTrace();
        }*/
        assertTrue(testType.contains("телевізор"));
    }

    //2. вводим в поиск телевизор, сортируем по возрастанию цены и проверяем что на первых 5 ти страницах нет телевизоров дороже 10000
    @org.junit.Test
    public void secondTest() {
        int expensiveItems;
        expensiveItems = new StartPage()
                .navigateTo("https://hotline.ua/")
                .closePopUp()
                .textToSearchField("телевизор")
                .clickSearchButton()
                .waitForProductsListPage()
                .sortByPrice()
                .countExpensiveItems(10000, 5);
        assertEquals(0, expensiveItems);
    }
}
