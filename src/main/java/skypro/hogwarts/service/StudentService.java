package skypro.hogwarts.service;

import org.springframework.stereotype.Service;
import skypro.hogwarts.model.Student;
import skypro.hogwarts.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student  createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student editeStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }


    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Collection<Student> getStudentsByAge(int age) {
        return studentRepository.findAll().stream().filter(s -> s.getAge() == age).toList();
    }

    public Collection<Student> getStudentsByAgeBetween(int min, int max) {
        return studentRepository.findStudentsByAgeBetween(min, max);
    }

    public Collection<Student> getStundentByName(String name) {
        return studentRepository.findStudentByNameIgnoreCase(name);
    }
}
