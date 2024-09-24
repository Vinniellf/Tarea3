package com.example.tarea.Repositories;

import com.example.tarea.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Consulta que busca en todas las columnas relevantes
    @Query("SELECT e FROM Employee e WHERE " +
            "LOWER(e.first_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(e.last_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(e.job.jobTitle) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(e.department.departmentName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(e.department.location.city) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Employee> searchEmployees(@Param("searchTerm") String searchTerm);
}
