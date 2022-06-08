/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author PABLO
 */
public class NotaDTO {

    private int idNota, idMateria, idMatricula, idPeriodo;
    private double notaP1, notaP2, notaP3, notaP1Q, notaP2Q, notaP3Q;
    private String nombres, materia, periodo;

    
    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public double getNotaP1() {
        return notaP1;
    }

    public void setNotaP1(double notaP1) {
        this.notaP1 = notaP1;
    }

    public double getNotaP2() {
        return notaP2;
    }

    public void setNotaP2(double notaP2) {
        this.notaP2 = notaP2;
    }

    public double getNotaP3() {
        return notaP3;
    }

    public void setNotaP3(double notaP3) {
        this.notaP3 = notaP3;
    }

    public double getNotaP1Q() {
        return notaP1Q;
    }

    public void setNotaP1Q(double notaP1Q) {
        this.notaP1Q = notaP1Q;
    }

    public double getNotaP2Q() {
        return notaP2Q;
    }

    public void setNotaP2Q(double notaP2Q) {
        this.notaP2Q = notaP2Q;
    }

    public double getNotaP3Q() {
        return notaP3Q;
    }

    public void setNotaP3Q(double notaP3Q) {
        this.notaP3Q = notaP3Q;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

}
