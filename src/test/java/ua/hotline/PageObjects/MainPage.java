package ua.hotline.PageObjects;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage {
    private By closePopUp = By.xpath("//div[@class='confirm-city dropdown-bd active']/i[@class='close']");
    private By searchField = By.xpath("//input[@id='searchbox']");
    private By doSearch = By.xpath("//input[@id='doSearch']");
    public MainPage closePopUp() {
        WebElement closePopUpElement = Driver.getDriver().findElement(closePopUp);
        closePopUpElement.click();
        return new MainPage();
    }
    public MainPage textToSearchField(String text) {
        WebElement searchFieldElement = Driver.getDriver().findElement(searchField);
        searchFieldElement.sendKeys(text);
        return new MainPage();
    }
    public ItemList clickSearchButton() {
        WebElement doSearchElement = Driver.getDriver().findElement(doSearch);
        doSearchElement.click();
        return new ItemList();
    }
}
