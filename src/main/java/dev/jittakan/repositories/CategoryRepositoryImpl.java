package dev.jittakan.repositories;

import dev.jittakan.configs.MysqlConn;
import dev.jittakan.configs.Repository;
import dev.jittakan.models.entities.Category;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RepositoryJdbc
@Repository
public class CategoryRepositoryImpl implements CrudRepository<Category> {

    private Connection conn;

    @Inject
    public CategoryRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Category> listing() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categories")){
            while(rs.next()){
                Category category = getCategories(rs);
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public Category byId(Long id) throws SQLException {
        Category category = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categories AS c WHERE c.id = ?")){
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    category = getCategories(rs);
                }
            }
        }
        return category;
    }

    @Override
    public void save(Category category) throws SQLException {

    }

    @Override
    public void eliminate(Long id) throws SQLException {

    }

    private Category getCategories(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setName(rs.getString("name"));
        category.setId(rs.getLong("id"));
        return category;
    }
}
