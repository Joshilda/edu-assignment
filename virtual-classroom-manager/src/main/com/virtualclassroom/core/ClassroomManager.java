package com.virtualclassroom.core;

import com.virtualclassroom.exceptions.EntityNotFoundException;
import com.virtualclassroom.exceptions.ValidationException;
import com.virtualclassroom.models.Assignment;
import com.virtualclassroom.models.Classroom;
import com.virtualclassroom.models.Student;
import com.virtualclassroom.utils.ConsoleLogger;

import java.util.*;

public class ClassroomManager {
    private static final ClassroomManager INSTANCE = new ClassroomManager();

    private final Map<String, Classroom> classrooms = new LinkedHashMap<>();
    private final Map<String, String> studentToClassroom = new HashMap<>();

    private ClassroomManager() {}

    public static ClassroomManager getInstance() {
        return INSTANCE;
    }

    public synchronized void addClassroom(String name) {
        validateName(name, "Classroom");
        if (classrooms.containsKey(name)) {
            throw new ValidationException("Classroom already exists: " + name);
        }
        Classroom c = EntityFactory.createClassroom(name);
        classrooms.put(name, c);
        ConsoleLogger.getLogger().info(String.format("Classroom '%s' created", name));
    }

    public synchronized void removeClassroom(String name) {
        validateName(name, "Classroom");
        Classroom removed = classrooms.remove(name);
        if (removed == null) throw new EntityNotFoundException("Classroom not found: " + name);
        removed.getStudents().forEach(s -> studentToClassroom.remove(s.getId()));
        ConsoleLogger.getLogger().info(String.format("Classroom '%s' removed", name));
    }

    public Collection<String> listClassrooms() {
        return Collections.unmodifiableCollection(classrooms.keySet());
    }

    public Classroom getClassroom(String name) {
        Classroom c = classrooms.get(name);
        if (c == null) throw new EntityNotFoundException("Classroom not found: " + name);
        return c;
    }

    public synchronized void addStudentToClassroom(String studentId, String studentName, String classroomName) {
        validateName(studentId, "Student ID");
        validateName(classroomName, "Classroom");
        Classroom c = classrooms.get(classroomName);
        if (c == null) throw new EntityNotFoundException("Classroom not found: " + classroomName);

        if (studentToClassroom.containsKey(studentId)) {
            throw new ValidationException("Student with id '" + studentId + "' already enrolled in classroom: " + studentToClassroom.get(studentId));
        }

        Student s = EntityFactory.createStudent(studentId, studentName);
        c.addStudent(s);
        studentToClassroom.put(studentId, classroomName);

        ConsoleLogger.getLogger().info(String.format("Student '%s' enrolled in classroom '%s'", studentId, classroomName));
    }

    public Collection<Student> listStudents(String classroomName) {
        Classroom c = getClassroom(classroomName);
        return c.getStudents();
    }

    public synchronized void scheduleAssignment(String classroomName, String title, String description) {
        validateName(classroomName, "Classroom");
        validateName(title, "Assignment title");
        Classroom c = getClassroom(classroomName);
        Assignment a = EntityFactory.createAssignment(title, description);
        c.addAssignment(a);
        Notifier.getInstance().notifyStudentsOfAssignment(c, title);
        ConsoleLogger.getLogger().info(String.format("Assignment '%s' scheduled for classroom '%s'", title, classroomName));
    }

    public synchronized void submitAssignment(String studentId, String classroomName, String assignmentTitle) {
        validateName(studentId, "Student ID");
        validateName(classroomName, "Classroom");
        validateName(assignmentTitle, "Assignment title");

        Classroom c = getClassroom(classroomName);
        if (!c.hasStudent(studentId)) throw new EntityNotFoundException("Student not found in classroom: " + studentId);

        Optional<Assignment> assignment = c.findAssignmentByTitle(assignmentTitle);
        if (assignment.isEmpty()) throw new EntityNotFoundException("Assignment not found in classroom: " + assignmentTitle);

        ConsoleLogger.getLogger().info(String.format("Assignment '%s' submitted by student '%s' in classroom '%s'",
                assignmentTitle, studentId, classroomName));
        System.out.printf("Assignment submitted by Student %s in %s.%n", studentId, classroomName);
    }

    private void validateName(String value, String field) {
        if (value == null || value.isBlank()) throw new ValidationException(field + " cannot be blank");
    }
}
