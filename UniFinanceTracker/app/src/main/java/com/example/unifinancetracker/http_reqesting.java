package com.example.unifinancetracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class http_reqesting {
    private String login = "http://192.168.1.254/financialaid/product/userLogin.php";
    private String c_expense = "http://192.168.1.254/financialaid/product/saveExpense.php";
    private String reg_link = "http://192.168.1.254/financialaid/product/createuser.php";
    private String del_expense = "http://192.168.1.254/financialaid/product/delexpense.php";
    private String get_exp_link = "http://192.168.1.254/financialaid/product/getExpenses.php";
    private String get_exp_weeks = "http://192.168.1.254/financialaid/product/getweeklyexp.php";
    private String getgoal_link = "http://192.168.1.254/financialaid/product/getgoal.php";
    private String goal_change_link = "http://192.168.1.254/financialaid/product/changegoal.php";
    // retrieves favourites of a user using their id

    //post method
    boolean changeGoal(String id, String category,int value, String percentage, String clause) throws JSONException, IOException {
        JSONObject cred = new JSONObject();
        cred.put("userid",id); //values required for log in
        cred.put("category",category);
        cred.put("value", value);
        cred.put("clause", clause);
        cred.put("percentage",percentage);
        URL obj = null;
        try {
            obj = new URL(goal_change_link);
        } catch (MalformedURLException e) {
            return false;
        }
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        try {
            postConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            return false;
        }
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = null;
        try {
            os = postConnection.getOutputStream();
        } catch (IOException e) {
            return false;
        }
        os.write(cred.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            return true;


        } else {
            System.out.println("POST NOT WORKED");
        }
        return false;
    }

    //string is json array
    String get_goal(String id) throws IOException {
        URL urlForGetRequest;
        try {
            urlForGetRequest = new URL(getgoal_link);
        } catch (MalformedURLException e) {
            return "";
        }
        String readLine = null;
        HttpURLConnection conection = null;
        try {
            conection = (HttpURLConnection) urlForGetRequest.openConnection();
        } catch (IOException e) {
            return "";
        }
        try {
            conection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            return "";
        }
        conection.setRequestProperty("userid", id); // set userId its a sample here
        int responseCode = 0;
        try {
            responseCode = conection.getResponseCode();
        } catch (IOException e) {
            return "";
        }
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            return response.toString();
            //GetAndPost.POSTRequest(response.toString());
        } else {
            return "";
        }
    }

    //returned string is json array
    String get_expenses(int id) throws IOException {
        URL urlForGetRequest;
        try {
            urlForGetRequest = new URL(get_exp_link);
        } catch (MalformedURLException e) {
            return "";
        }
        String readLine = null;
        HttpURLConnection conection = null;
        try {
            conection = (HttpURLConnection) urlForGetRequest.openConnection();
        } catch (IOException e) {
            return "";
        }
        try {
            conection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            return "";
        }
        conection.setRequestProperty("userid", String.valueOf(id)); // set userId its a sample here
        int responseCode = 0;
        try {
            responseCode = conection.getResponseCode();
        } catch (IOException e) {
            return "";
        }
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            return response.toString();
            //GetAndPost.POSTRequest(response.toString());
        } else {
            return "";
        }
    }

    //string is json array
    String get_weekly_expenses(String uid) throws IOException {
        URL urlForGetRequest;
        try {
            urlForGetRequest = new URL(get_exp_weeks);
        } catch (MalformedURLException e) {
            return "";
        }
        String readLine = null;
        HttpURLConnection conection = null;
        try {
            conection = (HttpURLConnection) urlForGetRequest.openConnection();
        } catch (IOException e) {
            return "";
        }
        try {
            conection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            return "";
        }
        conection.setRequestProperty("userid", uid);
        int responseCode = 0;
        try {
            responseCode = conection.getResponseCode();
        } catch (IOException e) {
            return "";
        }
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            return response.toString();
            //GetAndPost.POSTRequest(response.toString());
        } else {
            return "";
        }
    }

    //post method, repose of the username and id is required
    String[] logIn(String email, String password) throws JSONException, IOException {
        String[] newarray = new String[2];
        JSONObject cred = new JSONObject();
        cred.put("email",email); //values required for log in
        cred.put("password",password);
        URL obj = null;
        try {
            obj = new URL(login);
        } catch (MalformedURLException e) {
            return newarray;
        }
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        try {
            postConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            return newarray;
        }
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = null;
        try {
            os = postConnection.getOutputStream();
        } catch (IOException e) {
            return newarray;
        }
        os.write(cred.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            JSONArray jarray = new JSONArray(response);
            newarray[0] = String.valueOf(jarray.getInt(0)); //id
            newarray[1] = jarray.getString(1); //username
            return newarray;


        } else {
            System.out.println("POST NOT WORKED");
        }
        return newarray;
    }

    //post method
    boolean createExpense(String id, String category, int amount) throws JSONException, IOException {
        JSONObject cred = new JSONObject();
        cred.put("userid",id); //values required for log in
        cred.put("category",category);
        cred.put("amount", amount);
        URL obj = null;
        try {
            obj = new URL(c_expense);
        } catch (MalformedURLException e) {
            return false;
        }
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        try {
            postConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            return false;
        }
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = null;
        try {
            os = postConnection.getOutputStream();
        } catch (IOException e) {
            return false;
        }
        os.write(cred.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            return true;


        } else {
            System.out.println("POST NOT WORKED");
        }
        return false;
    }

    //post method
    boolean delexp(String id, String expid) throws IOException {
        JSONObject cred = new JSONObject();

        try {
            cred.put("userid",id);
            cred.put("id",expid); //values required for log in
        } catch (JSONException e) {
            e.printStackTrace();
        }
        URL obj = null;
        try {
            obj = new URL(del_expense);
        } catch (MalformedURLException e) {
            return false;
        }
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        try {
            postConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            return false;
        }
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = null;
        try {
            os = postConnection.getOutputStream();
        } catch (IOException e) {
            return false;
        }
        try {
            os.write(cred.toString().getBytes("UTF-8"));
            os.flush();
            os.close();
        } catch (IOException e) {
            return false;
        }

        int responseCode = 0;
        try {
            responseCode = postConnection.getResponseCode();
        } catch (IOException e) {
            return false;
        }
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            return true;


        } else {
            System.out.println("POST NOT WORKED");
        }
        return false;
    }

    //post method
    boolean sign_up(String email, String password, String username) throws IOException {
        JSONObject cred = new JSONObject();

        try {
            cred.put("username",username);
            cred.put("email",email); //values required for log in
            cred.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        URL obj = null;
        try {
            obj = new URL(reg_link);
        } catch (MalformedURLException e) {
            return false;
        }
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        try {
            postConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            return false;
        }
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = null;
        try {
            os = postConnection.getOutputStream();
        } catch (IOException e) {
            return false;
        }
        os.write(cred.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            return true;


        } else {
            System.out.println("POST NOT WORKED");
        }
        return false;
    }
}

