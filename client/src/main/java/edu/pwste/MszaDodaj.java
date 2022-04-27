package edu.pwste;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
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

        if (kalendarz.getValue() != null) {
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

    public void przypiszMK(ActionEvent actionEvent) {
        if(kalendarz.getValue()!=null && combo_msza.getSelectionModel().getSelectedItem()!=null && combo_ksiadz.getSelectionModel().getSelectedItem()!=null) {
            var url = "http://localhost:3000/kalendarz/addmszaksiadz";
            var urlParameters = "msza=" + lista.get(combo_msza.getSelectionModel().getSelectedIndex()).id + "&ksiadz=" + (combo_ksiadz.getSelectionModel().getSelectedIndex() + 1);
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
                        alert.setContentText("Ksiądz został dodany do Mszy");
                        lbl_data.setText("");
                        lbl_ksiadz.setText("");
                        lbl_msza.setText("");
                        kalendarz.setValue(null);
                        combo_msza.getSelectionModel().clearSelection();
                        combo_msza.getItems().clear();
                        alert.showAndWait();
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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Błąd podczas dodawania przypisywania księdza do mszy");
                    alert.setHeaderText("Błąd podczas dodawania przypisywania księdza do mszy");
                    alert.setContentText("Ten ksiądz jest już przypisany do tej mszy");
                    alert.showAndWait();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
            } finally {
                con.disconnect();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd podczas dodawania przypisywania księdza do mszy");
            alert.setContentText("Nie wybrałeś jakiegoś parametru");
            alert.showAndWait();
        }
    }
}
