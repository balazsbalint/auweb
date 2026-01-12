package com.legar.auweb.backend;

import com.legar.auweb.dto.DdmDto;
import com.legar.auweb.dto.ProgramDto;
import com.legar.auweb.entity.Program;
import com.legar.auweb.repository.DdmRepository;
import com.legar.auweb.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Service class for managing Program entities.
 * This class provides methods to retrieve and persist Program objects.
 */
@Service
public class Programs {

    private final ProgramRepository programRepository;
    private final DdmRepository ddmRepository;

    public Programs(ProgramRepository programRepository, DdmRepository ddmRepository) {
        this.programRepository = programRepository;
        this.ddmRepository = ddmRepository;
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

    /**
     * Retrieves a list of all DDM entities as DTOs.
     *
     * @return a list of DdmDto objects representing all stored DDMs.
     */
    @Transactional(readOnly = true)
    public List<DdmDto> getDdmDtos() {
        return ddmRepository.findAll().stream()
                .map(DdmDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<Program> findById(Long id) {
        return programRepository.findById(id);
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
    }

    @Transactional(readOnly = true)
    public Program getProgramById(Long id, EagerLoad... eagerLoad) {
        Program program = programRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Program not found with id: " + id)
        );
        Arrays.stream(eagerLoad).forEach( toBeLoad -> { switch (toBeLoad) {
            case FLOW:
                program.getFlows().size();
                break;
            case INPUT_OUTPUT:
                program.getInputs().size();
                program.getOutputs().size();
                break;
            case WORK:
            case WORKING_STORAGE:
                program.getWorks().size();
                break;
            default:
                break;
        } });

        return program;
    }
}
