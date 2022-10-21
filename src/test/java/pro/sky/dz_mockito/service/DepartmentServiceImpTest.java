package pro.sky.dz_mockito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.dz_mockito.exception.BadRequestException;
import pro.sky.dz_mockito.exception.DepartmentNotFoundException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.dz_mockito.service.EmployeeValues.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImpTest {
    @Mock
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @BeforeEach
    void init() {
        departmentService = new DepartmentServiceImp(employeeService);
    }

    @Test
    void getMaxSalaryFromDepartmentTest() {
        when(employeeService.getEmployees()).thenReturn(MAP_EMP);
        assertAll("testing getMaxSalaryFromDepartment",
                () -> assertEquals(departmentService.getMaxSalaryFromDepartment(2), IVAN),
                () -> assertThrows(BadRequestException.class,
                        () -> departmentService.getMaxSalaryFromDepartment(null)),
                () -> assertThrows(DepartmentNotFoundException.class,
                        () -> departmentService.getMaxSalaryFromDepartment(5))
        );
    }

    @Test
    void getMinSalaryFromDepartmentTest() {
        when(employeeService.getEmployees()).thenReturn(MAP_EMP);
        assertAll("testing getMinSalaryFromDepartment",
                () -> assertEquals(departmentService.getMinSalaryFromDepartment(1), ALENA),
                () -> assertThrows(BadRequestException.class,
                        () -> departmentService.getMinSalaryFromDepartment(null)),
                () -> assertThrows(DepartmentNotFoundException.class,
                        () -> departmentService.getMinSalaryFromDepartment(10))
        );
    }

    @Test
    void getEmployeesFromDepartmentTest() {
        when(employeeService.getEmployees()).thenReturn(MAP_EMP);
        assertAll("testing getEmployeesFromDepartment",
                () -> assertIterableEquals(departmentService.getEmployeesFromDepartment(1), DEPARTMENT1),
                () -> assertIterableEquals(departmentService.getEmployeesFromDepartment(2), DEPARTMENT2),
                () -> assertThrows(BadRequestException.class,
                        () -> departmentService.getEmployeesFromDepartment(null)),
                () -> assertThrows(DepartmentNotFoundException.class,
                        () -> departmentService.getEmployeesFromDepartment(10))
        );
    }

    @Test
    void getEmployeesTest() {
        when(employeeService.getEmployees()).thenReturn(MAP_EMP);
            assertIterableEquals(Collections.singleton(departmentService.getEmployeesAll()),
                Collections.singleton(MAP_EMP_DEP));
            assertNotSame(Collections.singleton(departmentService.getEmployeesAll()),
                    Collections.singleton(MAP_EMP_DEP));
    }
}