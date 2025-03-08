package codingtechniques.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import codingtechniques.model.Employee;
import codingtechniques.service.EmployeeService;

@Controller
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    // Existing features
    @GetMapping("/employees")
    public String employees(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Employee> employees;
        if (keyword != null && !keyword.isEmpty()) {
            employees = employeeService.searchEmployees(keyword);
        } else {
            employees = (List<Employee>) employeeService.findEmployees();
        }
        model.addAttribute("employees", employees);
        model.addAttribute("keyword", keyword);
        return "employees";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("employee", new Employee());

        // Fetch existing managers
        List<String> managers = employeeService.getDistinctManagers();
        model.addAttribute("managers", managers);
        
        return "registration";
    }
    
    @PostMapping("/registerEmployee")
    public String registerEmployee(@ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration"; // Stay on the same page if there are errors
        }

        employeeService.saveEmployee(employee); // Save employee
        return "redirect:/employees"; // Redirect to employee list after successful registration
    }

 // Save employee
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee, String newManager) {
        if (newManager != null && !newManager.trim().isEmpty()) {
            employee.setManager(newManager); // Assign new manager if entered
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

 // Show Update Form
    @GetMapping("/updateEmployee/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeId(id);
        model.addAttribute("employee", employee);

        List<String> managers = employeeService.getDistinctManagers(); // Fetch managers
        model.addAttribute("managers", managers);
        
        return "updateForm"; // Points to update.html
    }

    // Handle Update Request
    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute Employee employee, String newManager) {
        if (newManager != null && !newManager.trim().isEmpty()) {
            employee.setManager(newManager); // Assign new manager if entered
        }
        employeeService.saveEmployee(employee); // Save updated employee
        return "redirect:/employees"; // Redirect to employee list
    }

    @GetMapping("/deleteEmployee/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    // New features for manager listing and employees under manager
    @GetMapping("/managers")
    public String listManagers(Model model) {
        List<String> managers = employeeService.getDistinctManagers(); // Fetch distinct managers
        model.addAttribute("managers", managers);
        return "managers";
    }

    @GetMapping("/managers/{manager}")
    public String listEmployeesUnderManager(@PathVariable String manager, Model model) {
        List<Employee> employees = employeeService.getEmployeesByManager(manager); // Fetch employees under a specific manager
        model.addAttribute("manager", manager);
        model.addAttribute("employees", employees);
        return "employees-under-manager";
    }
}
