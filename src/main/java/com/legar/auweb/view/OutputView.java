package com.legar.auweb.view;

import com.legar.auweb.backend.EagerLoad;
import com.legar.auweb.backend.Programs;
import com.legar.auweb.dto.InputOutputDto;
import com.legar.auweb.entity.Program;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "output", layout = RootView.class)
public class OutputView extends VerticalLayout implements HasUrlParameter<Long> {

    private final Programs programs;
    private final Grid<InputOutputDto> outputGrid = new Grid<>(InputOutputDto.class, false);

    public OutputView(Programs programs) {
        this.programs = programs;
        configureGrid(outputGrid);
        add(new H2("Output Devices"), outputGrid);
    }

    private void configureGrid(Grid<InputOutputDto> grid) {
        grid.addColumn(InputOutputDto::getName).setHeader("Name");
        grid.addColumn(InputOutputDto::getUri).setHeader("URI");
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        Program program = programs.getProgramById(parameter, EagerLoad.INPUT_OUTPUT);
        displayProgramIO(program);
    }

    private void displayProgramIO(Program program) {
        List<InputOutputDto> outputs = program.getOutputs().stream()
                .map(InputOutputDto::fromEntity)
                .toList();

        outputGrid.setItems(outputs);
    }
}
