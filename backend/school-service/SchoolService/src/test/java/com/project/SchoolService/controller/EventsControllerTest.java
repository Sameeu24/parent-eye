package com.project.SchoolService.controller;

import com.project.SchoolService.domain.Events;
import com.project.SchoolService.service.EventsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventsControllerTest {

    @InjectMocks
    private EventsController eventsController;

    @Mock
    private EventsService eventsService;

    public EventsControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEvent() {
        Events event = new Events();
        event.setId(1L);
        event.setSubject("Math Meeting");

        when(eventsService.createEvent(any(Events.class))).thenReturn(event);

        ResponseEntity<Events> response = eventsController.createEvent(event);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Math Meeting", response.getBody().getSubject());
        verify(eventsService, times(1)).createEvent(event);
    }

    @Test
    void getAllEvents() {
        when(eventsService.getAllEvents()).thenReturn(Collections.singletonList(new Events()));

        ResponseEntity<List<Events>> response = eventsController.getAllEvents();
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
        verify(eventsService, times(1)).getAllEvents();
    }

    @Test
    void getEventsById_found() {
        Events event = new Events();
        event.setId(1L);
        when(eventsService.getEventsById(1L)).thenReturn(event);

        ResponseEntity<Events> response = eventsController.getEventsById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(eventsService, times(1)).getEventsById(1L);
    }

    @Test
    void getEventsById_notFound() {
        when(eventsService.getEventsById(1L)).thenThrow(new RuntimeException("Event not found with id :1"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventsController.getEventsById(1L);
        });
        assertEquals("Event not found with id :1", exception.getMessage());
    }

    @Test
    void updateEvent_found() {
        Events event = new Events();
        event.setId(1L);
        event.setSubject("Math Meeting");

        when(eventsService.updateEvents(1L, event)).thenReturn(event);

        ResponseEntity<Events> response = eventsController.updateEvent(1L, event);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(eventsService, times(1)).updateEvents(1L, event);
    }

    @Test
    void updateEvent_notFound() {
        Events event = new Events();
        event.setId(1L);

        when(eventsService.updateEvents(1L, event)).thenThrow(new RuntimeException("Event not found with id :1"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventsController.updateEvent(1L, event);
        });
        assertEquals("Event not found with id :1", exception.getMessage());
    }

    @Test
    void deleteEvents_found() {
        doNothing().when(eventsService).deleteEvents(1L);

        ResponseEntity<Void> response = eventsController.deleteEventsById(1L);
        assertEquals(204, response.getStatusCodeValue());
        verify(eventsService, times(1)).deleteEvents(1L);
    }

    @Test
    void deleteEvents_notFound() {
        doThrow(new RuntimeException("Event not found with id :1")).when(eventsService).deleteEvents(1L);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventsController.deleteEventsById(1L);
        });
        assertEquals("Event not found with id :1", exception.getMessage());
    }
}
