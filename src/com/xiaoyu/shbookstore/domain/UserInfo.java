package com.xiaoyu.shbookstore.domain;

public class UserInfo {
	private int bonus;
	private String level;
	private String userid;
	
	private String usersession;
	private int favoritiescount;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getUsersession() {
		return usersession;
	}
	public void setUsersession(String usersession) {
		this.usersession = usersession;
	}
	public int getFavoritiescount() {
		return favoritiescount;
	}
	public void setFavoritiescount(int favoritiescount) {
		this.favoritiescount = favoritiescount;
	}
	@Override
	public String toString() {
		return "UserInfo [bonus=" + bonus + ", level=" + level + ", userId="
				+ userid + ", usersession=" + usersession
				+ ", favoritiescount=" + favoritiescount + "]";
	}
	
	
}
