package edu.pwste;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class FormEvent {


    @FXML
    private TextField txtNazwa;
    @FXML
    private TextField txtTyp;

    @FXML
    private TextField txtOpis;


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

    private boolean wydarzenieValidate()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if( txtNazwa.getText().equals("")|| txtOpis.getText().equals("")|| txtTyp.getText().equals(""))
        {
            alert.setTitle("Błąd podczas dodawania wydarzenia");
            alert.setHeaderText("Błąd");
            alert.setContentText("Żadne z pól nie może być puste!");
            alert.showAndWait();
            return false;
        }
        return true;
    }
@FXML
private void wyczysc()
{
    txtNazwa.setText("");
    txtOpis.setText("");
    txtTyp.setText("");
}
    void sendreq(){
        var url = "http://localhost:3000/wydarzenia/add";
        var urlParameters = "nazwa="+txtNazwa.getText()+"&typ="+txtTyp.getText()+"&opis="+txtOpis.getText();
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        HttpURLConnection con = null;
        try {

            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (var wr = new DataOutputStream(con.getOutputStream())) {

                wr.write(postData);
                if (con.getResponseMessage().equals("OK")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pomyślnie dodano");
                    alert.setContentText("Wydarzenie pomyslnie dodane");
                    alert.showAndWait();
                    wyczysc();
                }
            }

            StringBuilder content = null;

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

           wyczysc();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            con.disconnect();
        }


    }

    @FXML
    void addWydarzenie(ActionEvent event) {
        if(wydarzenieValidate()){
            sendreq();
        }
    }
}
