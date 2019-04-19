package com.example.demo;

import com.netflix.appinfo.AmazonInfo;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtilsProperties properties){
        EurekaInstanceConfigBean bean = new EurekaInstanceConfigBean(new InetUtils(properties));
        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        bean.setDataCenterInfo(info);

        return bean;
    }
}
