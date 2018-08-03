package com.example.thando.qvaya.AdminCoordinator;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.net.HttpURLConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.EditText;
import android.net.Uri;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedWriter;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.io.OutputStreamWriter;
import java.net.URL;
import android.provider.MediaStore;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import android.util.Base64;



import com.example.thando.qvaya.AdminDriver.AdminHome;
import com.example.thando.qvaya.R;


public class AdminCreateCoordinator extends AppCompatActivity {
    Button CaptureImageFromCamera,UploadImageToServer;

    ImageView ImageViewHolder;

    EditText imageName;

    ProgressDialog progressDialog ;

    Intent intent ;

    public  static final int RequestPermissionCode  = 1 ;

    Bitmap bitmap;

    boolean check = true;

    String GetImageNameFromEditText;

    String ImageNameFieldOnServer = "image_name" ;

    String ImagePathFieldOnServer = "image_path" ;

    String ImageUploadPathOnSever ="https://qvayaapp.000webhostapp.com/Thando/MyApi/capture_img_upload_to_server.php" ;



    //Gender
    Spinner spin;

    String genderEmployee;

    private EditText emailBus;
    private EditText age;
    private EditText homAddEmp;
    private EditText firstnametxt;
    private EditText employeeSurname;
    private EditText cellnumbertxt;
    private EditText yearstarted;
    private AppCompatButton btnCreateDriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_coordinator);

        //Gender of the employee
        spin =  (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> passengerAdapter = ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item);
        passengerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(passengerAdapter);




        //End of the gender of employee




        cellnumbertxt =  findViewById(R.id.CellNumbertxt);


        firstnametxt = findViewById(R.id.EmpNameTxt);

        InputFilter filter1 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \nLetters Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        firstnametxt.setFilters(new InputFilter[] { filter1 });


        //-----------
        employeeSurname = findViewById(R.id.EmpSurnametxt);

        InputFilter filter2 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \nLetters Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        employeeSurname.setFilters(new InputFilter[] { filter2 });



        //--------------



        emailBus = findViewById(R.id.EmailEMployeeBus);

        //InputFilter filter3 = new InputFilter() {
        //   public CharSequence filter(CharSequence source, int start, int end,
        //                               Spanned dest, int dstart, int dend) {
        //        for (int i = start; i < end; i++) {
        //            if (!Character.isLetter(source.charAt(i)) && !Character.isDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
        //                Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \n Letters Only!", Toast.LENGTH_SHORT).show();
        //                return "";
        //            }
        //        }
        //        return null;
        //    }

        //  };

        //  emailBus.setFilters(new InputFilter[] { filter3 });
        //--------------------------------------------------------

        //-------------

        age = findViewById(R.id.AgeEmptxt);

        InputFilter filter4 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \n Numbers Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        age.setFilters(new InputFilter[] { filter4 });

        //------------
        homAddEmp = findViewById(R.id.HomeAddEmptxt);

        InputFilter filter5 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i)) && !Character.isDigit(source.charAt(i))&& !Character.isWhitespace(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \n Letters Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        homAddEmp.setFilters(new InputFilter[] { filter5 });
        //-------------
        cellnumbertxt = findViewById(R.id.CellNumbertxt);

        InputFilter filter6 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \n Letters Only and numbers!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        cellnumbertxt.setFilters(new InputFilter[] { filter6 });



        //-------------
        yearstarted = findViewById(R.id.testtxt);

        InputFilter filter7 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(AdminCreateCoordinator.this, "Invalid Input \n Numbers Only!", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }

        };

        yearstarted.setFilters(new InputFilter[] { filter7 });
        //--------------

        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), AdminHome.class));
            }
        });
