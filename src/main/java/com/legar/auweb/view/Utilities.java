package com.legar.auweb.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.grid.editor.EditorSaveListener;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.function.ValueProvider;

import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class Utilities {
    private Utilities() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Adds an editable column to the specified Grid. The column allows inline editing of cell values
     * with a TextField as the editor component. Changes are automatically saved using the provided
     * save listener.
     *
     * @param <T>           the type of the items in the Grid
     * @param grid          the Grid to which the editable column will be added
     * @param valueProvider a ValueProvider to extract the value of the property to be displayed
     * @param setter        a Setter to update the value of the property on the item when changes are saved
     * @param save          an EditorSaveListener that handles saving of changes made in the column's editor
     * @param validator     a BiPredicate to validate the input value before saving
     * @param errorMessage  the error message to display if validation fails
     * @return the newly created editable column for the Grid
     */
    public static <T> Grid.Column<T> addEditableColumn(Grid<T> grid,
                                                       ValueProvider<T, String> valueProvider,
                                                       Setter<T, String> setter,
                                                       EditorSaveListener<T> save,
                                                       BiPredicate<? super String, T> validator,
                                                       Supplier<String> errorMessage) {
        TextField textField = new TextField();
        Editor<T> editor = grid.getEditor();
        editor.setBuffered(true);
        Binder<T> binder = editor.getBinder();
        Grid.Column<T> column = grid.addColumn(valueProvider)
                .setEditorComponent(textField);
        Validator<? super String> v = (validator == null)
                ? (value, context) -> ValidationResult.ok()
                : (value, context) -> validator.test(value, editor.getItem())
                        ? ValidationResult.ok()
                        : ValidationResult.error(errorMessage.get());
        binder.forField(textField)
                .withValidator(v)
                .bind(valueProvider, setter);
        grid.addItemClickListener(event -> {
            if (editor.isOpen()) {
                editor.save();
            }
            editor.editItem(event.getItem());
        });
        editor.addSaveListener(save);
        textField.addValueChangeListener(event -> editor.save());
        return column;
    }

    /**
     * Adds an editable column to the specified Grid using a TextField editor and the default
     * validation behavior (no validation). This overload is a convenience wrapper around the
     * full method signature.
     *
     * @param <T>           the type of the items in the Grid
     * @param grid          the Grid to which the editable column will be added
     * @param valueProvider a ValueProvider to extract the value of the property to be displayed
     * @param setter        a Setter to update the value of the property on the item when changes are saved
     * @param save          an EditorSaveListener that handles saving of changes made in the column's editor
     * @return the newly created editable column for the Grid
     * @see #addEditableColumn(Grid, ValueProvider, Setter, EditorSaveListener, BiPredicate, Supplier<String>)
     */
    public static <T> Grid.Column<T> addEditableColumn(Grid<T> grid,
                                                       ValueProvider<T, String> valueProvider,
                                                       Setter<T, String> setter,
                                                       EditorSaveListener<T> save) {
        return addEditableColumn(grid, valueProvider, setter, save, null, null);
    }

    public static boolean isValidNaturalIdentifier(String name) {
        return name != null
                && name.length() <= 32
                && name.matches("^[A-Z](?:[A-Z0-9]*(-[A-Z0-9]+)*){0,31}$");
    }

    public static boolean isBlankOrNull(String value) {
        return value == null || value.isBlank();
    }
}