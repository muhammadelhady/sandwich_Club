package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
           closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView mainName=(TextView)findViewById(R.id.mainName);
        mainName.setText("Main Name: "+sandwich.getMainName());
        TextView placeOfOrigin=(TextView)findViewById(R.id.placeOfOrigin);
        placeOfOrigin.setText("Place Of Origin: "+sandwich.getPlaceOfOrigin());
        TextView description= (TextView)findViewById(R.id.descripton);
        description.setText("Description: "+sandwich.getDescription());
        TextView ingrediants= (TextView)findViewById(R.id.ingrediants);
        ingrediants.setText("Ingrediants: ");
        ArrayList<String> ingrediantsArray= (ArrayList<String>) sandwich.getIngredients();
        for (int i = 0 ; i < ingrediantsArray.size();i++)
        {
            ingrediants.append(" "+ingrediantsArray.get(i));
        }
        TextView alsoKnownAs=(TextView)findViewById(R.id.AlsoKnownAs);
        alsoKnownAs.setText("Also Known As: ");
        ArrayList<String> alsoKnownAsArray= (ArrayList<String>) sandwich.getAlsoKnownAs();
        for (int i = 0 ; i < alsoKnownAsArray.size();i++)
        {
            alsoKnownAs.append(" "+alsoKnownAsArray.get(i));
        }
    }
}
