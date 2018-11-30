package fr.laerce.cinema.model;

import java.util.Objects;

public class Film {
    public Film(int id, String titre, String afficheNom, double note){
        this.id = id;
        this.titre = titre;
        this.afficheNom = afficheNom;
        this.note = note;
    }
    int id;
    String titre;
    String afficheNom;
    double note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAfficheNom() {
        return afficheNom;
    }

    public void setAfficheNom(String afficheNom) {
        this.afficheNom = afficheNom;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    @Override
    //permet de comparer un object avec l'object en question (this)
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return getId () == film.getId ();
    }

    @Override
    //genere un hashcode unique propre a l'objet
    public int hashCode() {
        return Objects.hash (getId ());
    }
}