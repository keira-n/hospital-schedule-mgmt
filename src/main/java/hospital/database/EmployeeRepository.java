package hospital.database;

import hospital.staffClasses.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// NOTE: <Employee, String> refers to the @Id field (databaseId)
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    void deleteById(int id);
    boolean existsById(int id);
}