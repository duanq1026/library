package com.hniu.service;


import com.hniu.exception.SystemErrorException;

import java.util.Map;

public interface WxLoginService  {

    Map<String, Object> wxLogin(String code) throws SystemErrorException;
}
