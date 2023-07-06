package etu2064.framework.modele;

import etu2064.framework.myAnnotations.Url;
import etu2064.framework.view.ModelView;
import java.util.Map;

public class Person {
    String nom;
    int age;
/// GETTER & SETTER
    public String getnom() {return nom;}    public void setnom(String nom) {this.nom = nom;}
    public int getage() {return age;}       public void setage(int age) {this.age = age;}

    public Person(){}
    public Person(String nom, int age) {
        this.setnom(nom);
        this.setage(age);
    }

    @Url(name="findAll_Person")
    public ModelView find(){
      String jsp = "load.jsp";
      ModelView mv = new ModelView();
      mv.setView(jsp);
      return mv;
    }
    @Url(name="simple_Person")
    public String simple(){
      String jsp = "load.jsp";
      return jsp;
    }
    @Url(name="send_Person")
    public ModelView send(){
      String jsp = "send.jsp";
      Person p = new Person("Ialy", 25);
      Map <String , Object> att = new ModelView().addItem("pers",p);
      ModelView mv = new ModelView();
      mv.setView(jsp);
      mv.setAttribut(att);
      return mv;
    }
    @Url(name="form_Person")
    public ModelView form(){
      String jsp = "form.jsp";
      ModelView mv = new ModelView();
      mv.setView(jsp);
      return mv;
    }
    @Url(name="save_Person")
    public ModelView save(){
      String jsp = "save.jsp";
      ModelView mv = new ModelView();
      Person p = new Person(this.getnom(), this.getage());
      Map<String, Object> att = new ModelView().addItem("form",p);
      mv.setView(jsp);
      mv.setAttribut(att);
      return mv;
    }
}
