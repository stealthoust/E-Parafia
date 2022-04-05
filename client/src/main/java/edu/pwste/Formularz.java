package edu.pwste;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;

import java.io.IOException;

public class Formularz {

    public TableView table_view;
    public MenuButton mszaBtn;

    //String loudScreaming = json.getJSONObject("LabelData").getString("slogan");
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void switchToFormularz() throws IOException {
        App.setRoot("formularz");
    }
    @FXML
    private void switchToKalendarz() throws IOException {
        App.setRoot("kalendarz");
    }
    @FXML
    private void switchToKsiegaOsob() throws IOException {
        App.setRoot("ksiega_osob");
    }
    @FXML
    private void switchToMszaDodaj() throws IOException {
        mszaBtn.hide();
        App.setRoot("msza_dodaj");
    }
    @FXML
    private void switchToMszaInfo() throws IOException {
        mszaBtn.hide();
        App.setRoot("msza_info");
    }
}
