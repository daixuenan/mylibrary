package com.dai.gallery.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.dai.gallery.R;
import com.dai.gallery.adapter.PageFragmentAdapter;
import com.dai.gallery.network.ApiGetRequest;
import com.dai.gallery.network.interfaces.NetWorkCallBack;
import com.dai.gallery.ui.base.BaseActivity;
import com.dai.gallery.ui.base.BaseFragment;
import com.dai.gallery.ui.home.fragment.HomeFragment;
import com.dai.gallery.ui.me.fragment.MeFragment;
import com.dai.gallery.utils.Configure;
import com.dai.plugin.bottomtablelayout.BottomTabLayout;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.tabLayout)
    BottomTabLayout tabLayout;

    private PageFragmentAdapter pageFragmentAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        //权限
        initPermissions();

        //版本检测
        initVersionInfo();

        loadMenu();
    }

    private void init() {
        List<BaseFragment> pageList = new ArrayList<>();
        pageList.add(HomeFragment.newInstant());
        pageList.add(MeFragment.newInstant());
        pageFragmentAdapter = new PageFragmentAdapter(getSupportFragmentManager(), pageList);
        viewPager.setOffscreenPageLimit(pageList.size());
        viewPager.setAdapter(pageFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initPermissions() {
        //初始化权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_APN_SETTINGS)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) throws Exception {
//                    if (permission.granted) {
//                        //用户已经同意该权限
//                        if (ActivityCompat.checkSelfPermission(StartActivity.this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                            return;
//                        }
//                    } else if (permission.shouldShowRequestPermissionRationale) {
//                        //用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
//                    } else {
//                        //用户拒绝了该权限，并且选中『不再询问』，提醒用户手动打开权限
//                    }
                        }
                    });
        }
    }

    private void loadMenu() {
        ApiGetRequest.getMainMenu(new NetWorkCallBack() {
            @Override
            public void onResponse(boolean isSucceed, int status, String response) {
                if (isSucceed) {
//                    List<BottomMenuBean> bottomMenuBeans = JSON.parseArray(response, BottomMenuBean.class);
//                    if (bottomMenuBeans != null && bottomMenuBeans.size() > 0) {
//                        for (BottomMenuBean bottomMenuBean : bottomMenuBeans) {
//                            BottomTabLayout.Tab tab = null;
//                            if (ARouterConstants.ROUTER_HOME.equals(bottomMenuBean.getNativeUrl())) {
//                                //首页
//                                tab = tabLayout.getmTabs().get(0);
//                            } else if (ARouterConstants.ROUTER_LOAN.equals(bottomMenuBean.getNativeUrl())) {
//                                //贷款
//                                tab = tabLayout.getmTabs().get(1);
//                            } else if (ARouterConstants.ROUTER_ME.equals(bottomMenuBean.getNativeUrl())) {
//                                //我的
//                                tab = tabLayout.getmTabs().get(2);
//                            }
//                            if (tab != null) {
//                                tab.setText(bottomMenuBean.getMenuName());
//                                tab.setSelectedUrl(bottomMenuBean.getClickIconUrl());
//                                tab.setUnSelectedUrl(bottomMenuBean.getIconUrl());
//                            }
//                        }
//                    }
                }
            }
        });
    }

    private void initVersionInfo() {
        ApiGetRequest.getAppVersion(Configure.APP_VERSION, new NetWorkCallBack() {
            @Override
            public void onResponse(boolean isSucceed, int status, String response) {
                if (isSucceed) {
//                    final VersionBean versionBean = JSON.parseObject(response, VersionBean.class);
//                    //需要更新
//                    if (versionBean.getMustUpdate() == VersionBean.UPDATE_OPTIONAL) {
//                        if (!DateUtils.getCurrentDate().equals(CacheUtils.getString(mContext, CacheUtils.LAST_UPDATE_DATE + Configure.APP_VERSION))) {
//                            CacheUtils.setString(mContext, CacheUtils.LAST_UPDATE_DATE, DateUtils.getCurrentDate() + Configure.APP_VERSION);
//
//                            DialogBean dialogBean = new DialogBean(versionBean.getTextUpdateContent(), versionBean.getUrl(), "立即更新", DIALOG_UPDATE);
//                            dialogQueue.add(dialogBean);
//                            showDialog(dialogBean);
//                        }
//                    } else if (versionBean.getMustUpdate() == VersionBean.UPDATE_FORCE) {
//                        DialogUtils.getInstance().setDialog(new DownloadBottomDialog(mContext)).setOnEditDialogDataListener(new OnEditDialogDataListener() {
//                            @Override
//                            public void onEditDialogData(DialogBean dialogBean) {
//                                dialogBean.setUrl(versionBean.getUrl());
//                            }
//                        }).show();
//                    }
                }
            }
        });
    }

//    private void showDialog(final DialogBean mDialogBean) {
//        DialogUtils.getInstance().setDialog(new BottomDialog(mContext)).setOnEditDialogDataListener(new OnEditDialogDataListener() {
//            @Override
//            public void onEditDialogData(DialogBean dialogBean) {
//                dialogBean.setUrl(mDialogBean.getUrl());
//                dialogBean.setContent(mDialogBean.getContent());
//                dialogBean.setBtnTextOk(mDialogBean.getBtnTextOk());
//                dialogBean.setType(mDialogBean.getType());
//            }
//        }).setOnDialogSelectListener(new OnDialogSelectListener() {
//            @Override
//            public void onOkClicked() {
//                if (mDialogBean.getType() == DIALOG_SET_PERMISSION) {
//                    NotificationsUtils.setNoticePermission(mContext);
//                } else if (mDialogBean.getType() == DIALOG_UPDATE) {
//                    DialogUtils.getInstance().setDialog(new DownloadBottomDialog(mContext)).setOnEditDialogDataListener(new OnEditDialogDataListener() {
//                        @Override
//                        public void onEditDialogData(DialogBean dialogBean) {
//                            dialogBean.setUrl(mDialogBean.getUrl());
//                        }
//                    }).show();
//                }
//            }
//        }).setOnDialogClosedListener(new OnDialogClosedListener() {
//            @Override
//            public void onClosed() {
//                dialogQueue.remove();
//                if (dialogQueue.peek() != null) {
//                    showDialog(dialogQueue.peek());
//                }
//            }
//        }).show();
//    }
}
