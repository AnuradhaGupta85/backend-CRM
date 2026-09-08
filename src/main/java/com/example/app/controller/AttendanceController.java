package com.example.app.controller;

import com.example.app.entity.Attendance;
import com.example.app.repository.AttendanceRepository;
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
@RequestMapping("/api/v1/attendances")
@Tag(name = "Attendance")
public class AttendanceController {
    private final AttendanceRepository repository;
    public AttendanceController(AttendanceRepository repository) { this.repository = repository; }
    @PostMapping
    @Operation(summary = "Create attendance")
    public ResponseEntity<Attendance> create(@Valid @RequestBody Attendance value) { return ResponseEntity.ok(repository.save(value)); }
    @GetMapping
    @Operation(summary = "List attendance records")
    public ResponseEntity<Page<Attendance>> list(@PageableDefault(size = 20) Pageable pageable) { return ResponseEntity.ok(repository.findAll(pageable)); }
    @GetMapping("/{id}")
    @Operation(summary = "Get attendance by id")
    public ResponseEntity<Attendance> get(@PathVariable Long id) { return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PutMapping("/{id}")
    @Operation(summary = "Update attendance")
    public ResponseEntity<Attendance> update(@PathVariable Long id, @Valid @RequestBody Attendance value) { return repository.findById(id).map(old -> { value.setDate(value.getDate()); return ResponseEntity.ok(repository.save(value)); }).orElse(ResponseEntity.notFound().build()); }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete attendance")
    public ResponseEntity<Void> delete(@PathVariable Long id) { if (!repository.existsById(id)) return ResponseEntity.notFound().build(); repository.deleteById(id); return ResponseEntity.noContent().build(); }
}
