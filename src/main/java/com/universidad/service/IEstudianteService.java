package com.universidad.service; 

import com.universidad.dto.EstudianteDTO; 

import java.util.List;

public interface IEstudianteService { 
    
    List<EstudianteDTO> obtenerTodosLosEstudiantes(); 
    EstudianteDTO obtenerEstudiantePorId(Long id);
    EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO);
    void eliminarEstudiante(Long id);
    EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO);
}


