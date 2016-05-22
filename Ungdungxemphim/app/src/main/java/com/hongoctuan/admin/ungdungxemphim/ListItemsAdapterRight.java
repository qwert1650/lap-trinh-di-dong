package com.hongoctuan.admin.ungdungxemphim;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.View.ForgetPassWord;
import com.hongoctuan.admin.ungdungxemphim.BUS.LoginAccountBUS;
import com.hongoctuan.admin.ungdungxemphim.View.registerLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 4/16/2016.
 */
public class ListItemsAdapterRight extends ArrayAdapter<Object>
{
    Activity contextCha;
    ArrayList<String> dataArray_right;


    public ListItemsAdapterRight(Activity contextCha, List<Object> items, int x, ArrayList<String> dataArray_right) {
        // TODO Auto-generated constructor stub
        super(contextCha, android.R.layout.simple_list_item_single_choice, items);
        this.contextCha = contextCha;
        this.dataArray_right = dataArray_right;
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
        LayoutInflater inflator=contextCha.getLayoutInflater();
        convertView=inflator.inflate(R.layout.login_layout, null);
        final EditText txtName =(EditText) convertView.findViewById(R.id.eidt_tendangnhap);
        final EditText txtPass = (EditText) convertView.findViewById(R.id.edit_matkhau);
        Button btnLogin = (Button) convertView.findViewById(R.id.btn_login);
        TextView txtRegister = (TextView) convertView.findViewById(R.id.txt_dangkytaikhoan);
        TextView txtForgetPass = (TextView) convertView.findViewById(R.id.txt_quenmatkhau);
        String text=dataArray_right.get(position);
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
        return convertView;
    }
    public void loginAccount(String name, String pass){
        LoginAccountBUS loginAccount_bus = new LoginAccountBUS(contextCha);
        loginAccount_bus.execute(name, pass);
    }
}