package pro.sky.dz_mockito.service;

import org.springframework.stereotype.Service;
import pro.sky.dz_mockito.domain.Employee;
import pro.sky.dz_mockito.exception.BadRequestException;
import pro.sky.dz_mockito.exception.DepartmentNotFoundException;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImp implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImp(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getMaxSalaryFromDepartment(Integer departmentId) {
        if (departmentId == null) {
            throw new BadRequestException();
        }
        return employeeService.getEmployees()
                .values()
                .stream()
                .filter(emp -> emp.getDepartmentId() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(DepartmentNotFoundException::new);
    }

    @Override
    public Employee getMinSalaryFromDepartment(Integer departmentId) {
        if (departmentId == null) {
            throw new BadRequestException();
        }
        return employeeService.getEmployees()
                .values()
                .stream()
                .filter(emp -> emp.getDepartmentId() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(DepartmentNotFoundException::new);
    }

    @Override
    public Set<Employee> getEmployeesFromDepartment(Integer departmentId) {
        if (departmentId == null) {
            throw new BadRequestException();
        }
        Set<Employee> setTemp = employeeService.getEmployees()
                .values()
                .stream()
                .filter(emp -> emp.getDepartmentId() == departmentId).collect(Collectors.toSet());
        if (setTemp.isEmpty()) {
            throw new DepartmentNotFoundException();
        }
        return setTemp;
    }

    @Override
    public Map<Integer, Set<Employee>> getEmployeesAll() {
        return employeeService.getEmployees()
                .values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId, Collectors.toSet()));
    }
}
