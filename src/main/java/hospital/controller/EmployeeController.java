package hospital.controller;

import hospital.database.EmployeeRepository;
import hospital.schedule.Shift;
import hospital.staffClasses.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/employees") 
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    @Autowired 
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        // This finds all employees and sorts them by employeeId, ascending
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}