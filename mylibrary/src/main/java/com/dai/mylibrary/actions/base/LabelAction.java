package com.dai.mylibrary.actions.base;

import android.content.Context;

import com.dai.plugin.boardmenu.actions.base.BaseAction;

public class LabelAction extends BaseAction {

    public LabelAction(Context context) {
        super(context);
        setLabel("label");
        setIconUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556109229405&di=3fb5adad985188f7e5c0ee42897ef3e5&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01ea8b57f784f9a84a0d304fa0d887.png%401280w_1l_2o_100sh.png");
        setContent("text");
    }

    @Override
    public void onClick() {
        //TODO: onClick事件
    }
}
