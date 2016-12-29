package com.cnetsample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cnet.def.caller.IDownloadCallback;
import com.cnet.def.caller.IReqRefactor;
import com.cnet.def.http.CHttpMethod;
import com.cnet.def.http.HttpServer;
import com.cnet.def.http.request.CAbstractRequst;
import com.cnet.def.http.resp.IErrResp;
import com.cnet.def.http.resp.IRespBase;
import com.cnet.impl.volley.VolleyHttpServer;
import com.cnet.sample.R;
import com.cnet.util.StringUtil;
import com.cnet.util.gson.GsonUtil;
import com.cnetsample.demo.resp.ReqAppStartImg;
import com.cnetsample.demo.resp.ReqFeedback;
import com.cnetsample.demo.resp.ReqSubCategoryProductList;
import com.cnetsample.demo.resp.SyncReqAppStartImg;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity  implements Handler.Callback{
    //单异步
    private final int REQ_ONASYNC = 1;
    private final int REQ_ONEASYNC_Major = 2;
    private final int REQ_ONEASYNC_SYNC = 3;
    private final int REQ_MOREASYNC_ONE = 4;
    private final int REQ_MOREASYNC_TWO = 5;
    private final int REQ_MOREASYNC_THREE = 6;
    private final int REQ_MOREASYNC_SYNC_MAJORONE = 7;
    private final int REQ_MOREASYNC_SYNC_MAJORONE_SYNC = 8;
    private final int REQ_MOREASYNC_SYNC_MAJORTWO = 9;
    private final int REQ_MOREASYNC_SYNC_MAJORTWO_SYNC = 10;
    private final int REQ_MOREASYNC_SYNC_MAJORThree = 11;
    private final int REQ_ONEASYNC_CHANGEPARAMS_MAJOR = 12;
    private final int REQ_ONEASYNC_CHANGEPARAMS_SYNC_CHANGED = 13;
    private final int REQ_ONEASYNC_CHANGEPARAMS_SYNC_UNCHANGED = 14;
    private final int REQ_UPLOAD_FILE = 15;


    @BindView(R.id.mainactivity_home)
    TextView homeTv;
    @BindView(R.id.grid1)
    Button grid1;
    @BindView(R.id.grid2)
    Button grid2;
    @BindView(R.id.grid3)
    Button grid3;
    @BindView(R.id.grid4)
    Button grid4;
    @BindView(R.id.grid5)
    Button grid5;
    @BindView(R.id.grid6)
    Button grid6;
    @BindView(R.id.grid7)
    Button grid7;
    @BindView(R.id.grid8)
    Button grid8;
    @BindView(R.id.grid9)
    Button grid9;
    @BindView(R.id.scrollview)
    ScrollView scrollview ;

    private StringBuffer sb = new StringBuffer();
    private HashMap<Integer,String> reqMap = null;
    private Handler handler = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cnet.sample.R.layout.activity_main);
        ButterKnife.bind(this);
        homeTv.setText("Hello world");
        handler = new Handler(this);
    }


    @Override
    public boolean handleMessage(Message msg) {
        scrollview.fullScroll(View.FOCUS_DOWN);
        return false;
    }

    @OnClick({R.id.grid1, R.id.grid2, R.id.grid3,
            R.id.grid4,R.id.grid5, R.id.grid6, R.id.grid7, R.id.grid8, R.id.grid9})
    public void onClick(View view) {
        HttpServer.init(this, VolleyHttpServer.class,true);
        switch (view.getId()) {
            case R.id.grid1:
                //单异步
                onAsync();
                break;
            case R.id.grid2:
                //单异步+同步
                onOneAsyncAndSync();
                break;
            case R.id.grid3:
                //多异步
                onMoreAsyncs();
                break;
            case R.id.grid4:
                //多异步+同步
                onMoreAsyncsAndSync();
                break;
            case R.id.grid5:
                //同步
                onSync();
                break;
            case R.id.grid6:
                //文件上传
                onPostFile();
                break;
            case R.id.grid7:
                //文件下载
                downloadFile();
                break;
            case R.id.grid8:
                //Clean
                ic("");
                break;
            case R.id.grid9:
                //单异步+同步+换参
                onOneAsyncAndSyncAndChangeParam();
                break;
        }
    }

    /**
     * 同步
     */
    private void onSync(){
        showLoading();
        ic("==同步==");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (Exception e){

                }
                SyncReqAppStartImg startImg = new SyncReqAppStartImg();
                final IRespBase respBase = HttpServer.getDataBySync(startImg);
                if( respBase != null ){
                    LogUtil.e(respBase.toString());
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            i("" + GsonUtil.toJson(respBase));
                        }
                    });
                }
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        i("==同步==end");
                        dismissLoading();
                    }
                });
            }
        }).start();
    }

    private void onAsync() {
        i("\n==单异步==");
        ReqAppStartImg startImg = new ReqAppStartImg();
        startImg.setReqCode(REQ_ONASYNC);
        HttpServer.getData(this,startImg);
    }

    private void onOneAsyncAndSync() {
        i("\n==单异步+同步==");
        ReqAppStartImg majorRequest = new ReqAppStartImg();
        majorRequest.setReqCode(REQ_ONEASYNC_Major);
        //同步请求
        ReqSubCategoryProductList syncRequest = new ReqSubCategoryProductList();
        syncRequest.setReqCode(REQ_ONEASYNC_SYNC);
        syncRequest.setPostJson(true);
        syncRequest.setReqMethod(CHttpMethod.POST);
        majorRequest.addSyncRequest(syncRequest);
        HttpServer.getData(this,majorRequest);
    }

    private void onOneAsyncAndSyncAndChangeParam() {
        i("\n==单异步+同步+换参==");
        ReqAppStartImg majorRequest = new ReqAppStartImg();
        majorRequest.setReqCode(REQ_ONEASYNC_CHANGEPARAMS_MAJOR);
        //同步请求
        final ReqSubCategoryProductList syncRequest = new ReqSubCategoryProductList();
        syncRequest.setReqCode(REQ_ONEASYNC_CHANGEPARAMS_SYNC_CHANGED);
        syncRequest.setPostJson(true);
        syncRequest.setReqMethod(CHttpMethod.POST);
        majorRequest.addSyncRequest(syncRequest);

        final ReqSubCategoryProductList syncTwoRequest = new ReqSubCategoryProductList();
        syncTwoRequest.setReqCode(REQ_ONEASYNC_CHANGEPARAMS_SYNC_UNCHANGED);
        syncTwoRequest.setPostJson(true);
        syncTwoRequest.setReqMethod(CHttpMethod.POST);
        majorRequest.addSyncRequest(syncTwoRequest);
        //更换参数
        IReqRefactor reqRefactor = new IReqRefactor() {
            @Override
            public CAbstractRequst onRequestRefactor(int reqCode, CAbstractRequst originalRequest) {

                if(reqCode == REQ_ONEASYNC_CHANGEPARAMS_SYNC_CHANGED){
                    syncRequest.categoryID = "100";
                    i("\n==onRequestRefactor==" + parseReqCode(reqCode) + "==将categoryID的值有2替换成100==");
                    return syncRequest;
                }else if(reqCode == REQ_ONEASYNC_CHANGEPARAMS_SYNC_UNCHANGED){
                    i("\n==onRequestRefactor==" + parseReqCode(reqCode) );
                }
                return null;
            }
        };
        HttpServer.getData(this,reqRefactor,majorRequest);
    }

    private void onMoreAsyncs() {
        i("\n==多异步==");

        ReqSubCategoryProductList reqOne = new ReqSubCategoryProductList();
        reqOne.setReqCode(REQ_MOREASYNC_ONE);
        reqOne.setPostJson(true);
        reqOne.setReqMethod(CHttpMethod.POST);

        ReqSubCategoryProductList reqTwo = new ReqSubCategoryProductList();
        reqTwo.setReqCode(REQ_MOREASYNC_TWO);
        reqTwo.categoryID = "1";
        reqTwo.setPostJson(true);
        reqTwo.setReqMethod(CHttpMethod.POST);

        ReqFeedback reqThree = new ReqFeedback();
        reqThree.filePath = new File("storage/emulated/0/C/t/cc.jpg");
        reqThree.setReqCode(REQ_MOREASYNC_THREE);
        HttpServer.getData(this,reqOne,reqTwo,reqThree);
    }

    private void onMoreAsyncsAndSync() {
        i("\n==多异步+同步==");

        ReqFeedback majorOneReq = new ReqFeedback();
        majorOneReq.filePath = new File("storage/emulated/0/C/t/cc.jpg");
        majorOneReq.setReqCode(REQ_MOREASYNC_SYNC_MAJORONE);
        majorOneReq.setShowLoadding(true);

        ReqSubCategoryProductList majorOneSyncReq = new ReqSubCategoryProductList();
        majorOneSyncReq.setReqCode(REQ_MOREASYNC_SYNC_MAJORONE_SYNC);
        majorOneSyncReq.setPostJson(true);
        majorOneSyncReq.setReqMethod(CHttpMethod.POST);
        majorOneReq.addSyncRequest(majorOneSyncReq);

        ReqSubCategoryProductList majorTwoReq = new ReqSubCategoryProductList();
        majorTwoReq.setReqCode(REQ_MOREASYNC_SYNC_MAJORTWO);
        majorTwoReq.categoryID = "1";
        majorTwoReq.setPostJson(true);
        majorTwoReq.setReqMethod(CHttpMethod.POST);

        ReqFeedback majorTwoSyncReq = new ReqFeedback();
        majorTwoSyncReq.filePath = new File("storage/emulated/0/C/t/cc.jpg");
        majorTwoSyncReq.setReqCode(REQ_MOREASYNC_SYNC_MAJORTWO_SYNC);
        majorTwoReq.addSyncRequest(majorTwoSyncReq);

        ReqAppStartImg majorThreeReq = new ReqAppStartImg();
        majorThreeReq.setReqCode(REQ_MOREASYNC_SYNC_MAJORThree);

        HttpServer.getData(this,majorOneReq,majorTwoReq,majorThreeReq);
    }

    private void onPostFile(){
        i("==上传文件==");
        ReqFeedback feedBackReq = new ReqFeedback();
        feedBackReq.filePath = new File("storage/emulated/0/C/t/cc.jpg");
        feedBackReq.setReqCode(REQ_UPLOAD_FILE);
        HttpServer.getData(this,feedBackReq);
    }


    private void downloadFile(){
        i("==下载文件==");
        String url = "http://i1.mopimg.cn/img/tt/2015-08/1496/20150809135202504.jpg790x600.jpg";
        String desPath = "storage/emulated/0/C/t/"+ UUID.randomUUID().toString()+".jpg";
        HttpServer.downloadFile(url, desPath, true, new IDownloadCallback() {
            @Override
            public void onStart() {
                i("onStart()");
            }

            @Override
            public void onProgress(double downloadSize, double totalSize) {
                i("onProgress(" + downloadSize + "/" + totalSize+ ")");
            }

            @Override
            public void onComplete(String destiationPath) {
                i("onComplete()\n"+ "   " + destiationPath);
            }

            @Override
            public void onFailure(int errStatus, String errMsg) {
                i("onFailure(): "+  + errStatus + " msg: " + errMsg);
            }
        });
    }


    @Override
    public <T> void onInflateRequest(int reqCode, CAbstractRequst request, T respDataObj, IRespBase<T> resp) {
        i("==onInflateRequest==" + parseReqCode(reqCode) + "\n" + resp.getJson());
    }

    @Override
    public void onRequestStart(int reqCode, CAbstractRequst request) {
        i("\n==onRequestStart==" + parseReqCode(reqCode) + "\n" + request.getFullUrl());

    }

    @Override
    public void onRequestFailure(int reqCode, CAbstractRequst request, IErrResp errResp) {
        i("==onRequestFailure==" + parseReqCode(reqCode) +"\n"+ errResp.getErrMsg());
    }

    private String parseReqCode(int reqCode){
        if( reqMap == null ){
            reqMap = new HashMap<>();
            reqMap.put(REQ_ONASYNC,"单异步");
            reqMap.put(REQ_ONEASYNC_Major,"单异步+同步_主请求");
            reqMap.put(REQ_ONEASYNC_SYNC,"单异步+同步_子请求");
            reqMap.put(REQ_MOREASYNC_ONE,"多异步_请求1");
            reqMap.put(REQ_MOREASYNC_TWO,"多异步_请求2");
            reqMap.put(REQ_MOREASYNC_THREE,"多异步_请求3");
            reqMap.put(REQ_MOREASYNC_SYNC_MAJORONE,"多异步+同步_主请求1");
            reqMap.put(REQ_MOREASYNC_SYNC_MAJORONE_SYNC,"多异步+同步_主请求1_子请求");
            reqMap.put(REQ_MOREASYNC_SYNC_MAJORTWO,"多异步+同步_主请求2");
            reqMap.put(REQ_MOREASYNC_SYNC_MAJORTWO_SYNC,"多异步+同步_主请求2_子请求");
            reqMap.put(REQ_MOREASYNC_SYNC_MAJORThree,"多异步+同步_主请求3");
            reqMap.put(REQ_ONEASYNC_CHANGEPARAMS_MAJOR,"单异步+同步+换参_主请求");
            reqMap.put(REQ_ONEASYNC_CHANGEPARAMS_SYNC_CHANGED,"单异步+同步+换参_子请求_替换");
            reqMap.put(REQ_ONEASYNC_CHANGEPARAMS_SYNC_UNCHANGED,"单异步+同步+换参_子请求_不替换");
            reqMap.put(REQ_UPLOAD_FILE,"文件上传");
        }
        String flag = reqMap.get(reqCode);
        if(StringUtil.isEmpty(flag)){
            flag = reqCode + "";
        }
        return flag;
    }

    private void i(String msg){
        sb.append( msg + "\n");
        homeTv.setText(sb.toString());

        handler.sendEmptyMessageDelayed(1,100);
    }
    private void ic(String msg){
        sb = new StringBuffer();
        sb.append( msg + "\n");
        homeTv.setText(sb.toString());
    }
}
