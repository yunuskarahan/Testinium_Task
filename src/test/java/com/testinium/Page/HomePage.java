package com.testinium.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//a[@class='o-header__logo']")
    public WebElement headerLogo;

    @FindBy(id = "onetrust-reject-all-handler")
    public WebElement cookiesButton;

    @FindBy(xpath = "//span[@class='genderPopup__title']")
    public  WebElement popupText;

    @FindBy(id = "genderManButton")
    public  WebElement genderManButton;

    @FindBy(xpath = "//div[@class='o-header__search--wrapper']")
    public  WebElement searchBox;


    @FindBy(xpath = "//div[@class='o-header__search--wrapper']//input")
    public  WebElement searchBoxForClick;


    @FindBy(name= "qSugesstion")
    public  WebElement searchBoxForSendKeys;






}
