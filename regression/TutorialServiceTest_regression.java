import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.bezkoder.spring.restapi.model.Tutorial;
import com.bezkoder.spring.restapi.service.TutorialService;

public class TutorialServiceTest {

  @Autowired
  private TutorialService tutorialService;

  @Test
  public void testFindAll() {
    List<Tutorial> tutorials = tutorialService.findAll();
    assertNotNull(tutorials);
  }

  @Test
  public void testFindByTitleContaining() {
    List<Tutorial> tutorials = tutorialService.findByTitleContaining("test");
    assertNotNull(tutorials);
  }

  @Test
  public void testFindById() {
    Tutorial tutorial = tutorialService.findById(1);
    assertNotNull(tutorial);
  }

  @Test
  public void testSave() {
    Tutorial tutorial = new Tutorial();
    tutorial.setTitle("Test");
    tutorial.setDescription("Test Description");
    tutorial.setPublished(false);
    Tutorial savedTutorial = tutorialService.save(tutorial);
    assertNotNull(savedTutorial);
  }

  @Test
  public void testDeleteById() {
    tutorialService.deleteById(1);
    Tutorial tutorial = tutorialService.findById(1);
    assertNull(tutorial);
  }

  @Test
  public void testDeleteAll() {
    tutorialService.deleteAll();
    List<Tutorial> tutorials = tutorialService.findAll();
    assertTrue(tutorials.isEmpty());
  }

  @Test
  public void testFindByPublished() {
    List<Tutorial> tutorials = tutorialService.findByPublished(true);
    assertNotNull(tutorials);
  }
}