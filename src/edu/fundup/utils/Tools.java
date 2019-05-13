package edu.fundup.utils;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Tools {

    public Tooltip createToolTip(String text) {
        Tooltip thisToolTip = new Tooltip();


        thisToolTip.setText(text);

        thisToolTip.setStyle("\n"
                + "    -fx-border-color: black;\n"
                + "    -fx-border-width: 1px;\n"
                + "    -fx-font: normal bold 12pt \"Times New Roman\" ;\n"
                + "    -fx-text-fill: black;\n"
                + "    -fx-background-radius: 4;\n"
                + "    -fx-border-radius: 4;\n"
                + "    -fx-opacity: 1.0;");

        thisToolTip.setAutoHide(false);
        thisToolTip.setMaxWidth(300);
        thisToolTip.setGraphicTextGap(0.0);

        return thisToolTip;
    }
}
