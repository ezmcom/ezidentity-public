package com.ezmcom.ezidentity.rest.client;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

public abstract class RestClient {

    protected String restURLEndpoint;
    
    public RestClient(String restURLEndpoint) {
        this.restURLEndpoint = restURLEndpoint;
    }

    public abstract <T> T executeCall(String resource, UrlEncodedFormEntity entity) throws Exception;
    
    public <T> T executeCall(String resource, List<NameValuePair> pairs) throws Exception {
        return executeCall(resource, new UrlEncodedFormEntity(pairs, "UTF-8"));
    }
    
}
