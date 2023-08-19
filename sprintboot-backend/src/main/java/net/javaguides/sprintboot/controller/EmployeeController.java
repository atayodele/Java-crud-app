package net.javaguides.sprintboot.controller;

import net.javaguides.sprintboot.Dto.EmployeeDto;
import net.javaguides.sprintboot.model.Employee;
import net.javaguides.sprintboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return employeeService.getAllEmployee();
    }
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto model)
    {
        return employeeService.createEmployee(model);
    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id)
    {
        return employeeService.getEmployeeById(id);
    }
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody EmployeeDto model)
    {
        return employeeService.updateEmployeeDetails(id, model);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id)
    {
        return employeeService.deleteEmployee(id);
    }
    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        return employeeService.deleteAllEmployees();
    }
}
