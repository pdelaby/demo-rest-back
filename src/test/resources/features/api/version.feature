Feature: Version 
  Cette feature vérifie qu'un appel sur getVersion fonctionne 
 
  Scenario: Should test header 
    When I GET /version 
    Then response code should be 200 
    And response code should not be 401 
    And the body shoud not be null