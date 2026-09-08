package com.example.app.controller;

import com.example.app.entity.CustomerNote;
import com.example.app.repository.CustomerNoteRepository;
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
@RequestMapping("/api/v1/customer-notes")
@Tag(name = "CustomerNote")
public class CustomerNoteController {
    private final CustomerNoteRepository repository;
    public CustomerNoteController(CustomerNoteRepository repository) { this.repository = repository; }
    @PostMapping
    @Operation(summary = "Create customernote")
    public ResponseEntity<CustomerNote> create(@Valid @RequestBody CustomerNote value) { return ResponseEntity.ok(repository.save(value)); }
    @GetMapping
    @Operation(summary = "List customernote records")
    public ResponseEntity<Page<CustomerNote>> list(@PageableDefault(size = 20) Pageable pageable) { return ResponseEntity.ok(repository.findAll(pageable)); }
    @GetMapping("/{id}")
    @Operation(summary = "Get customernote by id")
    public ResponseEntity<CustomerNote> get(@PathVariable Long id) { return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PutMapping("/{id}")
    @Operation(summary = "Update customernote")
    public ResponseEntity<CustomerNote> update(@PathVariable Long id, @Valid @RequestBody CustomerNote value) { return repository.findById(id).map(old -> { value.setNote(value.getNote()); return ResponseEntity.ok(repository.save(value)); }).orElse(ResponseEntity.notFound().build()); }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customernote")
    public ResponseEntity<Void> delete(@PathVariable Long id) { if (!repository.existsById(id)) return ResponseEntity.notFound().build(); repository.deleteById(id); return ResponseEntity.noContent().build(); }
}
