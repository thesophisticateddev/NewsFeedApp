package com.example.demo.components;

import atlantafx.base.controls.Card;
import atlantafx.base.controls.ModalPane;
import atlantafx.base.controls.Spacer;
import atlantafx.base.layout.ModalBox;
import atlantafx.base.theme.Styles;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;

public class BasicCard extends Card {
    @FXML
    private String titleText;

    @FXML
    private String bodyText;

    @FXML
    private String subHeaderText;

    @FXML
    private String footerText;

    private Button detailsModalBtn;

    private ModalPane modalPane;

    public BasicCard() {
        super();
        setup();
    }

    public BasicCard(String titleText, String bodyText, String subHeaderText, String footerText) {
        super();
        this.titleText = titleText;
        this.bodyText = bodyText;
        this.subHeaderText = subHeaderText;
        this.footerText = footerText;
        setup();
    }

    private void createDialogOnDetailBtnClick(){
        this.modalPane = new ModalPane();
        Dialog dialog = new Dialog(450,450);


        TextFlow textFlow = new TextFlow(new Text("FULL SCREEN MODE"),new Text(this.bodyText));
        Button closeBtn = new Button("CLOSE");
        closeBtn.setOnAction(e ->{
            modalPane.hide(true);
        });
        VBox layout = new VBox(16,textFlow,closeBtn);
        dialog.getChildren().add(layout);
        this.detailsModalBtn.setOnAction(evt ->{
            System.out.println("Opening modal.....");
            modalPane.show(dialog);
        });
    }

    public void setup() {
        //Set dimensions of card
        this.setMaxWidth(300);
//        this.setMaxHeight(400);
        this.setPrefHeight(500);

        //Set title
        Label titleLabel = new Label(titleText);
        titleLabel.getStyleClass().add(Styles.TITLE_3);
        this.setHeader(titleLabel);

        //Set subHeader if required
        if (subHeaderText != null) {
            Label subHeaderText = new Label("");
            subHeaderText.getStyleClass().add(Styles.TEXT_SUBTLE);
            this.setSubHeader(subHeaderText);
        }

        //Set body
        String body;
        if (bodyText != null && bodyText.length() > 100) {
            body = bodyText.substring(0, 100);
        }
        else {
            body = bodyText;
        }
        TextFlow textBody = new TextFlow(new Text(body));
        this.setBody(textBody);

        //Set Footer

        HBox footerData = new HBox(10, new FontIcon(MaterialDesignA.ACCOUNT_BOX), new Label("861"), new Spacer(20),
                new FontIcon(MaterialDesignA.ALPHA),
                new Label("92"));
        Separator separator = new Separator(Orientation.HORIZONTAL);
        Separator separator2 = new Separator(Orientation.HORIZONTAL);
        Label footerDisplayText = new Label(footerText);
        footerDisplayText.getStyleClass().add(Styles.TEXT);

        //Details Modal Button
        this.detailsModalBtn = new Button("DETAILS");
        this.detailsModalBtn.getStyleClass().addAll(Styles.FLAT, Styles.ACCENT);
        this.detailsModalBtn.setMnemonicParsing(true);
        Separator separator3 = new Separator(Orientation.HORIZONTAL);
        createDialogOnDetailBtnClick();
        VBox footerContainer =
                new VBox(separator, footerData, separator2, footerDisplayText, separator3, this.detailsModalBtn);
        this.setFooter(footerContainer);



    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;

    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getSubHeaderText() {
        return subHeaderText;
    }

    public void setSubHeaderText(String subHeaderText) {
        this.subHeaderText = subHeaderText;
    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }
}
