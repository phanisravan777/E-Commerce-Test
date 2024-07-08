package com.bezkoder.spring.restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bezkoder.spring.restapi.model.Tutorial;
import com.bezkoder.spring.restapi.service.TutorialService;

@ExtendWith(MockitoExtension.class)
public class TutorialControllerTest {

  @InjectMocks
  TutorialController tutorialController;

  @Mock
  TutorialService tutorialService;

  @Test
  public void testGetAllTutorials() {
    List<Tutorial> tutorials = new ArrayList<>();
    when(tutorialService.findAll()).thenReturn(tutorials);
    ResponseEntity<List<Tutorial>> response = tutorialController.getAllTutorials(null);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(tutorials, response.getBody());
  }

  @Test
  public void testGetTutorialById() {
    Tutorial tutorial = new Tutorial();
    when(tutorialService.findById(1L)).thenReturn(tutorial);
    ResponseEntity<Tutorial> response = tutorialController.getTutorialById(1L);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(tutorial, response.getBody());
  }

  @Test
  public void testCreateTutorial() {
    Tutorial tutorial = new Tutorial();
    when(tutorialService.save(any(Tutorial.class))).thenReturn(tutorial);
    ResponseEntity<Tutorial> response = tutorialController.createTutorial(tutorial);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(tutorial, response.getBody());
  }

  @Test
  public void testUpdateTutorial() {
    Tutorial tutorial = new Tutorial();
    when(tutorialService.findById(1L)).thenReturn(tutorial);
    when(tutorialService.save(any(Tutorial.class))).thenReturn(tutorial);
    ResponseEntity<Tutorial> response = tutorialController.updateTutorial(1L, tutorial);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(tutorial, response.getBody());
  }

  @Test
  public void testDeleteTutorial() {
    ResponseEntity<HttpStatus> response = tutorialController.deleteTutorial(1L);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void testDeleteAllTutorials() {
    ResponseEntity<HttpStatus> response = tutorialController.deleteAllTutorials();
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  public void testFindByPublished() {
    List<Tutorial> tutorials = new ArrayList<>();
    when(tutorialService.findByPublished(true)).thenReturn(tutorials);
    ResponseEntity<List<Tutorial>> response = tutorialController.findByPublished();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(tutorials, response.getBody());
  }
}