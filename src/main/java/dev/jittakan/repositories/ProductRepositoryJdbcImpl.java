package dev.jittakan.repositories;

import dev.jittakan.configs.MysqlConn;
import dev.jittakan.configs.Repository;
import dev.jittakan.models.entities.Category;
import dev.jittakan.models.entities.Product;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
@RepositoryJdbc
public class ProductRepositoryJdbcImpl implements CrudRepository<Product> {

    @Inject
    private Logger log;

    @Inject
    @MysqlConn
    private Connection conn;

    @PostConstruct
    public void initialize(){
        log.info("initializing the bean " + this.getClass().getName());
    }

    @PreDestroy
    public void destroy(){
        log.info("destroy the bean" + this.getClass().getName());
    }

    @Override
    public List<Product> listing() throws SQLException {
        List<Product> products = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*,c.name AS category FROM products AS p "
            + "INNER JOIN category AS c ON(p.category_id = c.id) ORDER BY p.id ASC")){
            while(rs.next()){
                Product p = getProduct(rs);
                products.add(p);
            }
        }
        return products;
    }

    @Override
    public Product byId(Long id) throws SQLException {
        Product product = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT p.*,c.name AS category FROM products AS p "
                + "INNER JOIN category AS c ON(p.category_id = c.id) WHERE p.id = ?")){
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()){
               if(rs.next()){
                   product = getProduct(rs);
               }
            }
        }
        return product;
    }

    @Override
    public void save(Product product) throws SQLException {
        String sql;
        if(product.getId() != null && product.getId() > 0){
            sql = "UPDATE products SET name=?, price=?, sku=?, category_id=? WHERE id=?";
        }
        else{
            sql = "INSERT INTO products(name, price, sku, category_id, registration_date) VALUES (?, ?, ?, ?, ?)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setString(3, product.getSku());
            stmt.setLong(4, product.getCategory().getId());

            if(product.getId() != null && product.getId() > 0){
                stmt.setLong(5, product.getId());
            } else {
                stmt.setDate(5 ,Date.valueOf(product.getRegistrationDate()));
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminate(Long id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Product getProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getInt("price"));
        p.setSku(rs.getString("sku"));
        p.setRegistrationDate(rs.getDate("registration_date").toLocalDate());
        Category c = new Category();
        c.setId(rs.getLong("category_id"));
        c.setName(rs.getString("category"));
        p.setCategory(c);
        return p;
    }
}
