import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bezkoder.spring.restapi.controller.TutorialController;
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
    when(tutorialService.findAll()).thenReturn(Arrays.asList(new Tutorial()));
    ResponseEntity<List<Tutorial>> response = tutorialController.getAllTutorials(null);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody().size());
  }

  @Test
  public void testGetTutorialById() {
    when(tutorialService.findById(1L)).thenReturn(new Tutorial());
    ResponseEntity<Tutorial> response = tutorialController.getTutorialById(1L);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testCreateTutorial() {
    when(tutorialService.save(any(Tutorial.class))).thenReturn(new Tutorial());
    ResponseEntity<Tutorial> response = tutorialController.createTutorial(new Tutorial());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void testUpdateTutorial() {
    when(tutorialService.findById(1L)).thenReturn(new Tutorial());
    when(tutorialService.save(any(Tutorial.class))).thenReturn(new Tutorial());
    ResponseEntity<Tutorial> response = tutorialController.updateTutorial(1L, new Tutorial());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testDeleteTutorial() {
    ResponseEntity<HttpStatus> response = tutorialController.deleteTutorial(1L);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(tutorialService).deleteById(1L);
  }

  @Test
  public void testDeleteAllTutorials() {
    ResponseEntity<HttpStatus> response = tutorialController.deleteAllTutorials();
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(tutorialService).deleteAll();
  }

  @Test
  public void testFindByPublished() {
    when(tutorialService.findByPublished(true)).thenReturn(Arrays.asList(new Tutorial()));
    ResponseEntity<List<Tutorial>> response = tutorialController.findByPublished();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody().size());
  }
}