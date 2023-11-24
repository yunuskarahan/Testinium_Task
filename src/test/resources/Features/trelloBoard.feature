Feature: Trello board process

  @apıTest
  Scenario: Add, update and delete process
    Given Create board
    When Create a list on board.
    And Create a new card.
    And Update card.
    And Delete card.
    And Delete board.