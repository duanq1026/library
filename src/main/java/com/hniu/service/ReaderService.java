package com.hniu.service;

import com.hniu.entity.Readers;
import com.hniu.entity.vo.PageVo;
import com.hniu.entity.vo.ReaderVo;

import java.util.List;
import java.util.Map;

/**
 *
 * @Description: 读者业务逻辑
 * @author yangf
 * @date 2018年4月29日 上午11:52:48
 * @version 1.0
 */
public interface ReaderService {

    /**
     * 根据openid查询读者
     */
    Readers selectByWechat(String wxid);

    /**
     * Title: deleteByPrimaryKey
     * Description: 根据读者id主键删除读者
     * @param rid
     * @return
     */
    int deleteByPrimaryKey(Integer rid);
    /**
     *
     * Title: insert
     * Description: 添加读者
     * @param record
     * @return
     */
    Readers insert(Readers record);
    /**
     *
     * Title: selectByPrimaryKey
     * Description: 根据读者id主键查询读者
     * @param rid
     * @return
     */
    ReaderVo selectByPrimaryKey(Integer rid);
    /**
     *
     * Title: selectAll
     * Description: 查询读者列表
     * @return
     */
    void selectAll(PageVo<ReaderVo> pageVo);
    /**
     * Title: updateByPrimaryKey
     * Description: 根据读者id主键修改信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Readers record);

    /**
     *
     * Title: selectByShearch
     * Description: 根据微信号或名称模糊查询读者
     * @param
     * @return
     */
    void selectByShearch(PageVo<ReaderVo> pageVo);

    /**
     *
     * Title: selectSexCount
     * Description: TODO 统计男/女组数
     * @return
     */
    List<Map<String,Integer>> selectSexCount();
    /**
     *
     * Title: selectAgeCount
     * Description: 读者年龄间人数
     * @return
     *//*
    Integer selectAgeCount(Integer min, Integer max);*/
    /**
     *
     * Title: selectByRCode
     * Description: 根据书证号查询读者信息
     * @param readerCode
     * @return
     */
    ReaderVo selectByRCode(String readerCode);
    /**
     *
     * Title: updateOverdue
     * Description: 逾期次数+1
     * @param readerId
     * @return
     */
    int updateOverdue(Integer readerId);

    /**
     * 其他条件查询
     * @param reader
     * @return
     */
    List<ReaderVo> selectByOther(Readers reader);

    /**
     * 判读用户是否有借书资格
     * @param readerCode
     * @return
     */
    ReaderVo selectBorrowBooks(String readerCode);

    /**
     * 修改读者类型
     * @param reader
     * @return
     */
    int updateType(Readers reader);
}