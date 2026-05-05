package com.planmystudy.controller;

import com.planmystudy.model.Timetable;
import com.planmystudy.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timetable")
@CrossOrigin(origins = "*")
public class TimetableController {

    @Autowired
    private TimetableService service;

    // SAVE
    @PostMapping
    public Timetable add(@RequestBody Timetable t){
        return service.save(t);
    }

    // GET BY USER
    @GetMapping("/user/{userId}")
    public List<Timetable> getByUser(@PathVariable Long userId){
        return service.getByUser(userId);
    }

    // ✅ DELETE (THIS FIXES YOUR ISSUE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){

        boolean deleted = service.delete(id);

        if(deleted){
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Not found");
        }
    }
}