package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongoctuan.admin.ungdungxemphim.BUS.GetImageDetailBUS;
import com.hongoctuan.admin.ungdungxemphim.BUS.GetImageKhuyenMaiBUS;
import com.hongoctuan.admin.ungdungxemphim.DTO.KhuyenMaiDTO;
import com.hongoctuan.admin.ungdungxemphim.R;

public class Detail_NewsAndGift extends AppCompatActivity {
    ImageView ivHinhAnhKM;
    TextView txtTenKM,txtNgayKM,txtNoiDungKM,txtDieuKienKM,txtGhiChuKM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__news_and_gift);
        ivHinhAnhKM = (ImageView) findViewById(R.id.ivDetailKM);
        txtTenKM = (TextView) findViewById(R.id.txtDetailTenKM);
        txtNgayKM = (TextView) findViewById(R.id.txtDetailNgayKM);
        txtNoiDungKM = (TextView) findViewById(R.id.txtDetailNoiDungKM);
        txtDieuKienKM = (TextView) findViewById(R.id.txtDetailDieuKienKM);
        txtGhiChuKM = (TextView) findViewById(R.id.txtDetailGhiChuKM);

        Intent callerIntent=getIntent();
        final KhuyenMaiDTO khuyenMaiDTO= (KhuyenMaiDTO) callerIntent.getSerializableExtra("khuyenmai");
        GetImageDetailBUS getImageDetailBUS = new GetImageDetailBUS(this,ivHinhAnhKM);
        getImageDetailBUS.execute(khuyenMaiDTO.getHinhAnhKM());
        txtTenKM.setText(khuyenMaiDTO.getTenKM());
        txtNgayKM.setText(khuyenMaiDTO.getNgayKM());
        txtNoiDungKM.setText(khuyenMaiDTO.getNoiDungKM());
        txtDieuKienKM.setText(khuyenMaiDTO.getDieuKienKM());
        txtGhiChuKM.setText(khuyenMaiDTO.getGhiChuKM());


    }
}
