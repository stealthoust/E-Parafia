package edu.pwste;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormMsza implements Initializable {
    public MenuButton mszaBtn,formularzBtn;
    public TextField cmmTxt,cmrTxt,cmtgTxt,cmtmTxt,cmngTxt,cmnmTxt,mTxt,hTxt;
    public Button btnDodaj,btnDodajcm;
    public DatePicker kalendarz;

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

    private boolean oneMszaValidate()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(kalendarz.getValue()==null)
        {
            alert.setTitle("Błąd podczas dodawania pojedynczej mszy");
            alert.setHeaderText("Błąd zwiazany z data");
            alert.setContentText("Nie wybrano daty");
            alert.showAndWait();
            return false;
        }
       else if(hTxt.getText().equals("")||Integer.parseInt(hTxt.getText())<1 || Integer.parseInt(hTxt.getText())>24|| hTxt.getText().length()>2||hTxt.getText().length()<1  )
        {
            alert.setTitle("Błąd podczas dodawania pojedynczej mszy");
            alert.setHeaderText("Błąd związany z godziną");
            alert.setContentText("Podana godzina jest nieprawidłowa");
            alert.showAndWait();
            return false;
        }
        else if( mTxt.getText().equals("")|| Integer.parseInt(mTxt.getText())<0 || Integer.parseInt(mTxt.getText())>60|| mTxt.getText().length()>2||mTxt.getText().length()<1)
        {
            alert.setTitle("Błąd podczas dodawania pojedynczej mszy");
            alert.setHeaderText("Błąd związany z minutami");
            alert.setContentText("Podane minuty jest nieprawidłowa");
            alert.showAndWait();
            return false;
        }
    return true;
    }

    private boolean fewMszaValidate()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if( cmmTxt.getText().equals("")|| Integer.parseInt(cmmTxt.getText())<0 || Integer.parseInt(cmmTxt.getText())>12|| cmmTxt.getText().length()>2||cmmTxt.getText().length()<1)
        {
            alert.setTitle("Błąd podczas dodawania mszy na cały miesiac");
            alert.setHeaderText("Błąd związany z miesiacem");
            alert.setContentText("Podany miesiac jest nieprawidłowy");
            alert.showAndWait();
            return false;
        }
        else if( cmrTxt.getText().equals("")|| Integer.parseInt(cmrTxt.getText())<2021 || Integer.parseInt(cmrTxt.getText())>2030|| cmrTxt.getText().length()!=4)
        {
            alert.setTitle("Błąd podczas dodawania mszy na cały miesiac");
            alert.setHeaderText("Błąd związany z rokiem");
            alert.setContentText("Podany rok jest nieprawidłowy");
            alert.showAndWait();
            return false;
        }

        else if(cmtgTxt.getText().equals("")||Integer.parseInt(cmtgTxt.getText())<1 || Integer.parseInt(cmtgTxt.getText())>24|| cmtgTxt.getText().length()>2||cmtgTxt.getText().length()<1  )
        {
            alert.setTitle("Błąd podczas dodawania mszy na cały miesiac");
            alert.setHeaderText("Błąd związany z godziną");
            alert.setContentText("Podana godzina w tygodniu jest nieprawidłowa");
            alert.showAndWait();
            return false;
        }

        else if( cmtmTxt.getText().equals("")|| Integer.parseInt(cmtmTxt.getText())<0 || Integer.parseInt(cmtmTxt.getText())>60|| cmtmTxt.getText().length()>2||cmtmTxt.getText().length()<1)
        {
            alert.setTitle("Błąd podczas dodawania pojedynczej mszy");
            alert.setHeaderText("Błąd związany z minutami");
            alert.setContentText("Podane minuty w tygodniu są nieprawidłowe");
            alert.showAndWait();
            return false;
        }

        else if(cmngTxt.getText().equals("")||Integer.parseInt(cmngTxt.getText())<1 || Integer.parseInt(cmngTxt.getText())>24|| cmngTxt.getText().length()>2||cmngTxt.getText().length()<1  )
        {
            alert.setTitle("Błąd podczas dodawania mszy na cały miesiac");
            alert.setHeaderText("Błąd związany z godziną");
            alert.setContentText("Podana godzina w niedziele jest nieprawidłowa");
            alert.showAndWait();
            return false;
        }

        else if( cmnmTxt.getText().equals("")|| Integer.parseInt(cmnmTxt.getText())<0 || Integer.parseInt(cmnmTxt.getText())>60|| cmnmTxt.getText().length()>2||cmnmTxt.getText().length()<1)
        {
            alert.setTitle("Błąd podczas dodawania pojedynczej mszy");
            alert.setHeaderText("Błąd związany z minutami");
            alert.setContentText("Podane minuty w niedziele są nieprawidłowe");
            alert.showAndWait();
            return false;
        }
        return true;
    }


    private void inputSetup()
    {
        cmmTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cmmTxt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        cmrTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cmrTxt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        cmtgTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cmtgTxt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        cmtmTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cmtmTxt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        cmngTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cmngTxt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        cmnmTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cmnmTxt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        mTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    mTxt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        hTxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    hTxt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputSetup();
    }

    public void dodajMsze(ActionEvent actionEvent) {
        System.out.println(oneMszaValidate());
    }

    public void dodajMszeMiesiac(ActionEvent actionEvent) {
        System.out.println(fewMszaValidate());
    }
}
