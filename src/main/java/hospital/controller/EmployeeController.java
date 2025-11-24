package hospital.controller;

import hospital.service.EmployeeService;
import hospital.staffClasses.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/employees") 
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class EmployeeController {

    @Autowired 
    private EmployeeService employeeService;

    @GetMapping
    public List<Object> getAllEmployees() {
        return employeeService.getAllEmployeesSorted();
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.addEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>("Cannot create Employee, duplicate ID. Please put another ID.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        
        if (isDeleted) {
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }
}