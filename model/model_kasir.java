/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;
import com.controller.controller_kasir;
import com.koneksi.koneksi;
import com.view.form_kasir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author NICOLAS JUANLY NA70
 */
public class model_kasir implements com.controller.controller_kasir{
    String Ukuran ;
 
    @Override
    public void Simpan(form_kasir kasir) throws SQLException {
        if (kasir.UkuranM.isSelected()){
            Ukuran = "Ukuran M";
            
        }
        else if (kasir.UkuranL.isSelected()) {
            Ukuran = "Ukuran L";
            
        } else if (kasir.UkuranXl.isSelected()) {
            Ukuran = "Ukuran XL";
        }  else {
            Ukuran = "Ukuran XXL";
        } 
 
        
        
        try {
            Connection con = koneksi.getcon();
            String sql = "Insert Into toko Values(?,?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, kasir.txtId.getText());
            prepare.setString(2,  (String) kasir.txtjenisbaju.getSelectedItem());
            prepare.setString(3,Ukuran);
            prepare.setString(4, kasir.txtharga.getText());
            prepare.setString(5, kasir.txttotal.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil diSimpan");
            prepare.close();
            Batal(kasir);
        } catch (Exception e){
            System.out.println(e);
        } finally {
            Tampil(kasir);
        }

    }

    @Override
    public void Batal(form_kasir kasir) throws SQLException {
        kasir.txtId.setText("");
        kasir.txtjenisbaju.setSelectedItem("");
        kasir.txtharga.setText("");
        kasir.txttotal.setText("");
        
    }

    @Override
    public void Tampil(form_kasir kasir) throws SQLException {
            kasir.tblmodel.getDataVector().removeAllElements();
     kasir.tblmodel.fireTableDataChanged();
        try {
            Connection con = koneksi.getcon();
            Statement stt = con.createStatement();
           // Query Menampilkan Semua Data Pada Table Siswa
           // Dengan Urutan NIS Dari Kecil Ke Besar
           String sql = "SELECT * FROM toko";
           ResultSet res = stt.executeQuery(sql);
            while (res.next()) {                
                Object[] ob = new Object[7];
                ob[0] = res.getString(1);
                ob[1] = res.getString(2);
                ob[2] = res.getString(3);
                ob[3] = res.getString(4);
                ob[4] = res.getString(5);
                
                kasir.tblmodel.addRow(ob);
            }
           
        } catch (Exception e) {
            System.out.println(e);
        } 
    }

    @Override
    public void Hapus(form_kasir kasir) throws SQLException {
        try{
            Connection con = koneksi.getcon();
            String sql = "DELETE FROM toko WHERE id =?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, kasir.txtId.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhsil dihapus");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(kasir);
            
            
        }
    }

 

    @Override
    public void KlikTable(form_kasir kasir) throws SQLException {
        try{
            int pilih = kasir.Tabel.getSelectedRow();
            if (pilih == -1){
                return;
            }
            kasir.txtId.setText(kasir.tblmodel.getValueAt(pilih, 0).toString());
            kasir.txtharga.setText(kasir.tblmodel.getValueAt(pilih, 1).toString());
            kasir.txttotal.setText(kasir.tblmodel.getValueAt(pilih, 2).toString());
        } catch (Exception e) {
            
        }
        if(kasir.UkuranM.getText().equals(Ukuran)) {
            kasir.UkuranM.setSelected(true);
        } else if(kasir.UkuranL.getText().equals(Ukuran)) {
            kasir.UkuranL.setSelected(true);
        } else if(kasir.UkuranXl.getText().equals(Ukuran)) {
            kasir.UkuranXl.setSelected(true);
        } else {
            kasir.UkuranXxl.setSelected(true);
        }
    }

 

    

    

    
}
