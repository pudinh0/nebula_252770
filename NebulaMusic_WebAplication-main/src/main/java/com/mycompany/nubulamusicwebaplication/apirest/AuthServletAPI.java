/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.apirest;

import com.mycompany.nubulamusicwebaplication.dto.ResponseMessageDTO;
import com.mycompany.nubulamusicwebaplication.dto.UsuarioAuthDTO;
import com.mycompany.nubulamusicwebaplication.models.Usuario;
import com.mycompany.nubulamusicwebaplication.service.IUsuarioService;
import com.mycompany.nubulamusicwebaplication.service.UsuarioService;
import com.mycompany.nubulamusicwebaplication.util.JSONMapper;
import com.mycompany.nubulamusicwebaplication.util.JWTUtil;
import static com.mysql.cj.MysqlType.JSON;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Adel
 */
@WebServlet(name = "AuthServletAPI", urlPatterns = {"/api/auth/*"})
public class AuthServletAPI extends HttpServlet {

    private IUsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        UsuarioAuthDTO req = JSONMapper.mapper.readValue(request.getInputStream(), UsuarioAuthDTO.class);
        Usuario user = usuarioService.autenticar(req.getCorreo(), req.getContrasenia());
        ResponseMessageDTO mensaje = new ResponseMessageDTO();

        if (user == null) {
            response.setStatus(401);
            mensaje.setSuccess(false);
            mensaje.setMessage("credenciales invalidas");
            JSONMapper.mapper.writeValue(response.getWriter(), mensaje);

            return;
        }

        String token = JWTUtil.generarToken(user.getCorreo());
        mensaje.setSuccess(true);
        mensaje.setMessage(token);

        JSONMapper.mapper.writeValue(response.getWriter(), mensaje);
    }

}
