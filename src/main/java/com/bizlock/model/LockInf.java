package com.bizlock.model;

public class LockInf {
	
	private String lockName;
	
	private String lockIndex;
	
	private String holder;
	
	public LockInf(String lockName, String lockIndex, String holder) {
		this.lockName = lockName;
		this.lockIndex = lockIndex;
		this.holder = holder;
	}
	
	public String getLockIndex() {
		return lockIndex;
	}

	public void setLockIndex(String lockIndex) {
		this.lockIndex = lockIndex;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}
	
}
