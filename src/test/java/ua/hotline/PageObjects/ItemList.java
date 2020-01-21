package ua.hotline.PageObjects;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ItemList {
    private By firstItem = By.xpath("//li[@class='product-item'][1]/div[@class='item-info']/p/a");

    public ItemList waitForProductsListPage() {
        Driver.getDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"products-list cell-list\"]")));
        return new ItemList();
    }

    public Item openFirstItem() {
        WebElement firstItemElement = Driver.getDriver().findElement(firstItem);
        firstItemElement.click();
        return new Item();
    }

    public ItemList sortByPrice() {
        Driver.getDriver().findElement(By.xpath("//ul[@class='sorting-in']/li/select[@class='field']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        return new ItemList();
    }

    public int countExpensiveItemsOnCurrentPage(int maxPrice) {
       /* int expensiveItemsCount = 0;
        String priceString;
        String checkClass;
        String checkPrice;
        int price;
        for (int i = 1; i < 21; i++) {
            try {
                checkClass = Driver.getDriver().findElement(By.xpath("//li[@class='product-item'][" + i + "]/div[3]")).getAttribute("class");
                System.out.println("Item = " + i);
                if (checkClass.equals("item-price stick-bottom")) {


                        priceString = Driver.getDriver().findElement(By.xpath("//li[@class='product-item'][" + i +"]//span[@class='value']")).getText();

                    priceString = priceString.replaceAll(" ", "");
                    price = Integer.parseInt(priceString);
                    System.out.println("price = " + price);
                    if (price > maxPrice) {
                        expensiveItemsCount++;
                    }
                }
            } catch (NoSuchElementException ignored) {
            }
        }
        return expensiveItemsCount;
        */

        //div[contains(@class,'price-md')]/span[contains(@class,'value')]
        WebElement nextPage;
        int temp = 0;
        for (int i = 1; i < 5;i++ ){

            nextPage = Driver.getDriver().findElement(By.xpath("//a[@class='next']"));
            nextPage.click();
            WebElement price[] = Driver.getDriver().findElements(By.xpath("//div[span[contains(@class, 'value')]]")).toArray(new WebElement[0]);
            System.out.println("Price from: " + i + " page" );
            //maxPrice = checkPrice(price);
            if(maxPrice  < checkPrice(price)){
                temp = checkPrice(price);
            }
        }
        return temp;
    }

    public static int checkPrice(WebElement[] price){
        for (int i = 0; i < price.length;i++){
            String priceStr = price[i].getText();
            Integer priceValue = Integer.parseInt(priceStr.replace(" ", "").replace("грн", ""));
            if(priceValue > 10000) {
                return priceValue;
            }
        }
        System.out.println("Pages does not have price more than 10000");
        return 0;
    }

/*
    public int countExpensiveItems(int maxPrice, int amountOfPages) {
        int page = 1;
        int expansiveItemsCounter = 0;
        do {
            System.out.println("current page = " + page);
            expansiveItemsCounter += new ItemList().countExpensiveItemsOnCurrentPage(maxPrice);
            System.out.println("expensiveItemsCounter = " + expansiveItemsCounter);
            new ItemList().nextPage();
            page++;
        } while (page < (amountOfPages + 1));
        return expansiveItemsCounter;
    }

    private ItemList nextPage() {
        Driver.getDriver().findElement(By.xpath("//a[@class='next']")).click();
        return new ItemList();
    }*/
}
