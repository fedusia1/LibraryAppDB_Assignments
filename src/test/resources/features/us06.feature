
Feature: Books module
  As a librarian, I should be able to add new book into library
@us06 @db @ui
  Scenario Outline: Verify added book is matching with DB
    Given the "librarian" on the home page
    And the user navigates to "Books" page
    When the librarian click to add book
    And the librarian enter book name "<Book Name>"
    When the librarian enter ISBN "<ISBN>"
    And the librarian enter year "<Year>"
    When the librarian enter author "<Author>"
    And the librarian choose the book category "<Book Category>"
    And the librarian click to save changes
    Then verify "The book has been created." message is displayed
    And verify "<Book Name>" information must match with DB
    Examples:
      | Book Name                        | ISBN     | Year | Author                | Book Category        |
      | Head First Java from Liuda       | 10112021 | 2023 | Kathy Sierra          | Action and Adventure |
      | The Scrum Field Guide from Liuda | 11112021 | 2023 | Mitch Lacey           | Short Story          |
     ## | The Book Practices from Liuda    | 11112021 | 2023 | Liudmyla Fedorenko    | Classic              |
     ## | The Practices from Liuda         | 11112021 | 2023 | Liudmyla Fedorenko    | Crime and Detective  |
     ## | Good Job from Liuda              | 11112021 | 1977 | Liudmyla Fedorenko    | Short Story          |
