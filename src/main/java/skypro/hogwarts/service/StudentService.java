package skypro.hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import skypro.hogwarts.model.Student;
import skypro.hogwarts.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student  createStudent(Student student) {
        logger.info("createStudent");
        return studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        logger.info("getStudent");
        return studentRepository.findById(id).get();
    }

    public Student editeStudent(Student student) {
        logger.info("editeStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("deleteStudent");
        studentRepository.deleteById(id);
    }


    public Collection<Student> getAllStudents() {
        logger.info("getAllStudents");
        return studentRepository.findAll();
    }


    public Collection<Student> getStudentsByAge(int age) {
        logger.info("getStudentsByAge");
        return studentRepository.findAll().stream().filter(s -> s.getAge() == age).toList();
    }

    public Collection<Student> getStudentsByAgeBetween(int min, int max) {
        logger.info("getStudentsByAgeBetween");
        return studentRepository.findStudentsByAgeBetween(min, max);
    }

    public Collection<Student> getStundentByName(String name) {
        logger.info("getStundentByName");
        return studentRepository.findStudentByNameIgnoreCase(name);
    }

    public Collection<Integer> getStudentsAgeAverage() {
        logger.info("getStudentsAgeAverage");
        return studentRepository.getStudentsAgeAverage();
    }
    public Collection<Integer> getStudentsId() {
        logger.info("getStudentsId");
        return studentRepository.getStudentsId();
    }
    public Collection<Integer> getStudentsLastFive() {
        logger.info("getStudentsLastFive");
        return studentRepository.getStudentsLastFive();
    }
}
