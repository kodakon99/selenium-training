# Day 1
- WebDriver = remote control for the browser; Selenium Manager downloads drivers automatically
- @BeforeMethod / @Test / @AfterMethod run order
- A test needs an assertion or it can't fail
- Found locators with right-click → Inspect

# Day 2
https://www.saucedemo.com"
- Username CSS: #user-name    Xpath: $x("//input[@data-test='username']")
- Password CSS: #password     Xpath: $x("//input[@data-test='password']")
- Login Button CSS: #login-button   Xpath: $x("//input[contains(@id,'login-button')]")
- Error Message CSS: .error-message-container.error  Xpath: $x("//h3[@data-test='error']")


- All product cards  CSS: .inventory_item     Xpath: $x("//div[@data-test='inventory-item']")
- All product names  CSS:  .inventory_item_name     Xpath: $x("//div[@data-test='inventory-item-name']")
- All prices	CSS: .inventory_item_price    Xpath: $x("//div[@data-test='inventory-item-price']")
- Price of "Sauce Labs Backpack" only (XPath with ancestor)	Xpath: $x("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']")
- Add-to-cart button of "Sauce Labs Onesie" (without its id) Xpath: $x("//div[text()='Sauce Labs Onesie']/ancestor::div[@class='inventory_item']//button")
- Sort dropdown	CSS: .product_sort_container    Xpath: $x("//select[@data-test='product-sort-container']")
- Cart icon	CSS: #shopping_cart_container    Xpath: $x("//div[contains(@id,'shopping_cart')]")
- Cart badge number (add one item first)  CSS: .shopping_cart_badge  Xpath: $x("//span[@data-test='shopping-cart-badge']")

https://the-internet.herokuapp.com/tables
- All rows in Example 1's table body  CSS: #table1 tbody tr  Xpath: $x("//table[contains(@id,'table1')]/tbody/tr")
- The email of the person with last name "Conway"  Xpath: $x("//table[@id='table1']//td[text()='Conway']/../td[3]")
- The "delete" link in the row for "Bach"  Xpath: $x("//table[@id='table1']//td[text()='Bach']/ancestor::tr//a[@href='#delete']")


- Bach's first name	Xpath: $x("//table[@id='table1']//td[text()='Bach']/following-sibling::td[1]")
- Doe's Due amount	Xpath: $x("//table[@id='table2']//td[text()='Doe']/../td[4]")
- Smith's website	Xpath: $x("//table[@id='table1']//td[text()='Smith']/../td[5]")
- Bach's delete link  Xpath: $x("//table[@id='table1']//td[text()='Bach']/ancestor::tr//a[@href='#delete']")





