package hospital.controller;

import hospital.service.LeaveRequestService;
import hospital.schedule.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaverequests")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<LeaveRequest> requestLeave(@RequestBody LeaveRequest leaveRequest) {
        try {
            LeaveRequest savedRequest = leaveRequestService.submitRequest(leaveRequest);
            return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
            
        } catch (Exception e) {
            System.out.println("ERROR SAVING LEAVE REQUEST");
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestService.getAllRequests();
    }
    
    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getLeaveRequestsByEmployee(@PathVariable int employeeId) {
        return leaveRequestService.getRequestsByEmployee(employeeId);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeave(@PathVariable String id) {
        return leaveRequestService.approveRequest(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeave(@PathVariable String id) {
        return leaveRequestService.rejectRequest(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}