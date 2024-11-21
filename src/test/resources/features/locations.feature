@tests
Feature: Validate 'Our Locations' page functionality

  Background:
    Given I navigate to the "https://datacom.com/nz/en/contact-us" URL

  Scenario: Validate page loads successfully
    When the page is loaded
    Then the page title should be "Contact Us — Get In Touch"
    And the "Our Locations" section should be visible with xpath "//h2[@class='cmp-title__text ']"

  Scenario Outline: Validate "Our Locations" New Zealand Maps Tab
    When I click the country 'New Zealand'
    And I click the city '<City>' with "<xpath>"
    Then the Address '<Section>' should be '<Address>'
    And the Phone number '<Section>' should be '<Phone>'
    And the Email '<Section>' should be '<Email>'

    Examples:
      | City         | Address                                      | Phone          | Email                                | xpath                                 | Section |
      | Auckland     | 58 Gaunt Street, Auckland CBD, Auckland 1010 | +64 9 303 1489 | reception.gaunt@datacom.co.nz        | //div[@data-map-container-id='map00'] | 0       |
      | Christchurch | 67 Gloucester Street, Christchurch 8013      | +64 3 379 7775 | reception.christchurch@datacom.co.nz | //div[@data-map-container-id='map01'] | 1       |
      | Dunedin      | Level 1, 77 Vogel Street, Dunedin 9011       | +64 3 379 7775 | reception.christchurch@datacom.co.nz | //div[@data-map-container-id='map02'] | 2       |
