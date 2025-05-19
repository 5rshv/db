package skypro.hogwarts.TestRestTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import skypro.hogwarts.model.Faculty;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testGetFacultyInfo() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/id/2", Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty faculty = response.getBody();
        assertNotNull(faculty);
        assertEquals(2L, faculty.getId());
        assertEquals("string", faculty.getName());
        assertEquals("string", faculty.getColor());
    }

    @Test
    public void testGetFacultyByColor() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/color/red", Faculty[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty[] faculties = response.getBody();
        assertNotNull(faculties);
        for (Faculty faculty : faculties) {
            assertEquals("red", faculty.getColor());
        }
    }

    @Test
    public void testCreateFaculty() {
        Faculty faculty = new Faculty(103L, "KrtWin", "white");
        ResponseEntity<Faculty> response = restTemplate.postForEntity("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty created = response.getBody();
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("KrtWin", created.getName());
        assertEquals("white", created.getColor());
    }

    @Test
    public void testEditFaculty() {
        Faculty faculty = new Faculty(104L, "Label", "red");
        ResponseEntity<Faculty> response = restTemplate.exchange("http://localhost:" + port + "/faculty", HttpMethod.PUT, new HttpEntity<>(faculty), Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty updated = response.getBody();
        assertNotNull(updated);
        assertEquals(104L, updated.getId());
        assertEquals("Label", updated.getName());
        assertEquals("red", updated.getColor());
    }

    @Test
    public void testDeleteFaculty() {
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/faculty/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<Faculty> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/faculty/id/1", Faculty.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, getResponse.getStatusCode());
    }

    @Test
    public void testGetAllFaculties() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/all", Faculty[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty[] faculties = response.getBody();
        assertNotNull(faculties);
        assertTrue(faculties.length > 0);
    }

    @Test
    void testUpdateNonExistentFaculty_returnsNotFound() {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Faculty> request = new HttpEntity<>(new Faculty(56L, "Red", "Red"), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "/faculty", HttpMethod.PUT, request, String.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}