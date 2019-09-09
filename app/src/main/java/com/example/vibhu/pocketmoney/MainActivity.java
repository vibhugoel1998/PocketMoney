package com.example.vibhu.pocketmoney;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentHome.OnFragmentInteractionListener,FragmentOffers.OnFragmentInteractionListener {
    ImageView NaviDrawerlogo;
    TabLayout tabLayout;
    private DatabaseReference mdatabase;
    ImageView viewandeditimage;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    boolean firstTimer;
    CircleImageView imageView;
    String number;
    RecyclerView recyclerView;
    ArrayList<String> arrayList;
    OffersAdapter offersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        recyclerView=findViewById(R.id.offerRecyclerView);
        Log.d("taarak",R.id.offerRecyclerView+"");
        arrayList=new ArrayList<>();
        for(int i=1;i<=5;i++){
            mdatabase.child("ClientShow").child("adv"+i).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String ur=dataSnapshot.getValue(String.class);
                    arrayList.add(ur);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        offersAdapter=new OffersAdapter(arrayList,this);
        recyclerView.setAdapter(offersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        number=prefs.getString("number","No number entered");
        Log.d("spf",number);
        firstTimer=prefs.getBoolean("first",true);
        viewandeditimage=findViewById(R.id.viewandeditimage);
        tabLayout=findViewById(R.id.tablayout);
        NaviDrawerlogo=findViewById(R.id.openNavigation);
        NaviDrawerlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(Gravity.START);
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        imageView=(CircleImageView)header.findViewById(R.id.profileImage);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos=tab.getPosition();
                if(pos==0){
                    Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.fragment_content_2);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,0);
                    params.weight=1.0f;
                    params.leftMargin=dptopixels(15);
                    params.rightMargin=dptopixels(15);
                    params.bottomMargin=dptopixels(20);
                    params.topMargin=dptopixels(40);
                    params.setLayoutDirection(LinearLayout.VERTICAL);
                    fragment.getView().setLayoutParams(params);
                    Fragment fragment2=getSupportFragmentManager().findFragmentById(R.id.fragment_offers);
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,0);
                    params2.weight=0.0f;
                    params2.setLayoutDirection(LinearLayout.VERTICAL);
                    fragment2.getView().setLayoutParams(params2);
                }
                else if(pos==1){
                    Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.fragment_content_2);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,0);
                    params.weight=0.0f;
                    params.setLayoutDirection(LinearLayout.VERTICAL);
                    fragment.getView().setLayoutParams(params);
                    Fragment fragment2=getSupportFragmentManager().findFragmentById(R.id.fragment_offers);
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,0);
                    params2.weight=1.0f;
                    params2.leftMargin=dptopixels(15);
                    params2.rightMargin=dptopixels(15);
                    params2.bottomMargin=dptopixels(20);
                    params2.topMargin=dptopixels(40);
                    params2.setLayoutDirection(LinearLayout.VERTICAL);
                    fragment2.getView().setLayoutParams(params2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        String url=prefs.getString("ProfilePicture","none");
        if(!url.equals("none")){
            Picasso.get().load(url).into(imageView);
        }
        boolean done=prefs.getBoolean("checkAllDone",false);
            if (done) {
                mdatabase.child("Entries").child(number).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String val = dataSnapshot.child("ApprovalRequired").getValue(String.class);
                        if (val != null && val.equals("yes")) {
                            CardView cardView = findViewById(R.id.StatusApproval);
                            cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        } else {
                            CardView cardView = findViewById(R.id.StatusApproval);
                            cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else {
                CardView cardView = findViewById(R.id.StatusApproval);
                cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
            }



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }

    public void LoanCalc(View view){
        Intent intent=new Intent(MainActivity.this,LoanCalculator.class);
        startActivity(intent);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }

    public void OpenLoanCalculator(View view){
        Intent intent=new Intent(MainActivity.this,LoanCalculator.class);
        startActivity(intent);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }

    public void OpenLogin(View view){
        Intent intent=new Intent(MainActivity.this,DisplayActivity.class);
        intent.putExtra("first",firstTimer);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }

    public void OpenViewAndEditProfile(View view){
        Intent intent=new Intent(MainActivity.this,ViewProfile.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String url=prefs.getString("ProfilePicture","none");
        if(!url.equals("none")){
            Picasso.get().load(url).into(imageView);
        }
        boolean done=prefs.getBoolean("checkAllDone",false);
        if(done){
            mdatabase.child("Entries").child(number).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String val=dataSnapshot.child("ApprovalRequired").getValue(String.class);
                    if(val!=null && val.equals("yes")){
                        CardView cardView=findViewById(R.id.StatusApproval);
                        cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    }
                    else {
                        CardView cardView=findViewById(R.id.StatusApproval);
                        cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else{
            CardView cardView=findViewById(R.id.StatusApproval);
            cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        }
    }

    public void doAutoPayment(View view){
        Intent intent=new Intent(MainActivity.this,AutoPaymentStatus.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }

    public void DoLogout(View view){
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean("verified",false);
        editor.apply();
        Intent i = new Intent(MainActivity.this, PhoneNumberEntry.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void Cash(View view){
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean done=prefs.getBoolean("checkAllDone",false);
        if(done){
            Intent intent=new Intent(MainActivity.this,CashActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
        }
        else{
            Toast.makeText(this, "Please complete your profile first!", Toast.LENGTH_SHORT).show();
        }
    }

    public void SeeAllLoansTaken(View view){
        Intent intent=new Intent(MainActivity.this,AllLoansTaken.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);

    }

    public void ReferandEarn(View view){
        Intent intent=new Intent(this,ReferEarn.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }

    public void LoanStatement(View view){
        Intent intent=new Intent(this,AllLoansTaken.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }

    public void OpenFaq(View view){
        Intent intent=new Intent(this,FaqActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }

    public void OpenHelp(View view){
        Intent intent=new Intent(this,HelpActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }

    public void OpenAboutUs(View view){
        Intent intent=new Intent(this,AboutUsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }

    public int dptopixels(int a){
        Resources r = MainActivity.this.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                a,
                r.getDisplayMetrics()
        );
        return px;
    }

}
