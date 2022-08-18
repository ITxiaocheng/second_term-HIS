package com.obtk.controller.menu;

import java.util.List;

public class Menu {
    private int menu_id; //菜单id
    private String menu_name; // 菜单中文名称
    private int menu_fatherId;  // 菜单父级id
    private int menu_show;    // 菜单是否可见
    private String menu_icon;   // 菜单图标
    private String menu_path;    // 菜单路径

    private List<Menu> childMenu; // 子菜单

    public Menu() {
    }

    public Menu(int menu_id, String menu_name, int menu_fatherId, int menu_show, String menu_icon, String menu_path, List<Menu> childMenu) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_fatherId = menu_fatherId;
        this.menu_show = menu_show;
        this.menu_icon = menu_icon;
        this.menu_path = menu_path;
        this.childMenu = childMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                ", menu_fatherId=" + menu_fatherId +
                ", menu_show=" + menu_show +
                ", menu_icon='" + menu_icon + '\'' +
                ", menu_path='" + menu_path + '\'' +
                ", childMenu=" + childMenu +
                '}';
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getMenu_fatherId() {
        return menu_fatherId;
    }

    public void setMenu_fatherId(int menu_fatherId) {
        this.menu_fatherId = menu_fatherId;
    }

    public int getMenu_show() {
        return menu_show;
    }

    public void setMenu_show(int menu_show) {
        this.menu_show = menu_show;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public String getMenu_path() {
        return menu_path;
    }

    public void setMenu_path(String menu_path) {
        this.menu_path = menu_path;
    }

    public List<Menu> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<Menu> childMenu) {
        this.childMenu = childMenu;
    }
}
