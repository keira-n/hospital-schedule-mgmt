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

// --- 1. IMPORT THESE TWO CLASSES ---
import org.springframework.data.domain.Sort;
// REMOVED: Query (we don't need it)

@RestController
@RequestMapping("/api/shifts")
// This is the new, fixed line for CORS
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class ShiftController {

    @Autowired
    private ShiftRepository shiftRepository;

    // REMOVED: The MongoTemplate

    // --- 2. THIS IS THE FIX ---
    // We go back to using the repository, which is correct.
    // It returns a List<Shift>, not List<Object>.
    @GetMapping
    public List<Shift> getAllShifts() {
        // This finds all shifts and sorts them by employeeId, ascending.
        return shiftRepository.findAll(Sort.by(Sort.Direction.ASC, "employeeId"));
    }
    // --- END OF FIX ---


    // Your "create" method will now work because of the pom.xml fix
    @PostMapping
    public ResponseEntity<Shift> createShift(@RequestBody Shift shift) {
        try {
            Shift savedShift = shiftRepository.save(shift);
            return new ResponseEntity<>(savedShift, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


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