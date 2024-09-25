package com.example.demo.service;

import com.example.demo.model.State;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository repository;

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    public List<Task> findAllByState(State state) {
        return repository.findByState(state);
    }

    public Task updateState(Long id, State state) {
        Optional<Task> taskFromDbOpt = repository.findById(id);
        if (taskFromDbOpt.isPresent()) {
            Task taskFromDb = taskFromDbOpt.get();
            taskFromDb.setState(state);
            return repository.save(taskFromDb);
        } else {
            throw new IllegalArgumentException("Book with id: " + id + "not found");
        }
    }

    public Task save(Task book) {
        return repository.save(book);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
