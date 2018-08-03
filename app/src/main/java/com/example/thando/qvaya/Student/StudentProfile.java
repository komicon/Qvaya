package com.example.thando.qvaya.Student;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thando.qvaya.R;
import com.example.thando.qvaya.pushNitificationRealocatedriver.BackgroundWorkerDelatAlocate;
import com.example.thando.qvaya.pushNitificationRealocatedriver.ConfigsDelatAlocate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import static com.example.thando.qvaya.Student.StudentHome.StudentNumber;

public class StudentProfile extends AppCompatActivity {

    TextView  StudentNumb;
    TextView  IDNo;
    TextView  PassoprtNumber;
    TextView  DOB;
    TextView  StudentName;
    TextView  Gender;
    TextView  Citizenship;
    TextView  Disability;
    TextView  CellNumber;
    TextView  Email;
    TextView  Qualification;
    TextView  StudyPeriod;
    TextView  RegistrationPeriod;
    TextView  Destination;
    ImageView ProfileImg;
    String  StudID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), StudentHome.class));
                new Intent().putExtra("Studnum", StudentNumber);
            }
        });
        StudentName = findViewById(R.id.txtFullName);
        Email = findViewById(R.id.txtEmail);
        Gender = findViewById(R.id.txtGender);
        DOB = findViewById(R.id.txtDob);
        CellNumber = findViewById(R.id.txtNumber);
        IDNo = findViewById(R.id.txtIDNum);
        Qualification = findViewById(R.id.txtEducation);
        StudyPeriod = findViewById(R.id.txtStudyPeriod);
        RegistrationPeriod = findViewById(R.id.txtRegPer);
        PassoprtNumber = findViewById(R.id.txtPassport);
        Destination = findViewById(R.id.designation);
        ProfileImg = findViewById(R.id.profile);
        StudentNumb = findViewById(R.id.location);
        Citizenship = findViewById(R.id.txtCitizen);
        Disability = findViewById(R.id.txtDisability);
        //  String  StudID = "21301119";
        StudID = StudentNumber;
        //String  StudID = getIntent().getStringExtra("langa");
        Toast.makeText(this, StudID, Toast.LENGTH_LONG).show();

        if (StudentNumber == null) {
            StudID = new Intent().getStringExtra("stNum");
        }
        String type = "sefing";
        BackgroudStudentProf bkw = new BackgroudStudentProf(this);
        try {
            bkw.execute(type, StudID);
        } catch (Exception e)
        {
        }
        /// grab from database from database
//
        try {

            JSONObject jobj = new JSONObject(bkw.get());

            // Toast.makeText(getApplicationContext()," "+bkw.get(),Toast.LENGTH_LONG).show();

            JSONArray jr = jobj.getJSONArray("StudentProfile");


            for (int i = 0; i < jr.length(); i++) {

                //Getting json object
                JSONObject json = jr.getJSONObject(i);
                StudentName.setText(json.getString("StudentName"));
                //Adding the name of the student to array list
                // oldRess.setText(json.getString(ConfigsDelatAlocate.TAG_ResISNames));

                Email.setText(json.getString("Email"));
                Gender.setText(json.getString("Gender"));
                DOB.setText(json.getString("DOB"));
                CellNumber.setText(json.getString("CellNumber"));
                Qualification.setText(json.getString("Qualification"));
                IDNo.setText(json.getString("IDNo"));
                RegistrationPeriod.setText((json.getString("RegistrationPeriod")));
                StudyPeriod.setText(json.getString("StudyPeriod"));
                PassoprtNumber.setText(json.getString("PassoprtNumber"));
                Destination.setText(json.getString("ResID"));
                StudentNumb.setText(json.getString("StudentNumber"));
                Disability.setText(json.getString("Disability"));
                Citizenship.setText(json.getString("Citizenship"));

                ImageDownloader imgDown = new ImageDownloader();
                Bitmap myImage;
                try {
                    myImage = imgDown.execute(json.getString("StudentImage")).get();
                    ProfileImg.setImageBitmap(myImage);
                } catch (Exception e) {
                    e.printStackTrace();

                }


                //   Toast.makeText(this,json.getString(ConfigsDelatAlocate.TAG_ResISNames),Toast.LENGTH_LONG).show();
                //   oldTime.setText(json.getString(ConfigsDelatAlocate.TAG_Time));
                //  Toast.makeText(this,json.getString("StudentNumber"),Toast.LENGTH_LONG).show();
                //   Toast.makeText(this,json.getString("IDNo"),Toast.LENGTH_LONG).show();
                //    Toast.makeText(this,json.getString("PassoprtNumber"),Toast.LENGTH_LONG).show();
                //   Toast.makeText(this,json.getString("DOB"),Toast.LENGTH_LONG).show();
                //   Toast.makeText(this,json.getString("StudentName"),Toast.LENGTH_LONG).show();
                //    Toast.makeText(this,json.getString("Gender"),Toast.LENGTH_LONG).show();
                //    Toast.makeText(this,json.getString("Citizenship"),Toast.LENGTH_LONG).show();
                //  Toast.makeText(this,json.getString("ResID"),Toast.LENGTH_LONG).show();
                //  Toast.makeText(this,json.getString("CellNumber"),Toast.LENGTH_LONG).show();
                //   Toast.makeText(this,json.getString("Email"),Toast.LENGTH_LONG).show();
                //  Toast.makeText(this,json.getString("Qualification"),Toast.LENGTH_LONG).show();
                //  Toast.makeText(this,json.getString("StudyPeriod"),Toast.LENGTH_LONG).show();
                //  Toast.makeText(this,json.getString("RegistrationPeriod"),Toast.LENGTH_LONG).show();
                // Toast.makeText(this,json.getString(ConfigsDelatAlocate.TAG_Time),Toast.LENGTH_LONG).show();
                //   prevRess = json.getString(ConfigsDelatAlocate.TAG_ResISNames);
                //    prevTime = json.getString(ConfigsDelatAlocate.TAG_Time);

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public class ImageDownloader extends AsyncTask<String,Void,Bitmap>
    {


        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }

}
// String fetch_url = "https://qvayaapp.000webhostapp.com/Mtha/studentProfileDetails.php";

//{"StudentProfile":[{"StudentNumber":"21301119","IDNo":"9005204707086","PassoprtNumber":"N\/A","DOB":"1990 May 20",
//        "StudentName":"Patshaza Khumalo","Gender":"Female","Citizenship":"South African",
//        "ResID":"AdrianRoadMUT","Disability":"N\/A","CellNumber":"0765430090"
//        ,"Email":"peachQueen","Qualification":"Electrical engineering","RegistrationPeriod":"4 yrs"}]}