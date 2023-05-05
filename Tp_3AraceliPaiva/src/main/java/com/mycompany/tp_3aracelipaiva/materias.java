/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_3aracelipaiva;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author ARA
 */
public class materias {
    int id;
    String codigo;
    String materia;
    String docente;
    String inicio;
    String fin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    
    public void InsertarMateria(JTextField paramcodigo, JTextField paramdocente,JTextField parammateria, JTextField paraminicio, JTextField paramfin){
        setCodigo(paramcodigo.getText());
        setDocente(paramdocente.getText());
        setMateria(parammateria.getText());
        setInicio(paraminicio.getText());
        setFin(paramfin.getText());
        conexion objetoConexion = new conexion();
        String Consulta = "INSERT INTO materias(codigo, docente, materia, inicio, fin) VALUES (?,?,?,?,?);";
        try{
            CallableStatement cs = objetoConexion.establecerConexcion().prepareCall(Consulta);
            cs.setString(1,getCodigo());
            cs.setString(2,getMateria());
            cs.setString(3,getDocente());
            cs.setString(4,getInicio());
            cs.setString(5,getFin());
            cs.execute();
            JOptionPane.showInternalMessageDialog(null, "Se creo correctamente");
        }catch(Exception e){
            JOptionPane.showInternalMessageDialog(null, "No se creo correctamente, error:"+e.toString());
        }
        
    }
    public void MostrarTabla (JTable paramtabla){
        conexion  objetoConexion = new conexion();
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel> (model);
        paramtabla.setRowSorter(OrdenarTabla);
        String sql="";
        model.addColumn("Id");
        model.addColumn("Codigo");
        model.addColumn("Materia");
        model.addColumn("Docente");
        model.addColumn("Inicio");
        model.addColumn("Fin");
        paramtabla.setModel(model);
        sql="SELECT * FROM materias";
        String [] datos = new String[6];
        Statement st;
        try{
            st = objetoConexion.establecerConexcion().createStatement();
            ResultSet result= st.executeQuery(sql);
            while(result.next()){
                datos[0]=result.getString(1);
                datos[1]=result.getString(2);
                datos[2]=result.getString(3);
                datos[3]=result.getString(4);
                datos[4]=result.getString(5);
                datos[5]=result.getString(6);
                model.addRow(datos);
            }
            paramtabla.setModel(model);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al mostrar los registros"+e.toString());
        }
        
    }
}