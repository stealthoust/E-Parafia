package edu.pwste;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class PrimaryController implements Initializable {

    public int rok = java.time.LocalDate.now().getYear();
    public int miesiac = java.time.LocalDate.now().getMonthValue();
    public int dzien = java.time.LocalDate.now().getDayOfMonth();

    public MenuButton mszaBtn;
    public MenuButton formularzBtn;

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

    //------------------------------------------------------

    @FXML
    private TableView<wydarzenia> wydarzenia_tabela =new TableView();;

    @FXML
    private TableColumn<?, ?> colGodzina;

    @FXML
    private TableColumn<?, ?> colNazwa;

    @FXML
    private TableColumn<?, ?> colTyp;

    @FXML
    private TableColumn<?, ?> colOpis;

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

    public void wypiszWydarzenia() {

        String url = "http://localhost:3000/kalendarz/wydarzenia/"+rok+"/"+miesiac+"/"+dzien;
        tableViewSetup();
        tableViewPopulate(url);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wypiszWydarzenia();

    }

}


