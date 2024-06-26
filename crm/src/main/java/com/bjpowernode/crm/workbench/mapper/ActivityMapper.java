package com.bjpowernode.crm.workbench.mapper;

import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {

    int deleteByPrimaryKey(String id);


    int insertSelective(Activity record);


    Activity selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(Activity record);


    int updateByPrimaryKey(Activity record);

    /**
     * 保存创建的市场活动
     */
    int insertActivity(Activity activity);

    /**
     * 根据条件分页查询市场活动的列表
     * @param map
     * @return
     */
    List<Activity> selectActivityByConditionForPage(Map<String,Object> map);

    /**
     * 根据条件查询市场活动的总条数
     * @param map
     * @return
     */
    int selectCountOfActivityByCondition(Map<String,Object> map);

    /**
     * 根据ids批量删除市场活动
     * @param ids
     * @return
     */
    int deleteActivityByIds(String[] ids);

    /**
     * 根据id查询市场活动的信息
     * @param id
     * @return
     */
    Activity selectActivityById(String id);

    /**
     * 保存修改的市场活动
     * @param activity
     * @return
     */
    int updateActivity(Activity activity);

    /**
     * 查询所有的市场活动
     * @return
     */
    List<Activity> selectAllActivitys();

    List<Activity> selectXzActivitys(String[] id);

    /**
     * 批量保存创建的市场活动
     * @param activityList
     * @return
     */
    int insertActivityByList(List<Activity> activityList);

    /**
     * 根据id查询市场活动的明细信息
     * @param id
     * @return
     */
    Activity selectActivityForDetailById(String id);

    /**
     * 根据clueId查询该线索相关联的市场活动的明细信息
     * @param clueId
     * @return
     */
    List<Activity> selectActivityForDetailByClueId(String clueId);

    /**
     * 根据name模糊查询市场活动，并且把已经跟clueId关联过的市场活动排除
     * @param map
     * @return
     */
    List<Activity> selectActivityForDetailByNameClueId(Map<String, Object> map);

    /**
     * 根据ids查询市场活动的明细信息
     * @param ids
     * @return
     */
    List<Activity> selectActivityForDetailByIds(String[] ids);

    /**
     * 根据name模糊查询市场活动，并且查询那些跟clueId关联过的市场活动
     * @param map
     * @return
     */
    List<Activity> selectActivityForConvertByNameClueId(Map<String, Object> map);

}