package com.hongoctuan.admin.ungdungxemphim.View;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.BUS.RegisterAccountBUS;
import com.hongoctuan.admin.ungdungxemphim.DTO.AccountDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

/**
 * Created by admin on 4/30/2016.
 */
public class registerLayout  {
    Activity context;
    public registerLayout(Activity context) {
        this.context = context;
    }
    public void RegisterLayout() {
        LoginLayout loginlayout = new LoginLayout(context);
        loginlayout.removeloginAccount();
        LinearLayout ll = (LinearLayout) context.findViewById(R.id.line_loginlayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //them title dang ky tai khoan
        final TextView txtRegister = new TextView(context);
        txtRegister.setText("Đăng ký tài khoản");
        txtRegister.setTextSize(20);
        ll.addView(txtRegister, lp);
        //them textview ten dang nhap
        final TextView txtTendangnhap = new TextView(context);
        txtTendangnhap.setText("Tên đăng nhập:");
        ll.addView(txtTendangnhap, lp);
        //them edittext ten dang nhap
        final EditText editTendangnhap = new EditText(context);
        txtTendangnhap.setHint("Nhập tên đăng nhập");
        ll.addView(editTendangnhap,lp);
        //them textview pass
        final TextView txtPass = new TextView(context);
        txtPass.setText("Mật khẩu:");
        ll.addView(txtPass, lp);
        //them edittext mat khau
        final EditText editMatkhau = new EditText(context);
        editMatkhau.setHint("Nhập mật khẩu");
        ll.addView(editMatkhau,lp);
        //them textview cmnd
        final TextView txtCmnd = new TextView(context);
        txtCmnd.setText("Cmnd:");
        ll.addView(txtCmnd, lp);
        //them edittext cmnd
        final EditText editCmnd = new EditText(context);
        editCmnd.setHint("Nhập CMND");
        ll.addView(editCmnd,lp);
        //them textview phone
        final TextView txtPhone = new TextView(context);
        txtPhone.setText("Điện thoại:");
        ll.addView(txtPhone, lp);
        //them edittext phone
        final EditText editPhone = new EditText(context);
        editPhone.setHint("Nhập điện thoại");
        ll.addView(editPhone,lp);
        //them textview gioi tinh
        final TextView txtGioitinh = new TextView(context);
        txtGioitinh.setText("Giới tính");
        ll.addView(txtGioitinh, lp);
        //them horizontal linelayout giới tính.

        LinearLayout lineGioitinh = new LinearLayout(context);
        lineGioitinh.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lpGioitinh = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(lineGioitinh, lp);
        //them radiobutton Nam

        final RadioButton rbNam = new RadioButton(context);
        rbNam.setText("Nam");
        rbNam.setChecked(true);
        lineGioitinh.addView(rbNam,lpGioitinh);
        //them radiobutton Nu
        final RadioButton rbNu = new RadioButton(context);
        rbNu.setText("Nữ");
        lineGioitinh.addView(rbNu,lpGioitinh);
        //them textview tuoi
        final TextView txtTuoi = new TextView(context);
        txtTuoi.setText("Tuổi");
        ll.addView(txtTuoi, lp);
        //them edittext tuoi
        final EditText editTuoi = new EditText(context);
        editTuoi.setHint("Nhập tuổi");
        ll.addView(editTuoi,lp);
        //them button đang ky
        final Button btnDangky = new Button(context);
        btnDangky.setText("Đăng Ký");
        ll.addView(btnDangky, lp);
        //bat su kien check radio Nam
        rbNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbNu.isChecked() == true) {
                    rbNam.setChecked(true);
                    rbNu.setChecked(false);
                }
            }
        });
        //bat su kien check radio Nu
        rbNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbNam.isChecked()== true){
                    rbNam.setChecked(false);
                    rbNu.setChecked(true);
                }
            }
        });
        //bat su kien dang ky
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDTO user = new AccountDTO();
                user.setAccountName(editTendangnhap.getText().toString());
                user.setPassword(editMatkhau.getText().toString());
                user.setIdNumber(editCmnd.getText().toString());
                user.setPhoneNumber(editPhone.getText().toString());
                user.setAge(editTuoi.getText().toString());
                if(rbNam.isChecked()){
                    user.setSex("Nam");
                }else{
                    user.setSex("Nữ");
                }
                RegisterAccountBUS registerAccountBUS = new RegisterAccountBUS(context);
                registerAccountBUS.execute(user);


                //xoa giao dien register
                //xoa title register
                //xoa textview login
                ViewGroup txtRegisterParent = (ViewGroup)txtRegister.getParent();
                if(null!=txtRegisterParent) //for safety only  as you are doing onClick
                    txtRegisterParent.removeView(txtRegister);
                //xoa textview ten dang nhap
                ViewGroup txtTendangnhapParent = (ViewGroup)txtTendangnhap.getParent();
                if(null!=txtTendangnhapParent) //for safety only  as you are doing onClick
                    txtTendangnhapParent.removeView(txtTendangnhap);
                //xoa edittext ten dang nhap
                ViewGroup editTendangnhapParent = (ViewGroup)editTendangnhap.getParent();
                if(null!=editTendangnhapParent) //for safety only  as you are doing onClick
                    editTendangnhapParent.removeView(editTendangnhap);
                //xoa textview mat khau
                ViewGroup txtPassParent = (ViewGroup)txtPass.getParent();
                if(null!=txtPassParent) //for safety only  as you are doing onClick
                    txtPassParent.removeView(txtPass);
                //xoa edittext mat khau
                ViewGroup editMatkhauParent = (ViewGroup)editMatkhau.getParent();
                if(null!=editMatkhauParent) //for safety only  as you are doing onClick
                    editMatkhauParent.removeView(editMatkhau);
                //xoa textview cmnd
                ViewGroup txtCmndParent = (ViewGroup)txtCmnd.getParent();
                if(null!=txtCmndParent) //for safety only  as you are doing onClick
                    txtCmndParent.removeView(txtCmnd);
                //xoa edittext cmnd
                ViewGroup editCmndParent = (ViewGroup)editCmnd.getParent();
                if(null!=editCmndParent) //for safety only  as you are doing onClick
                    editCmndParent.removeView(editCmnd);
                //xoa textview phone
                ViewGroup txtPhoneParent = (ViewGroup)txtPhone.getParent();
                if(null!=txtPhoneParent) //for safety only  as you are doing onClick
                    txtPhoneParent.removeView(txtPhone);
                //xoa edittext cmnd
                ViewGroup editPhoneParent = (ViewGroup)editPhone.getParent();
                if(null!=editPhoneParent) //for safety only  as you are doing onClick
                    editPhoneParent.removeView(editPhone);
                //xoa textview gioi tinh
                ViewGroup txtGioitinhParent = (ViewGroup)txtGioitinh.getParent();
                if(null!=txtGioitinhParent) //for safety only  as you are doing onClick
                    txtGioitinhParent.removeView(txtGioitinh);
                //xoa radio nam
                ViewGroup rbNamParent = (ViewGroup)rbNam.getParent();
                if(null!=rbNamParent) //for safety only  as you are doing onClick
                    rbNamParent.removeView(rbNam);
                //xoa radio nu
                ViewGroup rbNuParent = (ViewGroup)rbNu.getParent();
                if(null!=rbNuParent) //for safety only  as you are doing onClick
                    rbNuParent.removeView(rbNu);
                //xoa textview tuoi
                ViewGroup txtTuoiParent = (ViewGroup)txtTuoi.getParent();
                if(null!=txtTuoiParent) //for safety only  as you are doing onClick
                    txtTuoiParent.removeView(txtTuoi);
                //xoa edittext tuoi
                ViewGroup editTuoiParent = (ViewGroup)editTuoi.getParent();
                if(null!=editTuoiParent) //for safety only  as you are doing onClick
                    editTuoiParent.removeView(editTuoi);
                //xoa button dang ky
                ViewGroup btnDangkyParent = (ViewGroup)btnDangky.getParent();
                if(null!=btnDangkyParent) //for safety only  as you are doing onClick
                    btnDangkyParent.removeView(btnDangky);
            }
        });
    }
}
