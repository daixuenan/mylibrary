package com.dai.gallery.network;

import static com.dai.gallery.utils.Configure.HOST;

/**
 * Created by dai on 2018/11/19.
 */

public class Apis {

    /**
     * GET
     */

    //banner
    public final static String API_GET_BANNER = HOST + "/api/mm/banner";

    /**
     * POST
     */

    /**
     * GET
     */

    //查询轮播
    public final static String API_GET_BANNER_LIST = HOST + "/api/banner/list";

    //查询广告
    public final static String API_GET_ADVERTISING_QUERY = HOST + "/api/advertising/query";

    //查询公告
    public final static String API_GET_NOTICE_PAGE = HOST + "/api/notice/page";

    //查询所有菜单信息
    public final static String API_GET_ICONMENU_FINDMENU = HOST + "/api/iconMenu/findMenu";

    //查询贷款产品列表
    public final static String API_GET_LOAN_PRODUCT = HOST + "/api/loanProduct";

    //查询金额分类信息
    public final static String API_GET_LOAN_PRODUCT_AMOUNTLABEL = HOST + "/api/loanProduct/amountLabel";

    //查询所有分类信息
    public final static String API_GET_CATEGORY_LIST = HOST + "/api/category/list";

    //关于我们
    public final static String API_GET_ABOUT_US = HOST + "/api/aboutUs";

    //获取当前用戶信息
    public final static String API_GET_USER_LOGIN_USER = HOST + "/api/user/loginUser";

    //获取应用程序版本
    public final static String API_GET_APPVERSION = HOST + "/api/appVersion";

    /**
     * POST
     */

    //发送短信验证码
    public final static String API_POST_MESSAGE_SEND_SMS_CODE = HOST + "/api/message/sendSmsCode";

    //手机号验证码登录
    public final static String API_POST_USER_LOGIN_SMS_CODE = HOST + "/api/user/register";

    //修改用户资料
    public final static String API_POST_USER_UPDATE_DATA = HOST + "/api/user/updateData";

    //新增用户联网记录接口
    public final static String API_POST_NETWORK_COUNT = HOST + "/api/userNetworkingRecord/save";

    //新增用户浏览记录
    public final static String API_POST_PRODUCT_VIEW_RECODE = HOST + "/api/userBrowsingHistory/save";

    /**
     *
     * old
     */
    /**
     * GET
     */
    //查询手机号是否注册
    public final static String API_GET_USER_CHECK_REGISTERED = HOST + "/api/user/checkRegistered";

    //获取当前登录用户信息
    public final static String API_GET_USER_CURRENT_LOGIN_USER = HOST + "/api/user/currentLoginUser";

    //查询所有广告
    public final static String API_GET_ADVERTISE = HOST + "/api/advertising/list";

    //根据userCode查询绑卡信息
    public final static String API_GET_BANKCARD_LIST = HOST + "/api/bank/userCode";

    //微信平台登录
    public final static String API_GET_WECHAT_LOGIN = HOST + "/api/thirdParty/weChat/login";

    /**
     * 已过期
     */
    //获取通知消息
    public final static String API_GET_FIND_APP_NOTICE = HOST + "/find/findAppNotice";

    //获取我的福利数据
    public final static String API_GET_HONGBAO_LIST = HOST + "/hongbao/list";

    //获取我的加息券
    public final static String API_GET_COUPON_LIST = HOST + "/coupon/list";

    //获取我的体验金
    public final static String API_GET_EXP_CASH = HOST + "/investment/exp/newDetail";

    //我的用户信息
    public final static String API_GET_USER_HOME = HOST + "/user/home";

    //用户信息详情
    public final static String API_GET_USER_DETAIL = HOST + "/user/detail";

    //账户URL ？？？
    public final static String API_GET_ACCOUNT_URL = HOST + "/account/getAccountUrl";

    //直投列表
    public final static String API_GET_PROJECT_LIST = HOST + "/project/list";

    //债转列表
    public final static String API_GET_INVEST_DEBT_LIST = HOST + "/project/queryCreditorList";

    //债转列表单个item
    public final static String API_GET_INVEST_DEBT_DETAIL = HOST + "/project/project/queryCreditorList";

    //银行列表
    public final static String API_GET_BANK_LIST = HOST + "/user/getBankList";

    //回款中列表
    public final static String API_GET_INVEST_REPAYING = HOST + "/project/myCurrent";

    //我的债权列表
    public final static String API_GET_INVEST_MY_DEBT_LIST = HOST + "/project/queryCreditorAssignmentList";

    //智投列表
    public final static String API_GET_INVEST_MONTHLY_NEWLIST = HOST + "/monthly/appList";

