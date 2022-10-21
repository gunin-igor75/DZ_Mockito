package pro.sky.dz_mockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.dz_mockito.domain.Employee;
import pro.sky.dz_mockito.service.DepartmentService;

import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    Employee getMaxSalary(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return departmentService.getMaxSalaryFromDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    Employee getMinSalary(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return departmentService.getMinSalaryFromDepartment(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    Set<Employee> getEmployeeDep(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return departmentService.getEmployeesFromDepartment(departmentId);
    }

    @GetMapping("/all")
    Map<Integer, Set<Employee>> getEmployeeAll() {
        return departmentService.getEmployeesAll();
    }
}
