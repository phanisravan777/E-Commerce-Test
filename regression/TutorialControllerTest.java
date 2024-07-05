package com.bezkoder.spring.restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bezkoder.spring.restapi.model.Tutorial;
import com.bezkoder.spring.restapi.service.TutorialService;

public class TutorialControllerTest {

  @InjectMocks
  TutorialController tutorialController;

  @Mock
  TutorialService tutorialService;

  @Test
  public void testGetAllTutorials() {
    List<Tutorial> tutorialList = new ArrayList<>();
    tutorialList.add(new Tutorial("Test Title", "Test Description", false));
    when(tutorialService.findAll()).thenReturn(tutorialList);

    ResponseEntity<List<Tutorial>> responseEntity = tutorialController.getAllTutorials(null);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(1, responseEntity.getBody().size());
  }

  @Test
  public void testGetTutorialById() {
    Tutorial tutorial = new Tutorial("Test Title", "Test Description", false);
    when(tutorialService.findById(1L)).thenReturn(tutorial);

    ResponseEntity<Tutorial> responseEntity = tutorialController.getTutorialById(1L);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals("Test Title", responseEntity.getBody().getTitle());
  }

  @Test
  public void testCreateTutorial() {
    Tutorial tutorial = new Tutorial("Test Title", "Test Description", false);
    when(tutorialService.save(tutorial)).thenReturn(tutorial);

    ResponseEntity<Tutorial> responseEntity = tutorialController.createTutorial(tutorial);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals("Test Title", responseEntity.getBody().getTitle());
  }

  @Test
  public void testUpdateTutorial() {
    Tutorial tutorial = new Tutorial("Test Title", "Test Description", false);
    when(tutorialService.findById(1L)).thenReturn(tutorial);

    tutorial.setTitle("Updated Title");
    when(tutorialService.save(tutorial)).thenReturn(tutorial);

    ResponseEntity<Tutorial> responseEntity = tutorialController.updateTutorial(1L, tutorial);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals("Updated Title", responseEntity.getBody().getTitle());
  }

  @Test
  public void testDeleteTutorial() {
    Tutorial tutorial = new Tutorial("Test Title", "Test Description", false);
    when(tutorialService.findById(1L)).thenReturn(tutorial);

    ResponseEntity<HttpStatus> responseEntity = tutorialController.deleteTutorial(1L);

    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }

  @Test
  public void testDeleteAllTutorials() {
    ResponseEntity<HttpStatus> responseEntity = tutorialController.deleteAllTutorials();

    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }

  @Test
  public void testFindByPublished() {
    List<Tutorial> tutorialList = new ArrayList<>();
    tutorialList.add(new Tutorial("Test Title", "Test Description", true));
    when(tutorialService.findByPublished(true)).thenReturn(tutorialList);

    ResponseEntity<List<Tutorial>> responseEntity = tutorialController.findByPublished();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(1, responseEntity.getBody().size());
  }
}