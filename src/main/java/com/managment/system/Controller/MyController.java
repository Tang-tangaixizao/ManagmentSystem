package com.managment.system.Controller;

import com.managment.system.Entity.*;
import com.managment.system.Service.MyService;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**Controller负责与前端对接
 * */
@Controller
public class MyController {
    @Autowired
    private MyService myService;
    private HttpServletRequest request;

    /**获取登录用户信息
     * */
    @RequestMapping(value="/getloginuserinfo",method = RequestMethod.POST)
    @ResponseBody
    public JsonFormat getLoginUserInfo(HttpServletRequest request, HttpServletResponse response){
        this.request=request;
        //用户名
        String name = request.getParameter("username");
        //密码
        String password = request.getParameter("password");
        request.getSession().setAttribute("name",name);
        request.getSession().setAttribute("password",password);
            //记住用户名、密码功能(注意：cookie存放密码会存在安全隐患)
            String remFlag = request.getParameter("rememberme");
            if("1".equals(remFlag)){ //"1"表示用户勾选记住密码
                String loginInfo = name+"-"+password;
                Cookie userCookie=new Cookie("loginInfo",loginInfo);

                userCookie.setMaxAge(30*24*60*60);   //存活期为一个月 30*24*60*60
                userCookie.setPath("/");
                response.addCookie(userCookie);
            }
        UserInfo[] userInfos= {myService.getLoginUserInfo(name, password,request)};
        return new JsonFormat(0, "", 50, userInfos);
    }

    /**根据ID获取登录用户信息
     * */
    @RequestMapping(value = "/getuserinfobyid",method = RequestMethod.POST)
    @ResponseBody
    public UserInfo getUserInfoById(int id){
        return myService.getUserInfoById(id);
    }

    /**获取用户信息
     * */
    @RequestMapping("/getuserinfo")
    @ResponseBody
    public JsonFormat getUserInfo(HttpServletRequest request, HttpServletResponse response){
        int page=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        return new JsonFormat(0, "", myService.selectCountUser(), myService.getUserInfo(page,limit));
    }
    /**获取角色信息
     * */
    @RequestMapping("/getrolo")
    @ResponseBody
    public JsonFormat getRoloInfo(){
        return new JsonFormat(0, "", 50, myService.getRoloInfo());
    }

    /**获取警告列表
     * */
    @RequestMapping("/getwarninglist")
    @ResponseBody
    public JsonFormat getWarningList(){
        return new JsonFormat(0, "", 50,myService.getWarningList());
    }

    /**获取登录信息列表
     * */
    @RequestMapping("/getlogininfolist")
    @ResponseBody
    public JsonFormat getLoginInfoList(HttpServletRequest request, HttpServletResponse response){
        int page=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        return new JsonFormat(0, "", myService.selectCountLoginInfo(),myService.getLoginInfoList(page,limit));
    }

    /**获取log信息
     * */
    @RequestMapping("/getloginfo")
    @ResponseBody
    public JsonFormat getLogInfo(HttpServletRequest request, HttpServletResponse response){
        int page=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        return new JsonFormat(0, "", myService.selectCountLog(),myService.getLogInfo(page,limit));
    }

    /**获取CPU使用率
     * */
    @RequestMapping("/getCPUinfo")
    @ResponseBody
    public Map<String, Object> getCPUInfo() throws SigarException {
        Date date = new Date( );
        SimpleDateFormat time =
                new SimpleDateFormat ("hh:mm:ss");
        Map<String,Object> map=new HashMap<>();

        map.put("data",myService.getCPUInfo()*100);
        map.put("time",time.format(date));
        map.put("status",true);
        return map;
    }

    /**获取内存信息
     * */
    @RequestMapping("/getmemoryinfo")
    @ResponseBody
    public Map<String,Object> getMemoryInfo() throws SigarException {
        Date date = new Date( );
        SimpleDateFormat time =
                new SimpleDateFormat ("hh:mm:ss");
        Map<String,Object> map=new HashMap<>();
        map.put("data", myService.getMemoryInfo());
        map.put("time",time.format(date));
        map.put("status",true);
        return map;
    }

