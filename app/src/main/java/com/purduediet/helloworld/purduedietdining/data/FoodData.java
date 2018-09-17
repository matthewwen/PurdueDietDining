package com.purduediet.helloworld.purduedietdining.data;

import android.util.Log;

import com.purduediet.helloworld.purduedietdining.database.DataMethod;
import com.purduediet.helloworld.purduedietdining.objects.ItemFood;

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
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FoodData {

    private static final String TAG = FoodData.class.getSimpleName();

    public static final String[] DINING_COURT = {"Earhart",
            "Ford",
            "Hillenbrand",
            "Wiley",
            "Windsor"};

    public static final String[] BLD = {"Breakfast",
        "Lunch",
        "Dinner"};

    private static final String UGSG_REQUEST_URL =
            "https://api.hfs.purdue.edu/menus/v2/locations";
    private static final String QUERYING =
            "q";

    private static final SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("MM-dd-yyyy");

    public static ArrayList<ItemFood> getAllData() {
        ArrayList<ItemFood> allFood = new ArrayList<>();
        for (int i = 0; i < DINING_COURT.length; i++){
            String usedURL = UGSG_REQUEST_URL + "/" + DINING_COURT[i] + "/" + simpleDateFormat.format(DataMethod.getCurrentTime());
            allFood.addAll(fetchFoodItemData(i, usedURL));
        }
        return allFood;
    }

    public static ArrayList<ItemFood> fetchFoodItemData(int diningCourt, String mUrl){
        //Log.v(TAG, "This is the URL: " + mUrl);
        URL url = createURL(mUrl);

        String jsonData;
        try {
            jsonData = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            jsonData = "";
            Log.e(TAG, "Activity does not recognize this url: " + mUrl);
        }
        return extractFoodItems(diningCourt, jsonData);
    }

    private static String makeHttpRequest(URL url) throws IOException{

        String sampleXml = "";

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            Log.v(TAG, "response code: " + urlConnection.getResponseCode());
            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                sampleXml = readFromStream(inputStream);
            }else {
                Log.e(TAG, "Connection to url failed");
            }
        }catch (IOException e){
            Log.e(TAG, "IOException e is made");
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }

        Log.v(TAG, "XML Result: " + sampleXml);

        return sampleXml;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createURL(String urlString){
        URL url;
        try{
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        return url;
    }

    private static ArrayList<ItemFood> extractFoodItems(int diningId, String jsonResponse){
        ArrayList<ItemFood> allFoods = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray jsonArrayMeals = jsonObject.getJSONArray("Meals");
            for (int a = 0 ; a < jsonArrayMeals.length(); a++) {

                //Getting if it's Breakfast, Lunch, Or Dinner.
                JSONObject mealObject = jsonArrayMeals.getJSONObject(a);
                int bldType = ItemFood.getType(mealObject.getString("Name"));
                JSONArray jsonStationArray = mealObject.getJSONArray("Stations");

                //Looking at each station
                for (int i = 0; i < jsonStationArray.length(); i++) {
                    JSONArray jsonFoodItemsArray = jsonStationArray.getJSONObject(i).getJSONArray("Items"); //getting items
                    String station = jsonStationArray.getJSONObject(i).getString("Name"); //getting the name of the station

                    //Getting the items at each station
                    for (int j = 0; j < jsonFoodItemsArray.length(); j++) {
                        JSONObject temp = jsonFoodItemsArray.getJSONObject(j); //gets food item
                        String name = temp.getString("Name"); //getting the name of the item
                        boolean[] isOrIsnt = new boolean[]{false, false, false, false, false, false, false, false, false, false, false}; //declaring everything as false
                        JSONArray jsonAllergiesArray;
                        //check if the list exists
                        if (temp.has("Allergens")){
                            jsonAllergiesArray = temp.getJSONArray("Allergens"); //getting list of allergies
                            //going through list of allergies
                            for (int k = 0; k < jsonAllergiesArray.length(); k++) {
                                isOrIsnt[k] = jsonAllergiesArray.getJSONObject(k).getBoolean("Value");
                            }
                        }else {
                            isOrIsnt[8] = temp.getBoolean("IsVegetarian");
                        }

                        ItemFood tempItemFood = new ItemFood(a * i + j, diningId, bldType, name, station, isOrIsnt[0],
                                isOrIsnt[1], isOrIsnt[2], isOrIsnt[3], isOrIsnt[4],
                                isOrIsnt[5], isOrIsnt[6], isOrIsnt[7], isOrIsnt[8],
                                isOrIsnt[9], isOrIsnt[10]);

                        allFoods.add(tempItemFood);
                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allFoods;
    }
}
