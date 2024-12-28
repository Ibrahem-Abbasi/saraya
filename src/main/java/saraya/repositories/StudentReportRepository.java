package saraya.repositories;

import saraya.entities.StudentReport;
import org.springframework.data.repository.CrudRepository;

public interface StudentReportRepository extends CrudRepository<StudentReport, Integer> {
}
