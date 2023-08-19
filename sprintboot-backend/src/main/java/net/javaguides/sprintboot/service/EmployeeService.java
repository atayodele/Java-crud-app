package net.javaguides.sprintboot.service;

import net.javaguides.sprintboot.Dto.EmployeeDto;
import net.javaguides.sprintboot.exception.ResourceNotFoundException;
import net.javaguides.sprintboot.model.Employee;
import net.javaguides.sprintboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity<Employee> createEmployee(EmployeeDto model){
        try {
            Employee emp = new Employee(model.getFirstName(), model.getLastName(), model.getEmail());
            return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> emp = employeeRepository.findAll();
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    public ResponseEntity<Employee> getEmployeeById(long id) throws ResourceNotFoundException {
        Optional<Employee> student = employeeRepository.findById(id);
        if(student.isPresent()){
            return ResponseEntity.ok(student.get());
        }
        throw new ResourceNotFoundException("Record not found with Id : "+ id);
    }
    public ResponseEntity<List<Employee>> getEmployeeByName(String name) {
        try {
            List<Employee> emp = new ArrayList<Employee>();

            if (name == null)
                emp.addAll(employeeRepository.findAll());
            else
                emp.addAll(employeeRepository.findByFirstName(name));
            if (emp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(emp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Employee> updateEmployeeDetails(long id, EmployeeDto model) {
        try{
            Employee emp = employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+id));
            emp.setFirstName(model.getFirstName());
            emp.setLastName(model.getLastName());
            emp.setEmail(model.getEmail());
            return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteEmployee(long id) {
        try {
            Employee emp = employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+id));
            employeeRepository.delete(emp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            employeeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
