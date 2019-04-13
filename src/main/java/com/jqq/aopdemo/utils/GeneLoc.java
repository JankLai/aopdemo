package com.jqq.aopdemo.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 选择地区工具，包含各国各地省级市级
 *
 */
public class GeneLoc {
	//各地区xml文件路径

	private static final String LOCAL_LIST_PATH = "src/main/java/com/jqq/aopdemo/sources/LocList.xml";
	//所有国家名称List
	private static final List<String> COUNTRY_REGION = new ArrayList<>();
	private SAXReader reader;
	private Document document;
	private Element rootElement;		//根元素

	
	//初始化
	public GeneLoc(){
		//1.读取
		reader = new SAXReader();
		try {
			document = reader.read(LOCAL_LIST_PATH);		
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		//2.获得根元素
		if(document==null){
			System.out.println("找不到LocList.xml文件！");
		}
		assert document != null;
		rootElement =  document.getRootElement();
		//3.初始化所有国家名称列表
		Iterator it =  rootElement.elementIterator();
		Element ele = null;
		while(it.hasNext()){
			ele = (Element)it.next();
			COUNTRY_REGION.add(ele.attributeValue("Name"));
		}
	}
	
	/**
	 *
	 * @author		LiuJinan
	 * @TODO		功能：	获取所有国家名称
	 * @time		2016-8-26 上午9:02:05
	 * @return		String[]
	 */
	public List<String> getCountry(){
		return COUNTRY_REGION;
	}
	
	/**
	 *
	 * @author		LiuJinan
	 * @TODO		功能：	根据国家名获取该国所有省份
	 * @time		2016-8-26 上午9:07:21
	 * @param countryName	国家名，从getCountry()从取出
	 * @return		List<Element>
	 */
	private List<Element> provinces(String countryName){
		Iterator it =  rootElement.elementIterator();
		List<Element> provinces = new ArrayList<Element>();
		Element ele = null;
		while(it.hasNext()){
			ele = (Element)it.next();
			COUNTRY_REGION.add(ele.attributeValue("Name"));
			if(ele.attributeValue("Name").equals(countryName)){
				provinces = ele.elements();
				break;
			}
		}
		return provinces;
	}
	
	/**
	 * 
	 * @author		LiuJinan
	 * @TODO		功能：	根据国家名获取该国所有省份
	 * @time		2016-8-26 上午9:07:21
	 * @param countryName	国家名，从getCountry()从取出
	 * @return		List<Element>
	 */
	public List<String> getProvinces(String countryName){
		List<Element> tmp = this.provinces(countryName);
		List<String> list = new ArrayList<String>();
		for(int i=0; i<tmp.size(); i++){
			list.add(tmp.get(i).attributeValue("Name"));
		}
		return list;
	}
	
	/**
	 * 
	 * @author		LiuJinan
	 * @TODO		功能：根据国家名和省份名，获取该省城市名列表
	 * @time		2016-8-26 上午9:15:24
	 * @param province
	 * @param provinceName
	 * @return
	 */
	private List<Element> cities(String countryName, String provinceName){
		List<Element> provinces =  this.provinces(countryName);
		List<Element> cities = new ArrayList<Element>();
		if(provinces==null || provinces.size()==0){		//没有这个城市
			return cities;
		}
		
		for(int i=0; i<provinces.size(); i++){
			if(provinces.get(i).attributeValue("Name").equals(provinceName)){
				cities = provinces.get(i).elements();
				break;
			}
		}
		return cities;
	}
	
	/**
	 *
	 * @author		LiuJinan
	 * @TODO		功能：根据国家名和省份名获取城市列表
	 * @time		2016-8-26 下午4:55:55
	 * @param countryName
	 * @param provinceName
	 * @return		List<String>
	 */
	public List<String> getCities(String countryName, String provinceName){
		List<Element> tmp =  this.cities(countryName, provinceName);
		List<String> cities = new ArrayList<String>();
		for(int i=0; i<tmp.size(); i++){
			cities.add(tmp.get(i).attributeValue("Name"));
		}
		return cities;
	}
	
//	public static GeneLoc getInstance(){
//		if(localutil==null){
//			localutil = new GeneLoc();
//		}
//		return localutil;
//	}
}
