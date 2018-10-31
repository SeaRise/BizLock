package com.bizlock.dal.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bizlock.dal.LockDao;

/*
 * lock table
 * id bigint(20) NOT NULL AUTO_INCREMENT COMMENT,'主键'
 * biz_index varchar(128) NOT NULL,'业务锁锁标识,唯一索引'
 * holder varchar(128) NOT NULL,'业务锁持有者标识'
 * PRIMARY KEY (id)
 * **/
@Repository
public class LockDaoImpl implements LockDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String LOCK_PREFIX = "bizlock_";
	
	private final String CREATE_SQL_PREFIX = "CREATE TABLE IF NOT EXISTS ";
	
	private final String CREATE_SQL_END = 
			" (" +
			"id bigint(20) NOT NULL AUTO_INCREMENT COMMENT," +
			"biz_index varchar(128) NOT NULL," +
			"holder varchar(128) NOT NULL," +
			"PRIMARY KEY (id)," +
			"UNIQUE INDEX index_deptno(biz_index)" +
			") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
	
	private final String INSERT_SQL_PREFIX = "insert into ";
	
	private final String INSERT_SQL_END = " (biz_index, holder) values (?, ?)";
	
	private final String DELETE_SQL_PREFIX = "delete from ";
	
	private final String DELETE_SQL_END = " where biz_index = ? and holder = ?";
	
	private final String SELECT_SQL_PREFIX = "select holder from  ";
	
	private final String SELECT_SQL_END = " where biz_index = ? and holder = ?";
	
	//这个方法应该用不上,因为实际生产环境中不可能不经过审批就建表
	public void create(String lockName) {
		jdbcTemplate.execute(buildCreateSql(lockName));
	}
	
	private String buildCreateSql(String lockName) {
		StringBuilder sql = new StringBuilder();
		sql.append(CREATE_SQL_PREFIX);
		sql.append(LOCK_PREFIX);
		sql.append(lockName);
		sql.append(CREATE_SQL_END);
		return sql.toString();
	}

	public boolean insert(String lockName, String bizIndex, String holder) {
		return jdbcTemplate.update(buildInsertSql(lockName), 
				new Object[]{bizIndex, holder}) != 0;
	}
	
	private String buildInsertSql(String lockName) {
		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_SQL_PREFIX);
		sql.append(LOCK_PREFIX);
		sql.append(lockName);
		sql.append(INSERT_SQL_END);
		return sql.toString();
	}

	public boolean delete(String lockName, String bizIndex, String holder) {
		return jdbcTemplate.update(buildDeleteSql(lockName), 
				new Object[]{bizIndex, holder}) != 0;
	}
	
	private String buildDeleteSql(String lockName) {
		StringBuilder sql = new StringBuilder();
		sql.append(DELETE_SQL_PREFIX);
		sql.append(LOCK_PREFIX);
		sql.append(lockName);
		sql.append(DELETE_SQL_END);
		return sql.toString();
	}

	public boolean select(String lockName, String bizIndex,  String holder) {
		return jdbcTemplate.query(buildSelectSql(lockName),
				new Object[]{lockName, bizIndex}, 
				new RowMapper<Boolean>() {
					public Boolean mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return rowNum != 0;
					}
				}).get(0);
	}
	
	private String buildSelectSql(String lockName) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT_SQL_PREFIX);
		sql.append(LOCK_PREFIX);
		sql.append(lockName);
		sql.append(SELECT_SQL_END);
		return sql.toString();
	}
	
}
