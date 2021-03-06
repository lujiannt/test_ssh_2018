package com.lj.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{
	protected int id;
	protected String orderNo;
	protected Date createTime;
	protected int userId;
	
	/*user*/
	protected User user;
	/*products*/
	protected List<OrderProduct> orderProducts;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}
	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNo=" + orderNo + ", createTime=" + createTime + ", userId=" + userId
				+ ", user=" + user + ", orderProducts=" + orderProducts + "]";
	}
	
}