    /**获取磁盘信息
     * */
    @RequestMapping("/getdiskinfo")
    @ResponseBody
    public Map<String,Object> getDiskInfo() throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("data",myService.getDiskInfo());
        map.put("status",true);
        return map;
    }

    /**获取网卡信息
     * */
    @RequestMapping("/getnetinfo")
    @ResponseBody
    public Map<String,Object> getNetInfo() throws Exception {
        return myService.getNetInfo();
    }

    /**退出操作
     * */
    @RequestMapping("/signout")
    public void signOut(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        try {
            response.sendRedirect(request.getContextPath()+"/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**查询用户信息
     * */
    @RequestMapping("/selectuserinfo")
    @ResponseBody
    public UserManagementInfo[] selectUserInfo(String username){
        username=username==""?"null":username;
        return myService.selectUserInfo(username);
    }

    /**查询角色信息
     * */
    @RequestMapping("/selectroloinfo")
    @ResponseBody
    public RoloInfo[] selectRoloInfo(String rolo){
        rolo=rolo==""?"null":rolo;
        return myService.selectRoloInfo(rolo);
    }

    /**查询日志信息
     * */
    @RequestMapping("/selectlog")
    @ResponseBody
    public LogInfo[] selectLogInfo(String username){
        username=username==""?"null":username;
        return myService.selectLogInfo(username);
    }

    /**查询登录信息
     * */
    @RequestMapping("/selectlogin")
    @ResponseBody
    public LoginInfo[] selectLoginInfo(String username){
        username=username==""?"null":username;
        return myService.selectLoginInfo(username);
    }

    /**添加用户
     * */
    @RequestMapping("/adduserinfo")
    @ResponseBody
    public int addUserInfo(String faceicon,String username,String name,String iphone,
                           String age,String sex,String rolo,String password){
        return myService.addUserInfo(faceicon,username,name,iphone,age,sex,rolo,password);
    }

    /**编辑用户信息
     * */
    @RequestMapping("/edituserinfo")
    @ResponseBody
    public int editUserInfo(String username, String rolo,String id){
        return myService.updateUserInfo(username,rolo,id);
    }

    /**删除指定用户
     * */
    @RequestMapping("/deluserinfobyid")
    @ResponseBody
    public int delUserInfoById(int id){
        return myService.delUserInfoById(id);
    }

    /**批量删除指定用户
     * */
    @RequestMapping("/deluserinfobyids")
    @ResponseBody
    public int delUserInfoByIds(Integer[] id){
        return myService.delUserInfoByIds(id);
    }

    /**添加角色
     * */
    @RequestMapping("/addroloinfo")
    @ResponseBody
    public int addRoloInfo(String rolo,String power){
        return myService.addRoloInfo(rolo,power);
    }

    /**编辑角色信息
     * */
    @RequestMapping("/editroloinfo")
    @ResponseBody
    public int editRoloInfo(String rolo,String power,String id){
        return myService.updateRoloInfo(rolo,power,id);
    }

    /**删除指定角色
     * */
    @RequestMapping("/delroloinfobyid")
    @ResponseBody
    public int delRoloInfoById(int id){
        return myService.delRoloInfoById(id);
    }

    /**批量删除指定角色
     * */
    @RequestMapping("/delroloinfobyids")
    @ResponseBody
    public int delRoloInfoByIds(Integer[] id){
        return myService.delRoloInfoByIds(id);
    }

    /**获取菜单信息
     * */
    @RequestMapping("/getmenuinfo")
    @ResponseBody
    public JsonFormat getMenuInfo(){
        return new JsonFormat( 0, "", 50,myService.getMenuInfo());
    }

    /**菜单编辑
     * */
    @RequestMapping("/menuedit")
    @ResponseBody
    public int menuEdit(String menuName,String menuUrl,
                        String power,String type,String time,String isHide,String id){
        return myService.menuEdit(menuName,menuUrl,power,type,time,isHide,id);
    }

    /**菜单删除
     * */
    @RequestMapping("/delmenubyid")
    @ResponseBody
    public int delMenuById(int id){
        return myService.delMenuById(id);
    }

    /**添加登录信息
     * */
    @RequestMapping("/addlogininfo")
    @ResponseBody
    public int addLoginInfo(String username,String logintime,String loginIP){
        return myService.addLoginInfo(username,logintime,loginIP);
    }

    /**编辑登录信息
     * */
    @RequestMapping("/editlogininfo")
    @ResponseBody
    public int editLoginInfo(String username,String logintime,String loginIP,String id){
        return myService.editLoginInfo(username,logintime,loginIP,id);
    }

    /**删除指定登录信息
     * */
    @RequestMapping("/dellogininfo")
    @ResponseBody
    public int delLoginInfo(int id){
        return myService.delLoginInfo(id);
    }

    /**批量删除指定登录信息
     * */
    @RequestMapping("/dellogininfobyids")
    @ResponseBody
    public int delLoginInfoByIds(Integer[] id){
        return myService.delLoginInfoByIds(id);
    }

    /**删除指定日志信息
     * */
    @RequestMapping("/dellogbyid")
    @ResponseBody
    public int delLogById(int id){
        return myService.delLogById(id);
    }

    /**批量删除指定日志信息
     * */
    @RequestMapping("/dellogbyids")
    @ResponseBody
    public int delLogByIds(int[] id){
        return myService.delLogByIds(id);
    }

    /**更新个人信息
     * */
    @RequestMapping("/changemyinfo")
    @ResponseBody
    public int changeMyInfo(String id,String faceicon,String username,String name,
                            String iphone,String age,String sex,String rolo){
        return myService.changeMyInfo(id,faceicon,username,name,iphone,age,sex,rolo);
    }

    /**修改密码功能
     * */
    @RequestMapping("/changepassword")
    @ResponseBody
    public int changePasssword(String id,String password){
        int result=myService.changePassword(id,password);
        return result;
    }

    /**添加菜单信息
     * */
    @RequestMapping("/addmenuinfo")
    @ResponseBody
    public int addMenuInfo(String oneName,String twoName,String menuName,String menuUrl,
                           String authority,String isMenu,String isHide){

        return myService.addMenuInfo(oneName, twoName, menuName, menuUrl, authority, isMenu, isHide);
    }
}