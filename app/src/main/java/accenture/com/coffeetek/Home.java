package accenture.com.coffeetek;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import accenture.com.coffeetek.Adapter.HomeAdapter;
import accenture.com.coffeetek.Model.Product;
import accenture.com.coffeetek.Util.Common;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LoadViews();

        LoadProducts();

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
                List<String> list_additional = new ArrayList<>();

                if ( !json_product.isNull("additional") ){
                    JSONArray additional = json_product.getJSONArray("additional");

                    for(int j = 0; j < additional.length(); j++) {
                        additional_value = additional.get(j).toString();
                        list_additional.add(additional_value);
                    }

                    //additional_value = json_product.getString("additional");
                }

                product.setTitle(title_value);
                product.setImage(bmp1);
                product.setSize(size_value);
                product.setSugar(sugar_value);
                product.setAdditional(list_additional);

                //formList.add(product);
                Common.productList.add(product);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new HomeAdapter( this, Common.productList );
        recyclerView.setAdapter(adapter);

    }

    private void LoadViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        if (getResources() != null)
            toolbarTitle.setText(getResources().getString(R.string.menu_text));

        recyclerView = (RecyclerView) findViewById( R.id.recycler_menu );
        layoutManager = new LinearLayoutManager( Home.this );
        recyclerView.setLayoutManager( layoutManager );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home_toolbar, menu);
        return true;
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
