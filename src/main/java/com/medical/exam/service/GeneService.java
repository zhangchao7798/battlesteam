package com.medical.exam.service;

import com.medical.exam.bean.po.Gene;

import java.util.List;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/12
 */
public interface GeneService {
    /**
     * select root genes
     * @return
     */
    List<Gene> selectRootGenes();

    /**
     *
     * @param id
     * @return
     */
    List<Gene> selectSubGenes(Long id);

    /**
     * find by id
     * @param id
     * @return
     */
    Gene findGeneById(Long id);
}
