package com.vonzhou.learn.utils;

import net.sf.json.JSONObject;

public class JsonUtils {
    public static String bean2Json(Object object) {
        JSONObject jsonObject = JSONObject.fromObject(object);
        return jsonObject.toString();
    }
}
