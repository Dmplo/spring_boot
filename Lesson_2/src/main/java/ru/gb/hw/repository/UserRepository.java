package ru.gb.hw.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.hw.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "select * from users";

        return jdbc.query(sql, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                return new User(id, firstName, lastName);
            }
        });
    }

    public User findById(Long id) {
        String sql = "select * from users where id = ?";
        return jdbc.queryForObject(sql, new Object[] { id }, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                return new User(id, firstName, lastName);
            }
        });
    }

    public void save(User user) {
        String sql = "insert into users(firstName, lastName) values(?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
    }
    public void delete(Long id) {
        String sql = "delete from users where id = ?";
        jdbc.update(sql, id);
    }

    public void update(User user) {
        String sql = "update users set firstName = ?, lastName = ? where id = ?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

}
