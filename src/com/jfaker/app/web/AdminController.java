package com.jfaker.app.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.jfaker.framework.security.model.Menu;
import com.jfaker.framework.security.model.User;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfaker.framework.security.web.UserController;
import com.swust.model.NewsModel;

/**
 * 
 * @author Hsongjiang
 * @date 2014-12-12
 * @自定义菜单标签处理类。 根据当前认证实体获取允许访问的所有菜单
 * 
 *              2015-01-12 教师如果没有部门需要选择
 */
public class AdminController extends CommonController {

	public void index() {
		/**
		 * hsongjiang 2015-03-13 big bug,第一次登录老是存在自动带上jsessiod的情况，导致报错，正常情况是，如果
		 * 没有登录，这个地方是不允许进入的。所有现在在这个地方判断是否正的登录了
		 * 
		 */
		if (ShiroUtils.getPrincipal() == null) {
//			render("login.html");
			new UserController().logout();
		//	redirect("/");
			return;
		}
		//系统关闭时只允许管理员登陆
//		AnnouncementModel announcement = AnnouncementModel.dao.get();
//		if(announcement!=null&&ShiroUtils.getUserId()!=1){
//			redirect("/tech/announcement/view/"+announcement.getInt("id"));
//			return;
//		}
		String username = ShiroUtils.getUsername();
		String realname = ShiroUtils.getRealName();
		User user = User.dao.getUser(username);

		// 获取所有可允许访问的菜单列表
		List<Menu> menus = getAllowedAccessMenu();
		// 循环迭代菜单列表，构成ID、List结构的Map
		Map<Integer, List<Menu>> menuMaps = buildMenuTreeMap(menus);
   
		List<Menu> treeFolders = menuMaps.get(Menu.ROOT_MENU);

		setAttr("menuMaps", menuMaps);
		setAttr("treeFolders", treeFolders);
		setAttr("realname", realname);
		if (StringUtils.equals(user.getStr("avatar"), "")) {
			setAttr("avatar", "dwz/themes/default/images/avatar_0.jpg");
		} else {
			setAttr("avatar", user.get("avatar"));
		}

		// 获取所有节点的列表信息

		render("index.html");

	}

	/**
	 * 获取当前登录账号所有允许访问的菜单列表
	 * 
	 * @return
	 */
	private List<Menu> getAllowedAccessMenu() {
		return Menu.dao.getAllowedAccessMenus(ShiroUtils.getUserId());
	}

	/**
	 * 循环迭代菜单列表，构成ID、List结构的Map
	 * 
	 * @param menus
	 * @return
	 */
	private Map<Integer, List<Menu>> buildMenuTreeMap(List<Menu> menus) {
		Map<Integer, List<Menu>> menuMap = new TreeMap<Integer, List<Menu>>();
		for (Menu menu : menus) {
			/**
			 * 判断是否有上一级菜单，如果有，则添加到上一级菜单的Map中去 如果没有上一级菜单，把该菜单作为根节点
			 */
			Integer parentMenuId = menu.getInt("parent_menu") == null ? Menu.ROOT_MENU
					: menu.getInt("parent_menu");
			if (!menuMap.containsKey(parentMenuId)) {
				List<Menu> subMenus = new ArrayList<Menu>();
				subMenus.add(menu);
				menuMap.put(parentMenuId, subMenus);
			} else {
				List<Menu> subMenus = menuMap.get(parentMenuId);
				subMenus.add(menu);
				menuMap.put(parentMenuId, subMenus);
			}
		}
		return menuMap;
	}

	/**
	 * 后台首页展示内容
	 * @Description 
	 * @author hsongjiang
	 * @date 2015年9月16日 下午10:04:49
	 */
	public void main(){
		
		List<NewsModel> newsnot = null;//NewsModel.dao.find("select * from oa_news where catid=? order by id desc limit 3",1);

		setAttr("newsNotes",newsnot);
		render("index_layout.html");
	}

}
