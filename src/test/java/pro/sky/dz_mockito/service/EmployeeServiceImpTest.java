package pro.sky.dz_mockito.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.dz_mockito.exception.*;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.dz_mockito.service.EmployeeValues.*;

public class EmployeeServiceImpTest {
    private EmployeeService employeeService;

    @BeforeEach
    void init() {
        employeeService = new EmployeeServiceImp();
    }

    @Test
    void addEmployeeTest() {
        assertAll("Testing addEmployee",
                () -> assertEquals(employeeService.addEmployee(IGOR), IGOR),
                () -> assertThrows(BadRequestException.class, () -> employeeService.addEmployee(null)),
                () -> assertThrows(BadRequestException.class, () -> employeeService.addEmployee(MAX)),
                () -> assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(IGOR))
        );
    }

    @Test
    void removeEmployee() {
        employeeService.addEmployee(ALENA);
        employeeService.addEmployee(PETR);
        assertAll("Testing removeEmployee",
                () -> assertEquals(employeeService.removeEmployee(ALENA), ALENA),
                () -> assertThrows(BadRequestException.class, () -> employeeService.removeEmployee(null)),
                () -> assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee(IGOR))
        );
    }

    @Test
    void findEmployee() {
        employeeService.addEmployee(ALENA);
        employeeService.addEmployee(PETR);
        assertAll("Testing removeEmployee",
                () -> assertEquals(employeeService.findEmployee(ALENA), ALENA),
                () -> assertThrows(BadRequestException.class, () -> employeeService.findEmployee(null)),
                () -> assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee(IGOR))
        );
    }
}
