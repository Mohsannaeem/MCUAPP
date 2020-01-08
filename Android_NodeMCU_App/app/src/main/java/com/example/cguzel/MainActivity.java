package com.example.cguzel.nodemcu_app;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.StatementEvent;

/**
 * Created by cguzel on 26.04.2016.
 */

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    private EditText ipAddress;
    private Button  left,right,reverse,toggle;
    private Button forward;
    String Status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipAddress = (EditText) findViewById(R.id.edt_ip);
        forward= (Button) findViewById(R.id.btn_forward);
        left    = (Button) findViewById(R.id.btn_left);
        right   = (Button) findViewById(R.id.btn_right);
        reverse = (Button)findViewById(R.id.btn_reverse);
        toggle  = (Button)findViewById(R.id.toggle);
        forward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean flag1=false;
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:{
                        if(!flag1){
                        Status="F";
                            //Connect to default port number. Ex: http://IpAddress:80
                            String serverAdress = ipAddress.getText().toString() + ":" + "80";
                            HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                            requestTask.execute(Status);
                            flag1=true;

                        }
                        break;}
                    case MotionEvent.ACTION_UP: {
                        Status="S";
                        String serverAdress = ipAddress.getText().toString() + ":" + "80";
                        HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                        requestTask.execute(Status);

                        break;}
                }



                return true;

            }

        });
        reverse.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean flag1=false;
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:{
                        if(!flag1){
                            Status="B";
                            //Connect to default port number. Ex: http://IpAddress:80
                            String serverAdress = ipAddress.getText().toString() + ":" + "80";
                            HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                            requestTask.execute(Status);
                            flag1=true;

                        }
                        break;}
                    case MotionEvent.ACTION_UP: {
                        Status="S";
                        String serverAdress = ipAddress.getText().toString() + ":" + "80";
                        HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                        requestTask.execute(Status);

                        break;}
                }



                return true;

            }

        });
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean flag1=false;
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:{
                        if(!flag1){
                            Status="L";
                            //Connect to default port number. Ex: http://IpAddress:80
                            String serverAdress = ipAddress.getText().toString() + ":" + "80";
                            HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                            requestTask.execute(Status);
                            flag1=true;

                        }
                        break;}
                    case MotionEvent.ACTION_UP: {
                        Status="S";
                        String serverAdress = ipAddress.getText().toString() + ":" + "80";
                        HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                        requestTask.execute(Status);

                        break;}
                }



                return true;

            }

        });
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean flag1=false;
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:{
                        if(!flag1){
                            Status="R";
                            //Connect to default port number. Ex: http://IpAddress:80
                            String serverAdress = ipAddress.getText().toString() + ":" + "80";
                            HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                            requestTask.execute(Status);
                            flag1=true;

                        }
                        break;}
                    case MotionEvent.ACTION_UP: {
                        Status="S";
                        String serverAdress = ipAddress.getText().toString() + ":" + "80";
                        HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                        requestTask.execute(Status);

                        break;}
                }



                return true;

            }

        });

    }

    /** When the button clicks this method executes**/
    public void buttonClick(View view) {
        String Status="0";
        if(view == toggle) {
            ipAddress.setVisibility(View.GONE);
            toggle.setVisibility(View.GONE);
        }
        if (ipAddress.getText().toString().equals(""))
            Toast.makeText(MainActivity.this, "Please enter the ip address...", Toast.LENGTH_SHORT).show();

        else {

            if (view == forward){
                if(forward.isPressed()){
                }

            }
            else if(view==left){

                if(left.isPressed()){
                    Status = "L";
                }
                else{
                    Status = "S";
                }
            }else if(view==right){

                if(right.isPressed()){
                    Status = "R";
                }
                else{
                    Status = "S";
                }
            }
            else if(view==reverse){

                if(reverse.isPressed()){
                    Status = "R";
                }
                else{
                    Status = "S";
                }
            }

            //Connect to default port number. Ex: http://IpAddress:80
            String serverAdress = ipAddress.getText().toString() + ":" + "80";
            HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
            requestTask.execute(Status);
        }
    }

    private class HttpRequestTask extends AsyncTask<String, Void, String> {

        private String serverAdress;
        private String serverResponse = "";
        public HttpRequestTask(String serverAdress) {
            this.serverAdress = serverAdress;
        }

        @Override
        protected String doInBackground(String... params) {


            String val = params[0];
            final String url = "http://" + serverAdress + "/Status/" + val;

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet getRequest = new HttpGet();
                getRequest.setURI(new URI(url));
                HttpResponse response = client.execute(getRequest);

                InputStream inputStream = null;
                inputStream = response.getEntity().getContent();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream));

                serverResponse = bufferedReader.readLine();
                inputStream.close();

            } catch (URISyntaxException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            }

            return serverResponse;
        }

        @Override
        protected void onPostExecute(String s) {
        }


        @Override
        protected void onPreExecute() {

        }
    }
}
