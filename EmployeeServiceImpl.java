package codingtechniques.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codingtechniques.model.Employee;
import codingtechniques.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    // Existing methods
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Iterable<Employee> findEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeId(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(keyword, keyword);
    }

    public List<String> getDistinctManagers() {
        List<String> managers = employeeRepository.findDistinctManagers();
        
        // Remove lowercase "other" if it exists
        managers.removeIf(manager -> manager.equalsIgnoreCase("other"));

        // Add only capitalized "Other" (if not already there)
        if (!managers.contains("Other")) {
            managers.add("Other");
        }

        return managers;
    }

    @Override
    public List<Employee> getEmployeesByManager(String manager) {
        return employeeRepository.findByManager(manager);
    }

	@Override
	public List<Employee> getManagers() {
		return employeeRepository.findByIsManagerTrue();
	}
}
