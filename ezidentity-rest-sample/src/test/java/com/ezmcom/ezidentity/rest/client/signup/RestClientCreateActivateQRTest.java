package com.ezmcom.ezidentity.rest.client.signup;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RestClientCreateActivateQRTest {

    RestClientCreateActivateQR target = null;
    String restURLEndpoint = "http://demo.ezmcom.com/ezwsrest";
    
    @Before
    public void before() {
        target = new RestClientCreateActivateQR(restURLEndpoint);
    }
    
    @Test
    public void testExecuteCall() {
        try {
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("actionBy", URLEncoder.encode("RestClientTest", "UTF-8")));
            params.add(new BasicNameValuePair("samlId", URLEncoder.encode("938B07ACA533775A3AFDAA710C87B30E", "UTF-8")));
            params.add(new BasicNameValuePair("userId", URLEncoder.encode("abc@8i.com.my", "UTF-8")));
            params.add(new BasicNameValuePair("contactType", URLEncoder.encode("2", "UTF-8")));
            params.add(new BasicNameValuePair("contactDetail", URLEncoder.encode("abc@8i.com.my", "UTF-8")));
            
            String resource = "/rest/engine/softtk/s/regeztoken"; // must start with a '/' char
            CreateActivateQRJSONResponse resp = target.executeCall(resource, params);
            
            // test object not null
            Assert.assertNotNull(resp);
            
            // test return code not null
            Assert.assertNotNull(resp.getReturnCode()); 
            
            // test return code is Success or Token Quota Reach, if Token Quota Reach, call to delete account
            Assert.assertTrue(resp.getReturnCode().intValue() == 0 || resp.getReturnCode().intValue() == -28);
            
            // test credential not null
            Assert.assertNotNull(resp.getCredential());
            
            // test Base64-encoded QRCode image is not null
            Assert.assertNotNull(resp.getEncodedQRCodeImg());
        }
        catch(Exception ex) {
            throw new AssertionError("Error in testing RestClientCreateActivateQR.executeCall", ex);
        }
    }
    
}
