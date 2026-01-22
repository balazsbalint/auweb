package com.legar.auweb.backend;

import com.legar.auweb.dto.AdabasFileDto;
import com.legar.auweb.dto.AdabasFieldDto;
import com.legar.auweb.dto.DdmDto;
import com.legar.auweb.dto.ProgramDto;
import com.legar.auweb.entity.AdabasField;
import com.legar.auweb.entity.Ddm;
import com.legar.auweb.entity.Program;
import com.legar.auweb.entity.Type;
import com.legar.auweb.repository.AdabasFieldRepository;
import com.legar.auweb.repository.DdmRepository;
import com.legar.auweb.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * Service class for managing Program entities.
 * This class provides methods to retrieve and persist Program objects.
 */
@Service
public class Programs {

    private final ProgramRepository programRepository;
    private final DdmRepository ddmRepository;
    private final AdabasFieldRepository fieldRepository;

    public Programs(ProgramRepository programRepository, DdmRepository ddmRepository, AdabasFieldRepository fieldRepository) {
        this.programRepository = programRepository;
        this.ddmRepository = ddmRepository;
        this.fieldRepository = fieldRepository;
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
    public void updateProgramUsingDto(ProgramDto programDto) {
        Program program = programRepository.findById(programDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Program not found with id: " + programDto.getId()));

        program.setAliasName(programDto.getAliasName());
        program.setName(programDto.getName());
        program.setDialect(programDto.getDialect());
        program.setUri(programDto.getUri());
        program.setLanguage(programDto.getLanguage());
        programRepository.save(program);
    }

    @Transactional
    public void updateFieldUsingDto(AdabasFieldDto fieldDto) {
        AdabasField field = fieldRepository.findById(fieldDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Field not found with id: " + fieldDto.getId()));

        field.setName(fieldDto.getName());
        field.setAlias(fieldDto.getAlias());
        field.setAdabasId(fieldDto.getShortName());
        field.setType(fieldDto.getType());
        field.setPrecision(fieldDto.getLength());
        field.setLevel(fieldDto.getLevel());

        fieldRepository.save(field);
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

    /**
     * Retrieves a list of AdabasFieldDto objects for the specified database and file IDs
     * by extracting field information from the corresponding DDM entity.
     *
     * @param dbId the ID of the database to search for
     * @param fId the file ID within the specified database
     * @return a list of AdabasFieldDto objects representing the fields associated with the given database and file,
     *         or an empty list if no matching fields are found
     */
    @Transactional(readOnly = true)
    public List<AdabasFieldDto> getFieldsByDbAndFile(int dbId, int fId) {
        List<Ddm> ddms = ddmRepository.findByDatabaseIdAndFileId(dbId, fId);
        if (ddms.isEmpty()) {
            return List.of();
        }

        // Assuming there is one primary DDM for the DB/File combination
        Ddm ddm = ddms.get(0);
        Type record = ddm.getRecord();

        if (record == null || record.getFields() == null) {
            return List.of();
        }

        return record.getFields().stream()
                .map(this::mapToAdabasFieldDto)
                .toList();
    }

    private AdabasFieldDto mapToAdabasFieldDto(Type type) {
        AdabasFieldDto dto = new AdabasFieldDto();
        dto.setId(type.getId());
        dto.setName(type.getName());
        dto.setAlias(type.getAlias());
        dto.setType(type.getType());
        dto.setLength(type.getPrecision() != null ? type.getPrecision() : 0);
        dto.setDecimals(type.getScale() != null ? type.getScale() : 0);

        if (type instanceof AdabasField adabasField) {
            dto.setShortName(adabasField.getAdabasId());
        }

        if ((type.getFields() != null) && !type.getFields().isEmpty()) {
            dto.setFields(type.getFields().stream()
                    .map(this::mapToAdabasFieldDto)
                    .toList());
        }

        return dto;
    }

    public List<AdabasFileDto> getFiles() {
        return ddmRepository.findAll().stream()
                .map(this::mapToAdabasFileDto)
                .toList();
    }

    private AdabasFileDto mapToAdabasFileDto(Ddm ddm) {
        AdabasFileDto dto = new AdabasFileDto();
        dto.setName(ddm.getName());
        dto.setUri(ddm.getUri());
        dto.setDatabase(ddm.getDatabaseId());
        dto.setFileId(ddm.getFileId());
        return dto;
    }
}
