package hospital.service;

import hospital.database.EmployeeRepository;
import hospital.staffClasses.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Object> getAllEmployeesSorted() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "id"));
        return mongoTemplate.find(query, Object.class, "employee");
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public boolean deleteEmployee(int id) {
        System.out.println("--- Service: Checking for Integer ID: " + id + " ---");

        if (employeeRepository.existsById(id)) {
            System.out.println("--- Found! Deleting... ---");
            employeeRepository.deleteById(id);
            return true;
        }
        
        System.out.println("--- Not Found (Check if ID is correct) ---");
        return false;
    }
}