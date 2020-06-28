package com.medical.exam.bean.po;

import com.medical.exam.bean.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/12
 */
@Table(name = "sys_gene")
public class Gene extends BaseEntity {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    private Long parentId;
    private String geneName;

    public Long getId() {
        return id;
    }

    public Gene setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Gene setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getGeneName() {
        return geneName;
    }

    public Gene setGeneName(String geneName) {
        this.geneName = geneName;
        return this;
    }

    @Override
    public String toString() {
        return "Gene{" +
            "id=" + id +
            ", parentId=" + parentId +
            ", geneName='" + geneName + '\'' +
            "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gene)) return false;

        Gene gene = (Gene) o;

        if (id != null ? !id.equals(gene.id) : gene.id != null) return false;
        if (parentId != null ? !parentId.equals(gene.parentId) : gene.parentId != null) return false;
        return geneName != null ? geneName.equals(gene.geneName) : gene.geneName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (geneName != null ? geneName.hashCode() : 0);
        return result;
    }
}
