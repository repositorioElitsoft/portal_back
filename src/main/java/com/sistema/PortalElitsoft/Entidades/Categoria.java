
package com.sistema.PortalElitsoft.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Noelid
 */
@Entity 
@Table ( name = "tbl_cat_exam")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_exam_id")
    private Long cat_exam_id;
    @Column(name = "cat_exam_titl") 
    private String cat_exam_titl; //titulo
    @Column(name = "cat_exam_desc") 
    private String cat_exam_desc;   //descripción
    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Examen> examenes = new LinkedHashSet<>();

    
    public Categoria() {
    }

    public Long getCat_exam_id() {
        return cat_exam_id;
    }

    public void setCat_exam_id(Long cat_exam_id) {
        this.cat_exam_id = cat_exam_id;
    }

    public String getCat_exam_titl() {
        return cat_exam_titl;
    }

    public void setCat_exam_titl(String cat_exam_titl) {
        this.cat_exam_titl = cat_exam_titl;
    }

    public String getCat_exam_desc() {
        return cat_exam_desc;
    }

    public void setCat_exam_desc(String cat_exam_desc) {
        this.cat_exam_desc = cat_exam_desc;
    }

    public Set<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(Set<Examen> examenes) {
        this.examenes = examenes;
    }
    
    
    
    
    
}
