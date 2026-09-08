package com.example.app.controller;

import com.example.app.entity.Employee;
import com.example.app.repository.EmployeeRepository;
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
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee")
public class EmployeeController {
  private final EmployeeRepository repository;

  public EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  @Operation(summary = "Create employee")
  public ResponseEntity<Employee> create(@Valid @RequestBody Employee value) {
    return ResponseEntity.ok(repository.save(value));
  }

  @GetMapping
  @Operation(summary = "List employee records")
  public ResponseEntity<Page<Employee>> list(@PageableDefault(size = 20) Pageable pageable) {
    return ResponseEntity.ok(repository.findAll(pageable));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get employee by id")
  public ResponseEntity<Employee> get(@PathVariable Long id) {
    return repository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update employee")
  public ResponseEntity<Employee> update(
      @PathVariable Long id, @Valid @RequestBody Employee value) {
    return repository
        .findById(id)
        .map(
            old -> {
              old.setFullName(value.getFullName());
              return ResponseEntity.ok(repository.save(old));
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete employee")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repository.existsById(id)) return ResponseEntity.notFound().build();
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
