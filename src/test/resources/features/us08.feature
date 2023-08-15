@us08
Feature: Users module
  As a librarian, I should be able to see users by status
  @ui @db
  Scenario: verify user status with DB
    Given the "librarian" on the home page
    And the user navigates to "Users" page
    When the user selected status "ACTIVE"
    And the gets number of users
    Then  verify "ACTIVE" status users count matching with DB