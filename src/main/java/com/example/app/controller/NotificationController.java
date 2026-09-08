package com.example.app.controller;

import com.example.app.entity.Notification;
import com.example.app.repository.NotificationRepository;
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
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notification")
public class NotificationController {
    private final NotificationRepository repository;
    public NotificationController(NotificationRepository repository) { this.repository = repository; }
    @PostMapping
    @Operation(summary = "Create notification")
    public ResponseEntity<Notification> create(@Valid @RequestBody Notification value) { return ResponseEntity.ok(repository.save(value)); }
    @GetMapping
    @Operation(summary = "List notification records")
    public ResponseEntity<Page<Notification>> list(@PageableDefault(size = 20) Pageable pageable) { return ResponseEntity.ok(repository.findAll(pageable)); }
    @GetMapping("/{id}")
    @Operation(summary = "Get notification by id")
    public ResponseEntity<Notification> get(@PathVariable Long id) { return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PutMapping("/{id}")
    @Operation(summary = "Update notification")
    public ResponseEntity<Notification> update(@PathVariable Long id, @Valid @RequestBody Notification value) { return repository.findById(id).map(old -> { value.setMessage(value.getMessage()); return ResponseEntity.ok(repository.save(value)); }).orElse(ResponseEntity.notFound().build()); }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete notification")
    public ResponseEntity<Void> delete(@PathVariable Long id) { if (!repository.existsById(id)) return ResponseEntity.notFound().build(); repository.deleteById(id); return ResponseEntity.noContent().build(); }
}
