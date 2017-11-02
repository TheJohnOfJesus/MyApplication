package com.example.john.myapplication.recyclerview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.john.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/14.
 */

public class ApplyedAdapter extends BaseAdapter {
    private List<AppleyedEntity> list = new ArrayList<AppleyedEntity>();
    private LayoutInflater inflater;
    private TextView noMessage;
    private AppleyedEntity tempApplyed;
    private String state = "", rank;
    private Context context;
    private String states[] = new String[]{"排队中", "等待中", "已就绪", "比赛中", "已出局", "等待排名赛"};

    public ApplyedAdapter(Context context, TextView noMessage) {
        inflater = LayoutInflater.from(context);
        this.noMessage = noMessage;
        this.context = context;
    }

    public void setData(List<AppleyedEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApplyedAdapter.ViewHolder holder = null;
        tempApplyed = list.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_midetail_applyed, null);
            holder = new ApplyedAdapter.ViewHolder();
            holder.ivRange = (ImageView) convertView.findViewById(R.id.iv_mid_applyed_range_icon);
            holder.range = (TextView) convertView.findViewById(R.id.tv_mid_applyed_range);
            holder.nickname = (TextView) convertView.findViewById(R.id.tv_mid_applyed_nickname);
            holder.state = (TextView) convertView.findViewById(R.id.tv_mid_applyed_state);
            holder.chip = (TextView) convertView.findViewById(R.id.tv_mid_applyed_chip);
            convertView.setTag(holder);
        } else {
            holder = (ApplyedAdapter.ViewHolder) convertView.getTag();
        }
        rank = tempApplyed.getPlayersRank();
        if (TextUtils.isEmpty(rank)) {
            holder.ivRange.setVisibility(View.GONE);
            holder.range.setVisibility(View.VISIBLE);
            holder.range.setText("-");
        } else if ("1".equals(rank)) {
            holder.ivRange.setImageResource(R.mipmap.award_level1);
            holder.ivRange.setVisibility(View.VISIBLE);
            holder.range.setVisibility(View.GONE);
        } else if ("2".equals(rank)) {
            holder.ivRange.setImageResource(R.mipmap.award_level2);
            holder.ivRange.setVisibility(View.VISIBLE);
            holder.range.setVisibility(View.GONE);
        } else if ("3".equals(rank)) {
            holder.ivRange.setImageResource(R.mipmap.award_level3);
            holder.ivRange.setVisibility(View.VISIBLE);
            holder.range.setVisibility(View.GONE);
        } else {
            holder.ivRange.setVisibility(View.GONE);
        }
        holder.nickname.setText(tempApplyed.getPlayersName());
        holder.state.setText(states[tempApplyed.getPlayersStatus()] + " " + tempApplyed.getSeatInfo());
        holder.chip.setText(TextUtils.isEmpty(tempApplyed.getCurrPoint()) ? "-" : tempApplyed.getCurrPoint());

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (getCount() == 0) {
            noMessage.setVisibility(View.VISIBLE);
        } else {
            noMessage.setVisibility(View.GONE);
        }
    }

    class ViewHolder {
        ImageView ivRange;
        TextView range, nickname, state, chip;
    }
}
