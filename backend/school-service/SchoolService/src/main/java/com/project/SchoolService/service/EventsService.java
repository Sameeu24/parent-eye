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
   // CREATE
    public Events createEvent(Events event){
        return eventsRepository.save(event);
    }
   // GET ALL
    public List<Events> getAllEvents(){
        return eventsRepository.findAll();
    }
    //GET BY ID
    public Events getEventsById(Long id){
        Optional<Events> events = eventsRepository.findById(id);
        if (events.isPresent()){
            return events.get();
        }else{
            throw new RuntimeException();
        }

    }
    //UPDATE
    public Events updateEvents(Long id, Events events){
        if (eventsRepository.existsById(id)){
            events.setId(id);
            return eventsRepository.save(events);
        }else{
            throw new RuntimeException();
        }
    }
    //DELETE
    public void deleteEvents(Long id){
        if (eventsRepository.existsById(id)){
            eventsRepository.deleteById(id);

        }else{
            throw new RuntimeException();
        }
    }

}
