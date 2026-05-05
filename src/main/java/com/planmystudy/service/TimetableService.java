package com.planmystudy.service;

import com.planmystudy.model.Timetable;
import com.planmystudy.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepository repo;

    public Timetable save(Timetable t){
        return repo.save(t);
    }

    public List<Timetable> getByUser(Long userId){
        return repo.findByUserId(userId);
    }

    // ✅ FIXED DELETE (IMPORTANT)
    public boolean delete(Long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}