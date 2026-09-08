package com.example.app.controller;

import com.example.app.entity.Task;
import com.example.app.repository.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Task")
public class TaskController {
  private final TaskRepository repository;

  public TaskController(TaskRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  @Operation(summary = "Create task")
  public ResponseEntity<Task> create(@Valid @RequestBody Task value) {
    return ResponseEntity.ok(repository.save(value));
  }

  @GetMapping
  @Operation(summary = "List task records")
  public ResponseEntity<Page<Task>> list(@PageableDefault(size = 20) Pageable pageable) {
    return ResponseEntity.ok(repository.findAll(pageable));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get task by id")
  public ResponseEntity<Task> get(@PathVariable Long id) {
    return repository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update task")
  public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody Task value) {
    return repository
        .findById(id)
        .map(
            old -> {
              value.setTitle(value.getTitle());
              return ResponseEntity.ok(repository.save(value));
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete task")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repository.existsById(id)) return ResponseEntity.notFound().build();
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
