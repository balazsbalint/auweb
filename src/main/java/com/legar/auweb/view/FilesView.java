package com.legar.auweb.view;

import com.legar.auweb.backend.Programs;
import com.legar.auweb.dto.AdabasFileDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("files")
public class FilesView extends VerticalLayout {

    private final Grid<AdabasFileDto> grid = new Grid<>(AdabasFileDto.class, false);
    private final Programs programs;

    public FilesView(Programs programs) {
        this.programs = programs;
        configureGrid();
        grid.setItems(programs.getFiles());
        add(grid);
    }

    private void configureGrid() {
        grid.addColumn(AdabasFileDto::getDatabase).setHeader("Database").setSortable(true);
        grid.addColumn(AdabasFileDto::getName).setHeader("Name").setSortable(true);
        grid.addColumn(AdabasFileDto::getFileId).setHeader("File ID").setSortable(true);
        grid.addColumn(AdabasFileDto::getUri).setHeader("URI").setSortable(true);

        grid.addColumn(new ComponentRenderer<>(file -> {
            return new Button("View Fields", click -> {
                String path = String.format("%d/%d", file.getDatabase(), file.getFileId());
                // If getName() doesn't represent the File ID, adjust to the correct integer property
                UI.getCurrent().navigate(FieldView.class, path);
            });
        })).setHeader("Actions");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
