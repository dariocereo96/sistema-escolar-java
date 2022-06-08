
package DTO;

import java.util.List;

/**
 *
 * @author user
 */
public class PensumDTO {
    private int idNivel;
    private List<MateriaDTO> materias;

    public PensumDTO(int idNivel, List<MateriaDTO> materias) {
        this.idNivel = idNivel;
        this.materias = materias;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public List<MateriaDTO> getMaterias() {
        return materias;
    }

    public void setMaterias(List<MateriaDTO> materias) {
        this.materias = materias;
    }
    
}
