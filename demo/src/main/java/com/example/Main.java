//package com.example;
//
//import com.njf2016.open.sdk.model.Concentrator;
//import com.njf2016.open.sdk.model.Device;
//import com.njf2016.open.sdk.model.DeviceData;
//import com.njf2016.open.sdk.openapi.OpenApi;
//import njf_open_service.openapi_sdk_demo.BuildConfig;
//
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        OpenApi.init(BuildConfig.appId, BuildConfig.secret);
//
//        String accessToken = OpenApi.getAccessToken();
//        System.out.println(accessToken);
//
//        List<Concentrator> concentrators = OpenApi.getIntelliConcentrators();
//        assert concentrators != null;
//        System.out.println(concentrators.get(0).getConcentrator());
//
//        List<Device> devices = OpenApi.getIntelliDevices(concentrators.get(0).getConcentrator());
//        assert devices != null;
//        System.out.println(devices.get(0).getId());
//
//        List<DeviceData> dataList = OpenApi.getIntelliDeviceData(devices.get(0).getId());
//        assert dataList != null;
//        System.out.println(dataList.get(0).getTime() + " : " + dataList.get(0).getData());
//    }
//}
