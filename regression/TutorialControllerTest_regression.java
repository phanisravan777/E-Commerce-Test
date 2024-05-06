package com.bezkoder.spring.restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bezkoder.spring.restapi.model.Tutorial;
import com.bezkoder.spring.restapi.service.TutorialService;

public class TutorialControllerTest {

  @InjectMocks
  TutorialController tutorialController;

  @Mock
  TutorialService tutorialService;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllTutorials() {
    List<Tutorial> list = new ArrayList<Tutorial>();
    Tutorial tutOne = new Tutorial("Spring Boot", "Spring Boot Description", true);
    Tutorial tutTwo = new Tutorial("Java Core", "Java Core Description", false);
    Tutorial tutThree = new Tutorial("JavaScript", "JavaScript Description", true);

    list.add(tutOne);
    list.add(tutTwo);
    list.add(tutThree);

    when(tutorialService.findAll()).thenReturn(list);

    List<Tutorial> result = tutorialController.getAllTutorials(null).getBody();
    assertEquals(3, result.size());
  }

  @Test
  public void testGetTutorialById() {
    when(tutorialService.findById(1L)).thenReturn(new Tutorial("Java Core", "Java Core Description", false));

    Tutorial tutorial = tutorialController.getTutorialById(1L).getBody();

    assertEquals("Java Core", tutorial.getTitle());
  }

  @Test
  public void testCreateTutorial() {
    Tutorial tut = new Tutorial("Spring Boot", "Spring Boot Description", true);
    when(tutorialService.save(tut)).thenReturn(tut);

    Tutorial result = tutorialController.createTutorial(tut).getBody();

    assertEquals("Spring Boot", result.getTitle());
  }

  @Test
  public void testUpdateTutorial() {
    Tutorial tut = new Tutorial("Spring Boot", "Spring Boot Description", true);
    when(tutorialService.findById(1L)).thenReturn(tut);
    when(tutorialService.save(tut)).thenReturn(tut);

    Tutorial result = tutorialController.updateTutorial(1L, tut).getBody();

    assertEquals("Spring Boot", result.getTitle());
  }

  @Test
  public void testDeleteTutorial() {
    Tutorial tut = new Tutorial("Spring Boot", "Spring Boot Description", true);
    when(tutorialService.findById(1L)).thenReturn(tut);

    ResponseEntity<HttpStatus> result = tutorialController.deleteTutorial(1L);

    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
  }

  @Test
  public void testDeleteAllTutorials() {
    ResponseEntity<HttpStatus> result = tutorialController.deleteAllTutorials();

    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
  }

  @Test
  public void testFindByPublished() {
    List<Tutorial> list = new ArrayList<Tutorial>();
    Tutorial tutOne = new Tutorial("Spring Boot", "Spring Boot Description", true);
    Tutorial tutTwo = new Tutorial("Java Core", "Java Core Description", false);
    Tutorial tutThree = new Tutorial("JavaScript", "JavaScript Description", true);

    list.add(tutOne);
    list.add(tutTwo);
    list.add(tutThree);

    when(tutorialService.findByPublished(true)).thenReturn(list);

    List<Tutorial> result = tutorialController.findByPublished().getBody();
    assertEquals(2, result.size());
  }
}