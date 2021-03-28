package com.sumit.restapi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sumit.restapi.DataModel.Datum;
import com.sumit.restapi.DataModel.UnknownBeans;
import com.sumit.restapi.Retrofit.APIClient;
import com.sumit.restapi.Retrofit.APIInterface;
import com.sumit.restapi.Utility.ErrorMessage;
import com.sumit.restapi.Utility.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    String JSON_URL="https://simplifiedcoding.net/demos/view-flipper/heroes.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getApi = findViewById(R.id.retrofitGetApi);
        Button postApi = findViewById(R.id.retrofitPostApi);


        getApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUnknownApi();
            }
        });

        postApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostApi();
            }
        });



    }


    private void getUnknownApi() {
        if (NetworkUtil.isNetworkAvailable (MainActivity.this)) {
            APIInterface apiService = APIClient.getClient ().create (APIInterface.class);
            Call<UnknownBeans> call = apiService.getUnknownApigeneral ();
            call.enqueue (new Callback<UnknownBeans> () {
                @Override
                public void onResponse(Call<UnknownBeans> call, Response<UnknownBeans> response) {
                    UnknownBeans unknownBeans = response.body();
                    Toast.makeText(MainActivity.this, ""+unknownBeans.getSupport().getUrl(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<UnknownBeans> call, Throwable t) {
                    t.printStackTrace ();
                    ErrorMessage.E ("Crashed in Registration" + t);
                }
            });

        } else {
            ErrorMessage.T (MainActivity.this, "You're offline");
        }
    }


    private void PostApi() {
        if (NetworkUtil.isNetworkAvailable (MainActivity.this)) {
            APIInterface apiService = APIClient.getClient ().create (APIInterface.class);
            Call<ResponseBody> call = apiService.postRetrofitApi ("","");
            call.enqueue (new Callback<ResponseBody> () {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(MainActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace ();
                    ErrorMessage.E ("Crashed in Registration" + t);
                }
            });

        } else {
            ErrorMessage.T (MainActivity.this, "You're offline");
        }
    }


    private void getUnknownApiTwo() {
        if (NetworkUtil.isNetworkAvailable (MainActivity.this)) {
            final Dialog materialDialog = ErrorMessage.initProgressDialog (MainActivity.this);
            APIInterface apiService = APIClient.getClient ().create (APIInterface.class);
            Call<ResponseBody> call = apiService.getUnknownApi ();
            call.enqueue (new Callback<ResponseBody> () {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    ErrorMessage.E ("Response" + response.code ());
                    JSONObject object = null;
                    if (response.isSuccessful () && response.body () != null) {
                        try {
                            materialDialog.dismiss ();
                            object = new JSONObject (response.body ().string ());
                            JSONArray data = new JSONArray (object.getString ("data"));


                            Gson gson = new Gson();

                            //1st Method
                            UnknownBeans unknownBeans = gson.fromJson(object.toString(), UnknownBeans.class);
                            Toast.makeText(MainActivity.this, ""+unknownBeans.getSupport().getUrl(), Toast.LENGTH_SHORT).show();

                        } catch (JSONException | IOException e) {
                            e.printStackTrace ();
                        }
                    } else {
                        materialDialog.dismiss ();
                        Toast.makeText (MainActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show ();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace ();
                    ErrorMessage.E ("Crashed in Registration" + t);
                    materialDialog.dismiss ();
                }
            });

        } else {
            ErrorMessage.T (MainActivity.this, "You're offline");
        }
    }


    private void getUnknownApiThree() {
        if (NetworkUtil.isNetworkAvailable (MainActivity.this)) {
            final Dialog materialDialog = ErrorMessage.initProgressDialog (MainActivity.this);
            APIInterface apiService = APIClient.getClient ().create (APIInterface.class);
            Call<ResponseBody> call = apiService.getUnknownApi ();
            call.enqueue (new Callback<ResponseBody> () {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    ErrorMessage.E ("Response" + response.code ());
                    JSONObject object = null;
                    if (response.isSuccessful () && response.body () != null) {
                        try {
                            materialDialog.dismiss ();
                            object = new JSONObject (response.body ().string ());

                            //2nd method for creaing class or List at runTime
                            Type listType = new TypeToken<UnknownBeans>(){}.getType();
                            UnknownBeans unknownBeans = new Gson().fromJson(object.toString(), listType);
                            Toast.makeText(MainActivity.this, ""+unknownBeans.getSupport().getUrl(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace ();
                        }
                    } else {
                        materialDialog.dismiss ();
                        Toast.makeText (MainActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show ();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace ();
                    ErrorMessage.E ("Crashed in Registration" + t);
                    materialDialog.dismiss ();
                }
            });

        } else {
            ErrorMessage.T (MainActivity.this, "You're offline");
        }
    }


    private void getUnknownApiFour() {
        if (NetworkUtil.isNetworkAvailable (MainActivity.this)) {
            final Dialog materialDialog = ErrorMessage.initProgressDialog (MainActivity.this);
            APIInterface apiService = APIClient.getClient ().create (APIInterface.class);
            Call<ResponseBody> call = apiService.getUnknownApi ();
            call.enqueue (new Callback<ResponseBody> () {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    ErrorMessage.E ("Response" + response.code ());
                    JSONObject object = null;
                    if (response.isSuccessful () && response.body () != null) {
                        try {
                            materialDialog.dismiss ();
                            object = new JSONObject (response.body ().string ());
                            JSONObject jsonObject = new JSONObject(object.getString("support"));
                            Toast.makeText(MainActivity.this, ""+jsonObject.getString(""), Toast.LENGTH_SHORT).show();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace ();
                        }
                    } else {
                        materialDialog.dismiss ();
                        Toast.makeText (MainActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show ();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace ();
                    ErrorMessage.E ("Crashed in Registration" + t);
                    materialDialog.dismiss ();
                }
            });

        } else {
            ErrorMessage.T (MainActivity.this, "You're offline");
        }
    }

 //comment

}
