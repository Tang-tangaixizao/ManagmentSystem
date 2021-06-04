package com.managment.system.Dao;

import com.managment.system.Entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**Dao层负责链接数据库
 * */
@Mapper
public interface DaoMapper {
    UserInfo getLoginUserInfo(@Param("username") String username,@Param("password") String password);

    RoloInfo[] getRoloInfo();

    UserManagementInfo[] getUserinfo(@Param("page") int page,@Param("limit") int limit);

    WarningInfo[] getWarningList();

    LoginInfo[] getLoginInfoList(@Param("page") int page,@Param("limit") int limit);

    LogInfo[] getLogInfo(@Param("page") int page,@Param("limit") int limit);

    UserInfo getUserInfoById(@Param("id") int id);

    void userLoginLog(@Param("username") String username,@Param("logintime") String logintime,@Param("loginIP")String loginIP);

    UserManagementInfo[] selectUserInfo(@Param("username")String username);

    RoloInfo[] selectRoloInfo(@Param("rolo") String rolo);
    LogInfo[] selectLog(@Param("username")String username);
    LoginInfo[] selectLoginInfo(@Param("username")String username);

    int addUserInfo(@Param("faceicon") String faceicon,@Param("username") String username,
                    @Param("name") String name,@Param("iphone") String iphone,
                    @Param("age") String age,@Param("sex") String sex,
                    @Param("rolo") String rolo,@Param("password") String password);

    int updateUserInfo(@Param("username") String username,
                       @Param("rolo") String rolo,
                       @Param("id") String id);

    int delUserInfoByIds(Integer[] id);

    int delUserInfoById(@Param("id") int id);

    int addRoloInfo(@Param("rolo") String rolo,@Param("power") String power);

    int updateRoloInfo(@Param("rolo") String rolo,@Param("power") String power,@Param("id")String id);

    int delRoloInfoById(@Param("id") int id);

    int delRoloInfoByIds(Integer[] id);

    int menuEdit(@Param("menuName") String menuName,
                 @Param("menuUrl") String menuUrl,
                 @Param("power") String power,
                 @Param("type") String type,
                 @Param("time") String time,
                 @Param("isHide") String isHide,
                 @Param("id")String id);

    int delMenuById(@Param("id") int id);

    int addLoginInfo(@Param("username") String username,
                     @Param("logintime") String logintime,
                     @Param("loginIP") String loginIP);

    int editLoginInfo(@Param("username") String username,
                      @Param("logintime") String logintime,
                      @Param("loginIP") String loginIP,
                      @Param("id")String id);

    int delLoginInfo(@Param("id") int id);

    int delLoginInfoByIds( Integer[] id);

    int delLogById(@Param("id") int id);

    int delLogByIds(@Param("id") int[] id);

    void Warning(@Param("time")String time,@Param("type") String type,@Param("explain")String explain);

    MenuInfo[] getMenuInfo();

    void observationLog(@Param("username") String username,
                        @Param("rolo") String rolo,
                        @Param("time") String time,
                        @Param("result") String result,
                        @Param("event") String event);

    RoloInfo getRoloById(@Param("id") int id);

    LogInfo getLogInfoById(@Param("id") int id);

    MenuInfo getMenuInfoById(@Param("id")int id);

    int changeMyInfo(@Param("id") String id,
                     @Param("faceicon") String faceicon,
                     @Param("username") String username,
                     @Param("name") String name,
                     @Param("iphone") String iphone,
                     @Param("age") String age,
                     @Param("sex") String sex,
                     @Param("rolo") String rolo);

    int changePassword(@Param("id") String id,@Param("password") String password);

    UserInfo selectUserName(@Param("username") String username);

    RoloInfo selectRolo(@Param("rolo") String rolo);

    int selectCountUser();

    int selectCountLoginInfo();

    int selectCountLog();

    int addMenuInfo(MenuInfo menuInfo);
}
