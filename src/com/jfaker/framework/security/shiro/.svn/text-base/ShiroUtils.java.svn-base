/*
 *  Copyright 2014-2015 snakerflow.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.jfaker.framework.security.shiro;

import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import com.jfaker.framework.security.model.User;

/**
 * shiro工具类
 * @author yuqs
 * @since 0.1
 */
public class ShiroUtils {
	/**
	 * 返回当前登录的认证实体ID
	 * @return
	 */
	public static Integer getUserId() {
		ShiroPrincipal principal = getPrincipal();
		if(principal != null) return principal.getId();
		return -1;
	}
	
	/**
	 * 分组
	 * @return
	 */
	public static Integer getGroupId(){
		User user = getUser();
		Integer grouptype = user.getInt("grouptype");
		if(user != null && grouptype != null) return grouptype;
		return -1;
	}
	
	public static User getUser() {
		ShiroPrincipal principal = getPrincipal();
		if(principal != null) return principal.getUser();
		return null;
	}
	
	/**
	 * 返回当前登录的认证实体部门ID
	 * @return
	 */
	public static Integer getOrgId() {
		User user = getUser();
		Integer org = user.getInt("org");
		if(user != null && org != null) return org;
		return -1;
	}
	
	/**
	 *  获取用户的学院信息
	 * @Description 
	 * @author hsongjiang
	 * @date 2015年10月6日 下午8:36:39 
	 * @return
	 */
	public static Integer getAcademyId(){
		User user = getUser();
		Integer org = user.getInt("academy");
		if(user != null && org != null) return org;
		return -1;
	}
	
	/**
	 * 获取当前登录的认证实体
	 * @return
	 */
	public static ShiroPrincipal getPrincipal() {
		Subject subject = SecurityUtils.getSubject();
		return (ShiroPrincipal)subject.getPrincipal();
	}
	
	/**
	 * 获取所有组集合
	 * @return
	 */
	public static List<String> getGroups() {
		List<String> groups = new ArrayList<String>();
		Integer orgId = getOrgId();
		ShiroPrincipal principal = getPrincipal();
		if(principal != null) {
			groups.addAll(principal.getRoles());
		}
		if(orgId != null) {
			groups.add(String.valueOf(orgId));
		}
		return groups;
	}
	
	/**
	 * 获取当前认证实体的中文名称
	 * @return
	 */
	public static String getFullname() {
		ShiroPrincipal principal = getPrincipal();
		if(principal != null) return principal.toString();
		return "";
	}
	
	/**
	 * 获取当前认证实体的登录名称
	 * @return
	 */
	public static String getUsername() {
		ShiroPrincipal principal = getPrincipal();
		if(principal != null) return principal.getUsername();
		throw new RuntimeException("user's name is null.");
	}
	
	/**
	 * 获取当前认证的实体部门名称
	 * @return
	 */
	public static String getOrgName() {
		User user = getUser();
		if(user != null) return user.get("orgName");
		return "";
	}
	
	/**
	 *  获取当前用户的realname
	 */
	
	public static String getRealName() {
		User user = getUser();
		if(user != null) return user.get("realname");
		return "";		
	}
	public static String getNickName() {
		User user = getUser();
		if(user != null) return user.get("nickname");
		return "";		
	}
	
	/**
	 * 获取密码
	 * @Description 
	 * @author hsongjiang
	 * @date 2015年9月19日 上午9:20:34 
	 * @return
	 */
	public static String getPlainPassword(){
		User user = getUser();
		if(user != null) return user.getStr("plainPassword");
		return "";	
	}
	/**
	 * 
	 * @Description 获取用户grouptype
	 * @author hmilysean
	 * @date 2015年12月21日 上午10:44:36 
	 * @return
	 */
	public static int getGrouptype(){
		User user = getUser();
		if(user != null) return user.getInt("grouptype");
		return -1;
	}
	/**
	 * @category 获取登录用户邮箱 
	 * @author inging44
	 * @date 2016年1月13日 上午11:12:36 
	 * @return
	 */
	public static String getEmail(){
		User user = getUser();
		if(user != null) return user.getStr("email");
		return "";
	}
}
