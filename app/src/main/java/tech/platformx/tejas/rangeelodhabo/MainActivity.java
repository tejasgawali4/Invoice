package tech.platformx.tejas.rangeelodhabo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

    WebView webView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);

        webView = (WebView) findViewById(R.id.yourwebview);
        // force web view to open inside application
        webView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        checkInternetConenction();

    }

    private void openURL()
    {
        webView.loadUrl("http://invoice.shivsamartha.com/");
    }

    private class MyWebViewClient extends WebViewClient
    {

        private ProgressBar progressBar;

        public MyWebViewClient()
        {
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    private boolean checkInternetConenction()
    {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            openURL();
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED  )
        {
            //Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Internet Is Not Available. Go to Settings?");

            alertDialogBuilder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1)
                        {

                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
                            startActivity(intent);

                        }

                    });

            alertDialogBuilder.setNegativeButton("Exit",
                    new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1)
                        {
                            finish();
                        }
                    });

            //Showing the alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return false;
        }
        return false;
    }

/*    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            //if Back key pressed and webview can navigate to previous page
            webView.goBack();
            // go back to previous page
            return true;
        }
        else
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Do You Want to Close App ?");

            alertDialogBuilder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1)
                        {
                            finish();
                        }

                    });

            alertDialogBuilder.setNegativeButton("No",
                    new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1)
                        {

                        }
                    });

            //Showing the alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            // finish the activity
        }
        return super.onKeyDown(keyCode, event);
    }
}