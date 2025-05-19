package ru.hogwarts.school.WebMvcTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import skypro.hogwarts.controller.StudentController;
import skypro.hogwarts.model.Student;
import skypro.hogwarts.service.StudentService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void shouldReturnStudentById() throws Exception {
        Student student = new Student(1L, 20, "John Doe");
        when(studentService.getStudent(1L)).thenReturn(student);

        mockMvc.perform(get("/student/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));

        verify(studentService).getStudent(student.getId());
    }

    @Test
    public void shouldReturnStudentsByAge() throws Exception {
        List<Student> students = List.of(new Student(1L, 20, "Alice"), new Student(2L, 20, "Bob"));
        when(studentService.getStudentsByAge(20)).thenReturn(students);

        mockMvc.perform(get("/student/age/20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(students.size()));

        verify(studentService).getStudentsByAge(20);
    }

    @Test
    public void shouldCreateStudent() throws Exception {
        Student student = new Student(1L, 23, "Dr Dre");
        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/student")
                        .content(new ObjectMapper().writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));

        verify(studentService).createStudent(any(Student.class));
    }

    @Test
    public void shouldEditStudent() throws Exception {
        Student student = new Student(1L, 21, "Dr Dre");
        when(studentService.editeStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/student")
                        .content(new ObjectMapper().writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(student.getAge()));

        verify(studentService).editeStudent(any(Student.class));
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isOk());

        verify(studentService).deleteStudent(1L);
    }

    @Test
    public void shouldReturnAllStudents() throws Exception {
        List<Student> students = Arrays.asList(
                new Student(1L, 21, "Jenifer"),
                new Student(2L, 26, "Krab Mar")
        );
        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(students.size()));

        verify(studentService).getAllStudents();
    }

    @Test
    public void shouldReturnStudentsByAgeRange() throws Exception {
        List<Student> students = List.of(new Student(1L, 18, "Jenifer"), new Student(2L, 21, "Krab Mar"));
        when(studentService.getStudentsByAgeBetween(18, 22)).thenReturn(students);

        mockMvc.perform(get("/student/ageBetween?min=18&max=22"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(students.size()));

        verify(studentService).getStudentsByAgeBetween(18, 22);
    }

    @Test
    public void shouldReturnBadRequestWhenCreatingStudentWithInvalidData() throws Exception {
        Student invalidStudent = new Student(52, 123 , "");

        mockMvc.perform(post("/student")
                        .content(new ObjectMapper().writeValueAsString(invalidStudent))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}