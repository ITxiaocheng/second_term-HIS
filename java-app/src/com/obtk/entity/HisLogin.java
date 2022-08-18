package com.obtk.entity;

/**
 * 登录&注册
 */
public class HisLogin {
    private Integer id; //序号
    private String name; //姓名
    private String idNumber; //身份证号
    private String phoneNumber; //电话号码
    private String password; //密码
    private Integer permission; //权限

    public HisLogin() {
    }

    public HisLogin(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public HisLogin(String name, String idNumber, String phoneNumber, String password) {
        this.name = name;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public HisLogin(Integer id, String name, String idNumber, String phoneNumber, String password) {
        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public HisLogin(String name, String idNumber, String phoneNumber, String password, Integer permission) {
        this.name = name;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.permission = permission;
    }

    public HisLogin(Integer id, String name, String idNumber, String phoneNumber, String password, Integer permission) {
        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.permission = permission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "HisLogin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", permission=" + permission +
                '}';
    }
}
