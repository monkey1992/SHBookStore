package com.xiaoyu.shbookstore.engine;

import java.util.List;
import java.util.Map;

import com.xiaoyu.shbookstore.domain.Brand;
import com.xiaoyu.shbookstore.domain.Product;
import com.xiaoyu.shbookstore.domain.Topic;

/**
 * 专题及品牌模块访问服务器的业务接口
 *
 */
public interface TopicAndBrandEngine {
	/**
	 * 促销快报访问服务器获取促销信息
	 * @param uri
	 * @param params
	 * @return
	 */
	List<Topic> getTopicInfoFromNet(String uri,Map<String,String> params);
	/**
	 * 专题商品列表访问服务器获取专题商品列表信息
	 * @param uri
	 * @param params
	 * @return
	 */
	
	List<Product> getTopicProductListFromNet(String uri,Map<String,String> params);
	//http://192.168.1.52:8080/RedBaby/topic/plist?page=1&pageNum=3&id=1234&orderby=sale_down
	//{"productlist":[{"id":"1200002","price":"66","marketprice":"66","name":"雅培银装a","pic":null},{"id":"1200003","price":"88","marketprice":"99","name":"优选","pic":null}],"response":"topic_productlist","list_count":3}
	/**
	 * 推荐品牌访问服务器获取品牌信息
	 * @param uri
	 * @param params
	 * @return
	 */
	List<Brand> getBrandInfosFromNet(String uri,Map<String,String> params);
	//http://192.168.1.52:8080/RedBaby/brand
	//{"response":"brand","brand":[{"value":[{"id":"1234","name":"ain","pic":null},{"id":"1234","name":"11","pic":null}],"key":"孕妇专区"},{"value":[{"id":"1234","name":"ann","pic":null},{"id":"1234","name":"1212","pic":null}],"key":"营养食品"}]}
	/**
	 * 品牌商品列表访问服务器获取品牌商品列表信息
	 * @param uri
	 * @param params
	 * @return
	 */
	List<Product> getBrandProductListFromNet(String uri,Map<String,String> params);
	/**
	 * 限时抢购访问服务器获取商品列表信息
	 * @param uri
	 * @param params
	 * @return
	 */
	List<Product> getLimitBuyProductListFromNet(String uri,Map<String,String> params);
	/**
	 * 新品上架访问服务器获取商品列表信息
	 * @param uri
	 * @param params
	 * @return
	 */
	List<Product> getNewProductListFromNet(String uri,Map<String,String> params);
	//http://192.168.1.52:8080/RedBaby/newproduct?page=1&pageNum=3&orderby=sale_down
//	{"productlist":[{"id":"1200002","price":"66","marketprice":"66","name":"雅培银装a","pic":null},{"id":"1200003","price":"88","marketprice":"99","name":"优选","pic":null}],"response":"newproduct","list_count":3
	/**
	 * 热门单品访问服务器获取商品列表信息
	 * @param uri
	 * @param params
	 * @return
	 */
	List<Product> getHotProductListFromNet(String uri,Map<String,String> params);
	//http://192.168.1.52:8080/RedBaby/hotproduct?page=1&pageNum=3&orderby=sale_down
	//{"productlist":[{"id":"1200002","price":"66","marketprice":"66","name":"雅培银装a","pic":null},{"id":"1200003","price":"88","marketprice":"99","name":"优选","pic":null}],"response":"hotproduct","list_count":3}
}
