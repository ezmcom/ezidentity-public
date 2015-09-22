package com.ezmcom.ezidentity.rest.client.signup;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.ezmcom.ezidentity.rest.client.RestClient;
import com.google.gson.Gson;

public class RestClientCreateActivateQR extends RestClient {

    public RestClientCreateActivateQR(String restURLEndpoint) {
        super(restURLEndpoint);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CreateActivateQRJSONResponse executeCall(String resource, UrlEncodedFormEntity formEntity) throws Exception {
        CloseableHttpClient httpClient = null;
        CreateActivateQRJSONResponse jsonResp = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(restURLEndpoint+resource);
            post.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            post.setEntity(formEntity);
            
            HttpResponse resp = httpClient.execute(post);
            if( resp.getStatusLine() == null ) {
                throw new Exception("HttpClient call status is null");
            }
            int statusCode = resp.getStatusLine().getStatusCode();
            if( statusCode != HttpStatus.SC_OK ) {
                throw new Exception("HttpClient response status is not " + HttpStatus.SC_OK);
            }
            
            HttpEntity entity = resp.getEntity();
            if( entity == null ) {
                throw new Exception("HttpClient response entity is null");
            }
            
            String json = null;
            InputStream is = null;
            try {
                is = entity.getContent();
                byte[] content = null;
                try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    int byteRead = 0;
                    byte[] buffer = new byte[1024];
                    while( (byteRead = is.read(buffer)) != -1 ) {
                        baos.write(buffer, 0, byteRead);
                    }
                    content = baos.toByteArray();
                }
                
                if( content == null || content.length == 0 ) {
                    throw new Exception("HttpClient response content is null or empty");
                }
                
                json = new String(content, "UTF-8");
            }
            finally {
                if( is != null ) try { is.close(); } catch(Throwable th) {}
            }
            
            // Convert JSON to the mapped class object
            jsonResp = new Gson().fromJson(json, CreateActivateQRJSONResponse.class);
        }
        finally {
            if( httpClient != null ) try { httpClient.close(); } catch(Throwable th) {}
        }
        
        return jsonResp;
    }

}
