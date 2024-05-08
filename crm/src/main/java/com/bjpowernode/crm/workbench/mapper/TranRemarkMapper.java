package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.TranRemark;

import java.util.List;

public interface TranRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Wed Nov 11 15:23:28 CST 2024
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Wed Nov 11 15:23:28 CST 2024
     */
    int insert(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Wed Nov 11 15:23:28 CST 2024
     */
    int insertSelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Wed Nov 11 15:23:28 CST 2024
     */
    TranRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Wed Nov 11 15:23:28 CST 2024
     */
    int updateByPrimaryKeySelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Wed Nov 11 15:23:28 CST 2024
     */
    int updateByPrimaryKey(TranRemark record);

    /**
     * 批量保存创建的交易备注
     * @param list
     * @return
     */
    int insertTranRemarkByList(List<TranRemark> list);

    /**
     * 根据tranId查询该交易下所有备注的明细信息
     * @param tranId
     * @return
     */
    List<TranRemark> selectTranRemarkForDetailByTranId(String tranId);
}