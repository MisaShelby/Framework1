package etu1759.framework.servlet;

import etu1759.framework.*;
import etu1759.framework.annotation.*;
// import etu1759.modelView.ModelView;
import jakarta.servlet.*;
import java.io.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.HashMap; //sprint3
import java.util.Map; //sprint3

// import jakarta.management.modelmbean.ModelMBean;

import java.net.*;
import java.lang.reflect.*;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;

    public void init() throws ServletException {
        try {
            super.init();
            // String packages = String.valuesOf(getInitParamater("packages"));
            String packages = "etu1759.model";
            this.MappingUrls = new HashMap<>();
            String path = packages.replaceAll("[.]", "!\\\\");
            URL packageUrl = Thread.currentThread().getContextClassLoader().getResource(path);
            File packDir = new File(packageUrl.toURI());
            File[] inside = packDir.listFiles(file -> file.getName().endsWith(".class"));
            List<Class> lists = new ArrayList<>();
            for (File f : inside) {
                String c = packages + "." + f.getName().substring(0, f.getName().lastIndexOf("."));
                lists.add(Class.forName(c));
            }
            for (Class c : lists) {
                Method[] methods = c.getDeclaredMethods();
                for (Method m : methods) {
                    if (m.isAnnotationPresent(Annotation.class)) {
                        Annotation url = m.getAnnotation(Annotation.class);
                        if (!url.Url().isEmpty() && url.Url() != null) {
                            Mapping map = new Mapping(c.getName(), m.getName());
                            this.MappingUrls.put(url.Url(), map);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}