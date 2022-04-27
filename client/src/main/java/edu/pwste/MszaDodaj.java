package edu.pwste;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MszaDodaj implements Initializable {
    public MenuButton mszaBtn;
    public MenuButton formularzBtn;
    public DatePicker kalendarz;
    public ComboBox combo_msza,combo_ksiadz;
    public Label lbl_data,lbl_msza,lbl_ksiadz;
    public Button btn;
    public String url = "http://localhost:3000/kalendarz/zakresDat";
    private ObservableList<msze> lista;

    private void setCombo_ksiadz() {
        combo_ksiadz.getItems().addAll("Mirosław Mateja","Piotr Krzysik","Bernard Guzy","Marcin Kowalik","Konstanty Sepulak");
    }

    @FXML
    private void pokazComboKsiadz(){
        boolean isEmpty = combo_ksiadz.getSelectionModel().isEmpty();
        if (!isEmpty) {
            lbl_ksiadz.setText(combo_ksiadz.getSelectionModel().getSelectedItem().toString());
        }
        }

    @FXML
    private void pokazComboMsza(){
        boolean isEmpty = combo_msza.getSelectionModel().isEmpty();
        if (!isEmpty) {
            lbl_msza.setText(combo_msza.getSelectionModel().getSelectedItem().toString()+" ("+lista.get(combo_msza.getSelectionModel().getSelectedIndex()).typ+")");;
        }
    }

    public void pokazMsze(String link) throws Exception {
        MszaInfo mszaInfo = new MszaInfo();
        lista = mszaInfo.pobierzMsze(link);
        for (int i = 0; i < lista.size(); i++) {
            combo_msza.getItems().add(lista.get(i).godzina);
        }

    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void switchToFormularzUser() throws IOException {
        formularzBtn.hide();
        App.setRoot("formUser");
    }
    @FXML
    private void switchToFormularzEvent() throws IOException {
        formularzBtn.hide();
        App.setRoot("formEvent");
    }
    @FXML
    private void switchToFormularzMsza() throws IOException {
        formularzBtn.hide();
        App.setRoot("formMsza");
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
    private void switchToMszaEvent() throws IOException {
        mszaBtn.hide();
        App.setRoot("MszaEvent");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCombo_ksiadz();
    }

    public void pokazMsze(ActionEvent actionEvent) throws Exception {

        lbl_msza.setText("");
        combo_msza.getSelectionModel().clearSelection();
        combo_msza.getItems().clear();
        lbl_data.setText(kalendarz.getValue().toString());
        LocalDate data = kalendarz.getValue();
        int dzien = data.getDayOfMonth();
        int miesiac = data.getMonthValue();
        int rok = data.getYear();
        String link = "http://localhost:3000/kalendarz/mszedzis/" + rok + "/" + miesiac + "/" + dzien;
        pokazMsze(link);
    }
}
