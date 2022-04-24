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

public class MszaInfo implements Initializable {

    public MenuButton mszaBtn;
    public MenuButton formularzBtn;
    public DatePicker datePicker;
    public ComboBox mszaCombo;
    public TableView ksiadzTable;
    @FXML
    public TableView wydarzenia_tabela = new TableView();

    @FXML
    public TableView ksiadzTabela = new TableView();

    @FXML
    private TableColumn<ksiadz, String> colImie;

    @FXML
    private TableColumn<ksiadz, String> colNazwisko;

    @FXML
    private TableColumn<wydarzenia, String> colGodzina;

    @FXML
    private TableColumn<wydarzenia, String> colNazwa;

    @FXML
    private TableColumn<wydarzenia, String> colTyp;

    @FXML
    private TableColumn<wydarzenia, String> colOpis;

    public String url = "http://localhost:3000/kalendarz/zakresDat";
    private ObservableList<msze> lista;

    public MszaInfo() throws Exception {
    }

    private void tableViewSetup() {
        colGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        colNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        colTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));
        colOpis.setCellValueFactory(new PropertyValueFactory<>("opis"));
        colImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        colNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
    }

    public ZakresDat[] pobierzDane() throws Exception {
        Gson gson = new Gson();
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(URI.create(url)).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String odpowiedz = response.body();
        ZakresDat[] enums = gson.fromJson(odpowiedz, ZakresDat[].class);
        return enums;

    }

    public ZakresDat[] xd = pobierzDane();
    LocalDate minDate = LocalDate.of(xd[0].year, xd[0].month, xd[0].day);
    LocalDate maxDate = LocalDate.of(xd[1].year, xd[1].month, xd[1].day);

    public ObservableList<msze> pobierzMsze(String link) throws IOException, InterruptedException {
        Gson gson = new Gson();
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(URI.create(link)).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String odpowiedz = response.body();
        msze[] enums = gson.fromJson(odpowiedz, msze[].class);
        ObservableList<msze> lista = FXCollections.observableArrayList(enums);
        return lista;
    }

    public ObservableList<ksiadz> pobierzKsiezy(String link) throws IOException, InterruptedException {
        Gson gson = new Gson();
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(URI.create(link)).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String odpowiedz = response.body();
        ksiadz[] enums = gson.fromJson(odpowiedz, ksiadz[].class);
        ObservableList<ksiadz> lista = FXCollections.observableArrayList(enums);
        return lista;
    }

    private void tableViewPopulate(String linkW, String linkK) throws Exception {
        Kalendarz kalendarz = new Kalendarz();
        try {
            wydarzenia_tabela.setItems(kalendarz.pobierzWydarzenia(linkW));
            ksiadzTabela.setItems(pobierzKsiezy(linkK));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pokazMsze(String link) throws Exception {
        lista = pobierzMsze(link);
        for (int i = 0; i < lista.size(); i++) {
            mszaCombo.getItems().add(lista.get(i).godzina);
        }

    }

    public void pokaz() {
        //System.out.println(lista.get(mszaCombo.getSelectionModel().getSelectedIndex()).id );
        boolean isEmpty = mszaCombo.getSelectionModel().isEmpty();
        if (!isEmpty) {
            String id = String.valueOf(lista.get(mszaCombo.getSelectionModel().getSelectedIndex()).id);
            try {
                tableViewPopulate("http://localhost:3000/kalendarz/wydarzeniamsza/" + id, "http://localhost:3000/ksieza/msza/" + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        datePicker.setDayCellFactory(d ->
                new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
                    }
                });
        tableViewSetup();


    }

    public void pokazMsze(ActionEvent actionEvent) throws Exception {
        mszaCombo.getSelectionModel().clearSelection();
        mszaCombo.getItems().clear();
        LocalDate data = datePicker.getValue();
        int dzien = data.getDayOfMonth();
        int miesiac = data.getMonthValue();
        int rok = data.getYear();
        String link = "http://localhost:3000/kalendarz/mszedzis/" + rok + "/" + miesiac + "/" + dzien;
        pokazMsze(link);
    }
}
