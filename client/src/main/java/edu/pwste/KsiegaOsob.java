package edu.pwste;

import com.google.gson.JsonArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import javafx.scene.control.cell.PropertyValueFactory;

public class KsiegaOsob implements Initializable {

    public TableView table_view=new TableView();
    public MenuButton mszaBtn;
    public MenuButton formularzBtn;
    @FXML
    TableColumn <Osoba,Integer> col_id;
    @FXML
    TableColumn <Osoba,String> col_imie ;
    @FXML
    TableColumn <Osoba,String> col_nazwisko;
    @FXML
    TableColumn <Osoba, Date> col_data;
    @FXML
    TableColumn <Osoba,String> col_miejscowosc;
    @FXML
    TableColumn <Osoba,Integer> col_id_ksiedza;



    public String url = "http://localhost:3000/osoby";


        public ObservableList<Osoba> pobierzDane() throws Exception
    {

        Gson gson = new Gson();
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create(url)).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String odpowiedz = response.body();
        Osoba[] enums = gson.fromJson(odpowiedz,Osoba[].class);
        ObservableList<Osoba> lista = FXCollections.observableArrayList(enums);

        return lista;


    }

    private void tableViewSetup()
    {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        col_nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data_urodzenia"));
        col_miejscowosc.setCellValueFactory(new PropertyValueFactory<>("miejscowosc"));
        col_id_ksiedza.setCellValueFactory(new PropertyValueFactory<>("ksiadz"));
    }

    private void tableViewPopulate()
    {
        try {
            table_view.setItems(pobierzDane());
        } catch (Exception e) {
            e.printStackTrace();
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

        tableViewSetup();
        tableViewPopulate();

    }
}
