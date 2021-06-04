package com.managment.system.Service;

import com.managment.system.Dao.DaoMapper;
import com.managment.system.Entity.*;
import com.managment.system.Util.MyUtil;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**Service层负责业务逻辑
 * */
@Service
public class MyServiceImpl implements MyService {
    /**记录登录信息
     * */
    private UserInfo userInfo;
    @Autowired
    private DaoMapper daoMapper;
    @Override
    public UserInfo getLoginUserInfo(String username,String password,HttpServletRequest request) {
        /**监控登录信息
         * */
        userLoginLog(request,username);
        this.userInfo=daoMapper.getLoginUserInfo(username,password);
        return userInfo;
    }

    @Override
    public RoloInfo[] getRoloInfo() {
        return daoMapper.getRoloInfo();
    }

    @Override
    public UserManagementInfo[] getUserInfo(int page,int limit) {
        page=(page-1)*limit;
        return daoMapper.getUserinfo(page,limit);
    }

    @Override
    public WarningInfo[] getWarningList() {
        return daoMapper.getWarningList();
    }

    @Override
    public LoginInfo[] getLoginInfoList(int page,int limit) {
        page=(page-1)*limit;
        return daoMapper.getLoginInfoList(page,limit);
    }

    @Override
    public LogInfo[] getLogInfo(int page,int limit) {
        page=(page-1)*limit;
        return daoMapper.getLogInfo(page,limit);
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        return daoMapper.getUserInfoById(id);
    }

    @Override
    public double getCPUInfo() throws SigarException {
        double cpu=MyUtil.cpu();
        if(cpu>0.9){
            daoMapper.Warning(MyUtil.getNowTime(),"警告",
                    "CPU利用率超过90%或者90%以上，请及时处理否则影响服务器正常运行");
        }
        return cpu;
    }

    @Override
    public long getMemoryInfo() throws SigarException {
        long menory=MyUtil.memory();
        if(menory>7500){
            daoMapper.Warning(MyUtil.getNowTime(),"警告",
                    "内存利用率超过7.5GB或者7.5GB以上，请及时处理否则影响服务器正常运行");
        }
        return menory;
    }

    @Override
    public Map<String,Object> getDiskInfo() throws Exception {
        Map<String,Object> map=MyUtil.file();
        DiskInfo surplusSize=(DiskInfo)map.get("surplusSize");
        if(surplusSize.getSize()<10){
            daoMapper.Warning(MyUtil.getNowTime(),"警告",
                    "磁盘可用空间不足10GB!!请及时扩展磁盘空间否则影响服务器正常运行");
        }
        return map;
    }

    @Override
    public Map<String, Object> getNetInfo() throws Exception {
        Map<String,Object> map=MyUtil.net();
        int i=0;
        while(i<map.size()){
            MyNetInfo myNetInfo=(MyNetInfo) map.get("data"+i);
            if(myNetInfo.getList().get(0)==0){
                /**MyUtil.getNowTime()获取时间
                 * */
                daoMapper.Warning(MyUtil.getNowTime(),"警告",
                        myNetInfo.getName()+" 网卡异常!!请及时扩展磁盘空间否则影响服务器正常运行");
            }
            i++;
        }
        return map;
    }

