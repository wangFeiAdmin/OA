package com.wf.oa.serviceImpl;

import com.wf.oa.bean.Post;
import com.wf.oa.dao.OperationPostDao;
import com.wf.oa.service.OperationPostService;
import com.wf.oa.util.CreateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 为岗位设置权限
     * @param roleList
     * @return
     */
    public boolean addAuth(List<String> roleList){
        StringBuilder sb=new StringBuilder();
        //存储拼接好的sql
        List<String> values=new ArrayList<>();
        //获取用户编号
        String userno=roleList.get(0);
        //获取岗位编号
        String postno=roleList.get(1);
        for (int i=2; i<roleList.size(); i++){
            sb.append("(");
            sb.append("null,");
            sb.append(userno);
            sb.append(",");
            sb.append(postno);
            sb.append(",");
            sb.append(roleList.get(i));
            sb.append(")");
            values.add(sb.toString());
            sb=new StringBuilder();
        }
      return operationPostDao.addAuth(values);

    }
}