    //智投详情
    public final static String API_GET_INVEST_MONTHLY_DETAIL = HOST + "/monthly/appDetail";

    // 获取默认银行卡 来源:0充值1提现 GET
    public final static String API_GET_DEFAULT_CARD = HOST + "/account/getDefaultCard";

    // H5分享
    public final static String API_GET_APP_SHARE = HOST + "/app/share";

    // 发现页数据
    public final static String API_GET_FIND_LIST = HOST + "/find/findList";

    // 直投列表单个item
    public final static String API_GET_INVEST_DIRECT_LIST_ITEM = HOST + "/project/project/list";

    // 直投详情
    public final static String API_GET_INVEST_DIRECT_DETAIL = HOST + "/project/regularProjectNewDetails";

    //支付详情页
    public final static String API_GET_PAY_INVEST_DIRECT = HOST + "/project/regularProjectPayDetails";

    //直投支付详情加息券红包
    public final static String API_GET_PAY_INVEST_DIRECT_DETAIL = HOST + "/project/investmentDetail";

    //智投支付详情加息券红包
    public final static String API_GET_PAY_INVEST_AUTO_COUPON = HOST + "/project/getBestCouponMonthlyGain";

    //投资前风险评测
    public final static String API_GET_RISK_EVALUATE = HOST + "/user/userRiskEvaluate";

    //风险评测分数
    public final static String API_GET_RISK_EVALUATE_SCORE = HOST + "/user/riskEvaluate";

    //债转支付详情
    public final static String API_GET_DEBT_PAY_DETAIL = HOST + "/project/queryCreditorPayDetail";

    //首页数据
    public final static String API_GET_HOME = HOST + "/home";

    //智投支付详情页
    public final static String API_GET_PAY_INVEST_AUTO_DETAIL = HOST + "/monthly/payDetail";

    /**
     * 已过期
     */

    //头像上传
    public final static String API_POST_FILE_HEADIMAGE = HOST + "/api/data/file/headImage";

    //手机号密码登录
    public final static String API_POST_LOGIN_PHONE_PASSWORD = HOST + "/api/user/loginPhonePassword";

    //校验短信验证码
    public final static String API_POST_MESSAGE_CHECK = HOST + "/api/message/check";

    //手机号注册
    public final static String API_POST_USER_REGISTER = HOST + "/api/user/register";

    //重置密码
    public final static String API_POST_USER_RESET_PASSWORD = HOST + "/api/user/resetPassword";

    //修改用户昵称
    public final static String API_POST_USER_UPDATE_NICKNAME = HOST + "/api/user/updateNickname";

    //设置提佣密码
    public final static String API_POST_USER_COMISSION_PASSWORD = HOST + "/api/user/commissionPassword";

    //修改登录密码
    public final static String API_POST_USER_LOGIN_PASSWORD = HOST + "/api/user/updateLoginPassword";

    //新增绑卡信息
    public final static String API_POST_BANK_SAVE = HOST + "/api/bank/save";

    /**
     * 已过期
     */
    //兑换红包
    public final static String API_POST_USER_REDEEM_CODE = HOST + "/user/redeemCode";

    //更新消息状态
    public final static String API_POST_MESSAGE_UPDATE = HOST + "/message/update";

    //直投—加息券列表
    public final static String API_POST_PAY_INVEST_DIRECT_INTEREST_TICKET = HOST + "/coupon/investmentRateCouponList";

    //直投-红包列表
    public final static String API_POST_PAY_INVEST_DIRECT_REDPACKET_TICKET = HOST + "/hongbao/investmentHongbaoList";

    //直投—确定支付
    public final static String API_POST_PAY_INVEST_DIRECT = HOST + "/project/saveRegularProjectInvestment";

    //智投支付
    public final static String API_POST_PAY_INVEST_AUTO = HOST + "/monthly/doInvestment";

    //获取token
    public final static String API_GET_TOKEN = HOST + "/api/v1.0/auth/auth_token";

    //账户-授权 - 登录验证码
    public final static String API_GET_VALID_CODE = HOST + "/api/v1.0/auth/login_msg_token";

    //全局资源 - 查询资源版本
    public final static String API_GET_VERSION_DATA = HOST + "/api/v1.0/globals/versions";

    //移动端-轮播图 - 获取首页Banner
    public final static String API_GET_HOME_BANNER = HOST + "/api/v1.0/mobile/index_banner";

    //移动端-版本更新 - 获取版本信息
    public final static String API_GET_VERSION_DATA_DETAIL = HOST + "/api/v1.0/mobile/version";

    //全局资源 - 获取区域列表
    public final static String API_GET_DISTRICT_LIST = HOST + "/api/v1.0/globals/district_list";

    //全局资源 - 获取行业列表
    public final static String API_GET_INDUSTRY_LIST = HOST + "/api/v1.0/globals/industry_list";

