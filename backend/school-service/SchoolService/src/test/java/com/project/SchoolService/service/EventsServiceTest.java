package com.project.SchoolService.service;

import com.project.SchoolService.domain.Events;
import com.project.SchoolService.repository.EventsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventsServiceTest {

    @InjectMocks
    private EventsService eventsService;

    @Mock
    private EventsRepository eventsRepository;

    public EventsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEvent() {
        Events event = new Events();
        event.setId(1L);
        event.setSubject("Math Meeting");
        event.setDescription("Discuss math curriculum");

        when(eventsRepository.save(any(Events.class))).thenReturn(event);

        Events createdEvent = eventsService.createEvent(event);
        assertEquals("Math Meeting", createdEvent.getSubject());
        verify(eventsRepository, times(1)).save(event);
    }

    @Test
    void getAllEvents() {
        when(eventsRepository.findAll()).thenReturn(Collections.singletonList(new Events()));

        assertFalse(eventsService.getAllEvents().isEmpty());
        verify(eventsRepository, times(1)).findAll();
    }

    @Test
    void getEventsById_found() {
        Events event = new Events();
        event.setId(1L);
        when(eventsRepository.findById(1L)).thenReturn(Optional.of(event));

        Events foundEvent = eventsService.getEventsById(1L);
        assertNotNull(foundEvent);
        assertEquals(1L, foundEvent.getId());
        verify(eventsRepository, times(1)).findById(1L);
    }

    @Test
    void getEventsById_notFound() {
        when(eventsRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventsService.getEventsById(1L);
        });
        assertEquals("Event not found with id :1", exception.getMessage());
    }

    @Test
    void updateEvents_found() {
        Events existingEvent = new Events();
        existingEvent.setId(1L);
        existingEvent.setSubject("Math Meeting");

        Events updatedEvent = new Events();
        updatedEvent.setId(1L);
        updatedEvent.setSubject("Science Meeting");

        when(eventsRepository.existsById(1L)).thenReturn(true);
        when(eventsRepository.save(any(Events.class))).thenReturn(updatedEvent);

        Events result = eventsService.updateEvents(1L, updatedEvent);
        assertEquals("Science Meeting", result.getSubject());
        verify(eventsRepository, times(1)).save(updatedEvent);
    }

    @Test
    void updateEvents_notFound() {
        Events updatedEvent = new Events();
        updatedEvent.setId(1L);

        when(eventsRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventsService.updateEvents(1L, updatedEvent);
        });
        assertEquals("Event not found with id :1", exception.getMessage());
    }

    @Test
    void deleteEvents_found() {
        when(eventsRepository.existsById(1L)).thenReturn(true);

        eventsService.deleteEvents(1L);
        verify(eventsRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteEvents_notFound() {
        when(eventsRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventsService.deleteEvents(1L);
        });
        assertEquals("Event not found with id :1", exception.getMessage());
    }
}
