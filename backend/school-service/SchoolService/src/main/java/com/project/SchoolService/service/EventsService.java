package com.project.SchoolService.service;

import com.project.SchoolService.domain.Events;
import com.project.SchoolService.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {
    @Autowired
    private final EventsRepository eventsRepository;

    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }
    public List<Events> getAllEvents(){
        return eventsRepository.findAll();
    }
    public Events getEventsById(Long id){
        Optional<Events> events = eventsRepository.findById(id);
        if (events.isPresent()){
            return events.get();
        }else{
            throw new RuntimeException();
        }
    }
}
