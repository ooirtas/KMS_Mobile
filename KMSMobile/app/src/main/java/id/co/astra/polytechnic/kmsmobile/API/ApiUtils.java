package com.polytechnic.astra.ac.id.knowledgemanagementsystem.API;

import com.polytechnic.astra.ac.id.knowledgemanagementsystem.API.Service.ProdiService;

public class ApiUtils {
    public static final String API_URL = "http://10.3.84.25:8080/";

    public ApiUtils() {
    }

    public static ProdiService getProdiService(){
        return RetrofitClient.getClient(API_URL).create(ProdiService.class);
    }
}
