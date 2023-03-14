package etu2064.framework.servlet;

import etu2064.framework.Mapping;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

import java.io.IOException;
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
}
