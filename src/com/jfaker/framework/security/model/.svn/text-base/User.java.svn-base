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
package com.jfaker.framework.security.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfaker.framework.utils.Digests;
import com.jfaker.framework.utils.EncodeUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 用户模型
 * @author yuqs
 * @since 0.1
 */
public class User extends Model<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8781209142247805658L;
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	public static final User dao = new User();
	
	public Page<User> paginate (int pageNumber, int pageSize, User user) {
		StringBuilder from = new StringBuilder("from sec_user u left join sec_org o on u.org=o.id where 1=1 ");
		List<String> params = new ArrayList<String>();
		String username = user.getStr("username");
		String fullname = user.getStr("fullname");
		if(StringUtils.isNotEmpty(username)) {
			from.append(" and u.username=? ");
			params.add(username);
		}
		if(StringUtils.isNotEmpty(fullname)) {
			from.append(" and u.fullname=? ");
			params.add(fullname);
		}
		from.append(" order by id desc");
		return paginate(pageNumber, pageSize, "select u.*,o.name as orgName", from.toString(), params.toArray());
	}
	
	
	/**
	 * 
	 * @Description 
	 * @author Administrator
	 * @param name
	 * @return
	 */
	public User getBaseInfoByEmail(String email) {		
		return User.dao.findFirst("select * from sec_user where email=?",email);
	}
	 
	/**
	 * 
	 * @param id
	 * @return
	 * 用户表中org直接存的是班级,以后需要更改
	 * modify by hsongjiang
	 * 2015-3-1
	 */
	public User get(Integer id) {
		return User.dao.findFirst("select u.* , o.name as orgName from sec_user u left join sec_org o on u.org=o.id where u.id = ?", id);
	}
	
	
	/**
	 * 获取学生所有信息
	 *  @author  hsongjiang
	 *  
	 */
	
	public User getStudentInfo(Integer id) {
		return User.dao.findFirst("select u.*,o.*,g.name as orgName  from sec_user u left join sec_user_student_ext o on u.id=o.id left join sec_org g on  u.org = g.id where u.id=?", id);
	}
	
	
	/**
	 * 获取某个部门的学生信息
	 * @param orgId
	 * @return
	 * 
	 * @update by hsongjiang 2015-03-23,sec_user中部门
	 */
	public List<User> getStudentByOrg(Integer orgId) {
		String sql = "select u.*,o.name as orgName,e.parentactive,e.nation,s.totalScore,s.avgScore,s.mustAvgScore,s.acAvgScore  from sec_user u left join sec_user_student_ext e on  u.id=e.id left join sec_org o on u.org=o.id left join oa_scores s on u.id = s.uid";
		if(orgId != null && orgId > 0) {
			sql += " where o.id=" + orgId + " and u.grouptype like 'student' ";
		}
		return User.dao.find(sql);
	}

	
	/**
	 * 获取某个学生信息
	 * @param orgId
	 * @return
	 */
	public User getOneStudentByid(Integer Id) {
		String sql = "select u.*,o.name as orgName,e.*,e.id as extid  from sec_user u left join sec_user_student_ext e on  u.id=e.id left join sec_org o on u.org=o.id where u.id="+Id;

		return User.dao.findFirst(sql);
	}
	
	
	/**
	 * 手机端，通过某个班级，查找该班级学生姓名列表
	 * @param orgId:班级ID
	 * @return
	 */
	public List<User> getStudentNameByOrgMobile(Integer orgId) {
		String sql = "select u.id as id,u.realname as realname,u.gender as gender,o.name as orgName from sec_user u left join sec_org o on u.org=o.id ";
		if(orgId != null && orgId > 0) {
			sql += " where o.id=" + orgId + " and u.grouptype like 'student' ";
		}
		return User.dao.find(sql);
	}
	
	
	public List<User> getParentByOrg(Integer orgId) {
		String sql = "select u.*,o.name as orgName from sec_user u left join sec_org o on u.org=o.id ";
		if(orgId != null && orgId > 0) {
			sql += " where o.id=" + orgId + " and u.grouptype like 'parent' ";
		}
		return User.dao.find(sql);
	}
	
	
	public List<User> getByOrg(Integer orgId) {
		String sql = "select u.*,o.name as orgName from sec_user u left join sec_org o on u.org=o.id ";
		if(orgId != null && orgId > 0) {
			sql += " where u.org=" + orgId;
		}
		return User.dao.find(sql);
	}
	
	public List<Role> getRoles(Integer id) {
		return Role.dao.find("select r.* from sec_role r "
				+ "LEFT JOIN sec_role_user ru ON r.id=ru.role_id "
				+ "LEFT JOIN sec_user u ON u.id=ru.user_id "
				+ "WHERE u.id=?", id);
	}
	

	
	public void insertCascade(Integer id, Integer roleId) {
		Db.update("insert into sec_role_user (user_id, role_id) values (?,?)", id, roleId);
	}
	
	public void deleteCascade(Integer id) {
		Db.update("delete from sec_role_user where user_id = ?", id);
	}
	
	/**
	 * 根据用户ID查询该用户所拥有的权限列表
	 * @param userId
	 * @return
	 */
	public List<String> getAuthoritiesName(Integer userId) {
		String sql = "select a.name from sec_user u " + 
					" left outer join sec_role_user ru on u.id=ru.user_id " + 
					" left outer join sec_role r on ru.role_id=r.id " + 
					" left outer join sec_role_authority ra on r.id = ra.role_id " + 
					" left outer join sec_authority a on ra.authority_id = a.id " +                     
					" where u.id=? ";
		return Db.query(sql, userId);
	}
	
	/**
	 * 根据用户ID查询该用户所拥有的角色列表
	 * @param userId
	 * @return
	 */
	public List<String> getRolesName(Integer userId) {
		String sql = "select r.name from sec_user u " + 
					" left outer join sec_role_user ru on u.id=ru.user_id " + 
					" left outer join sec_role r on ru.role_id=r.id " + 
					" where u.id=? ";
		return Db.query(sql, userId);
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.set("salt", EncodeUtils.hexEncode(salt));

		byte[] hashPassword = Digests.sha1(user.getStr("plainPassword").getBytes(), salt, HASH_INTERATIONS);
		user.set("password", EncodeUtils.hexEncode(hashPassword));
	}
	
	/**
	 * 用于加密使用,前期使用明文
	 * @param user
	 * @param password
	 */
	public void entryptPasswordWithSalt(String password) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		set("salt", EncodeUtils.hexEncode(salt));

		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
		set("password", EncodeUtils.hexEncode(hashPassword));
	}	
	
	/**
	 * admin查询用户
	 * @param realname
	 * @param username
	 * @param orgId
	 * @param orgDepartment
	 * @param pageNum
	 * @param numperPage
	 * @return
	 */
	public Page<User> paginateUserList(String username,int grouptype,int pageNum,int numperPage) {
		String SQLlimitStr = ("from sec_user where 1=1");

		if(StringUtils.isNotEmpty(username)) {
			SQLlimitStr += " and username like '%" + username + "%' ";						
		}
		if(grouptype!=-1) {
			SQLlimitStr += " and grouptype="+grouptype;						
		}
		
		SQLlimitStr += " order by id asc";
		return dao.paginate(pageNum,numperPage, "select *", SQLlimitStr);
	}
	


	
	/**
	 * 根据某个学生的学号（用户名）找到真实姓名
	 * 2015-03-08
	 */
	public String getRealNameByUsername(String userName) {
		return dao.findFirst("select realname from sec_user where username=?",userName).getStr("realname");
	}

	
	public String getRealNameByUid(Integer uid) {
		return dao.findFirst("select realname from sec_user where id=?",uid).getStr("realname");
	}
	
	
	public String getOrgNameByUserName(String userName) {
		return dao.findFirst("select org from sec_user where username=?",userName).getStr("org");
	}
	


	/**
	 * @Description 检测该用户名是否存在
	 * @author inging44
	 * @param name
	 * @return 存在返回false，不存在返回true
	 */
	public User checkNickname(String name) {
		User nickname = User.dao.findFirst("select * from sec_user where nickname=?",name);
		return nickname;
		
	}
	
	/**
	 * @Description 根据电话取当前记录
	 * @author inging44
	 * @param tel
	 * @return User
	 */
	public User getUser(String username) {
		return User.dao.findFirst("select * from sec_user where username=?",username);
	}
	

	
	public User getByUid(int uid){
		String sql = "select * from sec_user where id=?";
		return findFirst(sql,uid);
	}

	/**
	 * @category 获取用户名片信息 
	 * @author inging44
	 * @date 2015年12月21日 下午1:35:46 
	 * @param uid
	 * @return
	 */
	public User getInfoCard(int uid){
		String sql = "select u.gender,u.avatar,u.nickname,u.id,u.verify,u.address,u.intrest,u.email,c.follow,c.fans,c.collect,c.topic from sec_user u "
				+ "left join fish_user_count c on u.id=c.uid where u.id="+uid;
		System.out.println(sql);
		return findFirst(sql);
	}
	
	/**
	 * @category  根据QQopenid查找用户
	 * @author inging44
	 * @date 2015年12月23日 下午1:31:33 
	 * @param openid
	 * @return
	 */
	public User getByQQ(String openid){
		String sql = "select * from sec_user where qqOpenid=?";
		return findFirst(sql,openid);
	}
	
	/**
	 * @category  根据微信WeichatOpenid查找用户
	 * @author inging44
	 * @date 2015年12月23日 下午1:32:52 
	 * @param openid
	 * @return
	 */
	public User getByWechat(String openid){
		String sql = "select * from sec_user where wechatOpenid=?";
		return findFirst(sql,openid);
	}
	
	/**
	 * @category  根据微博TOKEN查找用户
	 * @author inging44
	 * @date 2015年12月23日 下午3:10:31 
	 * @param token
	 * @return
	 */
	public User getByWeibo(String token){
		String sql = "select * from sec_user where weiboToken=?";
		return findFirst(sql,token);
	}
	
	/**
	 * 由于shiro获取密码时会出错，所以采用下面方法
	 * @Description 获取密码
	 * @author hmilysean
	 * @date 2016年1月21日 下午4:19:19 
	 * @return
	 */
	public String getPassword(){
		int uid=ShiroUtils.getUserId();
		String sql="select plainPassword from sec_user where id=?";
		User user = dao.findFirst(sql,uid);
		if(user!=null){
			return user.getStr("plainPassword");
		}
		return null;
	}
	
	
}
