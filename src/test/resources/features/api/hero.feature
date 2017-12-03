# language: en
# encoding: utf8
Feature: Hero 
  Création, modification, récupération et suppression de héros dans des cas usuels 
 
  Scenario: Création d'un héro
    When je crée un héro nommé 'SuperBob' 
    Then le système confirme que le héro est créé
    And je récupère le héro nommé 'SuperBob'
    And le système lui à assigné un id 
    
  Scenario: Recherche d'un héro
    Given je crée un héro nommé 'SuperGirl' 
    When je recherche les héros dont le nom contient 'Rgi'
    Then le système réponds une liste de héros
    And l'un d'entre eux s'appelle 'SuperGirl' 
    
  Scenario: Suppression d'un héro
    Given je crée un héro nommé 'Spiderman'
    And je stocke son id  
    And je supprime le hero avec cet id 
    When je recherche le héro avec cet id
    Then le héro n'est pas trouvé
    
  Scenario: Modification d'un héro
    Given je crée un héro nommé 'Lucky Luke'
    And je stocke son id  
    And je modifie le nom du héro avec cet id pour 'Jolly Ranger' 
    When je recherche le héro avec cet id
    Then je récupère le héro nommé 'Jolly Ranger'