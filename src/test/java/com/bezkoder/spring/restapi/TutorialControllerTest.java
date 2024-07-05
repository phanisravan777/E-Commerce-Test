package com.bezkoder.spring.restapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.bezkoder.spring.restapi.controller.TutorialController;
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
        Tutorial tutorial = new Tutorial("Test", "Test description", false);
        when(tutorialService.findAll()).thenReturn(Arrays.asList(tutorial));

        ResponseEntity<List<Tutorial>> response = tutorialController.getAllTutorials(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(tutorial, response.getBody().get(0));
    }

    @Test
    public void testGetTutorialById() {
        Tutorial tutorial = new Tutorial("Test", "Test description", false);
        when(tutorialService.findById(1L)).thenReturn(tutorial);

        ResponseEntity<Tutorial> response = tutorialController.getTutorialById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tutorial, response.getBody());
    }

    @Test
    public void testCreateTutorial() {
        Tutorial tutorial = new Tutorial("Test", "Test description", false);
        when(tutorialService.save(any(Tutorial.class))).thenReturn(tutorial);

        ResponseEntity<Tutorial> response = tutorialController.createTutorial(tutorial);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tutorial, response.getBody());
    }

    @Test
    public void testUpdateTutorial() {
        Tutorial tutorial = new Tutorial("Test", "Test description", false);
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
        verify(tutorialService, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAllTutorials() {
        ResponseEntity<HttpStatus> response = tutorialController.deleteAllTutorials();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(tutorialService, times(1)).deleteAll();
    }

    @Test
    public void testFindByPublished() {
        Tutorial tutorial = new Tutorial("Test", "Test description", true);
        when(tutorialService.findByPublished(true)).thenReturn(Arrays.asList(tutorial));

        ResponseEntity<List<Tutorial>> response = tutorialController.findByPublished();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(tutorial, response.getBody().get(0));
    }
}