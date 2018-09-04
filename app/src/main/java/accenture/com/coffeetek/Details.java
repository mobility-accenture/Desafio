package accenture.com.coffeetek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Details extends AppCompatActivity {

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

        TextView textView = (TextView) findViewById(R.id.details_text);
        textView.setText( String.valueOf(position) );



    }
}
