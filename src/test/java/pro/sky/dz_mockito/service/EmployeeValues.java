package pro.sky.dz_mockito.service;

import pro.sky.dz_mockito.domain.Employee;

import java.util.*;

class EmployeeValues {
    public final static Employee IGOR = new Employee("Igor", "Pupkin", 1, 50000);
    public final static Employee ALENA = new Employee("Alena", "Sidorova", 1, 45000);
    public final static Employee MAX = new Employee(null, "Ivanov", 2, 48000);
    public final static Employee PETR = new Employee("Petr", "Petrov", 2, 25000);
    public final static Employee IVAN = new Employee("Ivan", "Ivanov", 2, 55000);

    public static Map<String, Employee> MAP_EMP = new HashMap<>();
    static {
        MAP_EMP.put(IGOR.getFullName(), IGOR);
        MAP_EMP.put(PETR.getFullName(), PETR);
        MAP_EMP.put(ALENA.getFullName(), ALENA);
        MAP_EMP.put(IVAN.getFullName(), IVAN);
    }

    public static Set<Employee> DEPARTMENT1 = new HashSet<>(List.of(IGOR,ALENA));
    public static Set<Employee> DEPARTMENT2 = new HashSet<>(List.of(PETR,IVAN));
    public static Map<Integer, Set<Employee>> MAP_EMP_DEP = new HashMap<>();
    static {
        MAP_EMP_DEP.put(1, DEPARTMENT1);
        MAP_EMP_DEP.put(2, DEPARTMENT2);
    }
}
