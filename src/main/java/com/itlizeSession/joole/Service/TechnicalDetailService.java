package com.itlizeSession.joole.Service;

import com.itlizeSession.joole.Entity.TechnicalDetail;

import java.awt.font.TextHitInfo;
import java.util.List;

/**
 * @ClassName TechnicalDetail
 * @Description TODO
 * @Author Yi Lin
 * @Date 5/16/22 03:14
 * @Version 1.0
 **/
public interface TechnicalDetailService {
    public TechnicalDetail findOneById(Integer Id);

    public List<TechnicalDetail> findAll();

    public TechnicalDetail save(TechnicalDetail technicalDetail);

    public List<TechnicalDetail> getTechnicalDetailByName(String name);

    public List<TechnicalDetail>  getTechnicalDetailByProductType(Integer productTypeId);

    public List<TechnicalDetail>  getTechnicalDetailByProduct(Integer productId);
}
