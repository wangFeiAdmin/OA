package com.wf.oa.service;

import com.wf.oa.bean.Post;

import java.util.List;

public interface OperationPostService {
    /**
     * 查询岗位
     * @param post
     * @return
     */
    List<Post> getPost(Post post);

    /**
     * 删除岗位
     * @param id
     * @return
     */
    boolean  delete(String id);

    /**
     * 创建新的岗位
     * @param post
     * @return
     */
    boolean createPost(Post post);

    /**
     * 修改岗位信息
     * @param post
     * @return
     */
    boolean updatePost(Post post);

    /**
     * 给岗位设置权限
     * @param roleList
     * @return
     */
    boolean addAuth(List<String> roleList);
}
