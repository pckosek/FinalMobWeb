package com.example.pckosek.statusmonitor;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LedActionAdapter.RecyclerRowInterface{

    /* ------------------------*/
    /*    member variables     */

    private List<LedAction> ledActionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeContainer;
    private LedActionAdapter mAdapter;

    RequestQueue mRequestQueue;
    ResponseHelper mResponseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REQUEST QUEUE and HELPER
        mRequestQueue = Volley.newRequestQueue(this);
        mResponseHelper = new ResponseHelper();

        // SWIPE CONTAINER
        mSwipeContainer = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Log.i("SWIPE", "SWIPE_REFRESH");

                String url ="https://user.tjhsst.edu/pckosek/status";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, mResponseHelper, mResponseHelper);
                mRequestQueue.add(stringRequest);

                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                // fetchTimelineAsync(0);
            }
        });
        mSwipeContainer .setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        // RECYCLER VIEW
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new LedActionAdapter(ledActionList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void showDialog(String s_id, String s_st, String s_op, String s_usr) {
        showEditDialog(s_id, s_op, s_st, s_usr);
    }



    /* ------------------------------------------*/
    /*    HELPER CLASSES                         */

    class ResponseHelper implements Response.Listener<String>, Response.ErrorListener {

        public ResponseHelper() {
        }

        @Override
        public void onResponse(String response) {
            Log.i("KOSEK", response);


            Gson gson = new GsonBuilder().create();     // instantiate a gson builder
            ServerStatus serverStatus = gson.fromJson(response, ServerStatus.class);

            ledActionList.clear();
            for (int i=serverStatus.status.length-1; i>-1; i--) {
                ledActionList.add(serverStatus.status[i]);
            }
            mAdapter.notifyDataSetChanged();
            mSwipeContainer.setRefreshing(false);

        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i("KOSEK", "error");
        }
    }

    private void showEditDialog(String s_id, String s_st, String s_op, String s_usr) {
        FragmentManager fm = getSupportFragmentManager();
        EditDialogFragment editNameDialogFragment = EditDialogFragment.newInstance(s_id, s_st, s_op, s_usr);
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

}
