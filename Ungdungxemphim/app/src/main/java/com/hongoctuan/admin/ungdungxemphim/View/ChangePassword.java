package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.BUS.ChangePasswordBUS;
import com.hongoctuan.admin.ungdungxemphim.R;

public class ChangePassword extends AppCompatActivity {

    String username="";
    EditText editChangePass;
    Button btnChangePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("myData");
        username = packageFromCaller.getString("username");
        editChangePass = (EditText) findViewById(R.id.edit_matkhaumoi);
        btnChangePass = (Button) findViewById(R.id.btn_matkhaumoi);
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String []arr = {username,editChangePass.getText().toString()};
                ChangePasswordBUS changePasswordBUS = new ChangePasswordBUS();
                changePasswordBUS.execute(arr);
                Toast.makeText(getApplicationContext(),"Đỗi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
