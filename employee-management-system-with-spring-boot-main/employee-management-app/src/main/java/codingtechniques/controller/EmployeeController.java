

package codingtechniques.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import codingtechniques.model.Employee;
import codingtechniques.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	
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
	public String registration(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "registration";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
     return "redirect:/employees";
	}
	
	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(Model model, @PathVariable Long id) {
		 Employee employee = employeeService.getEmployeeId(id);
		 model.addAttribute("employee", employee);
		return "updateForm";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String delete(@PathVariable Long id) {
		 employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}

}