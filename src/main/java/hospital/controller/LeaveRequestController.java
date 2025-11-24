package hospital.controller;

import hospital.database.LeaveRequestRepository;
import hospital.schedule.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leaverequests")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class LeaveRequestController {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @PostMapping
    public ResponseEntity<LeaveRequest> requestLeave(@RequestBody LeaveRequest leaveRequest) {
        System.out.println("--- Received a Leave Request! ---");
        System.out.println("Employee ID: " + leaveRequest.getEmployeeId());
        System.out.println("Reason: " + leaveRequest.getReason());

        try {
            leaveRequest.setStatus("Pending");
            LeaveRequest savedRequest = leaveRequestRepository.save(leaveRequest);
            
            System.out.println("--- Successfully saved to database! ---");
            return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
            
        } catch (Exception e) {
            System.out.println("ERROR SAVING LEAVE REQUEST");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }
    
    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getLeaveRequestsByEmployee(@PathVariable int employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }


    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeave(@PathVariable String id) {
        Optional<LeaveRequest> requestOpt = leaveRequestRepository.findById(id);
        if (requestOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        LeaveRequest request = requestOpt.get();
        request.setStatus("Approved");
        leaveRequestRepository.save(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeave(@PathVariable String id) {
        Optional<LeaveRequest> requestOpt = leaveRequestRepository.findById(id);
        if (requestOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        LeaveRequest request = requestOpt.get();
        request.setStatus("Rejected");
        leaveRequestRepository.save(request);
        return ResponseEntity.ok(request);
    }
}