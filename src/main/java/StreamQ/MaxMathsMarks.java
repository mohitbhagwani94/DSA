package StreamQ;

import java.util.*;
import java.util.stream.*;

class Subject {
    String subjectName;
    Integer subjectMarks;

    public Subject(String subjectName, Integer subjectMarks) {
        this.subjectName = subjectName;
        this.subjectMarks = subjectMarks;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Integer getSubjectMarks() {
        return subjectMarks;
    }
}

class Student {
    String name;
    List<Subject> subjects;

    public Student(String name, List<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}

public class MaxMathsMarks {
    public static void main(String[] args) {
        // Sample data
        List<Student> students = Arrays.asList(
                new Student("Alice", Arrays.asList(
                        new Subject("Maths", 85),
                        new Subject("Science", 90))),
                new Student("Bob", Arrays.asList(
                        new Subject("Maths", 95),
                        new Subject("Science", 80))),
                new Student("Charlie", Arrays.asList(
                        new Subject("Maths", 88),
                        new Subject("Science", 92)))
        );

        //List<Student> students = new ArrayList<>();

        // Find student with max marks in Maths
        Optional<Student> topMathsStudent = students.stream().max(
                Comparator.comparingInt(stud -> stud.getSubjects().stream()
                        .filter(sub -> sub.getSubjectName().equalsIgnoreCase("Maths"))
                        .findFirst()
                        .map(Subject :: getSubjectMarks)
                        .orElse(0)
                )
        );


        topMathsStudent.ifPresent(s -> {
            int marks = s.getSubjects().stream()
                    .filter(sub -> sub.getSubjectName().equalsIgnoreCase("Maths"))
                    .findFirst().get().getSubjectMarks();

            System.out.println("Topper in Maths: " + s.getName() + " with " + marks + " marks");
        });
    }
}
