package com.example.jun.squarepang;

         import android.os.AsyncTask;

         import java.io.IOException;

         import okhttp3.FormBody;
         import okhttp3.OkHttpClient;
         import okhttp3.Request;
         import okhttp3.RequestBody;
         import okhttp3.Response;



public class HttpConn extends AsyncTask<Void,Void,String>{
    String answer;
    String url;
    String id,pw;

    public HttpConn(String url,String id,String pw){
        this.url = url;
        this.id = id;
        this.pw = pw;
    }

    @Override
    protected String doInBackground(Void... params) {

        OkHttpClient client = new OkHttpClient();
        Response response;
        RequestBody requestBody = null;

        requestBody = new FormBody.Builder().add("id",id).add("pw",pw).build();
        Request request = new Request.Builder()
                .url(url)

                .post(requestBody)
                .build();
        try {
            response = client.newCall(request).execute();
            answer = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer;
    }

    @Override
    protected void onPostExecute(String s) {
    }

}
