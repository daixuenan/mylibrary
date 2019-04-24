package com.dai.mylibrary.actions.base;

import android.content.Context;

import com.dai.plugin.boardmenu.actions.base.BaseAction;

public class ButtonAction extends BaseAction {

    public ButtonAction(Context context) {
        super(context);
        setLabel("button");
        setIconUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556109264565&di=4c8059b4be4e9e84df6a62d381fac0bf&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D2c61ea3d65600c33f079dec82a4c5134%2F5882b2b7d0a20cf476afd7f678094b36acaf99df.jpg");
    }

    @Override
    public void onClick() {
        //TODO: onClick事件
    }
}
