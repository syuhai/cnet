package com.cnet.impl.volley.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.cnet.impl.volley.HttpHeaderUtil;
import com.cnet.util.CListUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cuckoo
 * @date 2016-09-06
 * @description
 *      Custome StringRequest
 */
public class CMultiPartRequest extends Request<String> {
    private HashMap<String,Object> paramsMap = new HashMap<>() ;
    private HashMap<String,String> headerMap = null ;
    private final Response.Listener<String> mListener ;
    public CMultiPartRequest(int method, String url, HashMap<String, Object> postParams,
                             HashMap<String, String> headerMap, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        this.paramsMap = postParams;
        this.headerMap = headerMap;
    }


    @Override
    public Map<String, String> getHeaders() {
        return HttpHeaderUtil.appendAllHeader(headerMap);
    }

    public HashMap<String, Object> getParamsMap() {
        return CListUtil.f(paramsMap);
    }

    @Override
    public String getBodyContentType() {
        return null;
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}
