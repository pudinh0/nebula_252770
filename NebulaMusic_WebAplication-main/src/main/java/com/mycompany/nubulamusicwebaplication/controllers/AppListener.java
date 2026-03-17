/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.nubulamusicwebaplication.controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * sirve para inicializar variables ej catalogos
 *la etiqueta webListener funciona para que el web detecte la clase y corra desde un inicio los datos
 * @author adell
 */
@WebListener
public class AppListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent scr){
        ServletContext app = scr.getServletContext();
        
        app.setAttribute("appname", "popusitas");
    }
    
    public void contextDestroyer(ServletContextEvent scr){
    
        //todo el codigo que se ejecutara una vez que se detenge la aplicacion, para destruir el contexto (variables temporales y asi)
    }
}
