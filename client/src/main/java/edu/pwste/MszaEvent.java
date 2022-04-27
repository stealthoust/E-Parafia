package edu.pwste;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MszaEvent implements Initializable {


    public MenuButton mszaBtn;
    public MenuButton formularzBtn;
    public String url = "http://localhost:3000/kalendarz/zakresDat";
    public DatePicker kalendarz;
    public ComboBox combo_msza;
    public Button btn;
    private ObservableList<msze> lista;
    @FXML
    public TableView wydarzenia_tabela = new TableView();
    @FXML
    private TableColumn<wydarzenia, String> colNazwa;

    @FXML
    private TableColumn<wydarzenia, String> colTyp;

    @FXML
    private TableColumn<wydarzenia, String> colOpis;

    public void pokazMsze(String link) throws Exception {
        MszaInfo mszaInfo = new MszaInfo();
        lista = mszaInfo.pobierzMsze(link);
        for (int i = 0; i < lista.size(); i++) {
            combo_msza.getItems().add(lista.get(i).godzina);
        }

    }
    private void tableViewSetup() {

        colNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        colTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));
        colOpis.setCellValueFactory(new PropertyValueFactory<>("opis"));
    }
    private void tableViewPopulate(String link)
    {

        try {
            Kalendarz kalendarz = new Kalendarz();
            wydarzenia_tabela.setItems(kalendarz.pobierzWydarzenia(link));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void wypiszWydarzenia()
    {
        String url = "http://localhost:3000/wydarzenia";
        tableViewSetup();
        tableViewPopulate(url);

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
        wypiszWydarzenia();
    }




    public void pokazMsze(ActionEvent actionEvent) throws Exception {
        if (kalendarz.getValue() != null) {
            combo_msza.getSelectionModel().clearSelection();
            combo_msza.getItems().clear();
            LocalDate data = kalendarz.getValue();
            int dzien = data.getDayOfMonth();
            int miesiac = data.getMonthValue();
            int rok = data.getYear();
            String link = "http://localhost:3000/kalendarz/mszedzis/" + rok + "/" + miesiac + "/" + dzien;
            pokazMsze(link);
        }
    }

    public void Show(MouseEvent mouseEvent) {
        System.out.println(lista.get(wydarzenia_tabela.getSelectionModel().getSelectedIndex()).id);
    }
}
