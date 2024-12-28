package saraya.controllers.data;

import saraya.entities.Session;
import saraya.entities.Attendance;
import saraya.entities.SessionReport;
import saraya.services.data.SessionService;
import saraya.services.data.AttendanceService;
import saraya.services.data.SessionReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;
    private final AttendanceService attendanceService;
    private final SessionReportService sessionReportService;

    @Autowired
    public SessionController(SessionService sessionService, AttendanceService attendanceService,
                             SessionReportService sessionReportService) {
        this.sessionService = sessionService;
        this.attendanceService = attendanceService;
        this.sessionReportService = sessionReportService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable("id") Integer id) {
        return sessionService.getById(id).map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Session> saveSession(@RequestBody Session session) {
        return ResponseEntity.ok(sessionService.save(session));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable("id") Integer id,
                                                 @RequestBody Session session) {
        return ResponseEntity.ok(sessionService.update(session, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable("id") Integer id) {
        try {
            sessionService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ///// Attendances ///// //

    @PostMapping("/attendance")
    public ResponseEntity<Iterable<Attendance>> saveAttendances(
            @RequestBody Iterable<Attendance> attendances) {
        return ResponseEntity.ok(attendanceService.saveMany(attendances));
    }

    @PutMapping("/attendance/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable("id") Integer id,
                                                       @RequestBody Attendance attendance) {
        return ResponseEntity.ok(attendanceService.update(attendance, id));
    }

    // ///// Reports ///// //

    @PostMapping("/report")
    public ResponseEntity<SessionReport> saveSessionReport(@RequestBody SessionReport sessionReport) {
        return ResponseEntity.ok(sessionReportService.save(sessionReport));
    }

    @PutMapping("/report/{id}")
    public ResponseEntity<SessionReport> updateSessionReport(@PathVariable("id") Integer id,
                                                             @RequestBody SessionReport sessionReport) {
        return ResponseEntity.ok(sessionReportService.update(sessionReport, id));
    }

    @DeleteMapping("/report/{id}")
    public ResponseEntity<?> deleteSessionReport(@PathVariable("id") Integer id) {
        try {
            sessionReportService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}