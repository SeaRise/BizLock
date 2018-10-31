package com.bizlock;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizlock.model.LockInf;
import com.bizlock.service.LockService;

public class BizLockImpl implements BizLock {

	@Autowired
	private LockService lockService;
	
	private LockInf lockInf = null;
	
	private boolean init = false;
	
	public void init(LockInf lockInf) {
		this.lockInf = lockInf;
		init = true;
	}
	
	private void check() {
		if (!init) {
			throw new RuntimeException("biz lock is not init");
		}
	}
	
	public boolean tryLock() {
		check();
		return lockService.tryLock(lockInf);
	}

	public boolean unlock() {
		check();
		return lockService.unlock(lockInf);
	}

	public boolean isLocked() {
		check();
		return lockService.isLocked(lockInf);
	}

}
