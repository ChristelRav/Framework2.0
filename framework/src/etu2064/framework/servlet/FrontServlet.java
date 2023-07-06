package etu2064.framework.servlet;

import etu2064.framework.Mapping;
import etu2064.framework.myAnnotations.Url;
import etu2064.framework.view.ModelView;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class FrontServlet  extends HttpServlet{
    HashMap<String,Mapping> mappingUrls = new HashMap<String,Mapping>();
    String modele ;
    //INIT
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        modele = getInitParameter("package");
        String path = getServletContext().getRealPath("/WEB-INF/classes/"+modele);
        fillMappingUrls(path);
    }

    
    //GET
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException  {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        try{
            processRequest(req,res);

        }catch(Exception exp){
            out.print(exp);
        }
    }
    //POST
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException  {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        try{             
            processRequest(req,res);     
        }catch(Exception exp){
            out.print(exp);
        }
    }
 
    //PROCESS REQUEST
    public void processRequest(HttpServletRequest req,HttpServletResponse res)throws IOException , Exception , ServletException{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        ServletContext result = this.getServletContext();
        
        /// Display URL 
       try {
            String projet = req.getRequestURL().toString();
            String host = req.getHeader("Host");

            String[] partieUrl = projet.split(host);
            String chemin = partieUrl[1].substring(1);
            out.print("/"+chemin);

            out.print("<p>"+modele+"</p>");
            
            String action = chemin.substring(chemin.lastIndexOf("/") + 1);
            out.print("<p>"+action+"</p>");

            
            String packageName = modele.replace("/", ".");
            out.print("<p> Lala "+packageName+"</p>");

            out.print("<p> Lala "+mappingUrls.isEmpty()+"</p>");

        ///Display Class
        
            for (Map.Entry<String, Mapping> entry : mappingUrls.entrySet()) {
                out.println("<p><strong>Annotation  </strong>: \"" + entry.getKey() + "\"</p>");
                out.println("<p><strong>Class </strong>:" + entry.getValue().getClassName()+"</p>");
                out.println("<p><strong>Method </strong>:" + entry.getValue().getMethod()+"</p>");
                out.println("\n\n");
            }
           
           } catch (Exception e) {
            e.printStackTrace(out);
           }
        

    }
    //SEARCH CLASS
    public ArrayList<String> getClassNames(String path) {
        ArrayList<String> classNames = new ArrayList<>();
        try {
            File directory = new File(path);
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".class")) {
                    String className = file.getName().replace(".class", "");
                    classNames.add(className);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classNames;
    }
    
    //GET ANNOTATION + METHOD
    public void fillMappingUrls( String path) {
        ArrayList<String> classNames = getClassNames(path);
        String classesPrefix = "classes\\";
        String subPath = path.substring(path.indexOf(classesPrefix) + classesPrefix.length());
        String packageName =  subPath.replace("\\", ".");
        try {
            for (String className : classNames) {
                String val = packageName+className;
                Class<?> clazz = Class.forName(val);
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Url.class)) {
                        Url annotation = method.getAnnotation(Url.class);
                        String url = annotation.name();
                        Mapping mapping = new Mapping(className, method.getName());
                        this.mappingUrls.put(url, mapping);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
}