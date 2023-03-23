package etu2064.framework.servlet;

import etu2064.framework.Mapping;
import etu2064.framework.myAnnotations.Url;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

//@WebServlet(name = "FontServlet", value = "/FontServlet")
public class FontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;
//GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
//POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
/// PROCESS REQUEST
    public void processRequest(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("text/plain");
        PrintWriter out=res.getWriter();
/// Display URL
        try {
            out.print("URL Tomcat = ");
            String projet = req.getRequestURL().toString();
            String host = req.getHeader("Host");

            String[] partieUrl = projet.split(host);
            String chemin = partieUrl[1].substring(1);
            out.print("/"+chemin);

        } catch (Exception e) {
            out.print(e.getMessage());
        }
    }
/// INIT
    @Override
    public void init() throws ServletException {
        mappingUrls = new  HashMap<String,Mapping>();
        displayAnnot(mappingUrls);
    }
//GET CLASS
    public  String [] getEachClass(String path){
        ArrayList<String> c=new ArrayList<>();
        File modele = new File(path);
        String[] clazz = modele.list();
        for(int i=0; i<clazz.length; i++){
            String fl=FilenameUtils.getExtension(clazz[i]);
            if(fl.equalsIgnoreCase("java")){
                String [] java = clazz[i].split("[.]");
                c.add(java[0]);
            }
        }
        return c.toArray(new String[c.size()]);
    }
//GET ANNOTATION
    public void displayAnnot( HashMap<String,Mapping> mapping){
        try {
            String [] classe = this.getEachClass("/home/christelle/IdeaProjects/Framework2.0/src/main/java/etu2064/framework/modele/");
            for(int i =0 ;i< classe.length; i++){
                String className  = "etu2064.framework.modele." +classe[i];
                Class<?> clazz = Class.forName(className);
                Method [] methods = clazz.getDeclaredMethods();
                for (int j = 0 ; j<methods.length ; j++) {
                    Annotation[] url = methods[j].getAnnotations();
                    Url u = methods[i].getAnnotation(Url.class);
                    mapping.put(u.name(),new Mapping(classe[i],methods[i].getName()));
                    //System.out.println(u.name()+" / "+classe[i]+" / "+methods[i].getName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
