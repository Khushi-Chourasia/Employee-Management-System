package codingtechniques.service;

import java.util.List;

import codingtechniques.model.Employee;

public interface EmployeeService {
    
    // Existing methods
    void saveEmployee(Employee employee);

    Iterable<Employee> findEmployees();

    Employee getEmployeeId(Long id);

    void deleteEmployee(Long id);

    List<Employee> searchEmployees(String keyword);

    // New methods
    List<String> getDistinctManagers(); // Fetch distinct managers

    List<Employee> getEmployeesByManager(String manager); // Fetch employees under a manager

    List<Employee> getManagers(); // Fetch only employees marked as managers
}
