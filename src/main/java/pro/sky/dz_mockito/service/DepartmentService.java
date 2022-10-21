package pro.sky.dz_mockito.service;

import pro.sky.dz_mockito.domain.Employee;

import java.util.Map;
import java.util.Set;

public interface DepartmentService {
    Employee getMaxSalaryFromDepartment(Integer departmentId);

    Employee getMinSalaryFromDepartment(Integer departmentId);

    Set<Employee> getEmployeesFromDepartment(Integer departmentId);

    Map<Integer, Set<Employee>> getEmployeesAll();
}
