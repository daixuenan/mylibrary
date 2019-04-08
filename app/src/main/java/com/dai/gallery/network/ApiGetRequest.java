package com.dai.gallery.network;

import android.text.TextUtils;

import com.dai.gallery.network.base.AsyncApiClient;
import com.dai.gallery.network.interfaces.NetWorkCallBack;
import com.dai.gallery.utils.Configure;

/**
 * Created by dai on 2018/11/19.
 */

public class ApiGetRequest {

    //Banner
    public static void getBanner(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_BANNER, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //首页
    public static void getHome(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_HOME, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * old below
     *
     * @param url
     * @return
     */

    //type android传1
    private static String getUrl(String url) {
        return getUrl(url, true);
    }

    //是否传type
    private static String getUrl(String url, boolean isNeedType) {
        if (!TextUtils.isEmpty(Configure.TOKEN)) {
            url += "&token=" + Configure.TOKEN;
        }
        return url;
    }

    //查询轮播
    public static void getBannerList(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_BANNER_LIST, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询广告
    public static void getAdvertisingQuery(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_ADVERTISING_QUERY, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询公告
    public static void getNoticePage(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_NOTICE_PAGE, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询所有菜单信息
    public static void getMainMenu(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_ICONMENU_FINDMENU, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询贷款产品列表
    public static void getLoanProduct(int page, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s?page=%d&rows=10&isHome=1", Apis.API_GET_LOAN_PRODUCT, page);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param page                  页码
     * @param categoryCode          分类code
     * @param theAmountIsHigherThan 金额以上
     * @param theAmountIsLowerThan  金额以下
     * @param netWorkCallBack
     */
    public static void getLoanProduct(int page, String categoryCode, String theAmountIsHigherThan, String theAmountIsLowerThan, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s?page=%d&rows=10&theAmountIsHigherThan=%s&theAmountIsLowerThan=%s", Apis.API_GET_LOAN_PRODUCT, page, theAmountIsHigherThan, theAmountIsLowerThan);
            if (!TextUtils.isEmpty(categoryCode)) {
                url += "&categoryCode=" + categoryCode;
            }
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询所有分类信息
    public static void getCategoryList(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_CATEGORY_LIST, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询金额分类信息
    public static void getAmountLabel(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_LOAN_PRODUCT_AMOUNTLABEL, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //关于我们
    public static void getAboutUs(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_ABOUT_US, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //查询手机号是否注册
    public static void getUserInfo(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_USER_LOGIN_USER, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取应用程序版本  type App来源 1 安卓 ,2 IOS
    public static void getAppVersion(String appVersion, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s?appVersion=%s&type=1", Apis.API_GET_APPVERSION, appVersion);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * old
     *
     * @param phone
     * @param netWorkCallBack
     */
    //查询手机号是否注册
    public static void checkRegistered(String phone, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s?phone=%s", Apis.API_GET_USER_CHECK_REGISTERED, phone);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //首页底部菜单
    public static final int MENU_HOME = 1;

    //我的菜单列表
    public static final int MENU_ME = 2;

    //查询所有菜单信息
    public static void findMenu(int menuType, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s?menuType=%d", Apis.API_GET_ICONMENU_FINDMENU, menuType);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final int AD_INVEST = 1;
    public static final int AD_LOAN = 2;
    public static final int AD_SAFE = 3;
    public static final int AD_REGISTER = 4;
    public static final int AD_RESET_PASSWORD = 5;

    //查询所有广告 1-首页理财模块广告位2-首页贷款模块广告位3-首页保险模块广告位4-注册成功页广告位
    public static void getAdvertise(int index, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s?index=%d", Apis.API_GET_ADVERTISE, index);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据userCode查询绑卡信息
    public static void getBankCardList(String appVersion, NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(Apis.API_GET_BANKCARD_LIST, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //微信平台登录
    public static void getWechatLogin(String code, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s?code=%s", Apis.API_GET_WECHAT_LOGIN, code);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * old below
     */

    //获取我的福利红包
    public static void getHongBaoList(int status, int type, int page, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&status=%d&type=%d&page=%d", getUrl(Apis.API_GET_HONGBAO_LIST, false), status, type, page);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取我的加息券
    public static void getCouponList(int page, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&status=%d&page=%d", getUrl(Apis.API_GET_COUPON_LIST), 1, page);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取我的体验金
    public static void getExpCash(int page, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&page=%d", getUrl(Apis.API_GET_EXP_CASH), page);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //我的用户信息
    public static void getUserHome(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(getUrl(Apis.API_GET_USER_HOME), netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用户信息详情
    public static void getUserDetail(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(getUrl(Apis.API_GET_USER_DETAIL), netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //账户url ？？？
    public static void getAccountUrl(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(getUrl(Apis.API_GET_ACCOUNT_URL), netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //直投列表
    public static void getInvestDirectlyList(String tabType, int page, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&tabType=%s&page=%d", getUrl(Apis.API_GET_PROJECT_LIST), tabType, page);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //债转列表
    public static void getInvestDebtList(int page, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&page=%d", getUrl(Apis.API_GET_INVEST_DEBT_LIST), page);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //债转列表单个item
    public static void getInvestDebtItem(int projectId, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&projectId=%d", getUrl(Apis.API_GET_INVEST_DEBT_DETAIL), projectId);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取银行列表
    public static void getBankList(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(getUrl(Apis.API_GET_BANK_LIST), netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //回款中列表
    public static void getInvestRepayingList(int status, int page, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&status=%d&orderBy=%d&&page=%d", getUrl(Apis.API_GET_INVEST_REPAYING), status, 0, page);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取默认银行卡 来源:0充值1提现 GET
     *
     * @return
     */
    public static void getDefaultBankCard(int source, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&source=%d", getUrl(Apis.API_GET_DEFAULT_CARD), source);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //我的债权列表
    public static void getMyDebtList(int status, int page, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&status=%d&page=%d", getUrl(Apis.API_GET_INVEST_MY_DEBT_LIST), status, page);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //智投列表
    public static void getInvestAuto(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(getUrl(Apis.API_GET_INVEST_MONTHLY_NEWLIST), netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //智投详情
    public static void getInvestAutoDetail(int packageId, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&packageId=%d", getUrl(Apis.API_GET_INVEST_MONTHLY_DETAIL), packageId);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //H5页面分享
    public static void getAppShare(int shareId, NetWorkCallBack netWorkCallBack) {
        try {
            String url = "";
            if (shareId == 0) {
                url = getUrl(Apis.API_GET_APP_SHARE);
            } else {
                url = String.format("%s&shareId=%d", getUrl(Apis.API_GET_APP_SHARE), shareId);
            }
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发现页数据
    public static void getFindList(NetWorkCallBack netWorkCallBack) {
        try {
            AsyncApiClient.get(getUrl(Apis.API_GET_FIND_LIST), netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //直投列表单个item
    public static void getInvestDirectListItem(String id, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&projectId=%s", getUrl(Apis.API_GET_INVEST_DIRECT_LIST_ITEM), id);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //直投详情
    public static void getInvestDirectDetail(String id, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&id=%s", getUrl(Apis.API_GET_INVEST_DIRECT_DETAIL), id);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //直投支付
    public static void getPayInvestDirect(String id, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&id=%s", getUrl(Apis.API_GET_PAY_INVEST_DIRECT), id);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //即时获取直投红包和加息券
    public static void getPayInvestDirectDetail(String projectId, String amount, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&projectId=%s&amount=%s", getUrl(Apis.API_GET_PAY_INVEST_DIRECT_DETAIL), projectId, amount);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //即时获取智投红包和加息券
    public static void getPayInvestAutoDetail(String packageId, String amount, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&packageId=%s&amount=%s", getUrl(Apis.API_GET_PAY_INVEST_AUTO_COUPON), packageId, amount);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //风险评测分数
    public static void getRiskEvaluateValue(String score, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&score=%s", getUrl(Apis.API_GET_RISK_EVALUATE_SCORE), score);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //债转支付详情
    public static void getDebtPayDetail(int id, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&id=%d", getUrl(Apis.API_GET_DEBT_PAY_DETAIL), id);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //首页数据
    public static void getHome(String deviceToken, String on, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&deviceToken=%s&on=%s", getUrl(Apis.API_GET_HOME), deviceToken, on);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //智投支付页详情
    public static void getInvestAutoPayDetail(String packageId, NetWorkCallBack netWorkCallBack) {
        try {
            String url = String.format("%s&packageId=%s", getUrl(Apis.API_GET_PAY_INVEST_AUTO_DETAIL), packageId);
            AsyncApiClient.get(url, netWorkCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
