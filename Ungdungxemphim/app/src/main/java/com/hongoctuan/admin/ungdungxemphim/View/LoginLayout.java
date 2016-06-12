package com.hongoctuan.admin.ungdungxemphim.View;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.BUS.LoginAccountBUS;
import com.hongoctuan.admin.ungdungxemphim.DTO.AccountDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

/**
 * Created by admin on 4/30/2016.
 */
public class LoginLayout {

    String username ="";
    Activity context;
    public LoginLayout(Activity context) {
        this.context = context;
    }

    public void loginSucessLayout(){
        ImageView ivIcon = new ImageView(context);
        LinearLayout ll = (LinearLayout)context.findViewById(R.id.line_loginlayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ll.addView(ivIcon, lp);
        //them textview ten dang nhap
        TextView txtTendangnhap = new TextView(context);
        txtTendangnhap.setText("noi dung");
        ll.addView(txtTendangnhap, lp);
        //them button doi thong tin.
        Button btnDoithongtin = new Button(context);
        btnDoithongtin.setText("Đổi Thông Tin");
        ll.addView(btnDoithongtin, lp);
        //them button doi mat khau
        Button btnDoimatkhau = new Button(context);
        btnDoimatkhau.setText("Đổi Mật Khẩu");
        ll.addView(btnDoimatkhau, lp);
        //them button dang xuat
        Button btnLogout = new Button(context);
        btnLogout.setText("Nhap");
        ll.addView(btnLogout, lp);
    }
    public void updateloginAccout(){
        LinearLayout ll = (LinearLayout)context.findViewById(R.id.line_loginlayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //them textview ten dang nhap
        TextView txtTendangnhap= new TextView(context);
        txtTendangnhap.setText("Tên Đăng Nhập:");
        txtTendangnhap.setId(R.id.txt_tendangnhap);
        ll.addView(txtTendangnhap, lp);
        //them edittext ten dang nhap
        //them edittext ten dang nhap
        final EditText editTendangnhap=new EditText(context);
        editTendangnhap.setHint("Nhập username");
        editTendangnhap.setId(R.id.eidt_tendangnhap);
        ll.addView(editTendangnhap, lp);
        //them textview mat khau
        TextView txtMatkhau= new TextView(context);
        txtMatkhau.setText("Mật Khẩu:");
        txtMatkhau.setId(R.id.txt_matkhau);
        ll.addView(txtMatkhau, lp);
        //them edittext mat khau
        final EditText editMatkhau = new EditText(context);
        editMatkhau.setId(R.id.edit_password);
        editMatkhau.setHint("Nhập password");
        editMatkhau.setSingleLine();
        ll.addView(editMatkhau,lp);
        //them button login
        Button btnLogin =  new Button(context);
        btnLogin.setText("Đăng Nhập");
        btnLogin.setId(R.id.btn_logins);
        btnLogin.isClickable();
        btnLogin.setBackgroundColor(Color.WHITE);
        lp.setMargins(5, 5, 5, 5);
        btnLogin.setPadding(20, 20, 20, 20);
        ll.addView(btnLogin, lp);
        //them button login facebook
        Button btnLoginfacebook = new Button(context);
        btnLoginfacebook.setText("Đăng Nhập Facebook");
        btnLoginfacebook.isClickable();
        btnLoginfacebook.setBackgroundColor(Color.WHITE);
        lp.setMargins(5, 5, 5, 5);
        btnLoginfacebook.setId(R.id.btn_loginfacebook);
        btnLoginfacebook.setPadding(20,20,20,20);
        ll.addView(btnLoginfacebook, lp);
        //them textview register
        TextView txtRegister = new TextView(context);
        txtRegister.setText("Đăng Ký Tài Khoản");
        txtRegister.setId(R.id.txt_dangkytaikhoan);
        lp.setMargins(5, 5, 5, 5);
        txtRegister.setBackgroundColor(Color.WHITE);
        txtRegister.isClickable();
        txtRegister.setPadding(20, 20, 20, 20);
        txtRegister.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtRegister.setTypeface(null, Typeface.BOLD);
        ll.addView(txtRegister,lp);
        //them textview quen mat khau
        TextView txtQuenmatkhau= new TextView(context);
        txtQuenmatkhau.setText("Quên Mật Khẩu");
        lp.setMargins(5, 5, 5, 5);
        txtQuenmatkhau.setId(R.id.txt_quenmatkhau);
        txtQuenmatkhau.setBackgroundColor(Color.WHITE);
        txtQuenmatkhau.isClickable();
        txtQuenmatkhau.setPadding(20, 20, 20, 20);
        txtQuenmatkhau.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtQuenmatkhau.setTypeface(null, Typeface.BOLD);
        ll.addView(txtQuenmatkhau, lp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTendangnhap.getText().toString();
                String pass =editMatkhau.getText().toString();
                loginAccount(username,pass);
            }
        });
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerLayout registerlayout = new registerLayout(context);
                registerlayout.RegisterLayout();
            }
        });
        txtQuenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ForgetPassWord.class);
                context.startActivity(intent);
            }
        });
    }

    public void removeloginAccount(){
        //xoa textview name
        TextView txtTendangnhap = (TextView) context.findViewById(R.id.txt_tendangnhap);
        ViewGroup txtTendangnhaplayout = (ViewGroup)txtTendangnhap.getParent();
        if(null!= txtTendangnhaplayout) //for safety only  as you are doing onClick
            txtTendangnhaplayout.removeView(txtTendangnhap);
        //xoa textview pass
        TextView txtMatkhau = (TextView) context.findViewById(R.id.txt_matkhau);
        ViewGroup txtMatkhaulayout = (ViewGroup)txtMatkhau.getParent();
        if(null!= txtTendangnhaplayout) //for safety only  as you are doing onClick
            txtMatkhaulayout.removeView(txtMatkhau);
        //xoa edittext name
        EditText editTendangnhap = (EditText) context.findViewById(R.id.eidt_tendangnhap);
        ViewGroup editTendangnhaplayout = (ViewGroup)editTendangnhap.getParent();
        if(null!= editTendangnhaplayout) //for safety only  as you are doing onClick
            editTendangnhaplayout.removeView(editTendangnhap);
        //xoa edittext pass
        EditText editMatkhau = (EditText) context.findViewById(R.id.edit_password);
        ViewGroup editMatkhaulayout = (ViewGroup)editMatkhau.getParent();
        if(null!= editMatkhaulayout) //for safety only  as you are doing onClick
            editMatkhaulayout.removeView(editMatkhau);
        //xoa login facebook
        Button btnLoginfacebook = (Button) context.findViewById(R.id.btn_loginfacebook);
        ViewGroup btnLoginfacebooklayout = (ViewGroup)btnLoginfacebook.getParent();
        if(null!= btnLoginfacebooklayout) //for safety only  as you are doing onClick
            btnLoginfacebooklayout.removeView(btnLoginfacebook);
        //xoa login dang ky tai khoan.
        TextView txtRegister = (TextView) context.findViewById(R.id.txt_dangkytaikhoan);
        ViewGroup txtRegisterLayout = (ViewGroup) txtMatkhaulayout.getParent();
        if(null != txtRegisterLayout)
            txtMatkhaulayout.removeView(txtRegister);
        //xoa textview quen mat khau
        TextView txtQuenmatkhau = (TextView) context.findViewById(R.id.txt_quenmatkhau);
        ViewGroup txtQuenmatkhaulayout = (ViewGroup)txtQuenmatkhau.getParent();
        if(null!= txtQuenmatkhaulayout) //for safety only  as you are doing onClick
            txtQuenmatkhaulayout.removeView(txtQuenmatkhau);
        //xoa button login
        Button btnLogin = (Button) context.findViewById(R.id.btn_logins);
        ViewGroup btnLoginlayout = (ViewGroup)btnLogin.getParent();
        if(null!=btnLoginlayout) //for safety only  as you are doing onClick
            btnLoginlayout.removeView(btnLogin);
    }
    private void loginAccount(String name, String pass){
        LoginAccountBUS loginAccount_bus = new LoginAccountBUS(context);
        loginAccount_bus.execute(name, pass);
    }

    //update layout when login success.
    public void updateLogginSuccesLayout(final AccountDTO user){
        username = user.getAccountName();
        TextView txt_user_profile_name = (TextView) context.findViewById(R.id.user_profile_name);
        txt_user_profile_name.setText(username);
        //them imageview icon
        final ImageView ivIcon = new ImageView(context);
        LinearLayout ll = (LinearLayout)context.findViewById(R.id.line_loginlayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //them button doi thong tin.
        final Button btnChangeInfor = new Button(context);
        btnChangeInfor.setText("Đổi Thông Tin");
        btnChangeInfor.isClickable();
        btnChangeInfor.setBackgroundColor(Color.WHITE);
        lp.setMargins(5, 5, 5, 5);
        btnChangeInfor.setPadding(20, 20, 20, 20);
        ll.addView(btnChangeInfor, lp);
        //them button doi mat khau
        final Button btnChangePass = new Button(context);
        btnChangePass.setText("Đổi Mật Khẩu");
        btnChangePass.isClickable();
        btnChangePass.setBackgroundColor(Color.WHITE);
        lp.setMargins(5, 5, 5, 5);
        btnChangePass.setPadding(20, 20, 20, 20);
        ll.addView(btnChangePass, lp);
        //them button dang xuat
        final Button btnLogout = new Button(context);
        btnLogout.setText("Đăng Xuất");
        btnLogout.isClickable();
        btnLogout.setBackgroundColor(Color.WHITE);
        lp.setMargins(5, 5, 5, 5);
        btnLogout.setPadding(20, 20, 20, 20);
        ll.addView(btnLogout, lp);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt_accountname = (TextView)context.findViewById(R.id.user_profile_name);
                txt_accountname.setText("Account Name");
                SharedPreferences pre= context.getSharedPreferences("ungdungxemphim", context.MODE_PRIVATE);
                SharedPreferences.Editor editor=pre.edit();
                editor.clear();
                editor.putString("idname", "");
                editor.putString("username", "");
                editor.commit();
                ViewGroup ivIconlayout = (ViewGroup) ivIcon.getParent();
                if (null != ivIconlayout) //for safety only  as you are doing onClick
                    ivIconlayout.removeView(ivIcon);

                ViewGroup btnLogoutlayout = (ViewGroup) btnLogout.getParent();
                if (null != btnLogoutlayout) //for safety only  as you are doing onClick
                    btnLogoutlayout.removeView(btnLogout);

                ViewGroup btnChangePasslayout = (ViewGroup) btnChangePass.getParent();
                if (null != btnChangePasslayout) //for safety only  as you are doing onClick
                    btnChangePasslayout.removeView(btnChangePass);

                ViewGroup btnChangeInforLayout = (ViewGroup) btnChangeInfor.getParent();
                if (null != btnChangeInforLayout) //for safety only  as you are doing onClick
                    btnChangeInforLayout.removeView(btnChangeInfor);

                LoginLayout logoutAccout = new LoginLayout(context);
                logoutAccout.updateloginAccout();
            }
        });

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChangePassword.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                intent.putExtra("myData", bundle);
                context.startActivity(intent);
            }
        });
        btnChangeInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChangeInfor.class);
                intent.putExtra("user", user);
                context.startActivity(intent);
            }
        });
    }
}
