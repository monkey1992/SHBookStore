package com.xiaoyu.shbookstore.domain;
/**
 * 总计
 */
public class CheckoutAddup 
{
   
   /**
    * 商品数量总计
    */
   private int total_count;
   
   /**
    * 商品金额总计
    */
   private float total_price;
   
   /**
    * 商品积分总计
    */
   private float total_point;
   
   /**
    * 运费
    */
   private float freight;
   
   /**
    * 促销减钱
    */
   private float prom_cut;
   
   /**
    * @roseuid 534B820A024F
    */
   public CheckoutAddup() 
   {
    
   }

public int getTotal_count() {
	return total_count;
}

public void setTotal_count(int total_count) {
	this.total_count = total_count;
}

public float getTotal_price() {
	return total_price;
}

public void setTotal_price(float total_price) {
	this.total_price = total_price;
}

public float getTotal_point() {
	return total_point;
}

public void setTotal_point(float total_point) {
	this.total_point = total_point;
}

public float getFreight() {
	return freight;
}

public void setFreight(float freight) {
	this.freight = freight;
}

public float getProm_cut() {
	return prom_cut;
}

public void setProm_cut(float prom_cut) {
	this.prom_cut = prom_cut;
}
   
}
