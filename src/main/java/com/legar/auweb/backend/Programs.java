package com.legar.auweb.backend;

import com.legar.auweb.entity.Program;
import com.legar.auweb.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class Programs {

    private final ProgramRepository programRepository;

    public Programs(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Transactional(readOnly = true)
    public List<Program> getPrograms() {
        return programRepository.findAll();
    }

    @Transactional
    public void save(Program updatedProgram) {
        programRepository.save(updatedProgram);
    }
}
