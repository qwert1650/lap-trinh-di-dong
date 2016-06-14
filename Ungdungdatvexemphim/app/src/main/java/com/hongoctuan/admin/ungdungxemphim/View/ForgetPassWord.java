package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hongoctuan.admin.ungdungxemphim.BUS.ResetPasswordBUS;
import com.hongoctuan.admin.ungdungxemphim.R;

public class ForgetPassWord extends AppCompatActivity {

    Button btnsendPhone;
    EditText editPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass_word);
        btnsendPhone = (Button) findViewById(R.id.btn_sendphone);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        btnsendPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passnew = "123abc";
                String[] arr={editPhone.getText().toString(),passnew};
                ResetPasswordBUS resetPasswordBUS = new ResetPasswordBUS();
                resetPasswordBUS.execute(arr);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(editPhone.getText().toString(), null, passnew, null, null);
                Toast.makeText(getApplicationContext(), "Đã gửi mật khẩu mới đến bạn", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        Button btn_huy = (Button) findViewById(R.id.btn_forgotHuy);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
