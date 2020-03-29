package net.mshome.twisted.tmall.process.custom;

import net.mshome.twisted.tmall.service.IRoleService;
import net.mshome.twisted.tmall.service.IUserService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/28
 */
public class TmallUserManagerImpl extends AbstractManager implements UserEntityManager {

    private static final long serialVersionUID = -6836092804149039544L;

    private IUserService userService;

    private IRoleService roleService;

    public TmallUserManagerImpl(ProcessEngineConfigurationImpl processEngineConfiguration, IUserService userService) {
        super(processEngineConfiguration);
        this.userService = userService;
    }


    @Override
    public User createNewUser(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl userQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl userQuery) {
        return 0;
    }

    @Override
    public List<Group> findGroupsByUser(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserQuery createNewUserQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean checkPassword(String s, String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findUsersByNativeQuery(Map<String, Object> map, int i, int i1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findUserCountByNativeQuery(Map<String, Object> map) {
        return 0;
    }

    @Override
    public boolean isNewUser(User user) {
        return false;
    }

    @Override
    public Picture getUserPicture(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setUserPicture(String s, Picture picture) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deletePicture(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity create() {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity findById(String s) {
        net.mshome.twisted.tmall.entity.User user = userService.getById(s);
        UserEntity userEntity = new UserEntityImpl();
        userEntity.setId(user.getId().toString());
        userEntity.setFirstName(user.getRealName());
        return userEntity;
    }

    @Override
    public void insert(UserEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(UserEntity entity, boolean b) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity update(UserEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserEntity update(UserEntity entity, boolean b) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(UserEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(UserEntity entity, boolean b) {
        throw new UnsupportedOperationException();
    }

}
