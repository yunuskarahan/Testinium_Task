Feature: Testinium Task

  @test
  Scenario: Beymen website testing
    Given Navigate to home page
    Then Verify that user should be see Home Page
    When The user click Tüm Çerezleri Reddet button
    Then Verify that the user should be see "Alışveriş deneyiminizi kişiselleştirebilmemiz için lütfen cinsiyet seçiminizi yapınız."
    When User can click on gender man button
    And User can get products name from excel file
    And User can enter şort on search box.
    And User can delete text in search box.
    And User can enter gömlek on search box.
    And User can use enter keyword for search.
    Then Verify the user's search result is correct
    When User randomly selects product
    And Print product and price information to txt file
    And User can select  as a random sizes
    And User can add product on basket
    And User can click on the basket button
    Then Verify that should be same   product page price and basket price
    When User selects the number of baskets "2"
    Then Verify that the user that the number of items in the cart is "2"
    When User can click basket remove button
    Then Verify that the user should see "SEPETINIZDE ÜRÜN BULUNMAMAKTADIR" message









