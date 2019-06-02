Feature: Upload files feature

  Background: User is on contextis.pythonanywhere.com

    @test
    Scenario Outline: User should be able to upload files
      Given User is on homepage
      When User navigates to login page
      And User enters "<username>" and "<password>"
      And User clicks on Login
      And User clicks on upload
      And User clicks on choose file
      And User selects a file
      And User clicks on upload file
      Then User should be directed to welcome page
      Examples:
        | username | password |
        | user     | password |
