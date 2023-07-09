package dev.jittakan.controllers;

import dev.jittakan.configs.ProductServicePrincipal;
import dev.jittakan.models.Cart;
import dev.jittakan.models.CartItem;
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

@WebServlet("/cart/add")
public class AddCartServlet extends HttpServlet {

    @Inject
    @ProductServicePrincipal
    private ProductService service;

    @Inject
    private Cart cart;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Product> product = service.byId(id);
        if(product.isPresent()){
            CartItem item = new CartItem(1, product.get());
            cart.addItemCart(item);
        }
        resp.sendRedirect(req.getContextPath() + "/cart/view");
    }
}
