package com.cnetsample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.cnet.def.caller.IReqCallback;
import com.cnet.def.http.request.CAbstractRequst;
import com.cnet.def.http.resp.IErrResp;
import com.cnet.def.http.resp.IRespBase;


/**
 * @author Cuckoo
 * @date 2016-08-30
 * @description Base activity, used to manage all activitys
 */
public class BaseActivity extends Activity implements IReqCallback {
    private ProgressDialog loadingDialog = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void showLoadingDialog(){
        if( loadingDialog!= null && loadingDialog.isShowing()){
            return ;
        }
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setCancelable(false);
        loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        if(!isFinishing()){
            loadingDialog.show();
        }
    }

    public void dismissLoadingDialog(){
        if( loadingDialog != null && loadingDialog.isShowing()
                && !isFinishing()){
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    @Override
    public void showTipDialog(String message) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestStart(int reqCode, CAbstractRequst request) {

    }

    @Override
    public <T> void onInflateRequest(int reqCode, CAbstractRequst request, T respDataObj, IRespBase<T> resp) {

    }

    @Override
    public void onRequestFailure(int reqCode, CAbstractRequst request, IErrResp errResp) {

    }
}
