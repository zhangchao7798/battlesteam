package com.medical.exam.service.impl;

import com.medical.exam.bean.po.Gene;
import com.medical.exam.dao.GeneMapper;
import com.medical.exam.service.GeneService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/12
 */
@Service
public class GeneServiceImpl implements GeneService {
    private final static long ROOT_GENE_PARENT_ID = 0;
    private final GeneMapper geneMapper;

    public GeneServiceImpl(GeneMapper geneMapper) {
        this.geneMapper = geneMapper;
    }

    @Override
    public List<Gene> selectRootGenes() {
        Gene gene = new Gene();
        gene.setParentId(ROOT_GENE_PARENT_ID);
        return this.geneMapper.select(gene);
    }

    @Override
    public List<Gene> selectSubGenes(Long id) {
        Gene gene = new Gene();
        gene.setParentId(id);
        return this.geneMapper.select(gene);
    }

    @Override
    public Gene findGeneById(Long id) {

        return this.geneMapper.selectByPrimaryKey(id);
    }
}
