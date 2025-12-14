package com.legar.auweb.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Aliases")
@Route(value = "aliases", layout = RootView.class)
public class AliasesView extends VerticalLayout {
    public AliasesView() {
        H1 title = new H1("Aliases");
        add(title);
    }
}
