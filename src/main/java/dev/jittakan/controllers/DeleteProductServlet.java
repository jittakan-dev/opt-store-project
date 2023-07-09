package dev.jittakan.controllers;

import dev.jittakan.configs.ProductServicePrincipal;
import dev.jittakan.models.entities.Product;
import dev.jittakan.services.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/productos/eliminate")
public class DeleteProductServlet extends HttpServlet {

    @Inject
    @ProductServicePrincipal
    private ProductService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e){
            id = 0L;
        }
        if(id > 0){
            Optional<Product> o = service.byId(id);
            if(o.isPresent()){
                service.eliminate(id);
                resp.sendRedirect(req.getContextPath()+"/products");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "There is no product in the database!");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error, the ID is null, it must be sent as a parameter in the URL!");
        }
    }
}
