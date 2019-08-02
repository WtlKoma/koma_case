package com.wtl.koma.enums;
/**
 * <p>Description: 订单类型枚举类</p>
 * @author WTL
 * @date 2019年8月1日
 */
public enum OrderTypeEnum {
	
	ONE(1),
	TWO(2),
	THREE(3);
	
	private Integer key;
	
	OrderTypeEnum(Integer key){
		this.key = key;
	}
	
	/**
	 * 通过key返回所对应的枚举类型（没有则返回null）
	 * @param key
	 * @return
	 */
	public static OrderTypeEnum getOrderType(Integer key){
		OrderTypeEnum[] typeEnums = OrderTypeEnum.values();
		for(OrderTypeEnum typeEnum : typeEnums){
			if (typeEnum.key.equals(key)) {
				return typeEnum;
			}
		}
		
		return null;
	}
	
	public Integer getKey(){
		return this.key;
	}
	
}

