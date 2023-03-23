package etu2064.framework.modele;

import etu2064.framework.myAnnotations.Url;

public class Person {
    String nom;
    int age;
/// GETTER & SETTER
    @Url(name = "getNom_Person")
    public String getNom() {return nom;}
    @Url(name = "setNom_Person")
    public void setNom(String nom) {this.nom = nom;}
    @Url(name = "getAge_Person")
    public int getAge() {return age;}
    @Url(name = "setAge_Person")
    public void setAge(int age) {this.age = age;}

    public Person(String nom, int age) {
        this.setNom(nom);
        this.setAge(age);
    }
}
