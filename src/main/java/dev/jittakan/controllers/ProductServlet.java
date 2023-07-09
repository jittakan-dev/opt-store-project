package dev.jittakan.controllers;

import dev.jittakan.configs.ProductServicePrincipal;
import dev.jittakan.models.entities.Product;
import dev.jittakan.services.LoginService;
import dev.jittakan.services.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dev.jittakan.services.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/products.html", "/products"})
public class ProductServlet extends HttpServlet {

    @Inject
    @ProductServicePrincipal
    private ProductService service;

    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = service.listing();

        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("products", products);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", req.getAttribute("title") + ": Product list");
        getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);

    }
}
