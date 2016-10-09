package com.xiaoyu.shbookstore.domain;
public class PaymentInfo 
{
   
   /**
    * 支付类型，1=>货到付款 2=>货到POS机
    *           3=>支付宝(待定)
    */
   private String type;
   
   /**
    * @roseuid 534B824F02DB
    */
   public PaymentInfo() 
   {
    
   }

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}


   
}
