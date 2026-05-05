package com.planmystudy.service;

import com.planmystudy.model.Note;
import com.planmystudy.model.User;
import com.planmystudy.repository.NoteRepository;
import com.planmystudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    public Note addNote(Note note) {
        if (note.getUser() != null && note.getUser().getId() != null) {
            User user = userRepository.findById(note.getUser().getId()).orElse(null);
            note.setUser(user);
        }
        return noteRepository.save(note);
    }

    public List<Note> getNotesByUser(Long userId) {
        return noteRepository.findByUserId(userId);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}