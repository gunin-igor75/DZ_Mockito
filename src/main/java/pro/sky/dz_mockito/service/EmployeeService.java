package pro.sky.dz_mockito.service;


import pro.sky.dz_mockito.domain.Employee;

import java.util.Map;

public interface EmployeeService {
    Map<String, Employee> getEmployees();

    Employee addEmployee(Employee employee);

    Employee removeEmployee(Employee employee);

    Employee findEmployee(Employee employee);

}
