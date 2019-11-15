package com.example.genuisplaza.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.genuisplaza.model.User;

public class ParseUserData {

    List<User> userList;

    public void parse(String jsonData){

        userList = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(jsonData);

            if(jsonObj.has("data")){
                JSONArray usersArr = jsonObj.getJSONArray("data");

                for (int j = 0; j < usersArr.length(); j++) {
                    JSONObject userObj = usersArr.getJSONObject(j);
                    User user = new User();

                    if (userObj.has("id")) {
                        user.setId(Integer.parseInt(userObj.getString("id")));
                    }
                    if (userObj.has("email")) {
                        user.setEmail(userObj.getString("email"));
                    }
                    if (userObj.has("first_name")) {
                        user.setFirstName(userObj.getString("first_name"));
                    }
                    if (userObj.has("last_name")) {
                        user.setLastName(userObj.getString("last_name"));
                    }
                    if (userObj.has("avatar")) {
                        user.setAvatar(userObj.getString("avatar"));
                    }


                    userList.add(user);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public List<User> getUsers(){
        return userList;
    }
}
