package hospital.service;

import hospital.database.ShiftRepository;
import hospital.schedule.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> getAllShiftsSorted() {
        return shiftRepository.findAll(Sort.by(Sort.Direction.ASC, "employeeId"));
    }
    public Shift addShift(Shift shift) {
        return shiftRepository.save(shift);
    }
    public void deleteShiftById(String id) {
        shiftRepository.deleteById(id);
    }
}
