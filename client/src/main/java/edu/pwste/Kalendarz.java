package edu.pwste;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Kalendarz implements Initializable {
    @FXML
    public TableView wydarzenia_tabela=new TableView();
    public MenuButton mszaBtn;
    public MenuButton formularzBtn;
    public DatePicker datePicker;

    @FXML
    private TableColumn<wydarzenia, String> colGodzina;

    @FXML
    private TableColumn<wydarzenia, String> colNazwa;

    @FXML
    private TableColumn<wydarzenia, String> colTyp;

    @FXML
    private TableColumn<wydarzenia, String> colOpis;

    private void tableViewSetup() {
        colGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        colNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        colTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));
        colOpis.setCellValueFactory(new PropertyValueFactory<>("opis"));
    }
    private void tableViewPopulate(String link)
    {
        try {
            wydarzenia_tabela.setItems(pobierzWydarzenia(link));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<wydarzenia> pobierzWydarzenia(String link) throws Exception
    {

        Gson gson = new Gson();
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create(link)).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String odpowiedz = response.body();
        wydarzenia[] enums = gson.fromJson(odpowiedz,wydarzenia[].class);
        ObservableList<wydarzenia> lista = FXCollections.observableArrayList(enums);

        return lista;


    }

    public String url = "http://localhost:3000/kalendarz/zakresDat";
    public ZakresDat[] xd = pobierzDane();
    public ZakresDat[] pobierzDane() throws Exception
    {

        Gson gson = new Gson();
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create(url)).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String odpowiedz = response.body();
        ZakresDat[] enums = gson.fromJson(odpowiedz,ZakresDat[].class);


        return enums;

    }


    LocalDate minDate = LocalDate.of(xd[0].year, xd[0].month, xd[0].day);
    LocalDate maxDate = LocalDate.of(xd[1].year, xd[1].month, xd[1].day);



    public Kalendarz() throws Exception {
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
        datePicker.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
                    }});


    }

    public void wypiszWydarzenia(ActionEvent actionEvent) {
        LocalDate data = datePicker.getValue();

        int dzien = data.getDayOfMonth();
        int miesiac = data.getMonthValue();
        int rok = data.getYear();
        String url = "http://localhost:3000/kalendarz/wydarzenia/"+rok+"/"+miesiac+"/"+dzien;
        tableViewSetup();
        tableViewPopulate(url);

    }
}
