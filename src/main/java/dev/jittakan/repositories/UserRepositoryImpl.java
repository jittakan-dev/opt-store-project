package dev.jittakan.repositories;

import dev.jittakan.configs.MysqlConn;
import dev.jittakan.configs.Repository;
import dev.jittakan.models.entities.User;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RepositoryJdbc
public class UserRepositoryImpl implements UserRepository {

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<User> listing() throws SQLException {
        return null;
    }

    @Override
    public User byId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void save(User user) throws SQLException {

    }

    @Override
    public void eliminate(Long id) throws SQLException {

    }

    @Override
    public User byUsername(String username) throws SQLException {
        User user = null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from users where username=?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                }
            }
        }
        return user;
    }
}
