package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject Sandwich = new JSONObject(json);
            JSONObject name = Sandwich.getJSONObject("name");
            String mName= name.getString("mainName");
            JSONArray alsoKnowAs=name.getJSONArray("alsoKnownAs");
            ArrayList<String> alsoKnowAsArray= new ArrayList<>();
            for (int i = 0 ; i < alsoKnowAs.length();i++)
            {
                alsoKnowAsArray.add(alsoKnowAs.getString(i));
            }
            String placeOfOrigin = Sandwich.getString("placeOfOrigin");
            String description = Sandwich.getString("description");
            String Image = Sandwich.getString("image");
            JSONArray ingredients =Sandwich.getJSONArray("ingredients");
            ArrayList<String> ingredientsArray =  new ArrayList<>();
            for (int i = 0 ; i  < ingredients.length();i++)
            {
                ingredientsArray.add(ingredients.getString(i));
            }

            return new Sandwich(mName,alsoKnowAsArray,placeOfOrigin,description,Image,ingredientsArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
return null;
    }
}
