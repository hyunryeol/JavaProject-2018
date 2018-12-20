package com.example.user.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.network.API;
import com.example.user.myapplication.network.model.PCStatusModel;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Layout_6202 extends Activity {

    int i = 42;
    Button btnarray[];
    LinearLayout layout[] = new LinearLayout[7];
    int status[];
    int btncount = 0;
    Button btn_6202;
    Button seabar2;
    LinearLayout parent_layout;

    LinearLayout contaner_layout;
//    LinearLayout layout2;
//    LinearLayout layout3;
//    LinearLayout layout4;
//    LinearLayout layout5;
//    LinearLayout layout6;
//    LinearLayout layout7;
//    LinearLayout layout8;

    API api;

    //node 작업
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_6202);

        api = API.getInstance();

        //Random rnd = new Random();

        btnarray = new Button[42];
        status = new int[42];
//        for (int i=0; i<42; i++){
//            status[i] = rnd.nextInt(2);
//        }

        parent_layout = findViewById(R.id.parent_layout);
        contaner_layout = findViewById(R.id.createlayout);
        contaner_layout.setOrientation(LinearLayout.VERTICAL);
        contaner_layout.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

        for (int i=0;i<7;i++){
            layout[i] = new LinearLayout(Layout_6202.this);
            layout[i].setOrientation(LinearLayout.HORIZONTAL);
            layout[i].setGravity(Gravity.CENTER);
        }
        btn_6202 = (Button) findViewById(R.id.btn_6202);
        btn_6202.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(LinearLayout ll: layout) {
                    contaner_layout.addView(ll);
                }

                setContentView(parent_layout);

                contaner_layout.setVisibility(View.VISIBLE);

                LinearLayout.LayoutParams miss = (LinearLayout.LayoutParams) btn_6202.getLayoutParams();
                miss.bottomMargin=0;
                miss.topMargin=0;

                btn_6202.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 80));
//                btn_6202.startAnimation(animation);
            }
        });
        changeDisplayStatus(36,6);
        changeDisplayStatus(30,5);
        changeDisplayStatus(24,4);
        changeDisplayStatus(18,3);
        changeDisplayStatus(12,2);
        changeDisplayStatus(6,1);
        changeDisplayStatus(0,0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Call<List<PCStatusModel>> pcStatusRequest = api.getPcStatusService().getPCStatus();
        pcStatusRequest.enqueue(new Callback<List<PCStatusModel>>() {
            @Override
            public void onResponse(Call<List<PCStatusModel>> call, Response<List<PCStatusModel>> response) {
                if (!response.isSuccessful()) {
                    onFailure(call, new Throwable());
                }
                List<PCStatusModel> status = response.body();
                for (int i = 0; i < Layout_6202.this.status.length; i++) {
                    Layout_6202.this.status[i] = status.get(i).pc_status;
                }
                changeDisplayStatus(36,6);
                changeDisplayStatus(30,5);
                changeDisplayStatus(24,4);
                changeDisplayStatus(18,3);
                changeDisplayStatus(12,2);
                changeDisplayStatus(6,1);
                changeDisplayStatus(0,0);
            }

            @Override
            public void onFailure(Call<List<PCStatusModel>> call, Throwable t) {
                Log.d("DEBUG_APP", "onFailure: " + t.getMessage());
                Toast.makeText(Layout_6202.this, "서버에서 데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void changeDisplayStatus(int condition, int idx) {
        for (; i > condition; i--) {
            Button b = new Button(this);
            b.setText("" + i);
            b.setId(i);
            b.setTextSize(10);
            b.setGravity(1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 2, 2, 2);
            b.setLayoutParams(params);
            layout[idx].addView(b);
            if (i % 2 == 1) {
                TextView t = new TextView((this));
                t.setText("   ");
                layout[idx].addView(t);
            }
            btnarray[btncount] = b;
            pcstatus(btnarray[btncount], status[btncount]);
            btncount++;
        }
    }

    public void pcstatus(Button b, int i){
        if (i == 0){
            b.setBackgroundColor(Color.rgb(183,183,183));
        }
        else{
            b.setBackgroundColor(Color.rgb(247,208,236));
        }
    }
}
