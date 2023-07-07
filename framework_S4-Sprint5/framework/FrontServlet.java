package etu1759.framework.servlet;

import etu1759.framework.Mapping;
import etu1759.framework.utils.Fonctions;
import etu1759.annotation.url;

import java.lang.reflect.Method;
import java.net.URL;

import java.io.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FrontServlet extends HttpServlet{

    HashMap<String, Mapping> mappingUrls;

    public void init() throws ServletException {
        
        try {
            String packageName = getServletContext().getInitParameter("packageName");
// sprint3
            mappingUrls = Fonctions.mameno_HashMap(mappingUrls, packageName);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
// sprint5
        ModelView modelView = Fonctions.recup_ModelView(mappingUrls, request, response);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/"+modelView.getUrl());
        requestDispatcher.forward(request, response);
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();
        String packageName = getServletContext().getInitParameter("packageName");
        URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "//")); 
        out.println("Front Servlet");   
        out.println("Root: " + root);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
        
    }


}