//--------
        CaptureImageFromCamera = (Button)findViewById(R.id.CaptureImage);
        ImageViewHolder = (ImageView)findViewById(R.id.imageView);
        UploadImageToServer = (Button) findViewById(R.id.CreateDriverAdmin);
        imageName = (EditText)findViewById(R.id.imagetxt);
        EnableRuntimePermissionToAccessCamera();
        CaptureImageFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 7);

            }
        });
        UploadImageToServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetImageNameFromEditText = imageName.getText().toString();
                ImageUploadToServerFunction();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 7 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {

                // Adding captured image in bitmap.
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                // adding captured image in imageview.
                ImageViewHolder.setImageBitmap(bitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }

    // Requesting runtime permission to access camera.
    public void EnableRuntimePermissionToAccessCamera(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(AdminCreateCoordinator.this,
                Manifest.permission.CAMERA))
        {

            // Printing toast message after enabling runtime permission.
            Toast.makeText(AdminCreateCoordinator.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(AdminCreateCoordinator.this,new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    // Upload captured image online on server function.
    public void ImageUploadToServerFunction(){

        ByteArrayOutputStream byteArrayOutputStreamObject ;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        // Converting bitmap image to jpeg format, so by default image will upload in jpeg format.
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                // Showing progress dialog at image upload time.
                progressDialog = ProgressDialog.show(AdminCreateCoordinator.this,"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {
                progressDialog.dismiss();
                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                // Printing uploading success message coming from server on android app.
                Toast.makeText(AdminCreateCoordinator.this,string1,Toast.LENGTH_LONG).show();
                Log.i("image sats",string1);
                // Setting image as transparent after done uploading.
                ImageViewHolder.setImageResource(android.R.color.transparent);


            }

            @Override
            protected String doInBackground(Void... params) {

                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageNameFieldOnServer, GetImageNameFromEditText);

                HashMapParams.put(ImagePathFieldOnServer, ConvertImage);

                String FinalData = imageProcessClass.ImageHttpRequest(ImageUploadPathOnSever, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(19000);

                httpURLConnectionObject.setConnectTimeout(19000);

                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));

                bufferedWriterObject.write(bufferedWriterDataFN(PData));

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;

            stringBuilderObject = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

                if (check)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }

    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(AdminCreateCoordinator.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(AdminCreateCoordinator.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


    public void CreateCoordinaAdminBTN(View view) {

        GetImageNameFromEditText = imageName.getText().toString();

        ImageUploadToServerFunction();



        boolean error = false;
        genderEmployee= spin.getSelectedItem().toString();

        if(cellnumbertxt.getText().toString().length() < 10  )
        {
            error = true;
            cellnumbertxt.setError("Invalid Phone Number");
            if(cellnumbertxt.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
        if(cellnumbertxt.getText().toString().length() > 10){
            error = true;
            cellnumbertxt.setError("Invalid Phone Number, Must be ten Digits");
            if(cellnumbertxt.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
        if(firstnametxt.getText().toString().isEmpty())
        {
            error = true;
            firstnametxt.setError("Whats your first name?");
        }
        if(employeeSurname.getText().toString().isEmpty())
        {
            error = true;
            employeeSurname.setError("Whats your last name?");
        }

        if(emailBus.getText().toString().isEmpty())
        {
            error = true;
            emailBus.setError("Enter email");
        }


        if(genderEmployee.equalsIgnoreCase("--Select Gender--"))
        {
            error = true;
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
        }

        if(age.getText().toString().isEmpty())
        {
            error = true;
            age.setError("Enter Age");
            if(age.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
        if(age.getText().toString().length()>= 3)
        {
            error = true;
            age.setError("Enter Age, Must be two Digits");
            if(age.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }

        if(homAddEmp.getText().toString().isEmpty())
        {
            error = true;
            homAddEmp.setError("Enter Home Address");
        }
        if(yearstarted.getText().toString().isEmpty())
        {
            error = true;
            yearstarted.setError("Enter Year Started");
        }
        if(yearstarted.getText().toString().length() !=4){
            error = true;
            yearstarted.setError("Invalid Year, Must be four Digits");
            if(yearstarted.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
        if(!error)
        {

            //Assigning all the variables to the string before inserting to the database
            String genderEmployee = spin.getSelectedItem().toString();
            String JobType ="Bus Coordinator";
            String empName = firstnametxt.getText().toString();
            String empSurname = employeeSurname.getText().toString();
            String empEmail = emailBus.getText().toString();
            String empCell = cellnumbertxt.getText().toString();
            String empYear = yearstarted.getText().toString();
            String empHome = homAddEmp.getText().toString();
            String empAge = age.getText().toString();
            String type="InsertNewBusCoordinator";

            BackgroundCreateCoordinator backgroundWorker = new BackgroundCreateCoordinator(this);
            backgroundWorker.execute(type, genderEmployee, JobType,empName,empSurname,empEmail,empCell,empYear,empHome,empAge);


            Toast.makeText(this, "Coordinator created", Toast.LENGTH_SHORT).show();
            firstnametxt.getText().clear();
            employeeSurname.getText().clear();
            emailBus.getText().clear();
            cellnumbertxt.getText().clear();

            yearstarted.getText().clear();
            age.getText().clear();
            homAddEmp.getText().clear();
            spin.setSelection(0);


        } else {




            if(firstnametxt.getText().toString().isEmpty())
            {
                firstnametxt.requestFocus();
                if(firstnametxt.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }






            if(employeeSurname.getText().toString().isEmpty())
            {
                employeeSurname.requestFocus();
                if(employeeSurname.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }



            if(emailBus.getText().toString().isEmpty())
            {
                emailBus.requestFocus();
                if(emailBus.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }


            if(age.getText().toString().isEmpty())
            {
                age.setError("Invalid Must Enter digits");
                age.requestFocus();
                if(age.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }

            if(homAddEmp.getText().toString().isEmpty())
            {
                homAddEmp.requestFocus();
                if(homAddEmp.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }

            if(cellnumbertxt.getText().toString().length() < 10 || cellnumbertxt.getText().toString().isEmpty())
            {
                cellnumbertxt.setError("Invalid Must Have 10 digits");
                cellnumbertxt.requestFocus();
                if(cellnumbertxt.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }

            if(yearstarted.getText().toString().length() > 4 || yearstarted.getText().toString().length() < 4)
            {
                yearstarted.setError("Invalid Year");
                yearstarted.requestFocus();
                if(yearstarted.requestFocus()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        }

    }
}
