package com.example.john.myapplication.recyclerview;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by John on 2017/3/15.
 * <br>已报名玩家</br>
 */

public class AppleyedEntity implements MultiItemEntity {
    private int itemType;
    private String playersRank;//排名-只有出局的玩家或者比赛结束的比赛，并且当前比赛不是多日MTT，或者为多日MTT最后一场比赛时才有
    private String playersId;//玩家ID
    private int playersStatus=0;//玩家状态
    private String currPoint;//玩家当前筹码数
    private String playersName;//玩家姓名
    private String seatInfo;//座位信息

    public int getType() {
        return itemType;
    }

    public void setType(int itemType) {
        this.itemType = itemType;
    }

    public String getPlayersRank() {
        return playersRank;
    }

    public void setPlayersRank(String playersRank) {
        this.playersRank = playersRank;
    }

    public String getPlayersId() {
        return playersId;
    }

    public void setPlayersId(String playersId) {
        this.playersId = playersId;
    }

    public int getPlayersStatus() {
        return playersStatus;
    }

    public void setPlayersStatus(String playersStatus) {
        if (!TextUtils.isEmpty(playersStatus)){
            try {
                this.playersStatus = Integer.parseInt(playersStatus);
            }catch (Exception e){

            }
        }

    }

    public String getCurrPoint() {
        return currPoint;
    }

    public void setCurrPoint(String currPoint) {
        this.currPoint = currPoint;
    }

    public String getPlayersName() {
        return playersName;
    }

    public void setPlayersName(String playersName) {
        this.playersName = playersName;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public class Type{
       public static final int TOP=0;
       public static final int CONTENT=1;
       public static final int BOTTOM=2;
    }
}
