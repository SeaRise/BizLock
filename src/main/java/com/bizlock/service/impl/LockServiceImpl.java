package com.bizlock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizlock.dal.LockDao;
import com.bizlock.model.LockInf;
import com.bizlock.service.LockService;

@Service
public class LockServiceImpl implements LockService {

	@Autowired
	private LockDao lockDao;
	
	public boolean tryLock(LockInf lockInf) {
		return lockDao.insert(lockInf.getLockName(), 
				lockInf.getLockIndex(), 
				lockInf.getHolder());
	}

	public boolean unlock(LockInf lockInf) {
		return lockDao.delete(lockInf.getLockName(), 
				lockInf.getLockIndex(), 
				lockInf.getHolder());
	}
	
	public boolean isLocked(LockInf lockInf) {
		return lockDao.select(lockInf.getLockName(), 
				lockInf.getLockIndex(), 
				lockInf.getHolder());
	}
	
}
