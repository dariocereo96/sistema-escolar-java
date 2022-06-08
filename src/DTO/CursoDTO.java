package DTO;

import java.util.Objects;

/**
 *
 * @author PABLO
 */
public class CursoDTO {

    private int idCurso, idNivel, capacidad;
    private String descripcion, paralelo, jornada, estado;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

  
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.idCurso;
        hash = 31 * hash + this.idNivel;
        hash = 31 * hash + this.capacidad;
        hash = 31 * hash + Objects.hashCode(this.descripcion);
        hash = 31 * hash + Objects.hashCode(this.paralelo);
        hash = 31 * hash + Objects.hashCode(this.jornada);
        hash = 31 * hash + Objects.hashCode(this.estado);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CursoDTO other = (CursoDTO) obj;
        if (this.idCurso != other.idCurso) {
            return false;
        }
        if (this.idNivel != other.idNivel) {
            return false;
        }
        if (this.capacidad != other.capacidad) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.paralelo, other.paralelo)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return true;
    }

    
    

}
