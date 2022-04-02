package edu.pwste;

import javafx.fxml.FXML;

import java.io.IOException;

public class Kalendarz {
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
        App.setRoot("msza_dodaj");
    }
    @FXML
    private void switchToMszaInfo() throws IOException {
        App.setRoot("msza_info");
    }
}
