package com.bizlock;


public interface BizLock {
	boolean tryLock();
	boolean unlock();
	boolean isLocked();
}
