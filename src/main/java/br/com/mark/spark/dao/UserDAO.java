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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM USER";
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
		System.out.println("total"+users.size());
		System.out.println(users);
		
		
//		List<User> users = new ArrayList<User>();
		
//		List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql);
//		
//		for (Map<String, Object> row : rows) {
//			User user= new User();
//			user.setName((String)row.get("NAME"));
//			user.setAge((Integer)row.get("AGE"));
//			users.add(user);
//		}
		
		return users;
	}

	@Override
	public void save(User user) {
		String sql = "INSERT INTO CUSTOMER (NAME, AGE) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[]{ user.getName(), user.getAge() });
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

}
