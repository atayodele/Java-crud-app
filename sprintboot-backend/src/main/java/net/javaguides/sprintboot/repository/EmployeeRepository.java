package net.javaguides.sprintboot.repository;

import net.javaguides.sprintboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstName(String name);
    List<Employee> findByEmail(String email);
}
