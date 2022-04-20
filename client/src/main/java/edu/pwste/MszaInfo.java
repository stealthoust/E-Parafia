package edu.pwste;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    public TableView wydarzenia_tabela=new TableView();

    @FXML
    public TableView ksiadzTabela=new TableView();

    public String url = "http://localhost:3000/kalendarz/zakresDat";
    private ObservableList<msze> lista;

    public MszaInfo() throws Exception {
    }

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
    public ZakresDat[] xd = pobierzDane();
    LocalDate minDate = LocalDate.of(xd[0].year, xd[0].month, xd[0].day);
    LocalDate maxDate = LocalDate.of(xd[1].year, xd[1].month, xd[1].day);

    public ObservableList<msze> pobierzMsze(String link) throws IOException, InterruptedException {
        Gson gson = new Gson();
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create(link)).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String odpowiedz = response.body();
        msze[] enums = gson.fromJson(odpowiedz,msze[].class);
        ObservableList<msze> lista = FXCollections.observableArrayList(enums);

        return lista;
    }

    public void pokazMsze(String link) throws Exception {
         lista = pobierzMsze(link);

        for(int i = 0; i < lista.size(); i++)
        {
            mszaCombo.getItems().add(lista.get(i).godzina);
        }

    }
public void pokaz()
{
    System.out.println(lista.get(mszaCombo.getSelectionModel().getSelectedIndex()).id );
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
        datePicker.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
                    }});
        try {
            pokazMsze("http://localhost:3000/kalendarz/mszedzis/2022/04/03");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
