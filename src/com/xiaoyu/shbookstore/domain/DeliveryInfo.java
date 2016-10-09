package com.xiaoyu.shbookstore.domain;
/**
 * 送货时间
 */
public class DeliveryInfo 
{
   
   /**
    * 1 => 周一至周五送货 2=> 双休日及公众假期送货 3=> 
    * 时间不限，工作日双休日及公众假期均可送货
    */
   private String type;
   
   /**
    * @roseuid 534B82720266
    */
   public DeliveryInfo() 
   {
    
   }

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}


   
}
