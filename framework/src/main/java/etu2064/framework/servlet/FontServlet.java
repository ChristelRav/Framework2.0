package etu2064.framework.servlet;

import etu2064.framework.Mapping;
import etu2064.framework.myAnnotations.Url;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.apache.commons.io.FilenameUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
        try {
///HashMap
            for (Map.Entry<String, Mapping> entry : mappingUrls.entrySet()) {
                out.println("\tAnnotation : \"" + entry.getKey() + "\"");
                out.println("\tClass:" + entry.getValue().getClassName());
                out.println("\tMethod:" + entry.getValue().getMethod());
                out.println("\n\n\n");
            }
/// Display URL
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
        String path = "/home/christelle/IdeaProjects/Framework2.0/src/main/java/etu2064/framework/modele/";
        displayAnnot(mappingUrls,path);
    }
//GET CLASS
    public  String [] getEachClass(String path){
        ArrayList<String> c=new ArrayList<>();
        File modele = new File(path);
        String[] clazz = modele.list();
        for(int i=0; i<clazz.length; i++){
            //String fl=FilenameUtils.getExtension(clazz[i]);
            String [] java = clazz[i].split("[.]");
            if(java[1].equalsIgnoreCase("java")){
                c.add(java[0]);
            }
        }
        return c.toArray(new String[c.size()]);
    }
//GET ANNOTATION
    public void displayAnnot( HashMap<String,Mapping> mapping,String path){
        try {
            String [] classe = this.getEachClass(path);
            for(int i =0 ;i< classe.length; i++){
                String className  = "etu2064.framework.modele." +classe[i];
                Class<?> clazz = Class.forName(className);
                Method [] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    Annotation[] url = method.getAnnotations();
                    if(url.length > 0 ){
                        Url  u = methods[i].getAnnotation(Url.class);
                        System.out.println(u.toString());
                        mappingUrls.put(u.name(),new Mapping(classe[i],method.getName()));
                      // System.out.println(u.name()+" / "+classe[i]+" / "+methods[i].getName());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
