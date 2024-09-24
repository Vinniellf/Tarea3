package com.example.tarea.Repositories;

import com.example.tarea.Entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}
