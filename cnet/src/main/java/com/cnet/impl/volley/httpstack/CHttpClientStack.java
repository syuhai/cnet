package com.cnet.impl.volley.httpstack;

import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.cnet.impl.volley.request.CMultiPartRequest;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Cuckoo
 * @date 2016-09-17
 * @description
 *      采用HttpClient方式上传数据
 */
public class CHttpClientStack extends HttpClientStack {


    public CHttpClientStack() {
        super(new DefaultHttpClient());
    }

    protected void setEntityIfNonEmptyBody(HttpEntityEnclosingRequestBase httpRequest,
                                           Request<?> request) throws AuthFailureError {
        if (request instanceof CMultiPartRequest) {
            CMultiPartRequest multiRequest = (CMultiPartRequest) request;
            processStream(httpRequest,multiRequest);
        } else {
            super.setEntityIfNonEmptyBody(httpRequest, request);
        }
    }

    /**
     * 处理流上传
     * @param httpRequest
     * @param multiRequest
     */
    private void processStream(HttpEntityEnclosingRequestBase httpRequest,
                               CMultiPartRequest multiRequest){
        Iterator<Map.Entry<String, Object>> it = multiRequest.getParamsMap().entrySet().iterator();
        Map.Entry<String, Object> entry = null;
        MultipartEntity multipartEntity = new MultipartEntity();
        while (it.hasNext()) {
            entry = it.next();
            if (entry != null) {
                if (entry.getValue() instanceof File) {
                    multipartEntity.addPart(entry.getKey(), new FileBody(
                            (File) entry.getValue()));
                } else if (entry.getValue() instanceof Bitmap) {
                    Bitmap bt = (Bitmap) entry.getValue();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bt.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    multipartEntity.addPart(entry.getKey(), new ByteArrayBody(
                            baos.toByteArray(), entry.getKey()));
                } else if (entry.getValue() instanceof String) {
                    try {
                        String p = (String) entry.getValue();
                        multipartEntity.addPart(entry.getKey(), new StringBody(p, Charset.forName("UTF-8")));
                    } catch (UnsupportedEncodingException e) {
                    }
                }
            }
        }
        httpRequest.setEntity(multipartEntity);
    }
}
