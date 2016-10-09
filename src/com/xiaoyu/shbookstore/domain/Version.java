package com.xiaoyu.shbookstore.domain;

public class Version {
	private boolean isnew;
	private String version;
	private boolean force;
	private String url;
	
	public boolean isIsnew() {
		return isnew;
	}
	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isForce() {
		return force;
	}
	public void setForce(boolean force) {
		this.force = force;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Version [_new=" + isnew + ", version=" + version + ", force="
				+ force + ", url=" + url + "]";
	}
	
}
