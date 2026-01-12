package com.legar.auweb.view;

import com.legar.auweb.backend.Programs;
import com.legar.auweb.dto.DdmDto;
import com.legar.auweb.entity.Ddm;
import com.legar.auweb.repository.DdmRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.stream.Collectors;

@Route("ddms")
@PageTitle("DDMs | Auweb")
public class DdmView extends VerticalLayout {

    private final Grid<DdmDto> grid = new Grid<>(DdmDto.class, false);

    public DdmView(Programs programs) {
        setSizeFull();
        configureGrid();

        add(grid);

        // Load data using the service method
        grid.setItems(programs.getDdmDtos());
    }

    private void configureGrid() {
        grid.setSizeFull();
        
        grid.addColumn(DdmDto::getName).setHeader("Name").setSortable(true);
        
        // Ddm specific fields
        grid.addColumn(DdmDto::getDatabaseId).setHeader("Database ID").setSortable(true);
        grid.addColumn(DdmDto::getFileId).setHeader("File ID").setSortable(true);
        
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
