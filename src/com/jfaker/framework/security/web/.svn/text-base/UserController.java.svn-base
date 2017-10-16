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
package com.jfaker.framework.security.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.model.Org;
import com.jfaker.framework.security.model.Role;
import com.jfaker.framework.security.model.User;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfaker.framework.security.web.validate.UserValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.bjui.BjuiRender;
import com.jfinal.plugin.captcha.pmCaptchaRender;
import com.swust.utils.DefineJsonUtils;
import com.swust.utils.EduStringUtil;

/**
 * UserController
 * 
 * @author yuqs
 * @since 0.1 hsongjiang 2015-03-17, 添加管理员可以查看每个学生的详细信息
 * 
 */
public class UserController extends CommonController {

	public static final String menuRel = "userList";
	public void index() {
		keepPara();
		String username = getPara("username","").trim();
		Integer grouptype = getParaToInt("grouptype",-1);
		setAttr("page", User.dao.paginateUserList(username,grouptype,
				getParaToInt("pageCurrent", 1),getParaToInt("pageSize", 20)));
		setAttr("username", username);
		setAttr("grouptype", grouptype);
		keepModel(User.class);
		render("userList.html");
	}

	public void add() {
		setAttr("roles", Role.dao.getAll());
		setAttr("p_org", Org.dao.getList());
		render("userAdd.html");
	}

	public void edit() {
		setAttr("user", User.dao.get(getParaToInt()));
		setAttr("p_org", Org.dao.getList());
		List<Role> roles = Role.dao.getAll();
		List<Role> rs = User.dao.getRoles(getParaToInt());
		for (Role role : roles) {
			for (Role r : rs) {
				if (role.getInt("id").intValue() == r.getInt("id").intValue()) {
					role.put("selected", 1);
				}
				if (role.get("selected") == null) {
					role.put("selected", 0);
				}
			}
		}
		setAttr("roles", roles);
		render("userEdit.html");
	}

	public void view() {
		setAttr("user", User.dao.get(getParaToInt()));
		setAttr("roles", User.dao.getRoles(getParaToInt()));
		render("userView.html");
	}

	/**
	 * 后台添加用户，其中部门只能手写。 添加用户，有个大前提：部门中一定要有家长和教师。家长用于本系统的所有家长部门，而教师下面是各个单位。
	 * 
	 * @author hsongjiang 2015-03-03
	 */
	@Before({ UserValidator.class, Tx.class })
	public void save() {
		Integer[] orderIndexs = getParaValuesToInt("orderIndexs");
		Integer groupid = getParaToInt("user.grouptype", 0);
//		
		if(getPara("user.nickname").trim().length() < 2){
			render(BjuiRender.error("昵称过短"));
			return;		
		}
		User muser = User.dao.getUser(getPara("user.username").trim());
		
		if(muser !=null){
			render(BjuiRender.error("用户名已经存在"));
			return;			
		}
		
		User memail = User.dao.getBaseInfoByEmail(getPara("user.email").trim());
		if (memail != null) {
			render(BjuiRender.error("邮箱已经被注册了"));
			return;
		}
		
		User user = new User().set("grouptype", groupid)
				.set("realname", getPara("user.realname"))
				.set("nickname", getPara("user.nickname"))
				.set("email", getPara("user.email"))
				.set("username", getPara("user.username"))
				.set("plainPassword", getPara("user.plainPassword"))
				.set("gender", getParaToInt("user.gender",0)).set("regtime", new Date());
		user.entryptPasswordWithSalt(getPara("user.plainPassword")); // 加密密码
		user.save();

		if (orderIndexs != null) {
			for (Integer orderIndex : orderIndexs) {
				User.dao.insertCascade(user.getInt("id"), orderIndex);
			}
		}
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}

	/**
	 * 将用户的密码和帐号都重新，更新
	 */
	@Before({ UserValidator.class, Tx.class })
	public void update() {
		Integer[] orderIndexs = getParaValuesToInt("orderIndexs");
		Integer id = getParaToInt("user.id");

		String plainPassword = getPara("user.plainPassword").trim();

		User user = new User().set("id", id)
				.set("username", getPara("user.username","").trim())
				.set("realname", getPara("user.realname","").trim())
				.set("email", getPara("user.email","").trim())
				.set("nickname", getPara("user.nickname","").trim())
				.set("plainPassword", plainPassword);

		user.entryptPasswordWithSalt(plainPassword); // 加密密码
		user.update();

		User.dao.deleteCascade(id);
		if (orderIndexs != null) {
			for (Integer orderIndex : orderIndexs) {
				User.dao.insertCascade(id, orderIndex);
			}
		}
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}

	@Before(Tx.class)
	
	public void delete() {
		Integer id = getParaToInt();
		User.dao.deleteCascade(getParaToInt());// 删除角色

		User.dao.deleteById(id);
		render(BjuiRender.refresh(menuRel));
	}

	/**
	 * 两级联动，获取部门
	 * 
	 */

	public void getDepartment() {
		String dataString = "";
		if (getParaToInt(0) == 0) {
			String sll = "[[0, \"请选择学院\"]]";
			renderHtml(sll);
		} else {
			List<Org> list = Org.dao.getByParent_fid(getParaToInt());
			for (int i = 0; i < list.size(); i++) {
				String tempString = list.get(i).toString() + ",";
				dataString = dataString.concat(tempString);
			}
			renderHtml(DefineJsonUtils.myJsonFormatGrade(dataString));
		}
	}

