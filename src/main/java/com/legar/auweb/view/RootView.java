package com.legar.auweb.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class RootView extends AppLayout implements RouterLayout {
    public RootView() {
        createHeader();
        createDrawer();
    }

    private void createDrawer() {

        RouterLink naturalObjects = new RouterLink("Natural objects", NaturalObjectsView.class);
        naturalObjects.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink ddms = new RouterLink("ADABAS files", FilesView.class);
        ddms.setHighlightCondition(HighlightConditions.sameLocation());

        VerticalLayout drawerLayout = new VerticalLayout(naturalObjects, ddms);
        drawerLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        addToDrawer(drawerLayout);
    }

    private void createHeader() {
        H1 logo = new H1("AU Web");
        logo.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        logo.getStyle().set("margin", "0");

        HorizontalLayout header = new HorizontalLayout(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.setPadding(true);

        addToNavbar(header);
    }
}
