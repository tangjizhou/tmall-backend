package net.mshome.twisted.tmall.service.process;

import net.mshome.twisted.tmall.entity.Role;
import net.mshome.twisted.tmall.service.IRoleService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.AbstractManager;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/29
 */
public class GroupManagerImpl extends AbstractManager implements GroupEntityManager {

    private static final long serialVersionUID = -6836092804149039544L;
    private static final String TYPE = "system_role";

    private IRoleService roleService;

    public GroupManagerImpl(ProcessEngineConfigurationImpl processEngineConfiguration, IRoleService roleService) {
        super(processEngineConfiguration);
        this.roleService = roleService;
    }


    @Override
    public Group createNewGroup(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupQuery createNewGroupQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl groupQuery, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl groupQuery) {
        return 0;
    }

    @Override
    public List<Group> findGroupsByUser(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByNativeQuery(Map<String, Object> map, int i, int i1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long findGroupCountByNativeQuery(Map<String, Object> map) {
        return 0;
    }

    @Override
    public boolean isNewGroup(Group group) {
        return false;
    }

    @Override
    public GroupEntity create() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupEntity findById(String id) {
        Role role = roleService.getById(id);
        GroupEntity groupEntity = new GroupEntityImpl();
        groupEntity.setName(role.getName());
        groupEntity.setId(role.getId().toString());
        groupEntity.setType(TYPE);
        return groupEntity;
    }

    @Override
    public void insert(GroupEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(GroupEntity entity, boolean b) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupEntity update(GroupEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GroupEntity update(GroupEntity entity, boolean b) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(GroupEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(GroupEntity entity, boolean b) {
        throw new UnsupportedOperationException();
    }

}
