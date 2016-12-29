package com.cnet.def.http;

import android.content.Context;

import com.cnet.def.http.request.CAbstractRequst;
import com.cnet.def.http.resp.IRespBase;

import java.util.HashMap;

/**
 * @author Cuckoo
 * @date 2016-09-02
 * @description To manage all http request.
 */
public interface IHttpServer extends IHttpDownloadServer {

    /**
     * Get request by sync.
     *
     * @param method           Http request method. the vlaue is {@link CHttpMethod}
     * @param url
     * @param postParams       Params.
     * @param postParamsByJson 通过json上传参数， 与postParams互斥
     * @param headerMap        Headers
     * @param respObjClass     Response elements of data.
     * @param isReturnJson     Is Need return json?
     * @param request          The request infos
     * @param <T>
     * @param <E>
     * @return
     */
    <T, E extends IRespBase> E getDataBySync(Context context, CHttpMethod method, String url, HashMap<String, Object> postParams,
                                             String postParamsByJson,
                                             HashMap<String, String> headerMap,
                                             Class<T> respObjClass, boolean isReturnJson, CAbstractRequst request);


    /**
     * Get request by async
     *
     * @param method           Http request method. the vlaue is {@link CHttpMethod}
     * @param url
     * @param postParams       Params.
     * @param postParamsByJson 通过json上传参数， 与postParams互斥
     * @param headerMap        Headers
     * @param respObjClass     Response elements of data.
     * @param isReturnJson     Is Need return json?
     * @param request          The request infos
     * @param <T>
     * @return
     */
    <T> boolean getData(Context context, CHttpMethod method, String url, HashMap<String, Object> postParams,
                        String postParamsByJson,
                        HashMap<String, String> headerMap,
                        Class<T> respObjClass, boolean isReturnJson,
                        IHttpServerCallback callback, CAbstractRequst request);

}
