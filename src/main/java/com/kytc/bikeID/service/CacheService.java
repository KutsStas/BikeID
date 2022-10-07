package com.kytc.bikeID.service;

public interface CacheService {


    String addValue(String key, String value);

    String addValueExpired(String key, String value, int seconds);

    String getValueByKey(String key);


}

