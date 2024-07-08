package com.bezkoder.spring.restapi;

import static org.junit.jupiter.api.Assertions.*;

import com.bezkoder.spring.restapi.service.TutorialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.bezkoder.spring.restapi.model.Tutorial;
import org.mockito.Mockito;
import java.util.Arrays;

public class TutorialServiceTest {

    private TutorialService tutorialService;

    @BeforeEach
    public void setUp() {
        tutorialService = Mockito.mock(TutorialService.class);
    }

    @Test
    public void testFindAll() {
        Mockito.when(tutorialService.findAll()).thenReturn(Arrays.asList(new Tutorial()));
        List<Tutorial> tutorials = tutorialService.findAll();
        assertNotNull(tutorials);
        assertEquals(1, tutorials.size());
    }

    @Test
    public void testFindByTitleContaining() {
        String title = "Test";
        Mockito.when(tutorialService.findByTitleContaining(title)).thenReturn(Arrays.asList(new Tutorial()));
        List<Tutorial> tutorials = tutorialService.findByTitleContaining(title);
        assertNotNull(tutorials);
        assertEquals(1, tutorials.size());
    }

    @Test
    public void testFindById() {
        long id = 1;
        Mockito.when(tutorialService.findById(id)).thenReturn(new Tutorial());
        Tutorial tutorial = tutorialService.findById(id);
        assertNotNull(tutorial);
    }

    @Test
    public void testSave() {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Test");
        Mockito.when(tutorialService.save(tutorial)).thenReturn(tutorial);
        tutorial = tutorialService.save(tutorial);
        assertNotNull(tutorial);
        assertEquals("Test", tutorial.getTitle());
    }

    @Test
    public void testDeleteById() {
        long id = 1;
        Mockito.doNothing().when(tutorialService).deleteById(id);
        tutorialService.deleteById(id);
        Mockito.verify(tutorialService, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testDeleteAll() {
        Mockito.doNothing().when(tutorialService).deleteAll();
        tutorialService.deleteAll();
        Mockito.verify(tutorialService, Mockito.times(1)).deleteAll();
    }

    @Test
    public void testFindByPublished() {
        boolean isPublished = true;
        Mockito.when(tutorialService.findByPublished(isPublished)).thenReturn(Arrays.asList(new Tutorial()));
        List<Tutorial> tutorials = tutorialService.findByPublished(isPublished);
        assertNotNull(tutorials);
        assertEquals(1, tutorials.size());
    }
}