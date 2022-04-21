package edu.pwste;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FormUser {

    public TableView table_view;
    public MenuButton mszaBtn;
    @FXML
    public TextField txtName,txtNazwisko,txtMiejscowosc;
    @FXML
    public DatePicker dateDob;
    @FXML
    public ComboBox comboKsiadz;
    public Button btnZatwierdz;
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
    @FXML
    void userDodaj(ActionEvent event) {
        var url = "http://localhost:3000/osoby/add";
        var urlParameters = "imie="+txtName.getText()+"&nazwisko="+txtNazwisko.getText()+"&dt_urodzenia="+dateDob.getValue().toString()+"&miejscowosc="+txtMiejscowosc.getText()+"&id_ksiedza=1";
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

            System.out.println(content.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            con.disconnect();
        }
    }

}
