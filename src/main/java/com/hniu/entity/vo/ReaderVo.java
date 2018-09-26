package com.hniu.entity.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tbl_readers")
public class ReaderVo implements Serializable {
    /**
     * 读者id
     */
    @Id
    @Column(name = "reader_id")
    private Integer readerId;

    /**
     * 读者类别id
     */
    @Column(name="reader_type_name")
    private String readerTypeName;

    private Integer borrowDay;
    /**
     * 读者姓名
     */
    @Column(name = "reader_name")
    private String readerName;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;


    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 性别
     */
    private Byte sex;


    /**
     * 到期日期
     */
    @Column(name = "expiration_time")
    private Date expirationTime;

    /**
     * 办证日期
     */
    @Column(name = "create_time")
    private Date createTime;
    private Integer borrowNumber;
    /**
     * 逾期次数
     */
    @Column(name = "overdue_number")
    private Byte overdueNumber;

    /**
     * 证书状态
     */
    private Byte state;
    /**
     * 读者编号
     */
    @Column(name = "reader_code")
    private String readerCode;

    @Column(name = "session_key")
    private String sessionKey;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getBorrowDay() {
        return borrowDay;
    }

    public void setBorrowDay(Integer borrowDay) {
        this.borrowDay = borrowDay;
    }

    /**
     * 获取读者id
     *
     * @return reader_id - 读者id
     */
    public Integer getReaderId() {
        return readerId;
    }

    /**
     * 设置读者id
     *
     * @param readerId 读者id
     */
    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }


    /**
     * 获取读者姓名
     *
     * @return reader_name - 读者姓名
     */
    public String getReaderName() {
        return readerName;
    }

    /**
     * 设置读者姓名
     *
     * @param readerName 读者姓名
     */
    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }


    /**
     * 获取微信号
     *
     * @return wechat - 微信号
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * 设置微信号
     *
     * @param wechat 微信号
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }


    /**
     * 获取到期日期
     *
     * @return expiration_time - 到期日期
     */
    public Date getExpirationTime() {
        return expirationTime;
    }

    /**
     * 设置到期日期
     *
     * @param expirationTime 到期日期
     */
    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }


    /**
     * 获取逾期次数
     *
     * @return overdue_number - 逾期次数
     */
    public Byte getOverdueNumber() {
        return overdueNumber;
    }

    /**
     * 设置逾期次数
     *
     * @param overdueNumber 逾期次数
     */
    public void setOverdueNumber(Byte overdueNumber) {
        this.overdueNumber = overdueNumber;
    }

    /**
     * 获取读者编号
     *
     * @return reader_code - 读者编号
     */
    public String getReaderCode() {
        return readerCode;
    }

    /**
     * 设置读者编号
     *
     * @param readerCode 读者编号
     */
    public void setReaderCode(String readerCode) {
        this.readerCode = readerCode;
    }

    /**
     * @return session_key
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * @param sessionKey
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getReaderTypeName() {
        return readerTypeName;
    }

    public void setReaderTypeName(String readerTypeName) {
        this.readerTypeName = readerTypeName;
    }

    public Integer getBorrowNumber() {
        return borrowNumber;
    }

    public void setBorrowNumber(Integer borrowNumber) {
        this.borrowNumber = borrowNumber;
    }
}