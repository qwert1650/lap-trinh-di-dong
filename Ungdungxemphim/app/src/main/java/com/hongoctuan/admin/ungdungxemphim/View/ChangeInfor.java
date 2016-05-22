package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.hongoctuan.admin.ungdungxemphim.BUS.ChangeInforBUS;
import com.hongoctuan.admin.ungdungxemphim.DTO.AccountDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

public class ChangeInfor extends AppCompatActivity {
    EditText editCMND, editPhone, editAge;
    RadioButton rbNam, rbNu;
    Button btnXacnhan;
    String gioitinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_infor);
        editCMND = (EditText) findViewById(R.id.editCMND);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editAge = (EditText) findViewById(R.id.editAge);
        rbNam = (RadioButton) findViewById(R.id.rbNam);
        rbNu = (RadioButton) findViewById(R.id.rbNu);
        btnXacnhan = (Button) findViewById(R.id.btnXacnhan);
        Intent callerIntent=getIntent();
        final AccountDTO accountDTO= (AccountDTO) callerIntent.getSerializableExtra("user");
        if(accountDTO.getSex().equals("Nam"))
            rbNam.setChecked(true);
        else
            rbNu.setChecked(true);

        editCMND.setText(accountDTO.getIdNumber());
        editPhone.setText(accountDTO.getPhoneNumber());
        editAge.setText(accountDTO.getAge());
        gioitinh = accountDTO.getSex();
        rbNam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rbNu.setChecked(false);
                gioitinh = "Nam";
            }
        });
        rbNu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rbNam.setChecked(false);
                gioitinh = "Nu";
            }
        });
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String []arr = {accountDTO.getAccountName(),editCMND.getText().toString(),editPhone.getText().toString(),gioitinh,editAge.getText().toString()};
                ChangeInforBUS changeInforBUS = new ChangeInforBUS(getApplicationContext());
                changeInforBUS.execute(arr);
            }
        });
    }
}
