package com.legar.auweb.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.grid.editor.EditorSaveListener;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;

public class Utilities {
    private Utilities() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Adds an editable column to the specified Grid. The column allows inline editing of cell values
     * with a TextField as the editor component. Changes are automatically saved using the provided
     * save listener.
     *
     * @param <T> the type of the items in the Grid
     * @param grid the Grid to which the editable column will be added
     * @param valueProvider a ValueProvider to extract the value of the property to be displayed
     * @param setter a Setter to update the value of the property on the item when changes are saved
     * @param save an EditorSaveListener that handles saving of changes made in the column's editor
     * @return the newly created editable column for the Grid
     */
    public static <T> Grid.Column<T> addEditableColumn(Grid<T> grid,
                                                       ValueProvider<T, String> valueProvider,
                                                       Setter<T, String> setter,
                                                       EditorSaveListener<T> save) {
        TextField textField = new TextField();
        Editor<T> editor = grid.getEditor();
        editor.setBuffered(true);
        Binder<T> binder = editor.getBinder();
        Grid.Column<T> column = grid.addColumn(valueProvider)
                .setEditorComponent(textField);
        binder.forField(textField).bind(valueProvider, setter);
        grid.addItemClickListener(event -> {
            if (editor.isOpen()) { editor.save(); }
            editor.editItem(event.getItem());
        });
        editor.addSaveListener(save);
        textField.addValueChangeListener(event -> editor.save());
        return column;
    }
}
