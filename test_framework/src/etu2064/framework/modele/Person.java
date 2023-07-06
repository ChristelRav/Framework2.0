package etu2064.framework.modele;

import etu2064.framework.myAnnotations.Url;
import etu2064.framework.view.ModelView;
import java.util.Map;

public class Person {
    String nom;
    int age;
/// GETTER & SETTER
    public String getNom() {return nom;}    public void setNom(String nom) {this.nom = nom;}
    public int getAge() {return age;}       public void setAge(int age) {this.age = age;}

    public Person(){}
    public Person(String nom, int age) {
        this.setNom(nom);
        this.setAge(age);
    }

    @Url(name="findAll_Person")
    public ModelView findAll(){
      String jsp = "load.jsp";
      ModelView mv = new ModelView();
      mv.setView(jsp);
      return mv;
    }
    @Url(name="simple_Person")
    public String display(){
      String jsp = "load.jsp";
      return jsp;
    }
    @Url(name="send_Person")
    public ModelView sendEmp(){
      String jsp = "send.jsp";
      Person p = new Person("Ialy", 25);
      Map <String , Object> att = new ModelView().addItem("pers",p);
      ModelView mv = new ModelView();
      mv.setView(jsp);
      mv.setAttribut(att);
      return mv;
    }
}
