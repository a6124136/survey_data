package com.example.survey.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching  //Spring Framework驅動緩存的註解
@Configuration
public class CaffeineCacheConfig {
	//配置檔  至少需有個cacheManager的Bean 
	
	@Bean
	public CacheManager cacheManager(){
		
		CaffeineCacheManager cacheManager= new  CaffeineCacheManager();
		cacheManager.setCaffeine(Caffeine.newBuilder()
		.expireAfterAccess(600,TimeUnit.SECONDS)
		.initialCapacity(100)
		.maximumSize(50));
		return cacheManager;
	}
//	這配置照搬就好了  連結失效過期600秒  
//  使用的時候在方法或class上使用annotation 就好	
//  @Cacheable @cachePut 兩者都是暫存  @CacheEvict 刪除暫存
//	加在類別上 所有方法都支持 但一般只會加在方法上(因為有crud區別)
//	@Cacheable會先查是否有暫存值 有暫存資料就不進方法實作 直接回傳暫存資料
//	@cachePut不管是否有暫存，每次都會執行方法並把結果回傳後暫存

}
