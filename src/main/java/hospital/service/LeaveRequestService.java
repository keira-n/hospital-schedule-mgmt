package hospital.service;

import hospital.database.LeaveRequestRepository;
import hospital.schedule.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequest submitRequest(LeaveRequest leaveRequest) {
        System.out.println("--- Service: Processing Leave Request ---");
        leaveRequest.setStatus("Pending");
        return leaveRequestRepository.save(leaveRequest);
    }
    public List<LeaveRequest> getAllRequests() {
        return leaveRequestRepository.findAll();
    }

    public List<LeaveRequest> getRequestsByEmployee(int employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }
    public Optional<LeaveRequest> approveRequest(String id) {
        return leaveRequestRepository.findById(id).map(request -> {
            request.setStatus("Approved");
            return leaveRequestRepository.save(request);
        });
    }
    public Optional<LeaveRequest> rejectRequest(String id) {
        return leaveRequestRepository.findById(id).map(request -> {
            request.setStatus("Rejected");
            return leaveRequestRepository.save(request);
        });
    }
}
