package com.xx.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jishu02 on 2017/4/6.
 */
@Entity
@Table(name = "i_member_user" )
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    @Column(name = "password_level")
    private Byte passwordLevel;

    /**
     * 支付密码
     */
    @Column(name = "pay_password")
    private String payPassword;

    @Column(name = "pay_password_level")
    private Byte payPasswordLevel;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 是否激活
     */
    @Column(name = "is_active")
    private Byte isActive;

    /**
     * 手机收费认证
     */
    @Column(name = "mobile_active")
    private Byte mobileActive;

    /**
     * 邮箱是否认证
     */
    @Column(name = "email_active")
    private Byte emailActive;

    /**
     * 完善度
     */
    @Column(name = "finished_degree")
    private Byte finishedDegree;

    /**
     * 登陆状态
     */
    @Column(name = "login_status")
    private Byte loginStatus;

    /**
     * 用户状态
     */
    private Byte status;

    /**
     * 用户类型:匿名|会员
     */
    @Column(name = "user_type")
    private Long userType;

    /**
     * 来源主键
     */
    private Long source;

    /**
     * 来源详细描述
     */
    @Column(name = "source_detail")
    private String sourceDetail;

    /**
     * 推广主键
     */
    @Column(name = "promotion_id")
    private Long promotionId;

    /**
     * 等级主键
     */
    @Column(name = "member_level_id")
    private Long memberLevelId;

    /**
     * 经验值
     */
    private Integer exp;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 推荐码
     */
    @Column(name = "recommended_code")
    private String recommendedCode;

    /**
     * 个人资料主键
     */
    @Column(name = "personal_id")
    private Long personalId;

    /**
     * 客服主键
     */
    @Column(name = "server_id")
    private Long serverId;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 是否已完善个人资料
     */
    @Column(name = "is_personal_completed")
    private Byte isPersonalCompleted;

    /**
     * 创建者主键
     */
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 修改者主键
     */
    @Column(name = "modifier_id")
    private Long modifierId;

    /**
     * 修改时间
     */
    @Column(name = "modified_time")
    private Date modifiedTime;

    /**
     * 是否是代理
     */
    @Column(name = "is_agency")
    private Byte isAgency;

    /**
     * 是否已登陆
     */
    @Column(name = "is_login")
    private Byte isLogin;

    /**
     * 推荐类型字典
     */
    @Column(name = "recommend_type_id")
    private Long recommendTypeId;

    /**
     * 序号
     */
    @Column(name = "serial_no")
    private Long serialNo;

    /**
     * 登录次数
     */
    @Column(name = "login_count")
    private Long loginCount;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return password_level
     */
    public Byte getPasswordLevel() {
        return passwordLevel;
    }

    /**
     * @param passwordLevel
     */
    public void setPasswordLevel(Byte passwordLevel) {
        this.passwordLevel = passwordLevel;
    }

    /**
     * 获取支付密码
     *
     * @return pay_password - 支付密码
     */
    public String getPayPassword() {
        return payPassword;
    }

    /**
     * 设置支付密码
     *
     * @param payPassword 支付密码
     */
    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    /**
     * @return pay_password_level
     */
    public Byte getPayPasswordLevel() {
        return payPasswordLevel;
    }

    /**
     * @param payPasswordLevel
     */
    public void setPayPasswordLevel(Byte payPasswordLevel) {
        this.payPasswordLevel = payPasswordLevel;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取手机
     *
     * @return mobile - 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机
     *
     * @param mobile 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取是否激活
     *
     * @return is_active - 是否激活
     */
    public Byte getIsActive() {
        return isActive;
    }

    /**
     * 设置是否激活
     *
     * @param isActive 是否激活
     */
    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取手机收费认证
     *
     * @return mobile_active - 手机收费认证
     */
    public Byte getMobileActive() {
        return mobileActive;
    }

    /**
     * 设置手机收费认证
     *
     * @param mobileActive 手机收费认证
     */
    public void setMobileActive(Byte mobileActive) {
        this.mobileActive = mobileActive;
    }

    /**
     * 获取邮箱是否认证
     *
     * @return email_active - 邮箱是否认证
     */
    public Byte getEmailActive() {
        return emailActive;
    }

    /**
     * 设置邮箱是否认证
     *
     * @param emailActive 邮箱是否认证
     */
    public void setEmailActive(Byte emailActive) {
        this.emailActive = emailActive;
    }

    /**
     * 获取完善度
     *
     * @return finished_degree - 完善度
     */
    public Byte getFinishedDegree() {
        return finishedDegree;
    }

    /**
     * 设置完善度
     *
     * @param finishedDegree 完善度
     */
    public void setFinishedDegree(Byte finishedDegree) {
        this.finishedDegree = finishedDegree;
    }

    /**
     * 获取登陆状态
     *
     * @return login_status - 登陆状态
     */
    public Byte getLoginStatus() {
        return loginStatus;
    }

    /**
     * 设置登陆状态
     *
     * @param loginStatus 登陆状态
     */
    public void setLoginStatus(Byte loginStatus) {
        this.loginStatus = loginStatus;
    }

    /**
     * 获取用户状态
     *
     * @return status - 用户状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置用户状态
     *
     * @param status 用户状态
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取用户类型:匿名|会员
     *
     * @return user_type - 用户类型:匿名|会员
     */
    public Long getUserType() {
        return userType;
    }

    /**
     * 设置用户类型:匿名|会员
     *
     * @param userType 用户类型:匿名|会员
     */
    public void setUserType(Long userType) {
        this.userType = userType;
    }

    /**
     * 获取来源主键
     *
     * @return source - 来源主键
     */
    public Long getSource() {
        return source;
    }

    /**
     * 设置来源主键
     *
     * @param source 来源主键
     */
    public void setSource(Long source) {
        this.source = source;
    }

    /**
     * 获取来源详细描述
     *
     * @return source_detail - 来源详细描述
     */
    public String getSourceDetail() {
        return sourceDetail;
    }

    /**
     * 设置来源详细描述
     *
     * @param sourceDetail 来源详细描述
     */
    public void setSourceDetail(String sourceDetail) {
        this.sourceDetail = sourceDetail == null ? null : sourceDetail.trim();
    }

    /**
     * 获取推广主键
     *
     * @return promotion_id - 推广主键
     */
    public Long getPromotionId() {
        return promotionId;
    }

    /**
     * 设置推广主键
     *
     * @param promotionId 推广主键
     */
    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    /**
     * 获取等级主键
     *
     * @return member_level_id - 等级主键
     */
    public Long getMemberLevelId() {
        return memberLevelId;
    }

    /**
     * 设置等级主键
     *
     * @param memberLevelId 等级主键
     */
    public void setMemberLevelId(Long memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    /**
     * 获取经验值
     *
     * @return exp - 经验值
     */
    public Integer getExp() {
        return exp;
    }

    /**
     * 设置经验值
     *
     * @param exp 经验值
     */
    public void setExp(Integer exp) {
        this.exp = exp;
    }

    /**
     * 获取积分
     *
     * @return score - 积分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置积分
     *
     * @param score 积分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取推荐码
     *
     * @return recommended_code - 推荐码
     */
    public String getRecommendedCode() {
        return recommendedCode;
    }

    /**
     * 设置推荐码
     *
     * @param recommendedCode 推荐码
     */
    public void setRecommendedCode(String recommendedCode) {
        this.recommendedCode = recommendedCode == null ? null : recommendedCode.trim();
    }

    /**
     * 获取个人资料主键
     *
     * @return personal_id - 个人资料主键
     */
    public Long getPersonalId() {
        return personalId;
    }

    /**
     * 设置个人资料主键
     *
     * @param personalId 个人资料主键
     */
    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    /**
     * 获取客服主键
     *
     * @return server_id - 客服主键
     */
    public Long getServerId() {
        return serverId;
    }

    /**
     * 设置客服主键
     *
     * @param serverId 客服主键
     */
    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    /**
     * 获取是否删除
     *
     * @return is_deleted - 是否删除
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted 是否删除
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取是否已完善个人资料
     *
     * @return is_personal_completed - 是否已完善个人资料
     */
    public Byte getIsPersonalCompleted() {
        return isPersonalCompleted;
    }

    /**
     * 设置是否已完善个人资料
     *
     * @param isPersonalCompleted 是否已完善个人资料
     */
    public void setIsPersonalCompleted(Byte isPersonalCompleted) {
        this.isPersonalCompleted = isPersonalCompleted;
    }

    /**
     * 获取创建者主键
     *
     * @return creator_id - 创建者主键
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建者主键
     *
     * @param creatorId 创建者主键
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取修改者主键
     *
     * @return modifier_id - 修改者主键
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 设置修改者主键
     *
     * @param modifierId 修改者主键
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 获取修改时间
     *
     * @return modified_time - 修改时间
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifiedTime 修改时间
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * 获取是否是代理
     *
     * @return is_agency - 是否是代理
     */
    public Byte getIsAgency() {
        return isAgency;
    }

    /**
     * 设置是否是代理
     *
     * @param isAgency 是否是代理
     */
    public void setIsAgency(Byte isAgency) {
        this.isAgency = isAgency;
    }

    /**
     * 获取是否已登陆
     *
     * @return is_login - 是否已登陆
     */
    public Byte getIsLogin() {
        return isLogin;
    }

    /**
     * 设置是否已登陆
     *
     * @param isLogin 是否已登陆
     */
    public void setIsLogin(Byte isLogin) {
        this.isLogin = isLogin;
    }

    /**
     * 获取推荐类型字典
     *
     * @return recommend_type_id - 推荐类型字典
     */
    public Long getRecommendTypeId() {
        return recommendTypeId;
    }

    /**
     * 设置推荐类型字典
     *
     * @param recommendTypeId 推荐类型字典
     */
    public void setRecommendTypeId(Long recommendTypeId) {
        this.recommendTypeId = recommendTypeId;
    }

    /**
     * 获取序号
     *
     * @return serial_no - 序号
     */
    public Long getSerialNo() {
        return serialNo;
    }

    /**
     * 设置序号
     *
     * @param serialNo 序号
     */
    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * 获取登录次数
     *
     * @return login_count - 登录次数
     */
    public Long getLoginCount() {
        return loginCount;
    }

    /**
     * 设置登录次数
     *
     * @param loginCount 登录次数
     */
    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }


}
