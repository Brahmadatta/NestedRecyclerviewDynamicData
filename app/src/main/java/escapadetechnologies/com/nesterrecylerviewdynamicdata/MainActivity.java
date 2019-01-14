package escapadetechnologies.com.nesterrecylerviewdynamicdata;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import escapadetechnologies.com.nesterrecylerviewdynamicdata.Model.Data;
import escapadetechnologies.com.nesterrecylerviewdynamicdata.Model.Section;
import escapadetechnologies.com.nesterrecylerviewdynamicdata.adapters.RecylerviewDataAdapter;

public class MainActivity extends AppCompatActivity {


    RecyclerView recylerView_main;
    private ProgressDialog progressDialog;

    List<Data> allSampleData;
    private static String TEST_URL = "http://monaasoft.com/indianfm/api/test.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recylerView_main = findViewById(R.id.recylerview_Main);


        allSampleData = new ArrayList<Data>();

        testWebService(TEST_URL);

    }


    private void showProgressDialog(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    private void dismissDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void testWebService(String testUrl) {
        showProgressDialog();

        StringRequest stringRequest = new StringRequest(testUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismissDialog();

                Log.e("response",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String data = jsonObject.getString("data");
                    Log.e("data",data);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0 ; i < jsonArray.length() ; i ++){
                        JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                        String title = jsonObject1.getString("title");

                        List<Section> sections = new ArrayList<Section>();

                        JSONArray sectionsArray = jsonObject1.getJSONArray("section");

                        for (int j=0 ; j < sectionsArray.length() ; j++){

                            JSONObject obj = (JSONObject) sectionsArray.get(j);

                            Section section = new Section();

                            section.setName(obj.getString("name"));
                            section.setImage(obj.getString("image"));

                            sections.add(section);
                        }

                        Data dataa = new Data(title,sections);
                        dataa.setTitle(title);
                        dataa.setSections(sections);
                        allSampleData.add(dataa);

                        if (allSampleData != null){
                            recylerView_main.setHasFixedSize(true);
                            RecylerviewDataAdapter recylerviewDataAdapter = new RecylerviewDataAdapter(allSampleData,MainActivity.this);
                            recylerView_main.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                            recylerView_main.setAdapter(recylerviewDataAdapter);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);

    }
}
