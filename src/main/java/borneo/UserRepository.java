package borneo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> results = jdbcTemplate.query(sql, (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role")
        ), username);

        return results.isEmpty() ? null : results.get(0);
    }

    public void save(String username, String encodedPassword) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, 'USER')";
        jdbcTemplate.update(sql, username, encodedPassword);
    }
}