/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.apirest;

import com.mycompany.nubulamusicwebaplication.dto.ResponseMessageDTO;
import com.mycompany.nubulamusicwebaplication.dto.UsuarioDTO;
import com.mycompany.nubulamusicwebaplication.dto.UsuarioRequestDTO;
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
import com.mycompany.nubulamusicwebaplication.dto.UsuarioRequestDTO;
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ResponseMessageDTO apiResponse = new ResponseMessageDTO();
        // EN EL POST NO SE MANDAN PARAMETROS SE MANDA LA INFORMACION A GUARDAR EN EL CUERPO DE LA SOLICITUD
        try {
            UsuarioRequestDTO req = JSONMapper.mapper
                    .readValue(request.getInputStream(), UsuarioRequestDTO.class);

            usuarioService.registrar(
                    req.getNombre(),
                    req.getCorreo(),
                    req.getContrasenia(),
                    req.getPseudonimo(),
                    req.getEstado(),
                    req.getCuenta(),
                    req.getFechaNacimiento(),
                    req.isTerminosAceptados()
            );

            response.setStatus(HttpServletResponse.SC_CREATED);

            apiResponse.setSuccess(true);
            apiResponse.setMessage("Usuario creado correctamente");

            JSONMapper.mapper.writeValue(response.getWriter(), apiResponse);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());

            JSONMapper.mapper.writeValue(response.getWriter(), apiResponse);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ResponseMessageDTO apiResponse = new ResponseMessageDTO();
        // EL PUT MANDAMOS EN LOS PARAMETROS EL IDENTIFICADOR DEL ELEMENTO QUE SE VA A ACTUALIZAR
        // Y EL CUERPO DE LA SOLICITUD EL OBJETO CON LOS CAMPOS ACTUALIZADOS
        try {
            //PARAMETRO
            String pathInfo = request.getPathInfo();

            if (pathInfo == null || pathInfo.equals("/")) {
                throw new IllegalArgumentException("El id es obligatorio.");
            }

            Long id = Long.parseLong(pathInfo.substring(1));
            // CUERPO/BODY
            UsuarioRequestDTO req = JSONMapper.mapper
                    .readValue(request.getInputStream(), UsuarioRequestDTO.class);

            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNombre(req.getNombre());
            usuario.setCorreo(req.getCorreo());
            usuario.setPseudonimo(req.getPseudonimo());
            usuario.setEstado(req.getEstado());
            usuario.setCuenta(req.getCuenta());
            usuario.setFechaNacimiento(req.getFechaNacimiento());

            usuarioService.actualizarUsuario(usuario);

            apiResponse.setSuccess(true);
            apiResponse.setMessage("Usuario actualizado correctamente");

            JSONMapper.mapper.writeValue(response.getWriter(), apiResponse);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());
            
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ResponseMessageDTO apiResponse = new ResponseMessageDTO();

        try {
            // MANDAMOS EN LOS PARAMETROS EL IDENTIFICADOR DEL ELEMENTO A ELIMINAR
            String pathInfo = request.getPathInfo();

            if (pathInfo == null || pathInfo.equals("/")) {
                throw new IllegalArgumentException("El id es obligatorio.");
            }

            Long id = Long.parseLong(pathInfo.substring(1));

            usuarioService.eliminarUsuario(id);

            apiResponse.setSuccess(true);
            apiResponse.setMessage("Usuario eliminado correctamente");

            JSONMapper.mapper.writeValue(response.getWriter(), apiResponse);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());

            JSONMapper.mapper.writeValue(response.getWriter(), apiResponse);
        }
    }

}
