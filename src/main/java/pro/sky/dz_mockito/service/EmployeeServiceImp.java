package pro.sky.dz_mockito.service;

import org.springframework.stereotype.Service;
import pro.sky.dz_mockito.domain.Employee;
import pro.sky.dz_mockito.exception.BadRequestException;
import pro.sky.dz_mockito.exception.EmployeeAlreadyAddedException;
import pro.sky.dz_mockito.exception.EmployeeNotFoundException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private Map<String, Employee> employees = new HashMap<>();

    @Override
    public Map<String, Employee> getEmployees() {
        if (employees == null) {
            employees = new HashMap<>();
        }
        return Collections.unmodifiableMap(employees);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (employee == null || employee.getFirstName() == null || employee.getLastName() == null) {
            throw new BadRequestException();
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(Employee employee) {
        if (employee == null) {
            throw new BadRequestException();
        }
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee findEmployee(Employee employee) {
        if (employee == null) {
            throw new BadRequestException();
        }
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(employee.getFullName());
    }
}
