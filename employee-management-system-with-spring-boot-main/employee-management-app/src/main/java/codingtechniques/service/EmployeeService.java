package codingtechniques.service;

import java.util.List;
import codingtechniques.model.Employee;

public interface EmployeeService {
    void saveEmployee(Employee employee);
    Iterable<Employee> findEmployees();
    Employee getEmployeeId(Long id);
    void deleteEmployee(Long id);
    List<Employee> searchEmployees(String keyword);  // Add this method
}
