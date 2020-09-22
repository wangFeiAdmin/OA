package com.wf.oa.serviceImpl;

import com.wf.oa.bean.Post;
import com.wf.oa.dao.OperationPostDao;
import com.wf.oa.service.OperationPostService;
import com.wf.oa.util.CreateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationPostServiceImpl implements OperationPostService {

    @Autowired
    @Qualifier("operationPostDao")
    OperationPostDao operationPostDao;

    /**
     * 查询岗位
     * @param post
     * @return
     */
    public List<Post> getPost(Post post){
        return operationPostDao.selectPost(post);
    }

    /**
     * 删除岗位
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        Post p=new Post();
        p.setId(id);
        return operationPostDao.deletePost(p);
    }

    /**
     * 创建新的岗位
     * @param post
     * @return
     */
    @Override
    public boolean createPost(Post post) {
        //设置岗位编号
        post.setPostno(operationPostDao.getMaxPostNo()+1);
        //设置主键
        post.setId(CreateUUID.getUUID());
        //设置岗位留空
        post.setUserno(-1);
        return operationPostDao.addPost(post);
    }

    /**
     * 修改岗位信息
     * @param post
     * @return
     */
    @Override
    public boolean updatePost(Post post) {

        return operationPostDao.updatePost(post);
    }
}
