package com.legar.auweb.backend;

import com.legar.auweb.dto.ProgramDto;
import com.legar.auweb.entity.Program;
import com.legar.auweb.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service class for managing Program entities.
 * This class provides methods to retrieve and persist Program objects.
 */
@Service
public class Programs {

    private final ProgramRepository programRepository;

    public Programs(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    /**
     * Retrieves a list of all Program entities from the repository.
     *
     * @return a list of Program objects representing all stored programs.
     */
    @Transactional(readOnly = true)
    public List<Program> getPrograms() {
        return programRepository.findAll();
    }

    /**
     * Retrieves a list of all Program entities as DTOs.
     *
     * @return a list of ProgramDto objects representing all stored programs.
     */
    @Transactional(readOnly = true)
    public List<ProgramDto> getProgramDtos() {
        return programRepository.findAll().stream()
                .map(ProgramDto::fromEntity)
                .toList();
    }

    @Transactional
    public void save(Program updatedProgram) {
        programRepository.save(updatedProgram);
    }

    /**
     * Updates a Program entity with properties of the specified ProgramDto instance.
     *
     * @param programDto the DTO containing updated program data
     * @throws IllegalArgumentException if the program with the specified ID does not exist
     */
    @Transactional
    public void updateProgramFromDto(ProgramDto programDto) {
        Program program = programRepository.findById(programDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Program not found with id: " + programDto.getId()));

        program.setAliasName(programDto.getAliasName());
        program.setName(programDto.getName());
        program.setDialect(programDto.getDialect());
        program.setUri(programDto.getUri());
        program.setLanguage(programDto.getLanguage());

        programRepository.save(program);
    }
}
