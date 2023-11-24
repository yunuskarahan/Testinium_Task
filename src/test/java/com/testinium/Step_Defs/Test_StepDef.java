package com.testinium.Step_Defs;

import com.testinium.Page.BasketPage;
import com.testinium.Page.HomePage;
import com.testinium.Page.ProductPage;
import com.testinium.Utilities.BrowserUtils;
import com.testinium.Utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import java.io.*;
import java.util.Random;

import static com.testinium.Utilities.ConfigurationReader.getProperty;
import static org.junit.Assert.*;



public class Test_StepDef {

    HomePage homePage = new HomePage();
    ProductPage productPage=new ProductPage();
    BasketPage basketPage = new BasketPage();

    Actions actions = new Actions(Driver.getDriver());


    String shirt, shorts,price;

    @Given("Navigate to home page")
    public void navigateToHomePage() {
        Driver.getDriver().get(getProperty("url"));

    }

    @When("The user click Tüm Çerezleri Reddet button")
    public void theUserClickTumCerezleriReddetButton() {
        BrowserUtils.waitForClickability(homePage.cookiesButton, 15);
        homePage.cookiesButton.click();
    }

    @Then("Verify that the user should be see {string}")
    public void verifyThatTheUserShouldBeSee(String expectedText) {
        BrowserUtils.waitForVisibility(homePage.popupText, 15);
        String actualText = homePage.popupText.getText();
        System.out.println("actualText = " + actualText);
        assertEquals(expectedText, actualText);

    }

    @Then("Verify that user should be see Home Page")
    public void verifyThatUserShouldBeSeeHomePage() {
        String expectedUrl = getProperty("url");
        System.out.println("expectedUrl = " + expectedUrl);
        String actualUtl = Driver.getDriver().getCurrentUrl();
        System.out.println("actualUtl = " + actualUtl);

        assertEquals(expectedUrl, actualUtl);

    }

    @When("User can click on gender man button")
    public void userCanClickOnGenderManButton() {
        BrowserUtils.waitForClickability(homePage.genderManButton, 15);
        homePage.genderManButton.click();
    }



    @And("User can get products name from excel file")
    public void userCanGetProductsNameFromExcelFile() throws Exception {

        FileInputStream fs = new FileInputStream("searchKey.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        shorts = sheet.getRow(0).getCell(0).getStringCellValue();
        System.out.println(shorts);
        shirt = sheet.getRow(0).getCell(1).getStringCellValue();
        System.out.println(shirt);


        }

    @And("User can enter şort on search box.")
    public void userCanEnterSortOnSearchBox() {
        BrowserUtils.waitForVisibility(homePage.searchBox,15);
        homePage.searchBoxForClick.click();
        homePage.searchBoxForSendKeys.sendKeys(shorts);
        BrowserUtils.waitFor(2);

    }

    @And("User can delete text in search box.")
    public void userCanDeleteTextInSearchBox() {
        homePage.searchBoxForSendKeys.clear();
    }

    @And("User can enter gömlek on search box.")
    public void userCanEnterGomlekOnSearchBox() {
        homePage.searchBoxForSendKeys.sendKeys(shirt);
    }

    @And("User can use enter keyword for search.")
    public void userCanUseEnterKeywordForSearch() {
        homePage.searchBoxForSendKeys.sendKeys(Keys.ENTER);
    }

    @Then("Verify the user's search result is correct")
    public void verifyTheUserSSearchResultIsCorrect() {
        String searchResultText = productPage.searchResult.getText();
        String actualSearchResult =searchResultText.substring(1,searchResultText.length() - 1);

        assertEquals(shirt,actualSearchResult);

    }

    @When("User randomly selects product")
    public void userRandomlySelectsProduct() {

        Random rand = new Random();
        int randomProduct = rand.nextInt(productPage.randomProduct.size());
        productPage.randomProduct.get(randomProduct).click();


    }

    @And("Print product and price information to txt file")
    public void printProductAndPriceInformationToTxtFile() throws IOException {


        String title = productPage.productTitle.getText()+ " - "  ;
        price=  productPage.productPrice.getText();


        File file = new File("dosya.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(title);
        bWriter.write(price);
        bWriter.close();

    }

    @And("User can select  as a random sizes")
    public void userCanSelectAsARandomSizes() {

       if (!productPage.sizeOptions.isEmpty()){
           Random rand = new Random();
           int randomSize = rand.nextInt(productPage.sizeOptions.size());
           productPage.sizeOptions.get(randomSize).click();
       }else if (!productPage.sizeOptionsAge.isEmpty()){
            Random rand = new Random();
            int randomSize = rand.nextInt(productPage.sizeOptionsAge.size());
            productPage.sizeOptionsAge.get(randomSize).click();
        }




    }
    @And("User can add product on basket")
    public void userCanAddProductOnBasket() {
        BrowserUtils.waitForClickability(productPage.addBasketButton,5);
        productPage.addBasketButton.click();

    }




    @And("User can click on the basket button")
    public void userCanClickOnTheBasketButton() {
        productPage.basketButton.click();
    }
    @Then("Verify that should be same   product page price and basket price")
    public void verifyThatShouldBeSameProductPagePriceAndBasketPrice() {

        double productPrice =Double.parseDouble(basketPage.basketProductPrice.getText().replace(",", "").replace(" TL", ""));
        double basketProductPrice2 =Double.parseDouble(price.replace(",", "").replace(" TL", ""));
        Assert.assertEquals(productPrice, basketProductPrice2, 0.001);


    }


    @When("User selects the number of baskets {string}")
    public void userSelectsTheNumberOfBaskets(String quantity) {


        Select select =new Select(basketPage.quantityOptions);
      //  basketPage.quantityOptions.click();
        select.selectByValue(quantity);

    }

    @Then("Verify that the user that the number of items in the cart is {string}")
    public void verifyThatTheUserThatTheNumberOfItemsInTheCartIs(String productQuantity) {

        Select select =new Select(basketPage.quantityOptions);
       WebElement selectedOption = select.getFirstSelectedOption();
       assertEquals(productQuantity,selectedOption.getAttribute("value"));
        System.out.println(selectedOption.getAttribute("value"));

    }

    @When("User can click basket remove button")
    public void userCanClickBasketRemoveButton() {

        BrowserUtils.clickWithJS(basketPage.basketRemove);

        assertTrue(basketPage.productDeleteMessage.isDisplayed());
    }

    @Then("Verify that the user should see {string} message")
    public void verifyThatTheUserShouldSeeMessage(String expectedBasketMessageText) {


        try {
            BrowserUtils.waitForVisibility(basketPage.basketMessageText,5);
            String actualBasketMessageText = basketPage.basketMessageText.getText();
            System.out.println("actualBasketMessageText = " + actualBasketMessageText);
            assertEquals(expectedBasketMessageText,actualBasketMessageText);
        }catch (Exception e){
            BrowserUtils.clickWithJS(basketPage.basketRemove);
            BrowserUtils.waitForVisibility(basketPage.basketMessageText,5);
            String actualBasketMessageText = basketPage.basketMessageText.getText();
            System.out.println("actualBasketMessageText = " + actualBasketMessageText);
            assertEquals(expectedBasketMessageText,actualBasketMessageText);
        }

    }
}



