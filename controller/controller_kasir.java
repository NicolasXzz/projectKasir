/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.controller;
import com.view.form_kasir;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author NICOLAS JUANLY NA70
 */
public interface controller_kasir {
    public void Simpan(form_kasir kasir) throws SQLException;
    public void Batal(form_kasir kasir) throws SQLException;
    public void Tampil (form_kasir kasir) throws SQLException;
     public void Hapus (form_kasir kasir) throws SQLException;
     public void KlikTable(form_kasir kasir) throws SQLException;
    
}
