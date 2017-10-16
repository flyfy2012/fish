package com.swust.mobile.controller;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import com.jfaker.app.web.CommonController;
import com.jfaker.framework.security.model.User;
import com.jfaker.framework.security.shiro.ShiroUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.swust.model.AddressModel;
import com.swust.model.EmailCodeModel;
import com.swust.model.HarvestCommentModel;
import com.swust.model.HarvestModel;
import com.swust.model.HarvestPicturesModel;
import com.swust.model.MessageModel;
import com.swust.model.NewsModel;
import com.swust.model.SecretCodeModel;
import com.swust.model.ShoppingCartModel;
import com.swust.model.UserCountModel;
import com.swust.model.UserFollowModel;
import com.swust.utils.CreateRandom;
import com.swust.utils.EduStringUtil;
import com.swust.utils.SendEmail;
import com.swust.utils.TaobaoSdk;
import com.taobao.api.ApiException;

/**
 * 手机端用户中心所有
 * @Description 
 * @author inging44
 * @date 2015年9月5日 下午1:56:16 
 * @version V0.1
 */
public class UserControllerMobile extends CommonController {
	
	/**
	 * 修改名片信息
	 * @Description 
	 * @category 修改名片信息
	 * @author inging44
	 * @date 2015年12月17日 下午4:22:57
	 */
	public void changeInfo() {
		String nickname = getPara("nickname","");
		String birthday = getPara("birthday","");
		String gender = getPara("gender","");
		String intrest = getPara("intrest","");
		String address = getPara("district","");
		int userID = ShiroUtils.getUserId();
		if(userID == -1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;		
		}
		if(EduStringUtil.isEmpty(nickname)||EduStringUtil.isEmpty(birthday)||EduStringUtil.isEmpty(intrest)
				||EduStringUtil.isEmpty(address)){
			setAttr("error", "-1");
			setAttr("message", "信息不完整");
			renderJson();			
			return;				
		}
		new User().set("id", userID).set("nickname", nickname)
			.set("birthday", birthday)
			.set("gender", gender)
			.set("intrest", intrest)
			.set("address", address).update();
		setAttr("error", "0");
		setAttr("message","修改成功");
		renderJson();
	}

