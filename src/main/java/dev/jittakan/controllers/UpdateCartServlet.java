package dev.jittakan.controllers;

import dev.jittakan.models.Cart;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/cart/update")
public class UpdateCartServlet extends HttpServlet {

    @Inject
    private Cart cart;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            updateProducts(req, cart);
            updateQuantities(req, cart);

        resp.sendRedirect(req.getContextPath() + "/cart/view");
    }

    private void updateProducts(HttpServletRequest request, Cart cart) {
        String[] deleteIds = request.getParameterValues("deleteProducts");

        if (deleteIds != null && deleteIds.length > 0) {
            List<String> productIds = Arrays.asList(deleteIds);
            cart.removeProducts(productIds);
        }

    }

    private void updateQuantities(HttpServletRequest request, Cart cart) {

        Enumeration<String> enumer = request.getParameterNames();

        while (enumer.hasMoreElements()) {
            String paramName = enumer.nextElement();
            if (paramName.startsWith("cant_")) {
                String id = paramName.substring(5);
                String amount = request.getParameter(paramName);
                if (amount != null) {
                    cart.updateCantidad(id, Integer.parseInt(amount));
                }
            }
        }
    }
}
