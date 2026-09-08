package com.example.app.controller;

import com.example.app.entity.Lead;
import com.example.app.repository.LeadRepository;
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
@RequestMapping("/api/v1/leads")
@Tag(name = "Lead")
public class LeadController {
  private final LeadRepository repository;

  public LeadController(LeadRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  @Operation(summary = "Create lead")
  public ResponseEntity<Lead> create(@Valid @RequestBody Lead value) {
    return ResponseEntity.ok(repository.save(value));
  }

  @GetMapping
  @Operation(summary = "List lead records")
  public ResponseEntity<Page<Lead>> list(@PageableDefault(size = 20) Pageable pageable) {
    return ResponseEntity.ok(repository.findAll(pageable));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get lead by id")
  public ResponseEntity<Lead> get(@PathVariable Long id) {
    return repository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update lead")
  public ResponseEntity<Lead> update(@PathVariable Long id, @Valid @RequestBody Lead value) {
    return repository
        .findById(id)
        .map(
            old -> {
              old.setName(value.getName());
              return ResponseEntity.ok(repository.save(old));
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete lead")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repository.existsById(id)) return ResponseEntity.notFound().build();
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