    //选项类别 - 获取类别列表
//    public final static String API_GET_CATEGORY_LIST = HOST + "/api/v1.0/globals/category_list";

    //账户-其他 - 获取Wicode
    public final static String API_GET_AUTH_WICODE = HOST + "/api/v1.0/auth/wicode";

    //账户-授权 - 换手机验证码
    public final static String API_GET_AUTH_CHANGE_PHONE_MSG = HOST + "/api/v1.0/auth/change_phone_msg";

    //用户-当前用户 - 修改用户信息
    public final static String API_PUT_USER_INFO = HOST + "/api/v1.0/user/self_info";

    //客户-门店 - 获取门店列表
    public final static String API_GET_CLIENT_STORE_LIST = HOST + "/api/v1.0/client/store_list";

    //客户-门店 - 获取门店详情
    public final static String API_GET_CLIENT_STORE_DETAIL = HOST + "/api/v1.0/client/store";

    //删除SSID
    public final static String API_GET_CLIENT_DELETE_SSID = HOST + "/api/v1.0/client/network/ssid";

    //新增SSID
    public final static String API_GET_CLIENT_ADD_SSID = HOST + "/api/v1.0/client/network/ssid/";

    //修改SSID
    public final static String API_GET_CLIENT_EDIT_SSID = HOST + "/api/v1.0/client/network/ssid";

    //人事管理-权限 - 获取权限列表
    public final static String API_GET_PERMISSION_LIST = HOST + "/api/v1.0/pem/organization/permission_list";

    //人事管理-用户 - 获取用户
    public final static String API_GET_USER_INFO = HOST + "/api/v1.0/pem/organization/user/";

    //人事管理-用户 - 修改用户
    public final static String API_PUT_ORGANIZATION_USER_INFO = HOST + "/api/v1.0/pem/organization/user/";

    //人事管理-部门 - 获取部门列表
    public final static String API_PUT_ORGANIZATION_DEPARTMENT_LIST = HOST + "/api/v1.0/pem/organization/department_list";

    //人事管理-部门 - 获取部门
    public final static String API_PUT_ORGANIZATION_DEPARTMENT = HOST + "/api/v1.0/pem/organization/department/";

    //人事管理-用户 - 获取用户列表
    public final static String API_PUT_ORGANIZATION_USER_LIST = HOST + "/api/v1.0/pem/organization/user_list";

    //人事管理-职务 - 获取职务列表
    public final static String API_PUT_ORGANIZATION_TITLE_LIST = HOST + "/api/v1.0/pem/organization/title_list";

    //人事管理-岗位 - 获取岗位列表
    public final static String API_PUT_ORGANIZATION_POST_LIST = HOST + "/api/v1.0/pem/organization/post_list";

    //人事管理-角色 - 获取角色列表
    public final static String API_PUT_ORGANIZATION_ROLE_LIST = HOST + "/api/v1.0/pem/organization/role_list";

    //人事管理-岗位 - 删除岗位
    public final static String API_DELETE_ORGANIZATION_POST = HOST + "/api/v1.0/pem/organization/post/";

    //人事管理-部门 - 修改部门岗位(批量)
    public final static String API_PUT_ORGANIZATION_DEPARTMENT_POST = HOST + "/api/v1.0/pem/organization/department/%s/post";

    //人事管理-部门 - 新增部门岗位
    public final static String API_POST_ORGANIZATION_DEPT_POST = HOST + "/api/v1.0/pem/organization/dept_post";

    //人事管理-部门 - 修改部门岗位
    public final static String API_PUT_ORGANIZATION_POST = HOST + "/api/v1.0/pem/organization/dept_post/";

    //附录-其他 - 模糊查询(自动补全)
    public final static String API_GET_GLOBALS_FUZZY_KEY = HOST + "/api/v1.0/globals/fuzzy_key";

    //附录-云配置 - 获取用户云配置
    public final static String API_GET_GLOBALS_CLOUD_CONF = HOST + "/api/v1.0/globals/cloud_conf";

    //附录-云配置 - 修改用户云配置
    public final static String API_PUT_GLOBALS_CLOUD_CONF = HOST + "/api/v1.0/globals/cloud_conf";

    //人事管理-部门 - 删除部门岗位
    public final static String API_DELETE_ORGANIZATION_DEPT_POST = HOST + "/api/v1.0/pem/organization/dept_post/";

    //附录-文件 - 上传图片/文件
    public final static String API_POST_GLOBALS_FILE = HOST + "/api/v1.0/globals/file";

    //人事管理-角色 - 删除角色
    public final static String API_DELETE_ROLE = HOST + "/api/v1.0/pem/organization/role/";

}
