package com.jude.fishing.model.service;


import com.jude.fishing.config.API;
import com.jude.fishing.model.entities.Account;
import com.jude.fishing.model.entities.Evaluate;
import com.jude.fishing.model.entities.EvaluateDetail;
import com.jude.fishing.model.entities.PersonAround;
import com.jude.fishing.model.entities.PersonBrief;
import com.jude.fishing.model.entities.PersonDetail;
import com.jude.fishing.model.entities.PlaceBrief;
import com.jude.fishing.model.entities.PlaceDetail;
import com.jude.fishing.model.entities.Seed;
import com.jude.fishing.model.entities.Token;

import java.util.List;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by Mr.Jude on 2015/8/23.
 * 服务器接口
 */
public interface Service {

    @GET(API.URL.QiNiuToken)
    Observable<Token> getQiNiuToken();

    @FormUrlEncoded
    @POST(API.URL.GetPlace)
    Observable<List<PlaceBrief>> SyncPlace(
            @Field("time")String lastTime);

    @FormUrlEncoded
    @POST(API.URL.AddPlace)
    Observable<Object> PublishPlace(
            @Field("id")int id,
            @Field("name")String name,
            @Field("preview")String preview,
            @Field("briefAddr")String briefAddr,
            @Field("address")String address,
            @Field("cost")int cost,
            @Field("costType")int costType,
            @Field("fishType")String fishType,
            @Field("poolType")int poolType,
            @Field("serviceType")String serviceType,
            @Field("tel")String tel,
            @Field("content")String content,
            @Field("picture")String picture,
            @Field("lat")double lat,
            @Field("lng")double lng
            );

    @FormUrlEncoded
    @POST(API.URL.GetPlaceDetail)
    Observable<PlaceDetail> getPlaceDetail(@Field("id") int id);

    @FormUrlEncoded
    @POST(API.URL.GetEvaluate)
    Observable<List<Evaluate>> getEvaluate(
            @Field("id") int id,
            @Field("page") int page);

    @FormUrlEncoded
    @POST(API.URL.GetEvaluateDetail)
    Observable<EvaluateDetail> getEvaluateDetail(
            @Field("id") int id);

    @FormUrlEncoded
    @POST(API.URL.PublishEvaluate)
    Observable<Object> publishEvaluate(
            @Field("pid") int pid,
            @Field("content") String content,
            @Field("images") String images,
            @Field("score") int score);

    @FormUrlEncoded
    @POST(API.URL.CollectPlace)
    Observable<Object> collectPlace(
            @Field("id") int id);


    @FormUrlEncoded
    @POST(API.URL.UnCollectPlace)
    Observable<Object> unCollectPlace(
            @Field("id") int id);


    @GET(API.URL.MyColectPlace)
    Observable<List<PlaceBrief>> myPlaceCollect();

    @FormUrlEncoded
    @POST(API.URL.EvaluateComment)
    Observable<Object> EvaluateComment(
            @Field("sid") int sid,
            @Field("fid") int fid,
            @Field("content") String content);

    @FormUrlEncoded
    @POST(API.URL.Register)
    Observable<Object> register(@Field("tel") String tel,
                                @Field("password") String password,
                                @Field("code") String code);


    @FormUrlEncoded
    @POST(API.URL.UpdateLocation)
    Observable<Object> UpdateLocation(
            @Field("lat") double lat,
            @Field("lng") double lng,
            @Field("address") String address);

    @FormUrlEncoded
    @POST(API.URL.GetNearBy)
    Observable<List<PersonAround>> GetNearBy(
            @Field("lat") double lat,
            @Field("lng") double lng,
            @Field("page") int page
            );

    @FormUrlEncoded
    @POST(API.URL.Login)
    Observable<Account> login(@Field("tel") String tel, @Field("password") String password);

    /**
     * 修改用户数据
     * @param avatar
     * @param name
     * @param gender  0保密 1男 2女 //TODO 接口统一
     * @param address
     * @param age     钓龄
     * @param skill   擅长
     * @param sign    签名
     */
    @FormUrlEncoded
    @POST(API.URL.ModInfo)
    Observable<Object> modInfo(@Field("avatar") String avatar,
                               @Field("name") String name,
                               @Field("gender") int gender,
                               @Field("address") String address,
                               @Field("age") int age,
                               @Field("skill") String skill,
                               @Field("sign") String sign);

    @FormUrlEncoded
    @POST(API.URL.ModPass)
    Observable<Object> modPass(@Field("oldpwd") String oldpwd, @Field("newpwd") String newpwd);

    /**
     * 检查手机是否可用 201为已注册
     * @param tel
     * @return
     */
    @FormUrlEncoded
    @POST(API.URL.CheckTel)
    Observable<Object> checkTel(@Field("tel") String tel);

    /**
     * 绑定手机
     * @param tel
     * @param code
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(API.URL.BindTel)
    Observable<Object> bindTel(@Field("tel")String tel,
                               @Field("code")String code,
                               @Field("password")String password);

    @FormUrlEncoded
    @POST(API.URL.ResetPass)
    Observable<Object> resetPass(@Field("tel")String tel,
                                 @Field("code")String code,
                                 @Field("password")String password);

    @FormUrlEncoded
    @POST(API.URL.Attend)
    Observable<Object> attend(@Field("id")int id);

    @FormUrlEncoded
    @POST(API.URL.UnAttend)
    Observable<Object> unAttend(@Field("id")int id);

    @POST(API.URL.MyAttend)
    Observable<PersonBrief> myAttend();

    @POST(API.URL.MyFans)
    Observable<PersonBrief> myFans();

    @FormUrlEncoded
    @POST(API.URL.GetUserInfo)
    Observable<PersonDetail> getUserInfo(@Field("id")int id);

    @POST(API.URL.GetMyInfo)
    Observable<PersonDetail> getMyInfo();

    /**
     * 获取广场微博
     * @param page 页码
     * @param count 可选值 默认20
     * @return
     */
    @FormUrlEncoded
    @POST(API.URL.GetWeiboGround)
    Observable<List<Seed>> getWeiboGround(@Field("page")int page);

    @FormUrlEncoded
    @POST(API.URL.GetWeiboFriend)
    Observable<List<Seed>> getWeiboFriend(@Field("page")int page);

    @FormUrlEncoded
    @POST(API.URL.GetWeiboNearby)
    Observable<List<Seed>> getWeiboNearby(@Field("page")int page,
                                          @Field("count")int count,
                                          @Field("lat") double lat,
                                          @Field("lng") double lng);

    @FormUrlEncoded
    @POST(API.URL.GetWeiboMy)
    Observable<List<Seed>> getWeiboMy(@Field("page")int page);

    @FormUrlEncoded
    @POST(API.URL.AddWeibo)
    Observable<Object> addWeibo(@Field("content")String content,
                                @Field("images")String images,
                                @Field("address")String address,
                                @Field("lng")double lng,
                                @Field("lat")double lat);
}
