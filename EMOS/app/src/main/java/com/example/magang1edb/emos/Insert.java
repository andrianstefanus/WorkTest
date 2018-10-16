package com.example.magang1edb.emos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Insert extends AppCompatActivity {
    private TextInputEditText cabangInput;
    private TextInputEditText namaSupplierInput;
    private TextInputEditText alamatSupplierInput;
    private TextInputEditText kotaSupplierInput;
    private TextInputEditText pbfInput;
    private TextInputEditText noIjinPBFInput;
    private TextInputEditText noSIUPInput;
    private TextInputEditText namaAPJInput;
    private TextInputEditText noSIKAInput;
    private TextInputEditText namaPajakInput;
    private TextInputEditText npwpSupplierInput;
    private TextInputEditText namaKontakInput;
    private TextInputEditText teleponKontakInput;

    private TextInputLayout cabangLayout;
    private TextInputLayout namaSupplierLayout;
    private TextInputLayout alamatSupplierLayout;
    private TextInputLayout kotaSupplierLayout;
    private TextInputLayout pbfLayout;
    private TextInputLayout noIjinPBFLayout;
    private TextInputLayout noSIUPLayout;
    private TextInputLayout namaAPJLayout;
    private TextInputLayout noSIKALayout;
    private TextInputLayout namaPajakLayout;
    private TextInputLayout npwpSupplierLayout;
    private TextInputLayout namaKontakLayout;
    private TextInputLayout teleponKonntakLayout;
    private static TextInputLayout masaBerlakuPBFLayout;
    private static TextInputLayout masaBerlakuSIUPLayout;
    private static TextInputLayout masaBerlakuAPJLayout;

    private static Button masaBerlakuPBF;
    private static Button masaBerlakuSIUP;
    private static Button masaBerlakuAPJ;

    private DialogFragment dialogFragment;
    private static String activeDF;
    private static boolean onSelectPBF = false;
    private static boolean onSelectSIUP = false;
    private static boolean onSelectAPJ = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.insert_layout);

        cabangInput = findViewById(R.id.cabang);
        namaSupplierInput = findViewById(R.id.nama_supplier);
        alamatSupplierInput = findViewById(R.id.alamat_supplier);
        kotaSupplierInput = findViewById(R.id.kota_supplier);
        pbfInput = findViewById(R.id.pbf);
        noIjinPBFInput = findViewById(R.id.no_ijin_pbf);
        noSIUPInput = findViewById(R.id.no_siup);
        namaAPJInput = findViewById(R.id.nama_apj);
        noSIKAInput = findViewById(R.id.no_sika);
        namaPajakInput = findViewById(R.id.nama_pajak);
        npwpSupplierInput = findViewById(R.id.npwp_supplier);
        namaKontakInput = findViewById(R.id.nama_kontak);
        teleponKontakInput = findViewById(R.id.telepon_kontak);

        cabangLayout = findViewById(R.id.cabang_layout);
        namaSupplierLayout = findViewById(R.id.nama_supplier_layout);
        alamatSupplierLayout = findViewById(R.id.alamat_supplier_layout);
        kotaSupplierLayout = findViewById(R.id.kota_suplier_layout);
        pbfLayout = findViewById(R.id.pbf_layout);
        noIjinPBFLayout = findViewById(R.id.no_ijin_pbf_layout);
        noSIUPLayout = findViewById(R.id.no_siup_layout);
        namaAPJLayout = findViewById(R.id.nama_apj_layout);
        noSIKALayout = findViewById(R.id.no_sika_layout);
        namaPajakLayout = findViewById(R.id.nama_pajak_layout);
        npwpSupplierLayout = findViewById(R.id.npwp_supplier_layout);
        namaKontakLayout = findViewById(R.id.nama_kontak_layout);
        teleponKonntakLayout = findViewById(R.id.telepon_kontak_layout);
        masaBerlakuPBFLayout = findViewById(R.id.masa_berlaku_pbf_layout);
        masaBerlakuSIUPLayout = findViewById(R.id.masa_berlaku_siup_layout);
        masaBerlakuAPJLayout = findViewById(R.id.masa_berlaku_apj_layout);

        cabangInput.addTextChangedListener(new AutoCheckerInput(cabangInput));
        namaSupplierInput.addTextChangedListener(new AutoCheckerInput(namaSupplierInput));
        alamatSupplierInput.addTextChangedListener(new AutoCheckerInput(alamatSupplierInput));
        kotaSupplierInput.addTextChangedListener(new AutoCheckerInput(kotaSupplierInput));
        pbfInput.addTextChangedListener(new AutoCheckerInput(pbfInput));
        noIjinPBFInput.addTextChangedListener(new AutoCheckerInput(noIjinPBFInput));
        noSIUPInput.addTextChangedListener(new AutoCheckerInput(noSIUPInput));
        namaAPJInput.addTextChangedListener(new AutoCheckerInput(namaAPJInput));
        noSIKAInput.addTextChangedListener(new AutoCheckerInput(noSIKAInput));
        namaPajakInput.addTextChangedListener(new AutoCheckerInput(namaPajakInput));
        npwpSupplierInput.addTextChangedListener(new AutoCheckerInput(npwpSupplierInput));
        namaKontakInput.addTextChangedListener(new AutoCheckerInput(namaKontakInput));
        teleponKontakInput.addTextChangedListener(new AutoCheckerInput(teleponKontakInput));

        masaBerlakuPBF = findViewById(R.id.masa_berlaku_pbf);
        masaBerlakuSIUP = findViewById(R.id.masa_berlaku_siup);
        masaBerlakuAPJ = findViewById(R.id.masa_berlaku_apj);

        dialogFragment = new DatePickerFragment();
    }

    public void showDatePickerDialog(View view) {
        switch (view.getId()) {
            case R.id.masa_berlaku_pbf :  activeDF = "PBF"; break;
            case R.id.masa_berlaku_siup :  activeDF = "SIUP"; break;
            case R.id.masa_berlaku_apj :  activeDF = "APJ"; break;
        }
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void register(View view) {
        validate(cabangLayout, cabangInput, 10, getString(R.string.err_msg_cabang));
        validate(namaSupplierLayout, namaSupplierInput, 50, getString(R.string.err_msg_nama_supplier));
        validate(alamatSupplierLayout, alamatSupplierInput, 150, getString(R.string.err_msg_alamat_supplier));
        validate(kotaSupplierLayout, kotaSupplierInput, 50, getString(R.string.err_msg_kota_supplier));
        validate(pbfLayout, pbfInput, 50, getString(R.string.err_msg_pbf));
        validate(noIjinPBFLayout, noIjinPBFInput, 50, getString(R.string.err_msg_no_ijin_pbf));
        validate(noSIUPLayout, noSIUPInput, 50, getString(R.string.err_msg_no_siup));
        validate(namaAPJLayout, namaAPJInput, 50, getString(R.string.err_msg_nama_apj));
        validate(noSIKALayout, noSIKAInput, 50, getString(R.string.err_msg_no_sika));
        validate(namaPajakLayout, namaPajakInput, 50, getString(R.string.err_msg_nama_pajak));
        validate(npwpSupplierLayout, npwpSupplierInput, 20, getString(R.string.err_msg_npwp_supplier));
        validate(namaKontakLayout, namaKontakInput, 20, getString(R.string.err_msg_nama_kontak));
        validate(teleponKonntakLayout, teleponKontakInput, 20, getString(R.string.err_msg_telepon_kontak));
        validateDate(masaBerlakuPBFLayout, masaBerlakuPBF, onSelectPBF, getString(R.string.err_msg_masa_berlaku_pbf));
        validateDate(masaBerlakuSIUPLayout, masaBerlakuSIUP, onSelectSIUP, getString(R.string.err_msg_masa_berlaku_siup));
        validateDate(masaBerlakuAPJLayout, masaBerlakuAPJ, onSelectAPJ, getString(R.string.err_msg_masa_berlaku_apj));

        if (validate(cabangLayout, cabangInput, 10, getString(R.string.err_msg_cabang)) &&
                validate(namaSupplierLayout, namaSupplierInput, 50, getString(R.string.err_msg_nama_supplier)) &&
                validate(alamatSupplierLayout, alamatSupplierInput, 150, getString(R.string.err_msg_alamat_supplier)) &&
                validate(kotaSupplierLayout, kotaSupplierInput, 50, getString(R.string.err_msg_kota_supplier)) &&
                validate(pbfLayout, pbfInput, 50, getString(R.string.err_msg_pbf)) &&
                validate(noIjinPBFLayout, noIjinPBFInput, 50, getString(R.string.err_msg_no_ijin_pbf)) &&
                validate(noSIUPLayout, noSIUPInput, 50, getString(R.string.err_msg_no_siup)) &&
                validate(namaAPJLayout, namaAPJInput, 50, getString(R.string.err_msg_nama_apj)) &&
                validate(noSIKALayout, noSIKAInput, 50, getString(R.string.err_msg_no_sika)) &&
                validate(namaPajakLayout, namaPajakInput, 50, getString(R.string.err_msg_nama_pajak)) &&
                validate(npwpSupplierLayout, npwpSupplierInput, 20, getString(R.string.err_msg_npwp_supplier)) &&
                validate(namaKontakLayout, namaKontakInput, 20, getString(R.string.err_msg_nama_kontak)) &&
                validate(teleponKonntakLayout, teleponKontakInput, 20, getString(R.string.err_msg_telepon_kontak)))
            insertData();
    }

    private void insertData(){

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            String[] temp = new String[3];
            boolean status = false;
            switch (activeDF) {
                case "PBF" :
                    if (onSelectPBF) {
                        temp = masaBerlakuPBF.getText().toString().split(":")[1].trim().split("/");
                        status = true;
                    }
                    break;
                case "SIUP" :
                    if (onSelectSIUP) {
                        temp = masaBerlakuSIUP.getText().toString().split(":")[1].trim().split("/");
                        status = true;
                    }
                    break;
                case "APJ" :
                    if (onSelectAPJ) {
                        temp = masaBerlakuAPJ.getText().toString().split(":")[1].trim().split("/");
                        status = true;
                    }
                    break;
            }
            if (status){
                year = Integer.parseInt(temp[2]);
                month = Integer.parseInt(temp[1]);
                day = Integer.parseInt(temp[0]);
            }
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }


        public void onDateSet(DatePicker view, int year, int month, int day) {
            switch (activeDF) {
                case "PBF" :
                    masaBerlakuPBF.setText(masaBerlakuPBF.getText().toString().split(":")[0]+" :\n"+day+"/"+month+"/"+year);
                    masaBerlakuPBFLayout.setErrorEnabled(false);
                    onSelectPBF = true;
                    break;
                case "SIUP" :
                    masaBerlakuSIUP.setText(masaBerlakuSIUP.getText().toString().split(":")[0]+" :\n"+day+"/"+month+"/"+year);
                    masaBerlakuSIUPLayout.setErrorEnabled(false);
                    onSelectSIUP = true;
                    break;
                case "APJ" :
                    masaBerlakuAPJ.setText(masaBerlakuAPJ.getText().toString().split(":")[0]+" :\n"+day+"/"+month+"/"+year);
                    masaBerlakuAPJLayout.setErrorEnabled(false);
                    onSelectAPJ = true;
                    break;
            }
        }
    }

    private boolean validate(TextInputLayout targetLayout, TextInputEditText targetObject, Integer length, String msg_error){
        String value = targetObject.getText().toString();
        if (value.isEmpty() || value.length() > length) {
            targetLayout.setError(msg_error +" atau maks input "+length+" karakter.");
            requestFocus(targetObject);
            return false;
        } else {
            targetLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDate(TextInputLayout targetLayout, Button targetObject, boolean targetStatus, String msg_error){
        if (!targetStatus) {
            targetLayout.setError(msg_error);
            requestFocus(targetObject);
            return false;
        }else {
            targetLayout.setErrorEnabled(false);
        }
        return true;
    }

    private class AutoCheckerInput implements TextWatcher {
        private View view;
        private AutoCheckerInput(View view) { this.view = view; }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.cabang: validate(cabangLayout, cabangInput, 10, getString(R.string.err_msg_cabang)); break;
                case R.id.nama_supplier: validate(namaSupplierLayout, namaSupplierInput, 50, getString(R.string.err_msg_nama_supplier)); break;
                case R.id.alamat_supplier: validate(alamatSupplierLayout, alamatSupplierInput, 150, getString(R.string.err_msg_alamat_supplier)); break;
                case R.id.kota_supplier: validate(kotaSupplierLayout, kotaSupplierInput, 50, getString(R.string.err_msg_kota_supplier)); break;
                case R.id.pbf: validate(pbfLayout, pbfInput, 50, getString(R.string.err_msg_pbf)); break;
                case R.id.no_ijin_pbf: validate(noIjinPBFLayout, noIjinPBFInput, 50, getString(R.string.err_msg_no_ijin_pbf)); break;
                case R.id.no_siup: validate(noSIUPLayout, noSIUPInput, 50, getString(R.string.err_msg_no_siup)); break;
                case R.id.nama_apj: validate(namaAPJLayout, namaAPJInput, 50, getString(R.string.err_msg_nama_apj)); break;
                case R.id.no_sika: validate(noSIKALayout, noSIKAInput, 50, getString(R.string.err_msg_no_sika)); break;
                case R.id.nama_pajak: validate(namaPajakLayout, namaPajakInput, 50, getString(R.string.err_msg_nama_pajak)); break;
                case R.id.npwp_supplier: validate(npwpSupplierLayout, npwpSupplierInput, 20, getString(R.string.err_msg_npwp_supplier)); break;
                case R.id.nama_kontak: validate(namaKontakLayout, namaKontakInput, 20, getString(R.string.err_msg_nama_kontak)); break;
                case R.id.telepon_kontak: validate(teleponKonntakLayout, teleponKontakInput, 20, getString(R.string.err_msg_telepon_kontak)); break;
            }
        }
    }

    private void requestFocus(View view){
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
