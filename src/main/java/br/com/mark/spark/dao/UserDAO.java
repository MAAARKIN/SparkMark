package br.com.mark.spark.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.mark.spark.model.User;
import br.com.mark.spark.repository.UserRepository;

@Repository
public class UserDAO implements UserRepository {

	private JdbcTemplate jdbcTemplate;

	public UserDAO(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public User findById(Long id) {
		String sql = "SELECT * FROM USER WHERE ID = ?";
		return (User) jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM USER";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public void save(User user) {
		String sql = "INSERT INTO USER (NAME, AGE) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[] { user.getName(), user.getAge() });
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM USER WHERE ID = ? ";
		jdbcTemplate.update(sql, new Object[] { id });
	}

	@Override
	public void update(User user) {
		String sql = "UPDATE USER SET name=?, age=? WHERE id=?";
		jdbcTemplate.update(sql, new Object[] { user.getName(), user.getAge(), user.getId() });
	}

}
