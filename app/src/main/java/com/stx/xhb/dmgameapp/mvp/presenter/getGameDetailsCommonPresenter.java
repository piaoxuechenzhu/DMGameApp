package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.entity.GameDetailsContent;
import com.stx.xhb.dmgameapp.entity.GameNewsListEntity;
import com.stx.xhb.dmgameapp.mvp.contract.getGameDetailsCommonContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Author : jxnk25
 * Time: 2017/10/19 0019
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */

public class getGameDetailsCommonPresenter extends BasePresenter<getGameDetailsCommonContract.getGameDetailsDataView,getGameDetailsCommonContract.getGameDetailsCommonModel> implements getGameDetailsCommonContract.getGameDetailsCommonModel{
    @Override
    public void getGameNewsListData(String type, String id, String key, int page) {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new GameDetailsContent(page,id,key,type)))
                .url(API.GET_GAME_DETAILS)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        getView().showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().getGameNewsListFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            GameNewsListEntity newsListEntity = GsonUtil.newGson().fromJson(response, GameNewsListEntity.class);
                            if (newsListEntity.getCode() == Constants.SERVER_SUCCESS) {
                                getView().getGameNewsListDataSuccess(newsListEntity.getHtml());
                            } else {
                                getView().hideLoading();
                                getView().getGameNewsListFailed(newsListEntity.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        getView().hideLoading();
                    }
                });
    }

    @Override
    public void getGameToolsListData(String type, String id, String key, int page) {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new GameDetailsContent(page,id,key,type)))
                .url(API.GET_GAME_DETAILS)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        getView().showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().getGameToolsListFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            GameNewsListEntity newsListEntity = GsonUtil.newGson().fromJson(response, GameNewsListEntity.class);
                            if (newsListEntity.getCode() == Constants.SERVER_SUCCESS) {
                                getView().getGameToolsListDataSuccess(newsListEntity.getHtml());
                            } else {
                                getView().hideLoading();
                                getView().getGameToolsListFailed(newsListEntity.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        getView().hideLoading();
                    }
                });
    }
}
