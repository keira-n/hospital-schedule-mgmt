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

@RestController
@RequestMapping("/api/shifts")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class ShiftController {

    @Autowired
    private ShiftRepository shiftRepository;
    @GetMapping
    public List<Shift> getAllShifts() {
        // This finds all shifts and sorts them by employeeId, ascending
        return shiftRepository.findAll(Sort.by(Sort.Direction.ASC, "employeeId"));
    }

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