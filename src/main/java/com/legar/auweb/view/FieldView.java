package com.legar.auweb.view;

import com.legar.auweb.backend.Programs;
import com.legar.auweb.dto.AdabasFieldDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.grid.editor.EditorSaveEvent;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.WildcardParameter;

import java.util.List;
import java.util.Objects;

@Route(value = "fields", layout = RootView.class)
public class FieldView extends VerticalLayout implements HasUrlParameter<String> {

    private final Programs programs;
    private final Grid<AdabasFieldDto> fieldGrid = new Grid<>(AdabasFieldDto.class, false);
    private final H2 title = new H2("Fields");

    private String errorMessage;

    public FieldView(Programs programs) {
        this.programs = programs;
        configureGrid();
        add(title, fieldGrid);
    }

    private void configureGrid() {

        fieldGrid.addColumn(AdabasFieldDto::getName).setHeader("Name").setSortable(true);
        Grid.Column<AdabasFieldDto> alias = Utilities
                .addEditableColumn(
                        fieldGrid,
                        AdabasFieldDto::getAlias,
                        AdabasFieldDto::setAlias,
                        this::saveItem,
                        this::validateAliasValue,
                        () -> errorMessage)
                .setHeader("Alias")
                .setSortable(true);
        fieldGrid.addColumn(AdabasFieldDto::getShortName).setHeader("Short Name").setSortable(true);
        fieldGrid.addColumn(AdabasFieldDto::getType).setHeader("Type").setSortable(true);
        fieldGrid.addColumn(AdabasFieldDto::getLength).setHeader("Length").setSortable(true);
        fieldGrid.addColumn(AdabasFieldDto::getDecimals).setHeader("Decimals").setSortable(true);
        fieldGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private boolean validateAliasValue(String alias, AdabasFieldDto adabasFieldDto) {
        errorMessage = null;
        if (alias == null || alias.isBlank()) {
            return true;
        }
        if (Utilities.isValidNaturalIdentifier(alias)) {
            List<String> list = fieldGrid.getListDataView().getItems()
                    .filter(Objects::nonNull)
                    .filter(item -> !item.equals(adabasFieldDto))
                    .map(dto -> Utilities.isBlankOrNull(dto.getAlias())
                            ? dto.getName()
                            : dto.getAlias().toUpperCase())
                    .toList();

            boolean unique = fieldGrid.getListDataView().getItems()
                    .filter(Objects::nonNull)
                    .filter(item -> !item.equals(adabasFieldDto))
                    .map(dto -> Utilities.isBlankOrNull(dto.getAlias())
                            ? dto.getName()
                            : dto.getAlias())
                    .noneMatch(existing -> existing.equalsIgnoreCase(alias));
            if (!unique) {
                errorMessage = "Alias must be unique.";
                return false;
            }
        } else {
            errorMessage = "Invalid identifier.";
            return false;
        }
        return true;
    }

    private void saveItem(EditorSaveEvent<AdabasFieldDto> event) {
        programs.updateFieldUsingDto(event.getItem());
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
