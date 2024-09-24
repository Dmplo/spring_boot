package ru.gb.hw.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.hw.config.DbConfig;
import ru.gb.hw.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbc;
    private final DbConfig config;

    public List<User> findAll() {
        return jdbc.query(config.getFindAll(), new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                return new User(id, firstName, lastName);
            }
        });
    }

    public User findById(Long id) {
        return jdbc.queryForObject(config.getFindById(), new Object[] { id }, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                return new User(id, firstName, lastName);
            }
        });
    }

    public void save(User user) {
        jdbc.update(config.getSave(), user.getFirstName(), user.getLastName());
    }
    public void delete(Long id) {
        jdbc.update(config.getDelete(), id);
    }

    public void update(User user) {
        jdbc.update(config.getUpdate(), user.getFirstName(), user.getLastName(), user.getId());
    }

}
