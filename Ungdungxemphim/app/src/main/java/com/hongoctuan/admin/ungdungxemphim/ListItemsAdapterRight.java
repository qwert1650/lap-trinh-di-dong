package com.hongoctuan.admin.ungdungxemphim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.DTO.AccountDTO;
import com.hongoctuan.admin.ungdungxemphim.View.ChangeInfor;
import com.hongoctuan.admin.ungdungxemphim.View.ChangePassword;
import com.hongoctuan.admin.ungdungxemphim.View.ForgetPassWord;
import com.hongoctuan.admin.ungdungxemphim.BUS.LoginAccountBUS;
import com.hongoctuan.admin.ungdungxemphim.View.LoginLayout;
import com.hongoctuan.admin.ungdungxemphim.View.registerLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 4/16/2016.
 */
public class ListItemsAdapterRight extends ArrayAdapter<Object> {
    Activity contextCha;
    ArrayList<String> dataArray_right;
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        public void onItemClick(View view, ImageView icon);
    }

    public ListItemsAdapterRight(Activity contextCha, List<Object> items, int x, ArrayList<String> dataArray_right,OnItemClickListener onItemClickListener) {
        // TODO Auto-generated constructor stub
        super(contextCha, android.R.layout.simple_list_item_single_choice, items);
        this.contextCha = contextCha;
        this.dataArray_right = dataArray_right;
        this.mOnItemClickListener = onItemClickListener;
    }
    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return dataArray_right.get(position);
    }

    public int getItemInteger(int pos)
    {
        return pos;

    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dataArray_right.size();
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        SharedPreferences pre=contextCha.getSharedPreferences("ungdungxemphim", contextCha.MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        final String username = pre.getString("username", "");
        if(!username.equals("")){
            LayoutInflater inflator = contextCha.getLayoutInflater();
            convertView = inflator.inflate(R.layout.login_success, null);
            final Button btnLogout = (Button) convertView.findViewById(R.id.btnLogout);
            final Button btnChangePass = (Button) convertView.findViewById(R.id.btnChangePass);
            final Button btnChangeInfor = (Button) convertView.findViewById(R.id.btnChangeInfor);
            final AccountDTO user = new AccountDTO();
            pre = contextCha.getSharedPreferences("ProfileImage", Context.MODE_PRIVATE);
            String pathImage = pre.getString("URI", "");
            final ImageView iv_login = (ImageView) convertView.findViewById(R.id.iv_loginsuccess);
            iv_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    mOnItemClickListener.onItemClick(v, iv_login);
                    contextCha.startActivityForResult(Intent.createChooser(intent, contextCha.getString(R.string.select_picture)), 101);
                }
            });
            if(!pathImage.equals("")) {
                Bitmap bm = BitmapFactory.decodeFile(pathImage);
                iv_login.setImageBitmap(bm);
            }else {
                iv_login.setImageResource(R.drawable.ic_user);
            }
            user.setId(Integer.parseInt(pre.getString("idname", "0")));
            user.setAccountName(pre.getString("username", ""));
            user.setIdNumber(pre.getString("cmnd", ""));
            user.setPhoneNumber(pre.getString("phone", ""));
            user.setSex(pre.getString("gioitinh", ""));
            user.setAge(pre.getString("tuoi", ""));
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView txt_accountname = (TextView)contextCha.findViewById(R.id.user_profile_name);
                    txt_accountname.setText("Account Name");
                    SharedPreferences pre= contextCha.getSharedPreferences("ungdungxemphim", contextCha.MODE_PRIVATE);
                    SharedPreferences.Editor editor=pre.edit();
                    editor.clear();
                    editor.putString("idname", "");
                    editor.putString("username", "");
                    editor.commit();
                    ViewGroup btnLogoutlayout = (ViewGroup) btnLogout.getParent();
                    if (null != btnLogoutlayout) //for safety only  as you are doing onClick
                        btnLogoutlayout.removeView(btnLogout);

                    ViewGroup btnChangePasslayout = (ViewGroup) btnChangePass.getParent();
                    if (null != btnChangePasslayout) //for safety only  as you are doing onClick
                        btnChangePasslayout.removeView(btnChangePass);

                    ViewGroup btnChangeInforLayout = (ViewGroup) btnChangeInfor.getParent();
                    if (null != btnChangeInforLayout) //for safety only  as you are doing onClick
                        btnChangeInforLayout.removeView(btnChangeInfor);

                    LoginLayout logoutAccout = new LoginLayout(contextCha);
                    logoutAccout.updateloginAccout();
                }
            });

            btnChangePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(contextCha, ChangePassword.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    intent.putExtra("myData", bundle);
                    contextCha.startActivity(intent);
                }
            });
            btnChangeInfor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(contextCha, ChangeInfor.class);
                    intent.putExtra("user", user);
                    contextCha.startActivity(intent);
                }
            });
        }else {
            LayoutInflater inflator = contextCha.getLayoutInflater();
            convertView = inflator.inflate(R.layout.login_layout, null);
            final EditText txtName = (EditText) convertView.findViewById(R.id.eidt_tendangnhap);
            final EditText txtPass = (EditText) convertView.findViewById(R.id.edit_password);
            Button btnLogin = (Button) convertView.findViewById(R.id.btn_logins);
            TextView txtRegister = (TextView) convertView.findViewById(R.id.txt_dangkytaikhoan);
            TextView txtForgetPass = (TextView) convertView.findViewById(R.id.txt_quenmatkhau);

            String text = dataArray_right.get(position);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginAccount(txtName.getText().toString(), txtPass.getText().toString());
                }
            });
            txtRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerLayout registerlayout = new registerLayout(contextCha);
                    registerlayout.RegisterLayout();
                }
            });
            txtForgetPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(contextCha, ForgetPassWord.class);
                    contextCha.startActivity(intent);
                }
            });
        }
        return convertView;
    }
    public void loginAccount(String name, String pass){
        LoginAccountBUS loginAccount_bus = new LoginAccountBUS(contextCha);
        loginAccount_bus.execute(name, pass);
    }
}