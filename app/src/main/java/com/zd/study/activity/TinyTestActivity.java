package com.zd.study.activity;

import com.zd.study.java.bean.ListNode;

import java.util.Map;

import bankcomm.gmhelper.GMHelp;

public class TinyTestActivity {

    public static void main(String[] args) throws Exception {

        getRequest();
    }


    public static void getRequest() throws Exception {
        String priHex = "3C37D4D3384F15547754D8B8CC38BCE9B82451BFCD7E684222DEBAC7CE3C16C5";
        String xHex = "D483DF90C232ED64560635BBC8E3905235016C3B56C42733A618B3CBE51A72D4";
        String yHex = "41E1422B187B861DCB5C74B6DC5AFBBC644ED4532F0E0F418270B3F42DC3B611";
        String message = "{\"requestTime\":\"" + System.currentTimeMillis() + "\",\"openid\":\"36ed081c-42ce-41f6-88ba-aad5d430fb27\"}";
        System.out.println("加密前明文为：" + message);
        Map<String, Object> encryptMap = GMHelp.encrypt(xHex, yHex, priHex, message);
        String appletResponse = (String)encryptMap.get("appletResponse");
        System.out.println("加密后密文为：appletRequest:" + appletResponse);
        String appletKey = (String)encryptMap.get("appletKey");
        System.out.println("加密后秘钥为：appletKey:" + appletKey);
        String appletSign = (String)encryptMap.get("appletSign");
        System.out.println("加密后签名为：appletSign:" + appletSign);
    }
}
