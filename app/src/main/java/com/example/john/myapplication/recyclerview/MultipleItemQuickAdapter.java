package com.example.john.myapplication.recyclerview;

import android.content.Context;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.john.myapplication.R;

import java.util.List;

/**
 * John 2017/9/11
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<AppleyedEntity, BaseViewHolder> {
    private String states[] = new String[]{"排队中", "等待中", "已就绪", "比赛中", "已出局", "等待排名赛"};

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        addItemType(AppleyedEntity.Type.TOP, R.layout.item_recyclerview_rv_top);
        addItemType(AppleyedEntity.Type.BOTTOM, R.layout.item_recyclerview_rv_bottom);
        addItemType(AppleyedEntity.Type.CONTENT, R.layout.item_recyclerview_rv_content);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppleyedEntity tempApplyed) {
        switch (helper.getItemViewType()) {
            case AppleyedEntity.Type.TOP:
//                helper.setText(R.id.tv, item.getContent());
                break;
            case AppleyedEntity.Type.BOTTOM:
//                helper.setText(R.id.tv, item.getContent());
                break;
            case AppleyedEntity.Type.CONTENT:
                int position=helper.getLayoutPosition();
                if (0==position){
                    helper.setImageResource(R.id.iv_mid_applyed_range_icon,R.mipmap.award_level1);
                }else if (1==position){
                    helper.setImageResource(R.id.iv_mid_applyed_range_icon,R.mipmap.award_level2);
                }else if (2==position){
                    helper.setImageResource(R.id.iv_mid_applyed_range_icon,R.mipmap.award_level3);
                }else{
                    helper.setImageResource(R.id.iv_mid_applyed_range_icon,R.drawable.bg_white);
                }
//                switch () {
//                }
                helper.setText(R.id.tv_mid_applyed_nickname, tempApplyed.getPlayersName());
                helper.setText(R.id.tv_mid_applyed_state, states[tempApplyed.getPlayersStatus()] + " " + tempApplyed.getSeatInfo());
                helper.setText(R.id.tv_mid_applyed_chip, TextUtils.isEmpty(tempApplyed.getCurrPoint()) ? "-" : tempApplyed.getCurrPoint());
                break;
        }
    }

}
