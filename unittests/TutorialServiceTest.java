package com.bezkoder.spring.restapi;

import static org.junit.jupiter.api.Assertions.*;

import com.bezkoder.spring.restapi.service.TutorialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.bezkoder.spring.restapi.model.Tutorial;

public class TutorialServiceTest {

    private TutorialService tutorialService;

    @BeforeEach
    public void setUp() {
        tutorialService = new TutorialService();
    }

    @Test
    public void testFindAll() {
        List<Tutorial> tutorials = tutorialService.findAll();
        assertNotNull(tutorials);
    }

    @Test
    public void testFindByTitleContaining() {
        String title = "Test";
        List<Tutorial> tutorials = tutorialService.findByTitleContaining(title);
        assertNotNull(tutorials);
    }

    @Test
    public void testFindById() {
        long id = 1;
        Tutorial tutorial = tutorialService.findById(id);
        assertNotNull(tutorial);
    }

    @Test
    public void testSave() {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Test");
        tutorial = tutorialService.save(tutorial);
        assertNotNull(tutorial);
        assertEquals("Test", tutorial.getTitle());
    }

    @Test
    public void testDeleteById() {
        long id = 1;
        tutorialService.deleteById(id);
        Tutorial tutorial = tutorialService.findById(id);
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
        boolean isPublished = true;
        List<Tutorial> tutorials = tutorialService.findByPublished(isPublished);
        assertNotNull(tutorials);
    }
}