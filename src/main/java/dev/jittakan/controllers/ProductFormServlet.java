package dev.jittakan.controllers;

import dev.jittakan.configs.ProductServicePrincipal;
import dev.jittakan.models.entities.Category;
import dev.jittakan.models.entities.Product;
import dev.jittakan.services.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/products/form")
public class ProductFormServlet extends HttpServlet {

    @Inject
    @ProductServicePrincipal
    private ProductService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e){
            id = 0L;
        }
        Product product = new Product();
        product.setCategory(new Category());
        if(id > 0){
            Optional<Product> o = service.byId(id);
            if(o.isPresent()){
                product = o.get();
            }
        }
        req.setAttribute("categories", service.listingCategory());
        req.setAttribute("product", product);
        req.setAttribute("title", req.getAttribute("title") + ": Product form");
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        Integer precio;
        try{
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch(NumberFormatException e){
            precio = 0;
        }

        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_registro");
        Long categoriaId;
        try{
            categoriaId = Long.valueOf(req.getParameter("category"));
        } catch(NumberFormatException e){
            categoriaId = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if(name == null || name.isBlank()){
            errores.put("name", "el name es requerido!");
        }
        if(sku == null || sku.isBlank()){
            errores.put("sku", "el sku es requerido!");
        } else if(sku.length() > 10){
            errores.put("sku", "el sku debe tener max 10 caracteres");
        }
        if(fechaStr == null || fechaStr.isBlank()){
            errores.put("fecha_registro", "La fecha es requerida!");
        }
        if(precio.equals(0)){
            errores.put("precio", "El precio es requerido!");
        }
        if(categoriaId.equals(0L)){
            errores.put("category", "La category es requerida");
        }

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch(DateTimeParseException e){
            fecha = null;
        }
        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        } catch(NumberFormatException e){
            id = null;
        }
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setSku(sku);
        product.setPrice(precio);
        product.setRegistrationDate(fecha);

        Category category = new Category();
        category.setId(categoriaId);
        product.setCategory(category);

        if(errores.isEmpty()){
            service.saveProduct(product);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listingCategory());
            req.setAttribute("product", product);
            req.setAttribute("title", req.getAttribute("title") + ": Formulario de productos");
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
