package com.legar.auweb.view;

import com.legar.auweb.dto.AdabasFileDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("files")
public class FilesView extends VerticalLayout {

    private final Grid<AdabasFileDto> grid = new Grid<>(AdabasFileDto.class, false);

    public FilesView() {
        setSizeFull();
        configureGrid();
        add(grid);
    }

    private void configureGrid() {
        grid.addColumn(AdabasFileDto::getName).setHeader("Name").setSortable(true);
        grid.addColumn(AdabasFileDto::getDatabase).setHeader("Database").setSortable(true);
        grid.addColumn(AdabasFileDto::getUri).setHeader("URI").setSortable(true);
        
        // Example: showing the count of fields associated with the file
        grid.addColumn(file -> file.getFields() != null ? file.getFields().size() : 0)
            .setHeader("Fields Count");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    public void setFiles(List<AdabasFileDto> files) {
        grid.setItems(files);
    }
}
