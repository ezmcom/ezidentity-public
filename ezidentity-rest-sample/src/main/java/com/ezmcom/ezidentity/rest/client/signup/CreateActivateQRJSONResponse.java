package com.ezmcom.ezidentity.rest.client.signup;

import java.io.Serializable;

public class CreateActivateQRJSONResponse implements Serializable {

    private static final long serialVersionUID = 5806731930777142677L;
    
    private Integer returnCode;
    private String returnMessage;
    private String encodedQRCodeImg;
    private String credential;
    
    public Integer getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }
    public String getReturnMessage() {
        return returnMessage;
    }
    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
    public String getEncodedQRCodeImg() {
        return encodedQRCodeImg;
    }
    public void setEncodedQRCodeImg(String encodedQRCodeImg) {
        this.encodedQRCodeImg = encodedQRCodeImg;
    }
    public String getCredential() {
        return credential;
    }
    public void setCredential(String credential) {
        this.credential = credential;
    }

}
