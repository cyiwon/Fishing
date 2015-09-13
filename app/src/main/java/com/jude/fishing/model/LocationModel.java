package com.jude.fishing.model;

import android.content.Context;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.jude.beam.model.AbsModel;
import com.jude.fishing.config.Dir;
import com.jude.fishing.model.bean.Location;
import com.jude.library.imageprovider.Utils;
import com.jude.utils.JFileManager;

/**
 * Created by Mr.Jude on 2015/1/28.
 * 地理位置的管理
 */
public class LocationModel extends AbsModel{

    private static final String FILENAME = "location";
    private Location location;

    public static LocationModel getInstance(){
        return getInstance(LocationModel.class);
    }

    public Location getCurLocation(){
        return location;
    }

    @Override
    protected void onAppCreateOnBackThread(Context ctx) {
        location = (Location) JFileManager.getInstance().getFolder(Dir.Object).readObjectFromFile(FILENAME);
        if (location == null)location = new Location();
        startLocation(ctx);
    }

    public void startLocation(final Context ctx){
        final LocationManagerProxy mLocationManagerProxy;
        mLocationManagerProxy = LocationManagerProxy.getInstance(ctx);
        mLocationManagerProxy.setGpsEnable(false);
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 500*1000, 15, new AMapLocationListener() {
                    @Override
                    public void onLocationChanged(AMapLocation aMapLocation) {
                        location.setLocation(aMapLocation);
                        Utils.Log("Save location.AdCode is " + aMapLocation.getAdCode());
                        JFileManager.getInstance().getFolder(Dir.Object).writeObjectToFile(location,FILENAME);
                        uploadAddress();
                    }


                    @Override
                    public void onLocationChanged(android.location.Location location) {

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });
    }

    public void uploadAddress(){
//        if (AccountModel.getInstance().getAccount() == null)return;
//        RequestMap params = new RequestMap();
//        params.put("lng",location.getLongitude()+"");
//        params.put("lat",location.getLatitude()+"");
//        params.put("address",location.getAddress());
//        RequestManager.getInstance().post(API.URL.SyncLocation,params,null);
    }
}
