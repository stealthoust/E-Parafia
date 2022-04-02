package edu.pwste;

import com.google.gson.JsonArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
public class KsiegaOsob implements Initializable {

    public TableView table_view=new TableView();
    TableColumn col_id=new TableColumn("Id");
    TableColumn col_imie =new TableColumn("Imie");
    TableColumn col_nazwisko=new TableColumn("Nazwisko");
    TableColumn col_data=new TableColumn("Nazwisko");
    TableColumn col_miejscowosc=new TableColumn("Nazwisko");
    public String url = "http://10.8.42.15:3000/osoby";

    public Osoba[] pobierzDane() throws Exception
    {

        Gson gson = new Gson();
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create(url)).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String odpowiedz = response.body();
        Osoba[] enums = gson.fromJson(odpowiedz,Osoba[].class);


        return enums;


    }

    public void uzupelnijListe() throws Exception {
        table_view.getColumns().addAll(col_id,col_imie,col_nazwisko,col_data,col_miejscowosc);


    }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            System.out.println(pobierzDane()[0].imie);;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
