package ua.hotline;

import core.Driver;
import org.junit.After;
import org.junit.Assert;
import ua.hotline.PageObjects.StartPage;

public class Test {

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
        Assert.assertTrue(testType.contains("телевизор"));
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
        Assert.assertEquals(0, expensiveItems);
    }
}