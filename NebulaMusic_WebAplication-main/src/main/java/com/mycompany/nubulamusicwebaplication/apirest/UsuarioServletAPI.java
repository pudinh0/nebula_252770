/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.apirest;

import com.mycompany.nubulamusicwebaplication.dto.ResponseMessageDTO;
import com.mycompany.nubulamusicwebaplication.dto.UsuarioDTO;
import com.mycompany.nubulamusicwebaplication.models.Usuario;
import com.mycompany.nubulamusicwebaplication.service.IUsuarioService;
import com.mycompany.nubulamusicwebaplication.service.UsuarioService;
import com.mycompany.nubulamusicwebaplication.util.JSONMapper;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Adel
 */
@WebServlet(name = "UsuarioServletAPI", urlPatterns = {"/api/usuario/*"})
public class UsuarioServletAPI extends HttpServlet {

    private IUsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String pathInfo = request.getPathInfo();

            // /api/usuario todos los usuarios
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Usuario> usuarios = usuarioService.listarTodos();

                List<UsuarioDTO> usuariosDTO = usuarios.stream().map(u -> {

                    UsuarioDTO dto = new UsuarioDTO();
                    dto.setId(u.getId());
                    dto.setNombre(u.getNombre());
                    dto.setCorreo(u.getCorreo());
                    dto.setPseudonimo(u.getPseudonimo());
                    return dto;
                }).collect(Collectors.toList());

                JSONMapper.mapper.writeValue(response.getWriter(), usuariosDTO);
            } else {

                Long id = Long.parseLong(pathInfo.substring(1));

                Usuario usuario = usuarioService.buscarPorId(id);
                if (usuario == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    

                    ResponseMessageDTO mensaje = new ResponseMessageDTO();

                    mensaje.setSuccess(false);
                    mensaje.setMessage("no se encontreo el usuario buscado");
                            
                    JSONMapper.mapper.writeValue(response.getWriter(), mensaje);
                    return;
                }
                UsuarioDTO dto = new UsuarioDTO();
                    dto.setId(usuario.getId());
                    dto.setNombre(usuario.getNombre());
                    dto.setCorreo(usuario.getCorreo());
                    dto.setPseudonimo(usuario.getPseudonimo());
                JSONMapper.mapper.writeValue(response.getWriter(), dto);
            }
        } catch (IOException | NumberFormatException e) {
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    
            ResponseMessageDTO mensaje = new ResponseMessageDTO();

            mensaje.setSuccess(false);
            mensaje.setMessage("Ocurrio un error al procesar la solicitud");

            JSONMapper.mapper.writeValue(response.getWriter(), mensaje);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
