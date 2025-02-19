package codingtechniques.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import codingtechniques.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String firstname, String lastname);
}