	/**
	 * @category 查看用户名片
	 * @author inging44
	 * @date 2015年12月18日 下午8:28:17
	 */
	public void userInfo(){
		int uid = getParaToInt("uid",0);
		if(uid==0){
			setAttr("error", "-1");
			setAttr("message", "参数有误");
			renderJson();			
			return;	
		}
		setAttr("info",User.dao.getInfoCard(uid));
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
	/**
	 * @category 查看用户收藏 
	 * @author inging44
	 * @date 2015年12月18日 下午8:53:47
	 */
	public void collectList(){
		int uid = getParaToInt("uid",0);
		if(uid==0){
			setAttr("error", "-1");
			setAttr("message", "参数有误");
			renderJson();			
			return;	
		}
		
		setAttr("error", "0");
		setAttr("message", "操作成功");	
		setAttr("page",NewsModel.dao.getUserCollect(uid,getParaToInt("pageNumber",1),
				getParaToInt("pageSize",20),getPara("title","")));
		renderJson();
	}

	/**
	 * @category 取消收藏资讯 
	 * @author inging44
	 * @date 2015年12月18日 下午9:33:15
	 */
	public void cancelCollect(){
		int tid = getParaToInt("tid",0);
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		if(tid==0){
			setAttr("error", "-1");
			setAttr("message", "参数有误");
			renderJson();			
			return;
		}
		Record record = Db.findFirst("select * from fish_user_collect where uid=? and tid=?",uid,tid);
		if(record==null){
			setAttr("error", "-1");
			setAttr("message", "你没收藏这个！");
			renderJson();			
			return;
		}
		Db.update("delete from fish_user_collect where uid=? and tid=?",uid,tid);
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
	
	/**
	 * @category  查看用户关注列表       flag  1,我没有关注，2互相关注，4 我关注的
	 * @author inging44
	 * @date 2015年12月19日 上午11:09:09
	 */
	public void followList(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		int userid = getParaToInt("uid",0);
		if(userid==0){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();			
			return;
		}
		List<UserFollowModel> follows = UserFollowModel.dao.findFollows(uid);
		List<UserFollowModel> fans = UserFollowModel.dao.findFans(uid);
		
		Page<UserFollowModel> page = UserFollowModel.dao.findFollowPage(userid,getParaToInt("pageNumber",1),getParaToInt("pageSize",20));
		if(page!=null){
			for(UserFollowModel fuser:page.getList()){
				int flag = 0;//默认列表用户和我无关
				Boolean fanFlag = false;
				Boolean followFlag = false;
				if(fuser.getInt("uid")== uid){
					flag = 3;
				}
				for(UserFollowModel follow:follows){
					if(follow.getInt("uid")==fuser.getInt("uid")){//判断其是否为我的关注
						followFlag = true;
						break;
					}
				}
				for(UserFollowModel fan:fans){
					if(fan.getInt("uid")==fuser.getInt("uid")){//判断其是否为我的粉丝
						fanFlag = true;
						break;
					}
				}
				if(followFlag&&fanFlag){
					flag = 2;//互相关注
				}else if(followFlag&&!fanFlag){
					flag = 4;//我关注的
				}else if(!followFlag&&fanFlag){
					flag = 1;//关注我的  
				}
				fuser.put("harvest", HarvestModel.dao.findContentByUid(fuser.getInt("uid")));
				fuser.put("flag", flag);
			}
		}
		setAttr("page",page);
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
	
	
	/**
	 * @category  查看用户粉丝列表
	 * @author inging44
	 * @date 2015年12月19日 上午11:09:09
	 */
	public void fansList(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		int userid = getParaToInt("uid",0);
		if(userid==0){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();			
			return;
		}
		List<UserFollowModel> follows = UserFollowModel.dao.findFollows(uid);
		List<UserFollowModel> fans = UserFollowModel.dao.findFans(uid);
		
		Page<UserFollowModel> page = UserFollowModel.dao.findFansPage(userid,getParaToInt("pageNumber",1),getParaToInt("pageSize",20));
		if(page!=null){
			for(UserFollowModel fuser:page.getList()){
				int flag = 0;//默认列表用户和我无关
				Boolean fanFlag = false;
				Boolean followFlag = false;
				if(fuser.getInt("uid")== uid){
					flag = 3;
				}
				for(UserFollowModel follow:follows){
					if(follow.getInt("fuid")==fuser.getInt("uid")){//判断其是否为我的关注
						followFlag = true;
						break;
					}
				}
				for(UserFollowModel fan:fans){
					if(fan.getInt("uid")==fuser.getInt("uid")){//判断其是否为我的粉丝
						fanFlag = true;
						break;
					}
				}
				if(followFlag&&fanFlag){
					flag = 2;//互相关注
				}else if(!followFlag&&fanFlag){
					flag = 1;//关注我的  
				}else if(followFlag&&!fanFlag){
					flag = 4;//我关注的
				}
				fuser.put("harvest", HarvestModel.dao.findContentByUid(fuser.getInt("uid")));
				fuser.put("flag", flag);
			}
		}
		setAttr("page",page);
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
		
	}
	
	/**
	 * @category  关注
	 * @author inging44
	 * @date 2015年12月21日 上午11:27:53
	 */
	public void follow(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		int userid = getParaToInt("uid",0);
		if(userid==0){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();			
			return;
		}
		if(User.dao.findById(userid)==null){
			setAttr("error", "-1");
			setAttr("message", "用户不存在");
			renderJson();			
			return;
		}
		UserFollowModel re = UserFollowModel.dao.findByIds(userid,uid);
		if(re==null){
			new UserFollowModel().set("uid", uid).set("fuid", userid).set("dateline", mCurrentDateTime).save();
			UserCountModel.dao.follow(uid,userid);//用户统计表 关注人数和粉丝人数变化
		}
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
	
	/**
	 * @category  取消关注
	 * @author inging44
	 * @date 2015年12月21日 上午11:28:03
	 */
	public void cancelFollow(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		int userid = getParaToInt("uid",0);
		if(userid==0){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();			
			return;
		}
		UserFollowModel re = UserFollowModel.dao.findByIds(userid,uid);
		if(re!=null){
			UserFollowModel.dao.deleteByIds(userid,uid);
			UserCountModel.dao.cancelFollow(uid,userid);//用户统计表 关注人数和粉丝人数变化
		}
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}

	/**
	 * @category  查看用户渔获列表
	 * @author inging44
	 * @date 2015年12月19日 上午11:09:09
	 */
	public void harvestList(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		int userid = getParaToInt("uid",0);
		if(userid==0){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();			
			return;
		}
		User user = User.dao.findById(userid);
		if(user==null){
			setAttr("error", "-1");
			setAttr("message", "查看用户不存在");
			renderJson();			
			return;
		}
		Page<HarvestModel> page = HarvestModel.dao.paginate(userid,getParaToInt("pageNumber",1),getParaToInt("pageSize",20));
		setAttr("avatar",user.getStr("avatar"));
		setAttr("nickname",user.getStr("nickname"));
		for(HarvestModel harvest:page.getList()){
			harvest.put("pictures",HarvestPicturesModel.dao.getPictureByHid(harvest.getInt("id")));
		}
		setAttr("page",page);
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
	
	/**
	 * @category 删除我的渔获 
	 * @author inging44
	 * @date 2015年12月21日 下午1:59:13
	 */
	
	public void delHarvest(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		int hid = getParaToInt("hid",0);
		if(hid==0){
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();			
			return;
		}
		HarvestModel harvest = HarvestModel.dao.findById(hid);
		if(harvest==null){
			setAttr("error", "-1");
			setAttr("message", "当前渔获信息不存在");
			renderJson();			
			return;
		}
		if(harvest.getInt("uid")!=uid){
			setAttr("error", "-1");
			setAttr("message", "请不要操作别人的渔获");
			renderJson();			
			return;
		}
		HarvestCommentModel.dao.deleteComment(hid);//先删除评论
		HarvestModel.dao.deleteById(hid);
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
	
	/**
	 * @category  发送邮箱验证码
	 * @author inging44
	 * @date 2016年1月13日 上午10:46:35 
	 * @throws EmailException
	 */
	public void sendEmailCode() throws EmailException{
		String email = ShiroUtils.getEmail();
		if(EduStringUtil.isEmpty(email)){
			setAttr("error", "-1");
			setAttr("message", "请先绑定邮箱");
			renderJson();
			return;
		}
		
		String code = CreateRandom.getCode();
		//发送邮件
		String subject = "【富士】-邮箱验证码";
		StringBuffer msg = new StringBuffer();
		msg.append("<p>验证码在这儿：<br/></p>");
		msg.append("&nbsp;&nbsp;&nbsp;"+code+"<br/>看清楚了就乖乖回去填验证码吧。");
		SendEmail.sendEmail(email, subject, msg.toString());
		EmailCodeModel oldCode = EmailCodeModel.dao.findById(email);
		if(oldCode!=null){
			oldCode.set("code", code).set("secretCode", "").set("createTime", mCurrentDateTime).update();
		}else{
			new EmailCodeModel().set("email", email).set("code", code).set("secretCode", "").set("createTime", mCurrentDateTime).save();
		}
		setAttr("error", "0");
		setAttr("message", "发送成功");
		renderJson();
	}
	
	/**
	 * @category 检查邮箱验证码 
	 * @author inging44
	 * @date 2016年1月13日 下午12:54:39
	 */
	public void checkEmailCode(){
		String email = ShiroUtils.getEmail();
		if(EduStringUtil.isEmpty(email)){
			setAttr("error", "-1");
			setAttr("message", "请先绑定邮箱");
			renderJson();
			return;
		}
		String code = getPara("code","").trim();
		EmailCodeModel theCode = EmailCodeModel.dao.findById(email);
		if(theCode==null){
			setAttr("error", "-1");
			setAttr("message", "请先请求邮箱验证码");
			renderJson();
			return;
		}
		theCode = EmailCodeModel.dao.findByEmailAndCode(email,code);
		if(theCode==null){
			setAttr("error", "-1");
			setAttr("message", "邮箱验证码已过期");
			renderJson();
			return;
		}
		String secretCode = CreateRandom.getCode();
		SecretCodeModel secretCodeRecord = SecretCodeModel.dao.findById(ShiroUtils.getUsername());
		if(secretCodeRecord==null){
			new SecretCodeModel().set("tel", ShiroUtils.getUsername()).set("secretcode", secretCode).save();
		}else{
			secretCodeRecord.set("secretcode", secretCode).update();
		}
		EmailCodeModel.dao.deleteById(email);
		setAttr("secretCode",secretCode);
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();
	}
	/**
	 * @category  发送短信验证码
	 * @author inging44
	 * @date 2015年12月22日 上午9:46:45 
	 * @throws ApiException
	 */
	public void sendCheckCode() throws ApiException{
		String tel = getPara("tel","").trim();
		int type = getParaToInt("type",0);
		if(EduStringUtil.isEmpty(tel)||type==0){
			setAttr("error","-1");
			setAttr("message","参数错误");
			renderJson();
			return;
		}
		Boolean result = TaobaoSdk.sendCode(tel,type);
		if(result){
			setAttr("error","0");
			setAttr("message","验证码发送成功");
		}else{
			setAttr("error","-1");
			setAttr("message","网络错误，验证码发送失败");
		}
		renderJson();
	}
	/**
	 * @category  短信验证码验证
	 * @author inging44
	 * @date 2015年12月22日 上午10:07:46 
	 * @throws ApiException
	 */
	public void checkCode() throws ApiException{
		String tel = getPara("tel","").trim();
		if(EduStringUtil.isEmpty(tel)){
			setAttr("error","-1");
			setAttr("message","参数错误");
			renderJson();
			return;
		}
		String code = getPara("code","");
		Boolean result = TaobaoSdk.checkCode(tel,code);
		if(!result){
			setAttr("error", "-1");
			setAttr("message", "验证失败");
			renderJson();
			return;
		}
		String secretCode = CreateRandom.getCode();
		SecretCodeModel tc = SecretCodeModel.dao.findById(tel);
		if(tc!=null){
			tc.set("secretcode",secretCode).set("createTime",mCurrentDateTime).update();
		}else{
			new SecretCodeModel().set("tel",tel).set("secretcode",secretCode)
				.set("createTime",mCurrentDateTime).save();
		}
		setAttr("secretCode",secretCode);
		setAttr("error", "0");
		setAttr("message", "验证成功");
		renderJson();
	}
	
	/**
	 * @category 修改密码
	 * @author inging44
	 * @date 2015年12月21日 下午5:22:40 
	 * @throws ApiException
	 */
	public void changePassword(){
		
		String username = getPara("tel","");
		String secretCode = getPara("secretCode","");
//		String oldPassword = getPara("oldPsw","").trim();
		String newPassword = getPara("newPsw","").trim();
		if (EduStringUtil.isEmpty(username)||EduStringUtil.isEmpty(newPassword)) {
			setAttr("error", "-1");
			setAttr("message", "参数错误");
			renderJson();
			return;
		}
		User user = User.dao.getUser(username);
		if (user==null) {
			setAttr("error", "-1");
			setAttr("message", "该账号尚未注册");
			renderJson();
			return;
		}
		if (EduStringUtil.isEmpty(secretCode)) {
			setAttr("error", "-1");
			setAttr("message", "secretCode为空");
			renderJson();
			return;
		}
		SecretCodeModel tc = SecretCodeModel.dao.findById(username);
		if (tc == null ||tc.get("secretcode") == null ||!tc.get("secretcode").equals(secretCode)) {
			setAttr("error", "-1");
			setAttr("message", "未进行短信验证，请先验证短信");
			renderJson();
			return;
		}
//		if(!EduStringUtil.equals(user.getStr("plainPassword"), oldPassword)){
//			setAttr("error", "-1");
//			setAttr("message", "原始密码有误");
//			renderJson();
//			return;
//		}
		if(newPassword.length() < 5) {
			setAttr("error", "-1");
			setAttr("message", "密码太短");
			renderJson();			
			return;
		}
		
		user.set("plainPassword", newPassword);
		user.entryptPasswordWithSalt(newPassword); // 加密密码
		user.update();
		SecretCodeModel.dao.deleteById(tc);
		setAttr("error", "0");
		setAttr("message","修改成功");
		renderJson();
	}
	
	/**
	 * @category  修改手机
	 * @author inging44
	 * @date 2015年12月22日 下午7:11:21
	 */
	public void changeTel(){
		User user = ShiroUtils.getUser();
		if (user==null) { 
			setAttr("error", "-1");
			setAttr("message", "需要登录");
			renderJson();
			return;
		}
		String secretCode = getPara("secretCode","");
		String username = getPara("newTel","").trim();
		if (EduStringUtil.isEmpty(secretCode)||EduStringUtil.isEmpty(username)) {
			setAttr("error", "-1");
			setAttr("message", "参数提交不完全");
			renderJson();
			return;
		}
		SecretCodeModel tc = SecretCodeModel.dao.findById(ShiroUtils.getUsername());
		if (tc == null ||tc.get("secretcode") == null ||!tc.get("secretcode").equals(secretCode)) {
			setAttr("error", "-1");
			setAttr("message", "未进行短信验证，请先验证短信");
			renderJson();
			return;
		}
		if(User.dao.getUser(username)!=null){
			setAttr("error", "-1");
			setAttr("message", "该号码已被注册");
			renderJson();
			return;
		}
		user.set("username", username).update();
		SecretCodeModel.dao.deleteById(ShiroUtils.getUsername());
		setAttr("error", "0");
		setAttr("message","修改成功");
		renderJson();
	}
	
	/**
	 * @category 修改邮箱
	 * @author inging44
	 * @throws EmailException 
	 * @date 2015年12月22日 下午7:12:00
	 */
	public void changeEmail() throws EmailException {
		User user = ShiroUtils.getUser();
		int uid = ShiroUtils.getUserId();
		if (user==null) { 
			setAttr("error", "-1");
			setAttr("message", "需要登录");
			renderJson();
			return;
		}
		String secretCode = getPara("secretCode","");
		String email = getPara("email","").trim();
		if (EduStringUtil.isEmpty(secretCode)||EduStringUtil.isEmpty(email)) {
			setAttr("error", "-1");
			setAttr("message", "参数提交不完全");
			renderJson();
			return;
		}
		SecretCodeModel tc = SecretCodeModel.dao.findById(ShiroUtils.getUsername());
		if (tc == null ||tc.get("secretcode") == null ||!tc.get("secretcode").equals(secretCode)) {
			setAttr("error", "-1");
			setAttr("message", "未进行验证，请先验证 ");
			renderJson();
			return;
		}
		if(User.dao.getBaseInfoByEmail(email)!=null){
			setAttr("error", "-1");
			setAttr("message", "该邮箱已被注册");
			renderJson();
			return;
		}
		Record newEmailRecord = Db.findFirst("select * from fish_user_change_email where uid="+ uid);
		String verify = CreateRandom.getCode();
		if(newEmailRecord!=null){
			newEmailRecord.set("newEmail",email)
				.set("createTime",mCurrentDateTime)
				.set("changed", 0)
				.set("verify", verify);
			Db.update("fish_user_change_email","uid", newEmailRecord);
		}else{
			newEmailRecord = new Record().set("uid",uid)
					.set("newEmail",email)
					.set("createTime",mCurrentDateTime)
					.set("verify", verify);
			Db.save("fish_user_change_email", newEmailRecord);
		}
		
		//TODO 邮箱内容
		//发送邮件
		String subject = "【富士】-邮箱修改确认";
		StringBuffer msg = new StringBuffer();
		String href = "http://swust.tunnel.qydev.com/swust_fish/queRenEmail?verify="+verify;
		msg.append("<p>请点击下列链接完成邮箱修改</p>");
		msg.append("<a href='"+href+"' target='_blank'>"+href+"</a>");
		msg.append("<p>不点就打死</p>");
		SendEmail.sendEmail(email, subject, msg.toString());
		SecretCodeModel.dao.deleteById(ShiroUtils.getUsername());
		setAttr("error", "0");
		setAttr("message","设置成功，前往邮箱验证");
		renderJson();
	}
	
	/**
	 * @category  检测邮箱是否更换成功
	 * @author inging44
	 * @date 2015年12月23日 上午11:15:29
	 */
	public void checkEmailChanged(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "先登录");
			renderJson();			
			return;
		}
		Record newEmailRecord = Db.findFirst("select * from fish_user_change_email where uid="+ uid);
		if(newEmailRecord==null){
			setAttr("error", "-1");
			setAttr("message", "请先提交新邮箱");
			renderJson();			
			return;
		}
		if(!newEmailRecord.getBoolean("changed")){
			setAttr("error", "-1");
			setAttr("message", "请先前往邮箱验证");
			renderJson();			
			return;
		}
		Db.delete("fish_user_change_email","uid", newEmailRecord);
		setAttr("error", "0");
		setAttr("message","邮箱更换成功");
		renderJson();
	}
	
	/**
	 * @category 修改头像
	 * @author inging44
	 * @date 2015年12月22日 下午3:02:42
	 */
	public void changeAvatar(){
		User user = ShiroUtils.getUser();
		if(user==null){
			setAttr("error", "-1");
			setAttr("message", "先登录");
			renderJson();			
			return;
		}
		String avatar = getPara("avatar","").trim();
		if(EduStringUtil.isEmpty(avatar)){
			setAttr("error", "-1");
			setAttr("message", "avatar为空");
			renderJson();			
			return;			
		}
		user.set("avatar", avatar).update();
		setAttr("error", "0");
		setAttr("message","修改成功");
		renderJson();
	}
	
	/**
	 * @category  注册时检测手机号
	 * @author inging44
	 * @date 2015年12月22日 下午3:07:12
	 */
	public void checkTel(){
		String tel = getPara("tel","").trim();
		if(EduStringUtil.isEmpty(tel)){
			setAttr("error", "-1");
			setAttr("message", "参数为空");
			renderJson();			
			return;	
		}
		if(User.dao.getUser(tel)!=null){
			setAttr("error", "-1");
			setAttr("message", "该号码已被注册");
			renderJson();			
			return;	
		}
		setAttr("error", "0");
		setAttr("message", "该号码有效");
		renderJson();
	}
	
	/**
	 * @category 手机注册 
	 * @author inging44
	 * @date 2015年12月22日 下午5:31:26
	 */
	@Before(Tx.class)
	public void reg(){
		String qqOpenid = getPara("qqOpenid","").trim();
		String wechatOpenid = getPara("wechatOpenid","").trim();
		String weiboToken = getPara("weiboToken","").trim();
		
		String username = getPara("username","").trim();
		String password = getPara("password","").trim();
		String secretCode = getPara("secretCode","");
		if(EduStringUtil.isEmpty(username)||EduStringUtil.isEmpty(password)||EduStringUtil.isEmpty(secretCode)){
			setAttr("error", "-1");
			setAttr("message", "参数为空");
			renderJson();			
			return;	
		}
		
		SecretCodeModel tc = SecretCodeModel.dao.findById(username);
		System.out.println(tc);
		if (tc == null ||tc.get("secretcode") == null ||!tc.get("secretcode").equals(secretCode)) {
			setAttr("error", "-1");
			setAttr("message", "未进行 验证，请先验证 ");
			renderJson();
			return;
		}
		
		if(User.dao.getUser(username)!=null){
			setAttr("error", "-1");
			setAttr("message", "用户名已注册");
			renderJson();
			return;
		}
		User user = new User().set("username", username).set("enabled", 1).set("grouptype", 1)
				.set("plainPassword", password).set("regtime", mCurrentDateTime);
		
		if(EduStringUtil.isNotEmpty(qqOpenid)){
			user.set("qqOpenid", qqOpenid);
		}
		if(EduStringUtil.isNotEmpty(wechatOpenid)){
			user.set("wechatOpenid", wechatOpenid);
		}
		if(EduStringUtil.isNotEmpty(weiboToken)){
			user.set("weiboToken", weiboToken);
		}
		user.entryptPasswordWithSalt(password);
		user.save();
		User.dao.insertCascade(user.getInt("id"), 2);
		new UserCountModel().set("uid",user.getInt("id")).save();
		SecretCodeModel.dao.deleteById(username);
		setAttr("error", "0");
		setAttr("message", "注册成功");
		renderJson();
	}
	/**
	 * @category  QQ绑定已注册账号
	 * @author inging44
	 * @date 2015年12月23日 下午2:39:43
	 */
	public void qqBind(){
		String qqOpenid = getPara("qqOpenid","").trim();
		String username = getPara("tel","").trim();
		String secretCode = getPara("secretCode","").trim();
		if(EduStringUtil.isEmpty(qqOpenid)||EduStringUtil.isEmpty(username)
				||EduStringUtil.isEmpty(secretCode)){
			setAttr("error", "-1");
			setAttr("message", "参数为空");
			renderJson();			
			return;	
		}
		SecretCodeModel tc = SecretCodeModel.dao.findById(username);
		if (tc == null ||tc.get("secretcode") == null ||!tc.get("secretcode").equals(secretCode)) {
			setAttr("error", "-1");
			setAttr("message", "未进行 验证，请先验证 ");
			renderJson();
			return;
		}
		User user = null;
		user = User.dao.getByQQ(qqOpenid);
		if(user!=null){
			setAttr("error", "-1");
			setAttr("message", "当前QQ已经注册关联，可直接登录");
			renderJson();			
			return;	
		}
		user = User.dao.getUser(username);
		if(user==null){
			setAttr("error", "-1");
			setAttr("message", "手机号码尚未注册");
			renderJson();			
			return;
		}
		if(EduStringUtil.isNotEmpty(user.getStr("qqOpenid"))){
			setAttr("error", "-1");
			setAttr("message", "该账号已绑定了其他QQ");
			renderJson();			
			return;
		}
		user.set("qqOpenid", qqOpenid).update();
		SecretCodeModel.dao.deleteById(username);
		setAttr("error", "0");
		setAttr("message", "绑定成功");
		renderJson();
	}
	/**
	 * @category  微信绑定已有账号
	 * @author inging44
	 * @date 2015年12月23日 下午4:08:57
	 */
	public void wechatBind(){
		String wechatOpenid = getPara("wechatOpenid","").trim();
		String username = getPara("tel","").trim();
		String secretCode = getPara("secretCode","").trim();
		if(EduStringUtil.isEmpty(wechatOpenid)||EduStringUtil.isEmpty(username)
				||EduStringUtil.isEmpty(secretCode)){
			setAttr("error", "-1");
			setAttr("message", "参数为空");
			renderJson();			
			return;	
		}
		SecretCodeModel tc = SecretCodeModel.dao.findById(ShiroUtils.getUsername());
		if (tc == null ||tc.get("secretcode") == null ||!tc.get("secretcode").equals(secretCode)) {
			setAttr("error", "-1");
			setAttr("message", "未进行 验证，请先验证 ");
			renderJson();
			return;
		}
		User user = null;
		user = User.dao.getByWechat(wechatOpenid);
		if(user!=null){
			setAttr("error", "-1");
			setAttr("message", "当前微信已经注册关联，可直接登录");
			renderJson();			
			return;	
		}
		user = User.dao.getUser(username);
		if(user==null){
			setAttr("error", "-1");
			setAttr("message", "手机号码尚未注册");
			renderJson();			
			return;
		}
		if(EduStringUtil.isNotEmpty(user.getStr("wechatOpenid"))){
			setAttr("error", "-1");
			setAttr("message", "该账号已绑定了其他微信账号");
			renderJson();			
			return;
		}
		user.set("wechatOpenid", wechatOpenid).update();
		SecretCodeModel.dao.deleteById(ShiroUtils.getUsername());
		setAttr("error", "0");
		setAttr("message", "绑定成功");
		renderJson();
	}
	
	/**
	 * @category  微博绑定已有账号
	 * @author inging44
	 * @date 2015年12月23日 下午4:10:42
	 */
	public void weiboBind(){
		String weiboToken = getPara("weiboToken","").trim();
		String username = getPara("tel","").trim();
		String secretCode = getPara("secretCode","").trim();
		if(EduStringUtil.isEmpty(weiboToken)||EduStringUtil.isEmpty(username)
				||EduStringUtil.isEmpty(secretCode)){
			setAttr("error", "-1");
			setAttr("message", "参数为空");
			renderJson();			
			return;	
		}
		SecretCodeModel tc = SecretCodeModel.dao.findById(ShiroUtils.getUsername());
		if (tc == null ||tc.get("secretcode") == null ||!tc.get("secretcode").equals(secretCode)) {
			setAttr("error", "-1");
			setAttr("message", "未进行 验证，请先验证 ");
			renderJson();
			return;
		}
		User user = null;
		user = User.dao.getByWeibo(weiboToken);
		if(user!=null){
			setAttr("error", "-1");
			setAttr("message", "当前微博已经注册关联，可直接登录");
			renderJson();			
			return;	
		}
		user = User.dao.getUser(username);
		if(user==null){
			setAttr("error", "-1");
			setAttr("message", "手机号码尚未注册");
			renderJson();			
			return;
		}
		if(EduStringUtil.isNotEmpty(user.getStr("weiboToken"))){
			setAttr("error", "-1");
			setAttr("message", "该账号已绑定了其他微博账号");
			renderJson();			
			return;
		}
		user.set("weiboToken", weiboToken).update();
		SecretCodeModel.dao.deleteById(ShiroUtils.getUsername());
		setAttr("error", "0");
		setAttr("message", "绑定成功");
		renderJson();
	}
	
	/**
	 * @category 登录 手机or邮箱
	 * @author inging44
	 * @date 2015年12月23日 下午1:16:14
	 */
	public void userLogin() {
		String error = "";
		String username = getPara("username","").trim();//用户名为手机号
		String password = getPara("password","").trim();
		String email = getPara("email","").trim();
		int from = getParaToInt("from",0);//登录来源（1：andorid客户端，2：iphone客户端）
		User user = null;

		// 1、判断用户名是否为空
		if ((EduStringUtil.isEmpty(username) && EduStringUtil.isEmpty(email))|| EduStringUtil.isEmpty(password)) {
			setAttr("error", "-1");
			setAttr("message", "账号或密码不能为空");
			renderJson();
			return;
		}

		//2、判断登录方式
		if(!EduStringUtil.isEmpty(username)) {//手机登录
			user = User.dao.getUser(username);
		} else if(!EduStringUtil.isEmpty(email)) { //邮箱登录
			user = User.dao.getBaseInfoByEmail(email);
		}	
		
		if(user == null){
			setAttr("error", "-1");
			setAttr("message", "用户不存在");
			renderJson();
			return;		
		}
		
		//邮箱登录时需要username获得,用于权限获得
		username = user.getStr("username");
		
		// 3、用户存在但被禁止
		if (!user.getBoolean("enabled")) {
			setAttr("error", "-1");
			setAttr("message", "用户被禁止");
			renderJson();
			return;
		}
		
		//必须是普通会员
		if( user.getInt("grouptype")!=1 &&user.getInt("grouptype")!=2) {
			setAttr("error","-1");
			setAttr("message","这是普通用户和经销商登录接口");
			renderJson();
			return;
		}	
		
		// 获取用户相应权限及其session
		Subject subject = null;
		if (EduStringUtil.isEmpty(error)) {
			subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			try {
				subject.login(token);
			} catch (UnknownAccountException ue) {
				token.clear();
				error = "-1";
				setAttr("message","登录失败，您输入的账号不存在");
			} catch (IncorrectCredentialsException ie) {
				ie.printStackTrace();
				token.clear();
				error = "-1";
				setAttr("message","登录失败，密码不匹配");
			} catch (RuntimeException re) {
				re.printStackTrace();
				token.clear();
				error = "-1";
				setAttr("message","密码或帐号错误");
			}
		}
		if (EduStringUtil.isEmpty(error)) {
			setAttr("user",user);
			
			// 得到sessionID
			setAttr("JSESSIONID", SecurityUtils.getSubject().getSession().getId());
			
			SecurityUtils.getSubject().getSession().setTimeout(172800000); //(2天时间)登录成功后，设置session的过期时间,对于手机直接不过期。除非自己退出.(当前采用心跳包的形式搞定)
			user.set("fromwhere", from).set("online", 1).update();//更新用户登录方式
			error = "0";
			setAttr("message","登录成功");
		} 
		setAttr("error",error);
		renderJson();
	} 
	
	/**
	 * @category  QQ登录
	 * @author inging44
	 * @date 2015年12月23日 下午3:48:49
	 */
	public void qqLogin(){
		String openid = getPara("qqOpenid","").trim();
		int from = getParaToInt("from",0);
		if(EduStringUtil.isEmpty(openid)){
			setAttr("error", "-1");
			setAttr("message","参数为空");
			renderJson();
			return;
		}
		User user = User.dao.getByQQ(openid);
		if(user==null){
			setAttr("error", "-1");
			setAttr("message","该QQ尚未绑定账号");
			renderJson();
			return;
		}
		if (!user.getBoolean("enabled")) {
			setAttr("error", "-1");
			setAttr("message", "用户被禁止");
			renderJson();
			return;
		}
		
		//必须是普通会员
		if( !user.getInt("grouptype").equals(0) &&!user.getInt("grouptype").equals(1)) {
			setAttr("error","-1");
			setAttr("message","这是普通用户和经销商登录接口");
			renderJson();
			return;
		}	
		
		String password = user.getStr("plainPassword");
		String username = user.getStr("username");
		String error = "";
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try{
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException ue) {
			token.clear();
			error = "-1";
			setAttr("message","登录失败，账号存在问题");
		} catch (IncorrectCredentialsException ie) {
			ie.printStackTrace();
			token.clear();
			error = "-1";
			setAttr("message","登录失败，账号存在问题");
		} catch (RuntimeException re) {
			re.printStackTrace();
			token.clear();
			error = "-1";
			setAttr("message","登录失败，账号存在问题");
		}
		if(EduStringUtil.isEmpty(error)){
			setAttr("user",user);
			// 得到sessionID
			setAttr("JSESSIONID", SecurityUtils.getSubject().getSession().getId());
			SecurityUtils.getSubject().getSession().setTimeout(172800000); //(2天时间)登录成功后，设置session的过期时间,对于手机直接不过期。除非自己退出.(当前采用心跳包的形式搞定)
			user.set("online", 1).set("fromwhere", from).update();//更新用户登录方式
			setAttr("message","登录成功");
			error = "0";
		}
		setAttr("error",error);
		renderJson();
	}
	
	
	/**
	 * @category  微博登录
	 * @author inging44
	 * @date 2015年12月23日 下午3:48:49
	 */
	public void weiboLogin(){
		String weiboToken = getPara("weiboToken","").trim();
		int from = getParaToInt("from",0);
		if(EduStringUtil.isEmpty(weiboToken)){
			setAttr("error", "-1");
			setAttr("message","参数为空");
			renderJson();
			return;
		}
		User user = User.dao.getByWeibo(weiboToken);
		if(user==null){
			setAttr("error", "-1");
			setAttr("message","该微博尚未绑定账号");
			renderJson();
			return;
		}
		if (!user.getBoolean("enabled")) {
			setAttr("error", "-1");
			setAttr("message", "用户被禁止");
			renderJson();
			return;
		}
		
		//必须是普通会员
		if( user.getInt("grouptype")!=0 &&user.getInt("grouptype")!=1) {
			setAttr("error","-1");
			setAttr("message","这是普通用户和经销商登录接口");
			renderJson();
			return;
		}	
		
		String password = user.getStr("plainPassword");
		String username = user.getStr("username");
		String error = "";
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try{
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException ue) {
			token.clear();
			error = "-1";
			setAttr("message","登录失败，账号存在问题");
		} catch (IncorrectCredentialsException ie) {
			ie.printStackTrace();
			token.clear();
			error = "-1";
			setAttr("message","登录失败，账号存在问题");
		} catch (RuntimeException re) {
			re.printStackTrace();
			token.clear();
			error = "-1";
			setAttr("message","登录失败，账号存在问题");
		}
		if(EduStringUtil.isEmpty(error)){
			setAttr("user",user);
			// 得到sessionID
			setAttr("JSESSIONID", SecurityUtils.getSubject().getSession().getId());
			SecurityUtils.getSubject().getSession().setTimeout(172800000); //(2天时间)登录成功后，设置session的过期时间,对于手机直接不过期。除非自己退出.(当前采用心跳包的形式搞定)
			user.set("online", 1).set("fromwhere", from).update();//更新用户登录方式
			setAttr("message","登录成功");
			error = "0";
		}
		setAttr("error",error);
		renderJson();
	}
	
	
	/**
	 * @category 微信登录
	 * @author inging44
	 * @date 2015年12月23日 下午3:48:49
	 */
	public void wechatLogin(){
		String wechatOpenid = getPara("wechatOpenid","").trim();
		int from = getParaToInt("from",0);
		if(EduStringUtil.isEmpty(wechatOpenid)){
			setAttr("error", "-1");
			setAttr("message","参数为空");
			renderJson();
			return;
		}
		User user = User.dao.getByWechat(wechatOpenid);
		if(user==null){
			setAttr("error", "-1");
			setAttr("message","该微信尚未绑定账号");
			renderJson();
			return;
		}
		if (!user.getBoolean("enabled")) {
			setAttr("error", "-1");
			setAttr("message", "用户被禁止");
			renderJson();
			return;
		}
		
		//必须是普通会员
		if( user.getInt("grouptype")!=0 &&user.getInt("grouptype")!=1) {
			setAttr("error","-1");
			setAttr("message","这是普通用户和经销商登录接口");
			renderJson();
			return;
		}	
		
		String password = user.getStr("plainPassword");
		String username = user.getStr("username");
		String error = "";
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		try{
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException ue) {
			token.clear();
			error = "-1";
			setAttr("message","登录失败，账号存在问题");
		} catch (IncorrectCredentialsException ie) {
			ie.printStackTrace();
			token.clear();
			error = "-1";
			setAttr("message","登录失败，账号存在问题");
		} catch (RuntimeException re) {
			re.printStackTrace();
			token.clear();
			error = "-1";
			setAttr("message","登录失败，账号存在问题");
		}
		if(EduStringUtil.isEmpty(error)){
			setAttr("user",user);
			// 得到sessionID
			setAttr("JSESSIONID", SecurityUtils.getSubject().getSession().getId());
			SecurityUtils.getSubject().getSession().setTimeout(172800000); //(2天时间)登录成功后，设置session的过期时间,对于手机直接不过期。除非自己退出.(当前采用心跳包的形式搞定)
			user.set("online", 1).set("fromwhere", from).update();//更新用户登录方式
			setAttr("message","登录成功");
			error = "0";
		}
		setAttr("error",error);
		renderJson();
	}
	/**
	 * @category  退出登录
	 * @author inging44
	 * @date 2015年12月21日 下午2:33:11
	 */
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		int uid = ShiroUtils.getUserId();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
			new User().set("id", uid).set("online", 0).update();
		}
		setAttr("error", "0");
		setAttr("message","退出成功");
		renderJson();
	}
	
	/**
	 * @category  用户收货地址列表
	 * @author inging44
	 * @date 2015年12月21日 下午3:23:25
	 */
	public void addressList(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		setAttr("address",AddressModel.dao.getByUid(uid));
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();	
	}
	
	/**
	 * @category  添加收货地址
	 * @author inging44
	 * @date 2015年12月21日 下午4:20:34
	 */
	public void addAddr(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		String province = getPara("province","").trim();
		String city = getPara("city","").trim();
		String district = getPara("district","").trim();
		String street = getPara("street","").trim();
		Boolean isDefault = getParaToBoolean("isDefault",false);
		if(EduStringUtil.isEmpty(province)||EduStringUtil.isEmpty(city)
				||EduStringUtil.isEmpty(district)||EduStringUtil.isEmpty(street)){
			setAttr("error", "-1");
			setAttr("message", "地址不完整");
			renderJson();			
			return;
		}
		String zipCode = getPara("zipCode","").trim();
		String name = getPara("name","").trim();
		String tel = getPara("tel","").trim();
		if(EduStringUtil.isEmpty(zipCode)||EduStringUtil.isEmpty(name)||EduStringUtil.isEmpty(tel)){
			setAttr("error", "-1");
			setAttr("message", "信息提交不完整");
			renderJson();			
			return;
		}
		
		//若当前地址为默认，取消之前的所有默认
		if(isDefault){
			AddressModel.dao.setAllNotDefault(uid);
		}
		new AddressModel().set("name", name)
			.set("tel", tel)
			.set("uid", uid)
			.set("province", province)
			.set("city", city)
			.set("district", district)
			.set("street", street)
			.set("zipCode", zipCode)
			.set("isDefault", isDefault)
			.save();
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();	
	}
	
	/**
	 * @category  编辑收货地址
	 * @author inging44
	 * @date 2015年12月21日 下午4:33:06
	 */
	public void editAddr(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		int aid = getParaToInt("aid",0);
		AddressModel address = AddressModel.dao.findById(aid);
		if(address==null){
			setAttr("error", "-1");
			setAttr("message", "请选择有效地址");
			renderJson();			
			return;
		}
		if(address.getInt("uid")!=uid){
			setAttr("error", "-1");
			setAttr("message", "请勿操作他人地址");
			renderJson();			
			return;
		}
		String province = getPara("province","").trim();
		String city = getPara("city","").trim();
		String district = getPara("district","").trim();
		String street = getPara("street","").trim();
		Boolean isDefault = getParaToBoolean("isDefault",false);
		if(EduStringUtil.isEmpty(province)||EduStringUtil.isEmpty(city)
				||EduStringUtil.isEmpty(district)||EduStringUtil.isEmpty(street)){
			setAttr("error", "-1");
			setAttr("message", "地址不完整");
			renderJson();			
			return;
		}
		String zipCode = getPara("zipCode","").trim();
		String name = getPara("name","").trim();
		String tel = getPara("tel","").trim();
		if(EduStringUtil.isEmpty(zipCode)||EduStringUtil.isEmpty(name)||EduStringUtil.isEmpty(tel)){
			setAttr("error", "-1");
			setAttr("message", "信息提交不完整");
			renderJson();			
			return;
		}
		
		//若当前地址为默认，取消之前的所有默认
		if(isDefault){
			AddressModel.dao.setAllNotDefault(uid);
		}
		address.set("name", name)
			.set("tel", tel)
			.set("province", province)
			.set("city", city)
			.set("district", district)
			.set("street", street)
			.set("zipCode", zipCode)
			.set("isDefault", isDefault)
			.update();
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();	
	}
	
	/**
	 * @category  删除收货地址
	 * @author inging44
	 * @date 2015年12月21日 下午4:32:49
	 */
	public void delAddr(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		int aid = getParaToInt("aid",0);
		AddressModel address = AddressModel.dao.findById(aid);
		if(address==null){
			setAttr("error", "-1");
			setAttr("message", "请选择有效地址");
			renderJson();			
			return;
		}
		if(address.getInt("uid")!=uid){
			setAttr("error", "-1");
			setAttr("message", "请勿操作他人地址");
			renderJson();			
			return;
		}
		
		AddressModel.dao.deleteById(aid);
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();	
	}
	
	/**
	 * @category  设置默认收货地址
	 * @author inging44
	 * @date 2015年12月21日 下午5:18:54
	 */
	public void setDefAddr(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", "-1");
			setAttr("message", "请先登录");
			renderJson();			
			return;
		}
		int aid = getParaToInt("aid",0);
		AddressModel address = AddressModel.dao.findById(aid);
		if(address==null){
			setAttr("error", "-1");
			setAttr("message", "请选择有效地址");
			renderJson();			
			return;
		}
		if(address.getInt("uid")!=uid){
			setAttr("error", "-1");
			setAttr("message", "请勿操作他人地址");
			renderJson();			
			return;
		}
		AddressModel.dao.setAllNotDefault(uid);
		address.set("isDefault", 1).update();
		setAttr("error", "0");
		setAttr("message", "操作成功");
		renderJson();	
	}
	
	/**
	 * @category 用户统计信息
	 * @author inging44
	 * @date 2016年1月11日 下午5:45:02
	 */
	 public void getUserCount(){
		 int uid = ShiroUtils.getUserId();
		 if(uid==-1){
			 setAttr("error","-1");
			 setAttr("message","未登录");
			 renderJson();
			 return;
		 }
		 UserCountModel count = UserCountModel.dao.findById(uid);
		 count.put("shoppingCarGoodsNum", ShoppingCartModel.dao.getNumByUid(uid));
		 setAttr("count",count);
		 setAttr("error","0");
		 setAttr("message","操作成功");
		 renderJson();
	 }
	 /**
	  * @category 获取消息
	  * @Description 
	  * @author hmilysean
	  * @date 2016年1月25日 下午5:12:44
	  */
	 public void getMessage(){
		int uid = ShiroUtils.getUserId();
		if(uid==-1){
			setAttr("error", -1);
			setAttr("message", "请登录后在试");
			renderJson();
			return;
		}
		Page<MessageModel> messages = MessageModel.dao.getMymessage(uid,getParaToInt("pageNumber",1),getParaToInt("pageSize",20));
		setAttr("page", messages);
		setAttr("error",0);
		setAttr("message", "操作成功");
		renderJson();
	 }
	 
	 
}