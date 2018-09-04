package accenture.com.coffeetek;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    LinearLayout linear_Layout_Image_Details, linear_Layout_Information_Details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        LoadViews();
    }


    private void LoadViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        if (getResources() != null)
            toolbarTitle.setText(getResources().getString(R.string.details_text));


        Intent data = getIntent();
        int position = data.getIntExtra( "position", 0 );



        int screenHeight, screenWidth;

        //Code to determine screen's height and width.

        Display display = getWindowManager().getDefaultDisplay();
        if (android.os.Build.VERSION.SDK_INT>=13) {
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
            screenWidth = size.x;
        }
        else {
            screenWidth = display.getWidth();
            screenHeight = display.getHeight();
        }


        linear_Layout_Image_Details = (LinearLayout) findViewById(R.id.linear_Layout_Image_Details);
        linear_Layout_Information_Details = (LinearLayout) findViewById(R.id.linear_Layout_Information_Details);

        linear_Layout_Image_Details.getLayoutParams().height = (screenHeight)/5;
        linear_Layout_Information_Details.getLayoutParams().height = (screenHeight*3)/5;






    }
}
