package accenture.com.coffeetek.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import accenture.com.coffeetek.Interface.JsonAsyncResponse;
import accenture.com.coffeetek.Model.Product;
import accenture.com.coffeetek.R;

public class JsonTask extends AsyncTask<String, String, String> {


    private Context context = null;
    public JsonAsyncResponse delegate = null;

    public JsonTask(Context context) {
        this.context = context;
    }


    @Override
    protected String doInBackground(String... params) {

        String surl = params[0];


        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(surl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String linha;
            StringBuffer buffer = new StringBuffer();
            while((linha = reader.readLine()) != null) {
                buffer.append(linha);
                buffer.append("\n");
            }

            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String data) {
        // Faça alguma coisa com os dados


        Common.productList = new ArrayList<Product>();

        try {
            //JSONArray arr = new JSONArray(data).getJSONArray("products");
            JSONObject object = new JSONObject(data);
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

                        if (context.getResources() != null){
                            if ( additional_value.trim().equals( context.getResources().getString(R.string.cream_text).toLowerCase() ) ){
                                additional_cream = 1;
                            }
                            if ( additional_value.trim().equals( context.getResources().getString(R.string.chocolate_text).toLowerCase() ) ){
                                additional_chocolate = 1;
                            }
                            if ( additional_value.trim().equals( context.getResources().getString(R.string.cinnamon_text).toLowerCase() ) ){
                                additional_cinnamon = 1;
                            }
                            if ( additional_value.trim().equals( context.getResources().getString(R.string.coffee_text).toLowerCase() ) ){
                                additional_coffee = 1;
                            }
                            if ( additional_value.trim().equals( context.getResources().getString(R.string.milk_text).toLowerCase() ) ){
                                additional_milk = 1;
                            }
                        }

                    }

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

        delegate.processFinish(Common.productList);


    }

}