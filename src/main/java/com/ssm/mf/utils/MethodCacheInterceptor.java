package com.ssm.mf.utils;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MethodCacheInterceptor
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/21 10:50
 * @Version 1.0
 */
public class MethodCacheInterceptor implements MethodInterceptor {
	
	private static final Logger log = LogManager.getLogger(MethodCacheInterceptor.class);
	private RedisUtil redisUtil;
	
	/**
	 * 不加入services名称
	 * @Param
	 * @Return 
	 */
	private List<String> targetNamesList;
	
	/**
	 * 不加入缓存的方法名称
	 * @Return
	 */
	private List<String> methodNamesList;
	
	/**
	 * 缓存默认过期时间
	 * @Return
	 */
	private String defaultCacheExpireTime;
	
	/**
	 * 初始化读取不需要加入缓存的方法名和类名
	 * @Return
	 */
	public MethodCacheInterceptor() {
		try{
			String[] targetNames = {};
			String[] methodNames = {};
			// 创建list
			targetNamesList = new ArrayList<String>(targetNames.length);
			methodNamesList = new ArrayList<String>(methodNames.length);
			Integer maxLen = targetNames.length > methodNames.length ? targetNames.length : methodNames.length;
			// 将不需要缓存的类名和方法名添加到list中
			for (int i = 0; i < maxLen; i++) {
				if (i < targetNames.length) {
					targetNamesList.add(targetNames[i]);
				}
				if (i < methodNames.length) {
					methodNamesList.add(methodNames[i]);
				}
			}
		} catch(Exception e){
			log.error(e.getMessage(), e);
		}
	}
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Object value = null;
		String targetName = methodInvocation.getThis().getClass().getName();
		String methodName = methodInvocation.getMethod().getName();
		
		// 不需要换缓存的内容
		if (!isAddCache(targetName, methodName)){
			// 执行方法返沪结果
			return methodInvocation.proceed();
		}
		Object[] arguments = methodInvocation.getArguments();
		String key = getCacheKey(targetName, methodName, arguments);
		
		try{
			// 判断是否有缓存
			if(redisUtil.exists(key)){
				return redisUtil.get(key);
			}
			// 写入缓存
			value = methodInvocation.proceed();
			if (value != null){
				final String tKey = key;
				final Object tValue = value;
				new Thread(() ->redisUtil.set(tKey, tValue,Long.parseLong(defaultCacheExpireTime))).start();
			}
		} catch(Exception e){
			log.error(e.getMessage(), e);
			if (value == null){
				return methodInvocation.proceed();
			}
		}
		return value;
	}
	
	/**
	 * 是否加入缓存
	 * @Param [targetName, methodName]
	 * @Return boolean
	 */
	private boolean isAddCache(String targetName, String methodName){
		boolean flag = true;
		if(targetNamesList.contains(targetName) || methodNamesList.contains(methodName)
			|| targetName.contains("$$EnhancerBySpringCGLIB$$")){
			flag = false;
		}
		return flag;
	}
	
	private String getCacheKey(String targetName, String methodName, Object[] arguments){
		StringBuffer sbu = new StringBuffer();
		sbu.append(targetName).append("_").append(methodName);
		if(arguments != null && arguments.length != 0){
			for(int i = 0; i < arguments.length; i++){
				sbu.append("_").append(arguments[i]);
			}
		}
		return sbu.toString();
	}
	
	public RedisUtil getRedisUtil() {
		return redisUtil;
	}
	
	public void setRedisUtil(RedisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}
	
	public List<String> getTargetNamesList() {
		return targetNamesList;
	}
	
	public void setTargetNamesList(List<String> targetNamesList) {
		this.targetNamesList = targetNamesList;
	}
	
	public List<String> getMethodNamesList() {
		return methodNamesList;
	}
	
	public void setMethodNamesList(List<String> methodNamesList) {
		this.methodNamesList = methodNamesList;
	}
	
	public String getDefaultCacheExpireTime() {
		return defaultCacheExpireTime;
	}
	
	public void setDefaultCacheExpireTime(String defaultCacheExpireTime) {
		this.defaultCacheExpireTime = defaultCacheExpireTime;
	}
}
