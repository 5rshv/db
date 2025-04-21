package skypro.hogwarts.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import skypro.hogwarts.model.Faculty;
import skypro.hogwarts.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editeFaculty(Faculty faculty) {
        Faculty existing = facultyRepository.findById(faculty.getId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));

        existing.setName(existing.getName());
        existing.setColor(existing.getColor());

        return facultyRepository.save(existing);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }
}