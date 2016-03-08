package com.tboys.expressdelivery.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.tboys.expressdelivery.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Delivery_company_Activity extends AppCompatActivity implements LocationSource, PoiSearch.OnPoiSearchListener {


    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    //声明AMapLocationClient类对象
   private AMapLocationClient mapLocationClient = null;
    //声明定位回调监听器
   private AMapLocationListener mapLocationListener;
    //声明mLocationOption对象
   private AMapLocationClientOption mapLocationClientOption = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
   private MapView mapView;
   private AMap amap;

    private PoiResult poiResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("liuqian", "onCreate");
        setContentView(R.layout.activity_delivery_company_);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        amap = mapView.getMap();
        amap.setLocationSource(this);
        amap.setMyLocationEnabled(true);

        amap = mapView.getMap();


        // 设置定位
        amap.setLocationSource(this);
        amap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        amap.setMyLocationEnabled(true);
        //查找我的位置的按钮
        amap.getUiSettings().setMyLocationButtonEnabled(true);
        amap.getUiSettings().setZoomControlsEnabled(true);
        amap.getUiSettings().setZoomGesturesEnabled(true);
        amap.getUiSettings().setScaleControlsEnabled(true);
        amap.moveCamera(CameraUpdateFactory.zoomTo(16));

//        amap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
//            @Override
//            public void onCameraChange(CameraPosition cameraPosition) {
////                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(cameraPosition.target);
////                amap.moveCamera(cameraUpdate);
//                Toast.makeText(Delivery_company_Activity.this, cameraPosition.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCameraChangeFinish(CameraPosition cameraPosition) {
//                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(cameraPosition.target);
//                amap.moveCamera(cameraUpdate);
//            }
//        });

//        mapLocationListener = new AMapLocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation amapLocation) {
//                if (amapLocation != null) {
//                    if (amapLocation.getErrorCode() == 0) {
//                        //定位成功回调信息，设置相关消息
//                        //获取当前定位结果来源，如网络定位结果，详见定位类型表
//                        Log.d("ss", String.valueOf(amapLocation.getLocationType()));
//                        //获取纬度
//                        Log.d("ss", String.valueOf(amapLocation.getLatitude()));
//                        amapLocation.getLongitude();//获取经度
//                        Log.d("ss", String.valueOf(amapLocation.getLatitude()));
//                        amapLocation.getAccuracy();//获取精度信息
//                        Log.d("ss", String.valueOf(amapLocation.getLatitude()));
//                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date date = new Date(amapLocation.getTime());
//                        df.format(date);//定位时间
//                        Log.d("ss", String.valueOf(amapLocation.getLatitude()));
//                        amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                        //国家信息
//                        Log.d("ss", String.valueOf(amapLocation.getCountry()));
//                        //省信息
//                        Log.d("ss", String.valueOf(amapLocation.getProvince()));
//                        //城市信息
//                        Log.d("ss", String.valueOf(amapLocation.getCity()));
//                        //城区信息
//                        Log.d("ss", String.valueOf(amapLocation.getDistrict()));
//                        //街道信息
//                        Log.d("ss", String.valueOf(amapLocation.getStreet()));
//                        //街道门牌号信息
//                        Log.d("ss", String.valueOf(amapLocation.getStreetNum()));
//                        //城市编码
//                        Log.d("ss", String.valueOf(amapLocation.getCityCode()));
//                        //地区编码
//                        Log.d("ss", String.valueOf(amapLocation.getAdCode()));
//                    } else {
//                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
//                        Log.e("AmapError","location Error, ErrCode:"
//                                + amapLocation.getErrorCode() + ", errInfo:"
//                                + amapLocation.getErrorInfo());
//                    }
//                }
//            }
//        };
//
//
//
//
//
//
//
//
//
//
//
//
//
//

//        mapLocationClient.startLocation();

        seach();

    }

    private void seach() {
        currentPage = 0;
        query = new PoiSearch.Query("快递","","长沙");
        query.setPageSize(10);
        query.setPageSize(currentPage);

        PoiSearch poiSearch = new PoiSearch(this,query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void activate(final OnLocationChangedListener listener) {
        mapLocationClient = new AMapLocationClient(this);
        mapLocationClientOption = new AMapLocationClientOption();
        mapLocationClientOption.setOnceLocation(true);
        mapLocationClient.setLocationOption(mapLocationClientOption);
        mapLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                listener.onLocationChanged(aMapLocation);
            }
        });
        //开始定位
        mapLocationClient.startLocation();
    }

    @Override
    public void deactivate() {
        mapLocationClient.stopLocation();
    }

    @Override
    public void onPoiSearched(PoiResult result, int i) {
        if(i == 0) {
            if(result != null && result.getQuery() != null) {
                if(result.getQuery().equals(query)) {
                    Log.d("sss",result.toString());
                    poiResult = result;
                    List<PoiItem> poiItems = poiResult.getPois();
                    List<SuggestionCity> suggestionCities = poiResult.getSearchSuggestionCitys();
                    Log.d("sss", String.valueOf(poiResult == null) + String.valueOf( poiItems == null));
                    Log.d("sss", String.valueOf(poiItems.size()));

                    if(poiItems != null && poiItems.size() > 0) {
//                        PoiOverlay poiOverlay = new PoiOverlay(amap, poiItems);
//                        Log.d("sss","hahaha poiItem");
//                        poiOverlay.removeFromMap();
//                        poiOverlay.addToMap();
//                        poiOverlay.zoomToSpan();
                        for(PoiItem p : poiItems) {
                            Log.d("sss",p.toString());
                        }
                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                        Log.d("sss","hahaha suggestionCities>0");
                        Toast.makeText(Delivery_company_Activity.this, suggestionCities.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("sss","hahaha suggestionCities< 0");
                        Toast.makeText(Delivery_company_Activity.this, "没有，出错", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(Delivery_company_Activity.this, "没有，结果", Toast.LENGTH_SHORT).show();
            }
        } else if (i == 27) {
            Toast.makeText(Delivery_company_Activity.this, "网络错误", Toast.LENGTH_SHORT).show();
        } else if (i == 32) {
            Toast.makeText(Delivery_company_Activity.this, "KEY错", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Delivery_company_Activity.this, "其他错误" + i, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
