package com.legar.auweb.backend;

import com.legar.auweb.entity.Program;
import com.legar.auweb.repository.ProgramRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class Programs {

    private final ProgramRepository programRepository;

    public Programs(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<Program> getPrograms() {
        return programRepository.findAll();
    }
}
