package com.legar.auweb.view;

import com.legar.auweb.backend.Programs;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.grid.Grid;
import java.util.List;
import com.legar.auweb.entity.Program;

@PageTitle("Natural objects")
@Route(value = "natural-objects", layout = RootView.class)
public class NaturalObjectsView extends VerticalLayout {

    private final Programs programs;

    public NaturalObjectsView(Programs programs) {
        this.programs = programs;
        add(new H2("Natural Objects"));

        // Added grid and data population
        Grid<Program> programGrid = new Grid<>(Program.class);
        programGrid.setItems(getPrograms());
        programGrid.setColumns("id", "name", "modifier"); // Assumed attributes
        add(programGrid);
    }

    // Added method to fetch list of programs
    private List<Program> getPrograms() {
        return programs.getPrograms();
    }
}
