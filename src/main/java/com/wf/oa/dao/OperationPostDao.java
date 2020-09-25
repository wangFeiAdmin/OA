package com.wf.oa.dao;

import com.wf.oa.bean.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OperationPostDao {

    /**
     * 查询岗位
     * @param post
     * @return
     */
    List<Post> selectPost(Post post);

    /**
     * 删除岗位
     * @param p
     * @return
     */
    boolean deletePost(Post p);

    /**
     * 添加新的岗位
     * @param post
     * @return
     */
    boolean addPost(Post post);


    /**
     * 查询最大岗位编号
     * @return
     */
    int getMaxPostNo();

    /**
     * 修改岗位信息
     * @param post
     * @return
     */
    boolean updatePost(Post post);

    /**
     * 给对应的岗位设置权限
     * @param roleList
     * @return
     */
    boolean addAuth(List<String> roleList);
}
