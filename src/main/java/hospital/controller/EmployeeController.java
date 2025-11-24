package hospital.controller;

import hospital.database.EmployeeRepository;
import hospital.staffClasses.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DuplicateKeyException;
import java.util.List;

@RestController 
@RequestMapping("/api/employees") 
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class EmployeeController {

    @Autowired 
    private EmployeeRepository employeeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public List<Object> getAllEmployees() {
        
        System.out.println("--- DEBUG: getAllEmployees() method was called! ---");
        
        Query query = new Query();

        query.with(Sort.by(Sort.Direction.ASC, "id"));

        return mongoTemplate.find(query, Object.class, "employee");
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeRepository.save(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>("Cannot create Employee, duplicate ID. Please put another ID.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}