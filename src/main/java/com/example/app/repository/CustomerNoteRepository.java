package com.example.app.repository;

import com.example.app.entity.CustomerNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerNoteRepository extends JpaRepository<CustomerNote, Long> {
}
