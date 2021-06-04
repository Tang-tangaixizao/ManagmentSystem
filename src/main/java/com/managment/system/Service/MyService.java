package com.managment.system.Service;

import com.managment.system.Entity.*;
import org.hyperic.sigar.SigarException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface MyService {
    UserInfo getLoginUserInfo(String username,String password,HttpServletRequest request);

    RoloInfo[] getRoloInfo();

    UserManagementInfo[] getUserInfo(int page,int limit);

    WarningInfo[] getWarningList();

    LoginInfo[] getLoginInfoList(int page,int limit);

    LogInfo[] getLogInfo(int page,int limit);

    UserInfo getUserInfoById(int id);

    double getCPUInfo() throws SigarException;

    long getMemoryInfo() throws SigarException;

    Map<String,Object> getDiskInfo() throws Exception;

    Map<String,Object> getNetInfo() throws Exception;
    void userLoginLog(HttpServletRequest request,String username);

    UserManagementInfo[] selectUserInfo(String username);

    RoloInfo[] selectRoloInfo(String rolo);

    LogInfo[] selectLogInfo(String username);

    LoginInfo[] selectLoginInfo(String username);

    int addUserInfo(String faceicon, String username, String name, String iphone, String age, String sex, String rolo, String password);

    int updateUserInfo(String username, String rolo,String id);

    int delUserInfoByIds(Integer[] id);

    int delUserInfoById(int id);

    int addRoloInfo(String rolo, String power);

    int updateRoloInfo(String rolo, String power,String id);

    int delRoloInfoById(int id);

    int delRoloInfoByIds(Integer[] id);

    int menuEdit(String menuName, String menuUrl, String power, String type, String time, String isHide,String id);

    int delMenuById(int id);

    int addLoginInfo(String username, String logintime, String loginIP);

    int editLoginInfo(String username, String logintime, String loginIP,String id);

    int delLoginInfo(int id);

    int delLoginInfoByIds(Integer[] id);

    int delLogById(int id);

    int delLogByIds(int[] id);

    MenuInfo[] getMenuInfo();

    int changeMyInfo(String id, String faceicon, String username, String name, String iphone, String age, String sex, String rolo);

    int changePassword(String id, String password);

    int selectCountUser();

    int selectCountLoginInfo();

    int selectCountLog();

    int addMenuInfo(String oneName,String twoName,String menuName,String menuUrl
    ,String authority,String isMenu,String isHide);
}
