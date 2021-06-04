package com.managment.system.Entity;

public class MenuInfo {
    private int authorityId;
    private String authorityName;
    private int orderNumber;
    private String menuUrl;
    private String menuIcon;
    private String createTime;
    private String authority;
    private int checked;
    private String updateTime;
    private int isMenu;
    private int parentId;
    private String open;
    private String isHide;

    public MenuInfo() {
    }

    public MenuInfo(int authorityId,
                    String authorityName,
                    int orderNumber,
                    String menuUrl,
                    String menuIcon,
                    String createTime,
                    String authority,
                    int checked,
                    String updateTime,
                    int isMenu,
                    int parentId,
                    String open,
                    String isHide) {
        this.authorityId = authorityId;
        this.authorityName = authorityName;
        this.orderNumber = orderNumber;
        this.menuUrl = menuUrl;
        this.menuIcon = menuIcon;
        this.createTime = createTime;
        this.authority = authority;
        this.checked = checked;
        this.updateTime = updateTime;
        this.isMenu = isMenu;
        this.parentId = parentId;
        this.open = open;
        this.isHide = isHide;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setIsMenu(int isMenu) {
        this.isMenu = isMenu;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getAuthority() {
        return authority;
    }

    public int getChecked() {
        return checked;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public int getIsMenu() {
        return isMenu;
    }

    public int getParentId() {
        return parentId;
    }

    public String getOpen() {
        return open;
    }

    public String getIsHide() {
        return isHide;
    }
}
