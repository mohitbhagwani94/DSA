package StreamQ;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private double salary;

    // constructor
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
}

public class AgeGrouping {

    // Method to group employees by age range
    public static Map<String, List<String>> groupEmployeesByAgeRange(List<Employee> employees) {
//        return employees.stream()
//                .collect(Collectors.groupingBy(
//                        emp -> getAgeRange(emp.getAge()),          // key: age range
//                        Collectors.mapping(Employee::getName, Collectors.toList()) // value: list of names
//                ));

        return employees.stream()
                .collect(
                        Collectors.groupingBy(
                                emp -> getAgeRange(emp.getAge()),
                                Collectors.mapping(Employee::getName, Collectors.toList())
                        ));
    }

    // Helper method to decide the age group
    private static String getAgeRange(int age) {
        if (age < 20) return "Below 20";
        else if (age >= 20 && age < 30) return "20-29";
        else if (age >= 30 && age < 40) return "30-39";
        else if (age >= 40 && age < 50) return "40-49";
        else if (age >= 50 && age < 60) return "50-59";
        else return "60 and above";
    }

    // Simple demo
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 25, 50000),
                new Employee("Bob", 32, 60000),
                new Employee("Charlie", 45, 70000),
                new Employee("David", 19, 40000),
                new Employee("Eve", 55, 80000),
                new Employee("Frank", 61, 90000)
        );

        Map<String, List<String>> grouped = groupEmployeesByAgeRange(employees);

        grouped.forEach((ageRange, names) ->
                System.out.println(ageRange + " -> " + names)
        );
    }
}

