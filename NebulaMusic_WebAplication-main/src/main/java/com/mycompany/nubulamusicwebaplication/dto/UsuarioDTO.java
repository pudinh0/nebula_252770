/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.nubulamusicwebaplication.dto;

/**
 *
 * @author adell
 */
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String Correo;
    private String pseudonimo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nombre, String Correo, String pseudonimo) {
        this.id = id;
        this.nombre = nombre;
        this.Correo = Correo;
        this.pseudonimo = pseudonimo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }
    
    
    
    
}
