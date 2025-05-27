package skypro.hogwarts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro.hogwarts.model.Student;
import skypro.hogwarts.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<Collection<Student>> getStudentsByAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }


    @GetMapping("/ageBetween")
    public Collection<Student> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.getStudentsByAgeBetween(min, max);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editeStudent(@RequestBody Student book) {
        Student findStudent = studentService.editeStudent(book);
        if (findStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(findStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/ageBetweenAverage")
    public double getStudentAverageAge() {
        return studentService.getStudentsAgeAverage();
    }

    @GetMapping("/getStudentsId")
    public Collection<Integer> getStudentsId() {
        return studentService.getStudentsId();
    }

    @GetMapping("/getStudentsLastFive")
    public Collection<Integer> getStudentsLastFive() {
        return studentService.getStudentsLastFive();
    }

    @GetMapping("/getAllStudentsByNameA")
    public ResponseEntity<Collection<Student>> getAllStudentsByNameA() {
        return ResponseEntity.ok(studentService.getAllStudentsByNameA());
    }

    @GetMapping("/getLongIntTest1")
    public ResponseEntity<Long> getLongIntTest1() {
        return  ResponseEntity.ok(studentService.getLongIntTest1());
    }

    @GetMapping("/getLongIntTest2")
    public ResponseEntity<Long> getLongIntTest2() {
        return  ResponseEntity.ok(studentService.getLongIntTest2());
    }

    @GetMapping("/getLongIntTest3")
    public ResponseEntity<Long> getLongIntTest3() {
        return  ResponseEntity.ok(studentService.getLongIntTest3());
    }

    @GetMapping("/print-parallel")
    public ResponseEntity<List<Student>> getAllStudentsInThread() {
      return  ResponseEntity.ok(studentService.getAllStudentsInTread());
    }

    @GetMapping("/print-synchronized")
    public ResponseEntity<List<Student>> getAllStudentsInTreadSynchron() {
        return  ResponseEntity.ok(studentService.getAllStudentsInTreadSynchron());
    }
}