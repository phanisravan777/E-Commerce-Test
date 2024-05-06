package com.bezkoder.spring.restapi.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.bezkoder.spring.restapi.model.Tutorial;

public class TutorialTest {

    private Tutorial tutorial;

    @BeforeEach
    public void setUp() {
        tutorial = new Tutorial();
    }

    @Test
    public void testGetId() {
        long id = 1L;
        tutorial.setId(id);
        assertEquals(id, tutorial.getId());
    }

    @Test
    public void testGetTitle() {
        String title = "Test Title";
        tutorial.setTitle(title);
        assertEquals(title, tutorial.getTitle());
    }

    @Test
    public void testGetDescription() {
        String description = "Test Description";
        tutorial.setDescription(description);
        assertEquals(description, tutorial.getDescription());
    }

    @Test
    public void testIsPublished() {
        tutorial.setPublished(true);
        assertTrue(tutorial.isPublished());
    }

    @Test
    public void testToString() {
        String title = "Test Title";
        String description = "Test Description";
        boolean published = true;
        long id = 1L;
        tutorial.setId(id);
        tutorial.setTitle(title);
        tutorial.setDescription(description);
        tutorial.setPublished(published);
        String expectedOutput = "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
        assertEquals(expectedOutput, tutorial.toString());
    }

    // Edge Cases
    @Test
    public void testEmptyTitle() {
        String title = "";
        tutorial.setTitle(title);
        assertEquals(title, tutorial.getTitle());
    }

    @Test
    public void testNullTitle() {
        tutorial.setTitle(null);
        assertNull(tutorial.getTitle());
    }

    @Test
    public void testEmptyDescription() {
        String description = "";
        tutorial.setDescription(description);
        assertEquals(description, tutorial.getDescription());
    }

    @Test
    public void testNullDescription() {
        tutorial.setDescription(null);
        assertNull(tutorial.getDescription());
    }

    @Test
    public void testUnsetPublished() {
        assertFalse(tutorial.isPublished());
    }
}