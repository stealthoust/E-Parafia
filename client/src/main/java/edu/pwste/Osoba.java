package edu.pwste;

import java.util.Date;

public class Osoba {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(Date data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public int getId_ksiedza() {
        return id_ksiedza;
    }

    public void setId_ksiedza(int id_ksiedza) {
        this.id_ksiedza = id_ksiedza;
    }

    public int id;
    public String imie;
    public String nazwisko;
    public Date data_urodzenia;
    public String miejscowosc;
    public int id_ksiedza;

}
