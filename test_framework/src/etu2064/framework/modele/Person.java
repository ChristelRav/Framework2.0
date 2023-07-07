package etu2064.framework.modele;

import etu2064.framework.myAnnotations.Url;
import etu2064.framework.myAnnotations.Param;
import etu2064.framework.view.ModelView;
import java.util.Map;
import etu2064.framework.*;

public class Person {
    String nom;
    int age;
    FileUpload fu ;
/// GETTER & SETTER
    public String getnom() {return nom;}    public void setnom(String nom) {this.nom = nom;}
    public int getage() {return age;}       public void setage(int age) {this.age = age;}
    public FileUpload getFu() {return fu;}  public void setFu(FileUpload fu) {this.fu = fu;}

    public Person(){}
    public Person(String nom, int age) {
        this.setnom(nom);
        this.setage(age);
    }
    public Person(FileUpload fu) {
      this.setFu(fu);
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
    @Url(name="get_Person")
    public ModelView get(@Param(p="nom")String nom , @Param(p="age")int age){
      String jsp = "get.jsp";
      ModelView mv = new ModelView();
      Person p = new Person(nom, age);
      Map<String, Object> att = new ModelView().addItem("get",p);
      mv.setView(jsp);
      mv.setAttribut(att);
      return mv;
    }
    @Url(name="upload_Person")
    public ModelView upload(){
      String jsp = "upload.jsp";
      ModelView mv = new ModelView();
      mv.setView(jsp);
      return mv;
    }
    @Url(name="succes_Person")
    public ModelView succes(@Param(p="fu")FileUpload fu){
      String jsp = "succes.jsp";
      ModelView mv = new ModelView();
      Person p = new Person(fu);
      Map<String, Object> att = new ModelView().addItem("file",p);
      mv.setView(jsp);
      mv.setAttribut(att);
      return mv;
    }
}
