package com.legar.auweb.view;

import com.legar.auweb.backend.Programs;
import com.legar.auweb.dto.ProgramDto;
import com.legar.auweb.entity.Program;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.grid.editor.EditorSaveEvent;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@PageTitle("Natural objects")
@Route(value = "natural-objects", layout = RootView.class)
public class NaturalObjectsView extends VerticalLayout {

    private final Programs programs;

    public NaturalObjectsView(Programs programs) {
        this.programs = programs;
        add(new H2("Natural Objects"));

        // Added grid and data population
        Grid<ProgramDto> programGrid = createProgramGrid();
        add(programGrid);
    }

    private Grid<ProgramDto> createProgramGrid() {
        Grid<ProgramDto> programGrid = new Grid<>(ProgramDto.class);
        programGrid.setItems(programs.getProgramDtos());
        programGrid.setColumns("id", "name", "modifier", "aliasName");
        addGoButton(programGrid);
        makeAliasNameEditable(programGrid);
        return programGrid;
    }

    private void makeAliasNameEditable(Grid<ProgramDto> grid) {
        Grid.Column<ProgramDto> aliasName = grid.getColumnByKey("aliasName");
        TextField alias = new TextField();
        aliasName.setEditorComponent(alias);
        Editor<ProgramDto> editor = grid.getEditor();
        Binder<ProgramDto> binder = editor.getBinder();
        binder.forField(alias).bind(ProgramDto::getAliasName, ProgramDto::setAliasName);
        grid.addItemClickListener(event -> editor.editItem(event.getItem()));
        editor.addSaveListener(this::doSave);
        editor.setBuffered(true);
        alias.addValueChangeListener(event -> editor.save());
    }

    private void doSave(EditorSaveEvent<ProgramDto> event) {
        save(event.getItem());
    }

    private void addGoButton(Grid<ProgramDto> grid) {
        grid.addComponentColumn(program -> {
            Button goButton = new Button("Go");
            goButton.setThemeName("icon");
            goButton.addClickListener(event -> {
                event.getSource().getUI().ifPresent(ui -> ui.navigate("program/" + program.getId()));
            });
            goButton.setMaxHeight("1em");
            return goButton;
        }).setHeader("Action");

    }

    private void save(ProgramDto updatedProgram) {
        Optional
                .ofNullable(updatedProgram)
                .ifPresent(programs::updateProgramFromDto);
    }
}
