package com.legar.auweb.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Fields")
@Route(value = "fields", layout = RootView.class)
public class FieldsView extends VerticalLayout {
    public FieldsView() {
        add(new H2("Fields"));
    }
}