    /**用户登录记录功能模块
     * */
    @Override
    public void userLoginLog(HttpServletRequest request,String username) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){
            //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        daoMapper.userLoginLog(username,MyUtil.getNowTime(),ipAddress);
    }

    @Override
    public UserManagementInfo[] selectUserInfo(String username) {
        return daoMapper.selectUserInfo(username+"%");
    }

    @Override
    public RoloInfo[] selectRoloInfo(String rolo) {
        return daoMapper.selectRoloInfo(rolo+"%");
    }

    @Override
    public LogInfo[] selectLogInfo(String username) {
        return daoMapper.selectLog(username+"%");
    }

    @Override
    public LoginInfo[] selectLoginInfo(String username) {
        return daoMapper.selectLoginInfo(username+"%");
    }

    @Override
    public int addUserInfo(String faceicon, String username, String name,
                           String iphone, String age, String sex, String rolo, String password) {
        if(selectUserName(username)==null){
            observationLogInfo(MyUtil.getNowTime(),"添加了"+username+"用户信息","用户添加");
            return daoMapper.addUserInfo(faceicon,username,name,iphone,age,sex,rolo,password);
        }else{
            return -2;
        }
    }

    public UserInfo selectUserName(String username){
        return daoMapper.selectUserName(username);
    }

    @Override
    public int updateUserInfo(String username,String rolo,String id) {
        observationLogInfo(MyUtil.getNowTime(),"修改了"+username+"用户信息","用户修改");
        return daoMapper.updateUserInfo(username,rolo,id);
    }

    @Override
    public int delUserInfoByIds(Integer[] id) {
        String result="";
        for (int i = 0; i < id.length; i++) {
           result+= "删除了"+getUserInfoById(id[i]).getUsername()+"用户信息\n";
        }
        observationLogInfo(MyUtil.getNowTime(),result,"用户删除");
        return daoMapper.delUserInfoByIds(id);
    }

    @Override
    public int delUserInfoById(int id) {
        observationLogInfo(MyUtil.getNowTime(),"删除了"+
                getUserInfoById(id).getUsername()+"用户信息","用户删除");
        return daoMapper.delUserInfoById(id);
    }

    @Override
    public int addRoloInfo(String rolo, String power) {
        if(selectRolo(rolo)==null){
            observationLogInfo(MyUtil.getNowTime(),"添加了"+rolo+"角色信息","角色添加");
            return daoMapper.addRoloInfo(rolo,power);
        }else{
            return -2;
        }
    }

    public RoloInfo selectRolo(String rolo){
        return daoMapper.selectRolo(rolo);
    }
    @Override
    public int updateRoloInfo(String rolo, String power,String id) {
        observationLogInfo(MyUtil.getNowTime(),"修改了"+rolo+"角色信息","角色修改");
        return daoMapper.updateRoloInfo(rolo,power,id);
    }

    @Override
    public int delRoloInfoById(int id) {
        observationLogInfo(MyUtil.getNowTime(),"删除了"+
                getRoloById(id).getRolo()+"角色信息","角色删除");
        return daoMapper.delRoloInfoById(id);
    }

    public RoloInfo getRoloById(int id){
        return daoMapper.getRoloById(id);
    }
    @Override
    public int delRoloInfoByIds(Integer[] id) {
        String result="";
        for (int i = 0; i < id.length; i++) {
            result+= "删除了"+getUserInfoById(id[i]).getRolo()+"角色信息\n";
        }
        observationLogInfo(MyUtil.getNowTime(),result,"角色删除");
        return daoMapper.delRoloInfoByIds(id);
    }

    @Override
    public int menuEdit(String menuName, String menuUrl, String power, String type, String time, String isHide,String id) {
        observationLogInfo(MyUtil.getNowTime(),"修改了"+menuName+"菜单信息","菜单修改");
        type= "菜单".equals(type)?"1":"0";
        return daoMapper.menuEdit(menuName,menuUrl,power,type,time,isHide,id);
    }

    @Override
    public int delMenuById(int id) {
        observationLogInfo(MyUtil.getNowTime(),"删除了"+
                getMenuInfo(id).getAuthorityName()+"菜单信息","菜单删除");
        return daoMapper.delMenuById(id);
    }

    public MenuInfo getMenuInfo(int id){
        return daoMapper.getMenuInfoById(id);
    }

    @Override
    public int addLoginInfo(String username, String logintime, String loginIP) {
        return daoMapper.addLoginInfo(username,logintime,loginIP);
    }

    @Override
    public int editLoginInfo(String username, String logintime, String loginIP,String id) {
        return daoMapper.editLoginInfo(username,logintime,loginIP,id);
    }

    @Override
    public int delLoginInfo(int id) {
        return daoMapper.delLoginInfo(id);
    }

    @Override
    public int delLoginInfoByIds(Integer[] id) {
        return daoMapper.delLoginInfoByIds(id);
    }

    @Override
    public int delLogById(int id) {
        observationLogInfo(MyUtil.getNowTime(),"删除了"+
                getLogInfoById(id).getTime()+"时间的日志信息","日志删除");
        return daoMapper.delLogById(id);
    }

    public LogInfo getLogInfoById(int id){
        return daoMapper.getLogInfoById(id);
    }

    @Override
    public int delLogByIds(int[] id) {
        String result="";
        for (int i = 0; i < id.length; i++) {
            result+= "删除了"+getLogInfoById(id[i]).getTime()+"时间的日志信息\n";
        }
        observationLogInfo(MyUtil.getNowTime(),result,"日志删除");
        return daoMapper.delLogByIds(id);
    }

    @Override
    public MenuInfo[] getMenuInfo() {
        return daoMapper.getMenuInfo();
    }

    @Override
    public int changeMyInfo(String id, String faceicon, String username, String name, String iphone, String age, String sex, String rolo) {
        return daoMapper.changeMyInfo(id,faceicon,username,name,iphone,age,sex,rolo);
    }

    @Override
    public int changePassword(String id, String password) {
        if(userInfo.getPassword().equals(password)){
            return -2;
        }else{
            return daoMapper.changePassword(id,password);
        }
    }

    @Override
    public int selectCountUser() {
        return daoMapper.selectCountUser();
    }

    @Override
    public int selectCountLoginInfo() {
        return daoMapper.selectCountLoginInfo();
    }

    @Override
    public int selectCountLog() {
        return daoMapper.selectCountLog();
    }

    @Override
    public int addMenuInfo(String oneName, String twoName, String menuName, String menuUrl,
                           String authority, String isMenu, String isHide) {
        String authortyId="";
        switch (oneName){
            case "系统基础管理": authortyId="1";break;
            case "系统监控管理": authority="2";break;
            case "系统操作管理": authority="5";break;
        }
        switch (twoName){
            case "用户管理":authortyId+="001";break;
            case "角色管理":authortyId+="002";break;
            case "警告列表":authortyId+="001";break;
            case "实时监控":authortyId+="002";break;
            case "登录信息管理":authortyId+="001";break;
            case "操作日志管理":authortyId+="002";break;
        }
        int isMenuNum=0;
        if("是".equals(isMenu)){
            isMenuNum=1;
        }
        MenuInfo menuInfo=new MenuInfo(Integer.parseInt(authortyId+"0000"+Math.abs(new Random().nextInt())%100),
                menuName,Integer.parseInt(authortyId),
                menuUrl,"layui-icon-set",MyUtil.getNowTime().split(" ")[0],authority,0,MyUtil.getNowTime(),isMenuNum
        ,Integer.parseInt(authortyId),String.valueOf(true),isHide);

        return daoMapper.addMenuInfo(menuInfo);
    }

    /**观察者
     * 负责日志功能
     * */
    public void observationLogInfo(String time,String result,String event){
        daoMapper.observationLog(userInfo.getUsername(),userInfo.getRolo(),time,result,event);
    }
}