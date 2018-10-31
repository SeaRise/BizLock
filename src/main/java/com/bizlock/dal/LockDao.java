package com.bizlock.dal;


public interface LockDao {
	
	void create(String lockName);
	
	boolean insert(String lockName, String bizIndex, String holder);
	
	boolean delete(String lockName, String bizIndex, String holder);
	
	boolean select(String lockName, String bizIndex, String holder);
}
