Feature: Version
  Cette feature vérifie qu'un appel sur getVersion fonctionne

  Background:
    Given baseUri is http://localhost:8080/demo-rest-back

  Scenario: Should test header
    When I GET /version
    Then response code should be 200
    And response code should not be 401
    And response header Content-Type should exist