package com.legar.auweb.view;

import com.legar.auweb.backend.Programs;
import com.legar.auweb.dto.AdabasFieldDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.WildcardParameter;

@Route(value = "fields", layout = RootView.class)
public class FieldView extends VerticalLayout implements HasUrlParameter<String> {

    private final Programs programs;
    private final Grid<AdabasFieldDto> fieldGrid = new Grid<>(AdabasFieldDto.class, false);
    private final H2 title = new H2("Fields");

    public FieldView(Programs programs) {
        this.programs = programs;
        configureGrid();
        add(title, fieldGrid);
    }

    private void configureGrid() {
        Binder<AdabasFieldDto> binder = new Binder<>(AdabasFieldDto.class);
        Editor<AdabasFieldDto> editor = fieldGrid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        TextField nameField = new TextField();
        binder.forField(nameField).bind(AdabasFieldDto::getName, AdabasFieldDto::setName);

        fieldGrid.addColumn(AdabasFieldDto::getName)
                .setEditorComponent(nameField)
                .setHeader("Name")
                .setSortable(true);

        fieldGrid.addColumn(AdabasFieldDto::getShortName).setHeader("Short Name");
        fieldGrid.addColumn(AdabasFieldDto::getType).setHeader("Type");
        fieldGrid.addColumn(AdabasFieldDto::getLength).setHeader("Length");
        fieldGrid.addColumn(AdabasFieldDto::getDecimals).setHeader("Decimals");

        fieldGrid.addItemDoubleClickListener(e -> {
            editor.editItem(e.getItem());
            nameField.focus();
        });

        fieldGrid.getElement().addEventListener("keyup", event -> editor.cancel())
                .setFilter("event.key === 'Escape' || event.key === 'Esc'");

        fieldGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
        // Expected format: databaseId/fileId (e.g., "12/40")
        if (parameter != null && parameter.contains("/")) {
            String[] parts = parameter.split("/");
            try {
                int dbId = Integer.parseInt(parts[0]);
                int fId = Integer.parseInt(parts[1]);
                title.setText(String.format("Fields for DB %d, File %d", dbId, fId));
                
                fieldGrid.setItems(programs.getFieldsByDbAndFile(dbId, fId));
                
            } catch (NumberFormatException e) {
                title.setText("Invalid IDs provided");
            }
        }
    }
}
