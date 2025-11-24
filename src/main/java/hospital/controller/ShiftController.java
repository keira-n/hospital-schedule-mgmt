package hospital.controller;

import hospital.service.ShiftService;
import hospital.schedule.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@RestController
@RequestMapping("/api/shifts")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @GetMapping
    public List<Shift> getAllShifts() {
        return shiftService.getAllShiftsSorted();
    }

    @PostMapping
    public ResponseEntity<?> createShift(@RequestBody Shift shift) {
        try {
            Shift savedShift = shiftService.addShift(shift);
            return new ResponseEntity<>(savedShift, HttpStatus.CREATED);

        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>("Error: This shift (employee, date, time) already exists.", HttpStatus.CONFLICT);
        
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable String id) {
        try {
            // Delegate deletion to service
            shiftService.deleteShiftById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}