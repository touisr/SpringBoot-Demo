package com.laika.demoservice.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_customer")
public class Customer {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户编号
     */
    @Column(name = "customer_code")
    private String customerCode;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 归属代理商ID
     */
    @Column(name = "agent_code")
    private String agentCode;

    /**
     * 客户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 客户头像
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 客户性别,MAN=男人;WOMAN=女人;UNKNOW=未知
     */
    private String sex;

    /**
     * 状态：VALID=正常，DISABLED=冻结
     */
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否删除,0否，1是
     */
    private Integer flag;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取客户编号
     *
     * @return customer_code - 客户编号
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * 设置客户编号
     *
     * @param customerCode 客户编号
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取加密盐值
     *
     * @return salt - 加密盐值
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置加密盐值
     *
     * @param salt 加密盐值
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取归属代理商ID
     *
     * @return agent_code - 归属代理商ID
     */
    public String getAgentCode() {
        return agentCode;
    }

    /**
     * 设置归属代理商ID
     *
     * @param agentCode 归属代理商ID
     */
    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    /**
     * 获取客户昵称
     *
     * @return nick_name - 客户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置客户昵称
     *
     * @param nickName 客户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取客户头像
     *
     * @return pic_url - 客户头像
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置客户头像
     *
     * @param picUrl 客户头像
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取客户性别,MAN=男人;WOMAN=女人;UNKNOW=未知
     *
     * @return sex - 客户性别,MAN=男人;WOMAN=女人;UNKNOW=未知
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置客户性别,MAN=男人;WOMAN=女人;UNKNOW=未知
     *
     * @param sex 客户性别,MAN=男人;WOMAN=女人;UNKNOW=未知
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取状态：VALID=正常，DISABLED=冻结
     *
     * @return status - 状态：VALID=正常，DISABLED=冻结
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：VALID=正常，DISABLED=冻结
     *
     * @param status 状态：VALID=正常，DISABLED=冻结
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后更新时间
     *
     * @return update_time - 最后更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后更新时间
     *
     * @param updateTime 最后更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取是否删除,0否，1是
     *
     * @return flag - 是否删除,0否，1是
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置是否删除,0否，1是
     *
     * @param flag 是否删除,0否，1是
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}