package com.universidad.service.impl;

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante;
import com.universidad.repository.EstudianteRepository;
import com.universidad.service.IEstudianteService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @PostConstruct
    public void init() {
        estudianteRepository.init();
    }

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudiantes.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public EstudianteDTO obtenerEstudiantePorId(Long id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        return estudiante.map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }

    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));

        // Actualizar campos
        estudianteExistente.setNombre(estudianteDTO.getNombre());
        estudianteExistente.setApellido(estudianteDTO.getApellido());
        estudianteExistente.setEmail(estudianteDTO.getEmail());
        estudianteExistente.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudianteExistente.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());

        Estudiante estudianteActualizado = estudianteRepository.save(estudianteExistente);
        return convertToDTO(estudianteActualizado);
    }
    
    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        boolean existeEmail = estudianteRepository.findAll().stream()
                .anyMatch(e -> e.getEmail().equals(estudianteDTO.getEmail()));

        boolean existeNumeroInscripcion = estudianteRepository.findAll().stream()
                .anyMatch(e -> e.getNumeroInscripcion().equals(estudianteDTO.getNumeroInscripcion()));

        if (existeEmail || existeNumeroInscripcion) {
            throw new RuntimeException("Ya existe un estudiante con ese email o número de inscripción");
        }
        Estudiante nuevoEstudiante = convertToEntity(estudianteDTO);
        nuevoEstudiante.setId(null);
        Estudiante estudianteGuardado = estudianteRepository.save(nuevoEstudiante);
        return convertToDTO(estudianteGuardado);
    }

    @Override
    public void eliminarEstudiante(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el estudiante con ID: " + id);
        }
        estudianteRepository.deleteById(id);
    }

    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        return EstudianteDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .apellido(estudiante.getApellido())
                .email(estudiante.getEmail())
                .fechaNacimiento(estudiante.getFechaNacimiento())
                .numeroInscripcion(estudiante.getNumeroInscripcion())
                .build();
    }

    private Estudiante convertToEntity(EstudianteDTO estudianteDTO) {
        return Estudiante.builder()
                .id(estudianteDTO.getId())
                .nombre(estudianteDTO.getNombre())
                .apellido(estudianteDTO.getApellido())
                .email(estudianteDTO.getEmail())
                .fechaNacimiento(estudianteDTO.getFechaNacimiento())
                .numeroInscripcion(estudianteDTO.getNumeroInscripcion())
                .build();
    }
}