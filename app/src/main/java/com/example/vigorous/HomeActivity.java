package com.example.vigorous;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //initialize variable
    //Constant
    //Monday
    private static String MON_MY_PREFS = "mon_switch_prefs"; //shared preference name
    private static String MON_STATUS = "monday_on";
    private static String MON_SWITCH_STATUS = "mon_switch_status";

    //Tuesday
    private static String TUE_MY_PREFS = "tue_switch_prefs"; //shared preference name
    private static String TUE_STATUS = "tuesday_on";
    private static String TUE_SWITCH_STATUS = "tue_switch_status";

    //Wednesday
    private static String WED_MY_PREFS = "wed_switch_prefs"; //shared preference name
    private static String WED_STATUS = "wednesday_on";
    private static String WED_SWITCH_STATUS = "wed_switch_status";

    //Thursday
    private static String THU_MY_PREFS = "thu_switch_prefs"; //shared preference name
    private static String THU_STATUS = "thursday_on";
    private static String THU_SWITCH_STATUS = "thu_switch_status";

    //Friday
    private static String FRI_MY_PREFS = "fri_switch_prefs"; //shared preference name
    private static String FRI_STATUS = "friday_on";
    private static String FRI_SWITCH_STATUS = "fri_switch_status";

    //Saturday
    private static String SAT_MY_PREFS = "sat_switch_prefs"; //shared preference name
    private static String SAT_STATUS = "saturday_on";
    private static String SAT_SWITCH_STATUS = "sat_switch_status";

    //Sunday
    private static String SUN_MY_PREFS = "sun_switch_prefs"; //shared preference name
    private static String SUN_STATUS = "sunday_on";
    private static String SUN_SWITCH_STATUS = "sun_switch_status";

    //Pump
    private static String MY_PREFS = "switch_prefs"; //shared preference name
    private static String PUMP_STATUS = "pump_on"; //Pump image name
    private static String SWITCH_STATUS = "switch_status"; //Switch name

    //Save
    private static String SAVE_PREFS = "save_prefs";
    private static String SAVE_STATUS = "save_on";
    private static String SAVE_SWITCH_STATUS = "save_switch_status";

    //Monday If true or false
    boolean mon_switch_status;
    boolean monday_status;

    //Tuesday
    boolean tue_switch_status;
    boolean tuesday_status;

    //Wednesday
    boolean wed_switch_status;
    boolean wednesday_status;

    //Thursday
    boolean thu_switch_status;
    boolean thursday_status;

    //Friday
    boolean fri_switch_status;
    boolean friday_status;

    //Saturday
    boolean sat_switch_status;
    boolean saturday_status;

    //Sunday
    boolean sun_switch_status;
    boolean sunday_status;

    //Pump
    boolean switch_status;
    boolean pump_status;

    boolean save_switch_status;
    boolean save_status;

    ImageView pump, mon, tue, wed, thu, fri, sat, sun;
    Switch simpleSwitchPump, switch_mon, switch_tue, switch_wed, switch_thu, switch_fri, switch_sat, switch_sun, switch_save;
    TextView txt_soil, txt_plant_status;
    DatabaseReference myDB;
    SharedPreferences myPreference;
    SharedPreferences.Editor myEditor;


    //initialize variable
    MeowBottomNavigation bottomNav;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu_burger;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        //Assign variable
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawer_view);
        navigationView = findViewById(R.id.navigation_view);
        menu_burger = findViewById(R.id.burger);

        //Assign variable

        //Monday
        myPreference = getSharedPreferences(MON_MY_PREFS,MODE_PRIVATE);
        myEditor = getSharedPreferences(MON_MY_PREFS,MODE_PRIVATE).edit();
        //Tuesday
        myPreference = getSharedPreferences(TUE_MY_PREFS,MODE_PRIVATE);
        myEditor = getSharedPreferences(TUE_MY_PREFS,MODE_PRIVATE).edit();
        //Wednesday
        myPreference = getSharedPreferences(WED_MY_PREFS,MODE_PRIVATE);
        myEditor = getSharedPreferences(WED_MY_PREFS,MODE_PRIVATE).edit();
        //Thursday
        myPreference = getSharedPreferences(THU_MY_PREFS,MODE_PRIVATE);
        myEditor = getSharedPreferences(THU_MY_PREFS,MODE_PRIVATE).edit();
        //Friday
        myPreference = getSharedPreferences(FRI_MY_PREFS,MODE_PRIVATE);
        myEditor = getSharedPreferences(FRI_MY_PREFS,MODE_PRIVATE).edit();
        //Saturday
        myPreference = getSharedPreferences(SAT_MY_PREFS,MODE_PRIVATE);
        myEditor = getSharedPreferences(SAT_MY_PREFS,MODE_PRIVATE).edit();
        //Sunday
        myPreference = getSharedPreferences(SUN_MY_PREFS,MODE_PRIVATE);
        myEditor = getSharedPreferences(SUN_MY_PREFS,MODE_PRIVATE).edit();
        //PUMP
        myPreference = getSharedPreferences(MY_PREFS,MODE_PRIVATE);
        myEditor = getSharedPreferences(MY_PREFS,MODE_PRIVATE).edit();
        //Save
        myPreference = getSharedPreferences(SAVE_PREFS,MODE_PRIVATE);
        myEditor = getSharedPreferences(SAVE_PREFS,MODE_PRIVATE).edit();

        //Monday
        mon_switch_status = myPreference.getBoolean(MON_SWITCH_STATUS,false); // false default value here
        monday_status = myPreference.getBoolean(MON_STATUS,false);
        //Tuesday
        tue_switch_status = myPreference.getBoolean(TUE_SWITCH_STATUS,false);
        tuesday_status = myPreference.getBoolean(TUE_STATUS,false);
        //Wednesday
        wed_switch_status = myPreference.getBoolean(WED_SWITCH_STATUS,false);
        wednesday_status = myPreference.getBoolean(WED_STATUS,false);
        //Thursday
        thu_switch_status = myPreference.getBoolean(THU_SWITCH_STATUS,false);
        thursday_status = myPreference.getBoolean(THU_STATUS,false);
        //Friday
        fri_switch_status = myPreference.getBoolean(FRI_SWITCH_STATUS,false);
        friday_status = myPreference.getBoolean(FRI_STATUS,false);
        //Saturday
        sat_switch_status = myPreference.getBoolean(SAT_SWITCH_STATUS,false);
        saturday_status = myPreference.getBoolean(SAT_STATUS,false);
        //Sunday
        sun_switch_status = myPreference.getBoolean(SUN_SWITCH_STATUS,false);
        sunday_status = myPreference.getBoolean(SUN_STATUS,false);
        //Pump
        switch_status = myPreference.getBoolean(SWITCH_STATUS,false);
        pump_status = myPreference.getBoolean(PUMP_STATUS,false);
        //Save
        save_switch_status = myPreference.getBoolean(SAVE_SWITCH_STATUS,false);
        save_status = myPreference.getBoolean(SAVE_STATUS,false);

        //calendar image
        mon = findViewById(R.id.monday);
        tue = findViewById(R.id.tuesday);
        wed = findViewById(R.id.wednesday);
        thu = findViewById(R.id.thursday);
        fri = findViewById(R.id.friday);
        sat = findViewById(R.id.saturday);
        sun = findViewById(R.id.sunday);
        //pump
        pump = findViewById(R.id.pumptr);
        txt_soil = findViewById(R.id.soil);
        txt_plant_status = findViewById(R.id.plant_status);

        //Switch calendar
        switch_mon = findViewById(R.id.switch_mon);
        switch_tue = findViewById(R.id.switch_tue);
        switch_wed = findViewById(R.id.switch_wed);
        switch_thu = findViewById(R.id.switch_thu);
        switch_fri = findViewById(R.id.switch_fri);
        switch_sat = findViewById(R.id.switch_sat);
        switch_sun = findViewById(R.id.switch_sun);
        //Switch pump
        simpleSwitchPump = findViewById(R.id.switch4);



        //Transition of image
        //Monday
        switch_mon.setChecked(mon_switch_status);

        if (monday_status){ //if monday_status is true then the monday will be on else will be off
            mon.setImageResource(R.drawable.monday_colored_removebg);
        }else{
            mon.setImageResource(R.drawable.monday_bw_removebg);
        }

        //Tuesday
        switch_tue.setChecked(tue_switch_status);

        if (tuesday_status){
            tue.setImageResource(R.drawable.tuesday_colored_removebg);
        }else{
            tue.setImageResource(R.drawable.tuesday_bw_removebg);
        }

        //Wednesday
        switch_wed.setChecked(wed_switch_status);

        if (wednesday_status){
            wed.setImageResource(R.drawable.wednesday_colored);
        }else{
            wed.setImageResource(R.drawable.wednesday_bw_removebg);
        }

        //Thursday
        switch_thu.setChecked(tue_switch_status);

        if (thursday_status){
            thu.setImageResource(R.drawable.thursday_colored_removebg);
        }else{
            thu.setImageResource(R.drawable.thursday_bw_removebg);
        }

        //Friday
        switch_fri.setChecked(fri_switch_status);

        if (friday_status){
            fri.setImageResource(R.drawable.friday_colored);
        }else{
            fri.setImageResource(R.drawable.friday_bw_removebg);
        }

        //Saturday
        switch_sat.setChecked(sat_switch_status);

        if (saturday_status){
            sat.setImageResource(R.drawable.saturday_colored);
        }else{
            sat.setImageResource(R.drawable.saturday_bw_removebg);
        }

        //Sunday
        switch_sun.setChecked(sun_switch_status);

        if (sunday_status){
            sun.setImageResource(R.drawable.sunday_colored);
        }else{
            sun.setImageResource(R.drawable.sunday_bw_removebg);
        }
        //Pump
        simpleSwitchPump.setChecked(switch_status);

        if (pump_status){
            pump.setImageResource(R.drawable.pump2);
        }else{
            pump.setImageResource(R.drawable.pumpoff);
        }

        //Function of switch

        switch_mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mon.setImageResource(R.drawable.monday_colored_removebg);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Monday");
                    myref.setValue(1);
                    myEditor.putBoolean(MON_SWITCH_STATUS, true); //here will set switch status true
                    myEditor.putBoolean(MON_STATUS, true); //and also monday status set to true if switch button is on
                    myEditor.apply();
                    simpleSwitchPump.setChecked(false);
                } else {
                    mon.setImageResource(R.drawable.monday_bw_removebg);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Monday");
                    myref.setValue(0);
                    myEditor.putBoolean(MON_SWITCH_STATUS, false); //here will set switch status true
                    myEditor.putBoolean(MON_STATUS, false); //and also monday status set to off if switch button is off
                    myEditor.apply();
                }
            }
        });

        switch_tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tue.setImageResource(R.drawable.tuesday_colored_removebg);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Tuesday");
                    myref.setValue(1);
                    myEditor.putBoolean(TUE_SWITCH_STATUS, true);
                    myEditor.putBoolean(TUE_STATUS, true);
                    myEditor.apply();
                    simpleSwitchPump.setChecked(false);
                } else {
                    tue.setImageResource(R.drawable.tuesday_bw_removebg);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Tuesday");
                    myref.setValue(0);
                    myEditor.putBoolean(TUE_SWITCH_STATUS, false);
                    myEditor.putBoolean(TUE_STATUS, false);
                    myEditor.apply();
                }
            }
        });

        switch_wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    wed.setImageResource(R.drawable.wednesday_colored);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Wednesday");
                    myref.setValue(1);
                    myEditor.putBoolean(WED_SWITCH_STATUS, true);
                    myEditor.putBoolean(WED_STATUS, true);
                    myEditor.apply();
                    simpleSwitchPump.setChecked(false);
                } else {
                    wed.setImageResource(R.drawable.wednesday_bw_removebg);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Wednesday");
                    myref.setValue(0);
                    myEditor.putBoolean(WED_SWITCH_STATUS, false);
                    myEditor.putBoolean(WED_STATUS, false);
                    myEditor.apply();
                }
            }
        });

        switch_thu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    thu.setImageResource(R.drawable.thursday_colored_removebg);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Thursday");
                    myref.setValue(1);
                    myEditor.putBoolean(THU_SWITCH_STATUS, true);
                    myEditor.putBoolean(THU_STATUS, true);
                    myEditor.apply();
                    simpleSwitchPump.setChecked(false);
                } else {
                    thu.setImageResource(R.drawable.thursday_bw_removebg);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Thursday");
                    myref.setValue(0);
                    myEditor.putBoolean(THU_SWITCH_STATUS, false);
                    myEditor.putBoolean(THU_STATUS, false);
                    myEditor.apply();
                }
            }
        });

        switch_fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fri.setImageResource(R.drawable.friday_colored);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Friday");
                    myref.setValue(1);
                    myEditor.putBoolean(FRI_SWITCH_STATUS, true);
                    myEditor.putBoolean(FRI_STATUS, true);
                    myEditor.apply();
                    simpleSwitchPump.setChecked(false);
                } else {
                    fri.setImageResource(R.drawable.friday_bw_removebg);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Friday");
                    myref.setValue(0);
                    myEditor.putBoolean(FRI_SWITCH_STATUS, false);
                    myEditor.putBoolean(FRI_STATUS, false);
                    myEditor.apply();
                }
            }
        });

        switch_sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sat.setImageResource(R.drawable.saturday_colored);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Saturday");
                    myref.setValue(1);
                    myEditor.putBoolean(SAT_SWITCH_STATUS, true);
                    myEditor.putBoolean(SAT_STATUS, true);
                    myEditor.apply();
                    simpleSwitchPump.setChecked(false);
                } else {
                    sat.setImageResource(R.drawable.saturday_bw_removebg);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Saturday");
                    myref.setValue(0);
                    myEditor.putBoolean(SAT_SWITCH_STATUS, false);
                    myEditor.putBoolean(SAT_STATUS, false);
                    myEditor.apply();
                }
            }
        });

        switch_sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sun.setImageResource(R.drawable.sunday_colored);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Sunday");
                    myref.setValue(1);
                    myEditor.putBoolean(SUN_SWITCH_STATUS, true);
                    myEditor.putBoolean(SUN_STATUS, true);
                    myEditor.apply();
                    simpleSwitchPump.setChecked(false);
                } else {
                    sun.setImageResource(R.drawable.sunday_bw_removebg);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Date_of_the_Week/Sunday");
                    myref.setValue(0);
                    myEditor.putBoolean(SUN_SWITCH_STATUS, false);
                    myEditor.putBoolean(SUN_STATUS, false);
                    myEditor.apply();
                }
            }
        });



        simpleSwitchPump.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pump.setImageResource(R.drawable.pump2);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Pump");
                    myref.setValue(1);
                    myEditor.putBoolean(SWITCH_STATUS, true); //here will set switch status true
                    myEditor.putBoolean(PUMP_STATUS, true); //and also pump status set to true if switch button is on
                    myEditor.apply();
                    switch_mon.setChecked(false);
                    switch_tue.setChecked(false);
                    switch_wed.setChecked(false);
                    switch_thu.setChecked(false);
                    switch_fri.setChecked(false);
                    switch_sat.setChecked(false);
                    switch_sun.setChecked(false);

                } else {
                    pump.setImageResource(R.drawable.pumpoff);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("Pump");
                    myref.setValue(0);
                    myEditor.putBoolean(SWITCH_STATUS, false); //here will set switch status false
                    myEditor.putBoolean(PUMP_STATUS, false); //here will make in false
                    myEditor.apply();
                }
            }
        });




        myDB = FirebaseDatabase.getInstance().getReference("Soil");
        myDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String soil = snapshot.getValue().toString();
                int soilData = Integer.parseInt(soil);

                txt_soil.setText(soil);

                if (soilData < 370){
                    txt_plant_status.setText("Status:\n The Soil Moisture is in the water");
                }else if(soilData >= 370 && soilData < 600){
                    txt_plant_status.setText("Status:\n The Soil is Humid");
                }else if(soilData >= 600 && soilData < 1000){
                    txt_plant_status.setText("Status:\n The Soil is Dry!");
                }else{
                    txt_plant_status.setText("Status:\n Soil Moisture Sensor is not in the soil");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        navigationDrawer();


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        menu_burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void loadActivity(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_lay,fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Log:
                Intent intent = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.Instruction:
                Intent ent = new Intent(this, InstructionActivity.class);
                startActivity(ent);
                break;
            case R.id.About:
                Intent j = new Intent(this, AboutActivity.class);
                startActivity(j);
                break;
            case R.id.Logout:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(HomeActivity.this,SignAndRegActivity.class);
                                startActivity(i);
                                SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("remember","false");
                                editor.apply();
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}