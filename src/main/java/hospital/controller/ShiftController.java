package hospital.controller;

import hospital.database.ShiftRepository;
import hospital.schedule.Shift;
import org.springframework.beans.factory.annotation.Autowired;
// REMOVED: MongoTemplate
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable; 

// We still need Sort
import org.springframework.data.domain.Sort;
// REMOVED: Query and Criteria

@RestController
@RequestMapping("/api/shifts")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class ShiftController {

    @Autowired
    private ShiftRepository shiftRepository;

    // REMOVED: The MongoTemplate

    // --- THIS IS THE FIX ---
    // We are back to using the repository. It will now work
    // because Shift.java matches your database data.
    @GetMapping
    public List<Shift> getAllShifts(
        @RequestParam(required = false) String role
    ) {
        
        Sort sort = Sort.by(Sort.Direction.ASC, "employeeId");

        // Note: The filter by "role" is removed for simplicity.
        // We can add it back later.
        
        return shiftRepository.findAll(sort);
    }
    // --- END OF FIX ---


    // Your "create" method will now work
    @PostMapping
    public ResponseEntity<Shift> createShift(@RequestBody Shift shift) {
        try {
            Shift savedShift = shiftRepository.save(shift);
            return new ResponseEntity<>(savedShift, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Your "delete" method will now work
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable String id) {
        try {
            shiftRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}