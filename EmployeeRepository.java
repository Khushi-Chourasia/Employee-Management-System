package codingtechniques.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import codingtechniques.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Existing search functionality
    List<Employee> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String firstname, String lastname);

    // Fetch distinct managers
    @Query("SELECT DISTINCT e.manager FROM Employee e WHERE e.manager IS NOT NULL")
    List<String> findDistinctManagers();

    // Fetch employees under a specific manager
    List<Employee> findByManager(String manager);
    
    List<Employee> findByIsManagerTrue();
}
