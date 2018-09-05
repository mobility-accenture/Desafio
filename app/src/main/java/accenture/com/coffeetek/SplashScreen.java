package accenture.com.coffeetek;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import accenture.com.coffeetek.Interface.JsonAsyncResponse;
import accenture.com.coffeetek.Model.Product;
import accenture.com.coffeetek.Util.Common;
import accenture.com.coffeetek.Util.JsonTask;
import accenture.com.coffeetek.Util.NetworkUtils;

public class SplashScreen extends AppCompatActivity implements JsonAsyncResponse {

    JsonTask asyncTask = null;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //LoadProducts();

        if(NetworkUtils.isNetworkConnected(this)){
            String url = "https://desafio-mobility.herokuapp.com/products.json";
            asyncTask = new JsonTask(this);
            asyncTask.delegate = this;
            asyncTask.execute(url);

        }
        else {

            AlertDialog.Builder builder = new AlertDialog.Builder( SplashScreen.this );
            if (getResources() != null){
                builder.setTitle( getResources().getString(R.string.alert_title_text) );
                builder.setMessage( getResources().getString(R.string.alert_message_text) );
            }

            builder.setPositiveButton(getResources().getString(R.string.ok_text), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    if(NetworkUtils.isNetworkConnected(SplashScreen.this)){
                        String url = "https://desafio-mobility.herokuapp.com/products.json";
                        asyncTask = new JsonTask(SplashScreen.this);
                        asyncTask.delegate = SplashScreen.this;
                        asyncTask.execute(url);
                    }
                    else{
                        finish();
                    }


                }
            });
            builder.create().show();

        }


    }

    @Override
    public void processFinish(List<Product> productList) {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                Intent intent = new Intent( SplashScreen.this, Home.class );
                startActivity(intent);

                // Fecha esta activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    private void LoadProducts() {

        //List<Product> formList = new ArrayList<Product>();
        Common.productList = new ArrayList<Product>();

        try {
            JSONObject object = new JSONObject(loadJSONFromAsset());
            JSONArray json_products = object.getJSONArray("products");


            for (int i = 0; i < json_products.length(); i++) {

                Product product = new Product();

                JSONObject json_product = json_products.getJSONObject(i);

                String title_value = json_product.getString("title");
                String image_value = json_product.getString("image");

                String[] cutString = image_value.split(",");
                String image64 = "";

                if (cutString.length > 1){
                    image64 = cutString[1];
                }

                byte[] byteArray =  Base64.decode(image64, Base64.DEFAULT) ;
                Bitmap bmp1 = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                int size_value = json_product.getInt("size");
                int sugar_value = json_product.getInt("sugar");
                String additional_value = "";
                //List<String> list_additional = new ArrayList<>();

                int additional_cream = 0;
                int additional_chocolate = 0;
                int additional_cinnamon = 0;
                int additional_coffee = 0;
                int additional_milk = 0;

                if ( !json_product.isNull("additional") ){
                    JSONArray additional = json_product.getJSONArray("additional");

                    for(int j = 0; j < additional.length(); j++) {
                        additional_value = additional.get(j).toString();

                        if (getResources() != null){
                            if ( additional_value.trim().equals( getResources().getString(R.string.cream_text).toLowerCase() ) ){
                                additional_cream = 1;
                            }
                            if ( additional_value.trim().equals( getResources().getString(R.string.chocolate_text).toLowerCase() ) ){
                                additional_chocolate = 1;
                            }
                            if ( additional_value.trim().equals( getResources().getString(R.string.cinnamon_text).toLowerCase() ) ){
                                additional_cinnamon = 1;
                            }
                            if ( additional_value.trim().equals( getResources().getString(R.string.coffee_text).toLowerCase() ) ){
                                additional_coffee = 1;
                            }
                            if ( additional_value.trim().equals( getResources().getString(R.string.milk_text).toLowerCase() ) ){
                                additional_milk = 1;
                            }
                        }





                        //list_additional.add(additional_value);
                    }

                    //additional_value = json_product.getString("additional");
                }

                product.setTitle(title_value);
                product.setImage(bmp1);
                product.setSize(size_value);
                product.setSugar(sugar_value);
                product.setAdditional_Cream(additional_cream);
                product.setAdditional_Chocolate(additional_chocolate);
                product.setAdditional_Cinnamon(additional_cinnamon);
                product.setAdditional_Coffee(additional_coffee);
                product.setAdditional_Milk(additional_milk);

                //formList.add(product);
                Common.productList.add(product);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
