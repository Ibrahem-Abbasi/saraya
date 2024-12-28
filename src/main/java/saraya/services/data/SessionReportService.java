package saraya.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraya.entities.SessionReport;
import saraya.repositories.SessionReportRepository;

@Service
public class SessionReportService {
    private final SessionReportRepository repository;

    @Autowired
    public SessionReportService(SessionReportRepository repository) {
        this.repository = repository;
    }

    public SessionReport save(SessionReport sessionReport) {
        return repository.save(sessionReport);
    }

    public SessionReport update(SessionReport sessionReport, Integer id) {
        sessionReport.setId(id);
        return repository.save(sessionReport);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
