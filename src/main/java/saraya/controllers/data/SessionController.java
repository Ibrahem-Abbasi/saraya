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

    @PutMapping("/attendance/{attendanceId}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable("attendanceId") Integer attendanceId,
                                                       @RequestBody Attendance attendance) {
        return ResponseEntity.ok(attendanceService.update(attendance, attendanceId));
    }

    // ///// Reports ///// //

    @PostMapping("/report")
    public ResponseEntity<SessionReport> saveSessionReport(@RequestBody SessionReport sessionReport) {
        return ResponseEntity.ok(sessionReportService.save(sessionReport));
    }

    @PutMapping("/report/{reportId}")
    public ResponseEntity<SessionReport> updateSessionReport(@PathVariable("reportId") Integer reportId,
                                                             @RequestBody SessionReport sessionReport) {
        return ResponseEntity.ok(sessionReportService.update(sessionReport, reportId));
    }

    @DeleteMapping("/report/{reportId}")
    public ResponseEntity<?> deleteSessionReport(@PathVariable("reportId") Integer reportId) {
        try {
            sessionReportService.delete(reportId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}