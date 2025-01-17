package saraya.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraya.entities.Report;
import saraya.repositories.ReportRepository;

import java.util.Optional;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Iterable<Report> getAll() {
        return reportRepository.findAll();
    }

    public Optional<Report> getById(Integer id) {
        return reportRepository.findById(id);
    }

    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public Report update(Report report, Integer id) {
        report.setId(id);
        return reportRepository.save(report);
    }

    public void delete(Integer id) {
        reportRepository.deleteById(id);
    }
}
