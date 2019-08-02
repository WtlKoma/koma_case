package com.wtl.koma.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <p>Description: 配置的值</p>
 * @author WTL
 * @date 2019年7月18日
 */
@Data
@Component
@ConfigurationProperties(prefix = "conf.value")
public class ConfigurationValuePojo {
	
	/**
	 * 机器编号
	 */
	private String machineCode;
	
}

