package com.example.demo.controller;

import com.example.demo.model.StateWrapper;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/find_by_state")
    public ResponseEntity<List<Task>> getByState(@RequestBody StateWrapper state) {
        return ResponseEntity.ok(service.findAllByState(state.getState()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody Task book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateState(@RequestBody StateWrapper state, @PathVariable Long id) {
        return ResponseEntity.ok(service.updateState(id, state.getState()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
