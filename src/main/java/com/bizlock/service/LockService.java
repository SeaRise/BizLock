package com.bizlock.service;

import com.bizlock.model.LockInf;

public interface LockService {
	
	boolean tryLock(LockInf lockInf);
	
	boolean unlock(LockInf lockInf);
	
	boolean isLocked(LockInf lockInf);
}
