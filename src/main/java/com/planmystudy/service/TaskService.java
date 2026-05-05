package com.planmystudy.service;

import com.planmystudy.model.Task;
import com.planmystudy.model.User;
import com.planmystudy.repository.TaskRepository;
import com.planmystudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task addTask(Task task) {
        if (task.getUser() != null && task.getUser().getId() != null) {
            User user = userRepository.findById(task.getUser().getId()).orElse(null);
            task.setUser(user);
        }
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // 🔥 NEW METHOD (Progress)
    public Map<String, Integer> getTaskProgress(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);

        int completed = 0;
        int pending = 0;

        for (Task task : tasks) {
            if ("completed".equalsIgnoreCase(task.getStatus())) {
                completed++;
            } else {
                pending++;
            }
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("completed", completed);
        result.put("pending", pending);

        return result;
    }
}