	@ActionKey("/admin/login")
	public void login() {
		render("login.html");
	}

	/**
	 * @category 实际登录
	 */
	public void dologin() {

		String error = "";
		String username = getPara("username","").trim();
		String password = getPara("password","").trim();
		String captcha = getPara("captcha","").trim();
		String t = getPara("t","").trim();
		Boolean rememberMe = getParaToBoolean("remember",false);
		if(StringUtils.isBlank(t)){
			error = "验证码为空！";
			render(BjuiRender.error(error));
			return;
		}
		if(!pmCaptchaRender.validate(this, captcha.toUpperCase(), t)){
			error = "验证码错误！";
			render(BjuiRender.error(error));
			return;
		}
		
		User user = null;

		// 1、判断用户名,密码是否为空
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			error = "用户名或密码为空";
			render(BjuiRender.error(error));
			return;
		}
		
		user = User.dao.getUser(username);
		
		// 2、用户不存在，并且用户名不为数值,直接返回。
		if (user == null && !StringUtils.isNumeric(username)) {
			error = "帐号或密码有误";
			render(BjuiRender.error(error));
			return;
		}
		
		// 3、用户存在但被禁止
		if (user != null && !user.getBoolean("enabled")) {
			error = "用户被禁止";
			render(BjuiRender.error(error));
			return;
		}
		
		
		// 获取用户相应权限及其session
		Subject subject = null;
		
		if (StringUtils.isEmpty(error)) {
			subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			token.setRememberMe(rememberMe);
			try {
				subject.login(token);
			} catch (UnknownAccountException ue) {
				token.clear();
				error = "登录失败，您输入的账号不存在";
			} catch (IncorrectCredentialsException ie) {
				ie.printStackTrace();
				token.clear();
				error = "登录失败，密码不匹配";
			} catch (RuntimeException re) {
				re.printStackTrace();
				token.clear();
				error = "登录失败";
			}
		}
		
		if (StringUtils.isEmpty(error)) {
			new User().set("id", user.getInt("id")).set("online", 1).set("logintime", new Date()).set("fromwhere", 0).update();
			SecurityUtils.getSubject().getSession().setTimeout(172800000);
			render(BjuiRender.success("登录成功！"));
		} else {
			render(BjuiRender.error(error));
			return;
		}
	}
	
	/**
	 * hsongjiang,禁止一个人和启用一个人，操作字段为sec_user 的enabled为0表示禁止，为1表示开启,禁止后，用户无法登录。教师无法查看到他
	 * 2015-03-19
	 */
	public void enable() {
		Integer id = getParaToInt(0);
		new User().set("id", id).set("enabled", true).update();
		render(DwzRender.refresh("aadasfsf"));
	}
	
	
	public void disable() {
		Integer id = getParaToInt(0);
		new User().set("id", id).set("enabled", false).update();
		render(DwzRender.refresh("aaaa"));
	}

	
	
	public void editPwd(){
		render("changepwd.html");
	}
	/**
	 * 
	 * @Description 修改密码
	 * @author Hmilysean
	 * 
	 */
	public void changePsw() {
		System.out.println("___________________修改密码______________________");
		String oldPassword  = User.dao.getPassword();
		System.out.println("oldPassword:"+oldPassword);
		if(!EduStringUtil.equals(oldPassword, getPara("oldPsw"))){
			render(BjuiRender.error("密码错误！"));
			return;
		}
		String newPassword = getPara("newPsw1");
		String newPassword2=getPara("newPsw2");
		if(!newPassword.equals(newPassword2)){
			render(BjuiRender.error("两次密码不对"));
			return ;
		}
		User newUser = new User().set("id", ShiroUtils.getUserId()).set("plainPassword", newPassword);
		newUser.entryptPasswordWithSalt(newPassword);
		newUser.update();

		render(BjuiRender.success("修改成功",true));
	}
	
	
	
	public void editInfo() {
		User user = ShiroUtils.getUser();
		System.out.println(user.get("age"));
		setAttr("user", user);
		render("userInfoEdit.html");
	}
	public void updateInfo() {
		User user = ShiroUtils.getUser();
		user.set("id", user.getInt("id"))
				.set("username", getPara("user.username","").trim())
				.set("realname", getPara("user.realname","").trim())
				.set("email", getPara("user.email","").trim())
				.set("nickname", getPara("user.nickname","").trim())
				.set("gender", getParaToInt("user.gender",0))
				.set("age", getParaToInt("user.age",0));
		user.update();
		render(BjuiRender.closeCurrentAndRefresh(menuRel));
	}
	
	/**
	 * 验证码
	 * @Description 
	 * @author hsongjiang
	 * @date 2015年9月18日 下午9:38:29
	 */
	@Clear
	public void captcha(){
		// 验证码
		String t = getPara("t");
		if(StringUtils.isBlank(t)){renderNull();return;}
		render(new pmCaptchaRender(t));
	}
	
	/**
	 * 退出
	 * @Description 
	 * @author hsongjiang
	 * @date 2015年9月19日 上午9:54:07
	 */
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		int uid = ShiroUtils.getUserId();
		
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
			new User().set("id", uid).set("online", 0).update();
		}
		redirect("/");
		
	}
}
