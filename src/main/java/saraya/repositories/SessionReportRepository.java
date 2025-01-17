package saraya.repositories;

import org.springframework.data.repository.CrudRepository;
import saraya.entities.SessionReport;

public interface SessionReportRepository extends CrudRepository<SessionReport, Integer> {
}
