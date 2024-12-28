package saraya.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraya.entities.Attendance;
import saraya.repositories.AttendanceRepository;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Iterable<Attendance> saveMany(Iterable<Attendance> attendances) {
        return attendanceRepository.saveAll(attendances);
    }

    public Attendance update(Attendance attendance, Integer id) {
        attendance.setId(id);
        return attendanceRepository.save(attendance);
    }
}
