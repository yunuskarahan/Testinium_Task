package com.testinium.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage{


    @FindBy(xpath = "//div[@id='productListTitle']/b")
    public WebElement searchResult;

    @FindBy(xpath = "//div[@id='productList']/div")
    public List<WebElement> randomProduct;

    @FindBy(xpath = "//h1[@class='o-productDetail__title']/span[@class='o-productDetail__description']")
    public WebElement productTitle;

    @FindBy(id = "priceNew")
    public WebElement productPrice;

    @FindBy(id = "addBasket")
    public WebElement addBasketButton;

    @FindBy(xpath = "//div[@class='m-variation']/span[@class='m-variation__item']")
    public List<WebElement> sizeOptions;

    @FindBy(xpath = "//div[@class='m-variation']/span[@class='m-variation__item -criticalStock']")
    public List<WebElement> sizeOptionsAge;

    @FindBy(xpath = "//div[@class='m-notification success']/button[@class='m-notification__button btn']")
    public WebElement basketButton;


}
