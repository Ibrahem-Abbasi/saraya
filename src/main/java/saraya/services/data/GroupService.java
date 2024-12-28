package saraya.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraya.entities.Group;
import saraya.entities.User;
import saraya.repositories.GroupRepository;

import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Iterable<Group> getAll() {
        return groupRepository.findAll();
    }

    public Optional<Group> getById(Integer id) {
        return groupRepository.findById(id);
    }

    public Iterable<Group> getByTeacherId(Integer teacherId) {
        return groupRepository.findByTeacherId(teacherId);
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group update(Group group, Integer id) {
        group.setId(id);
        return groupRepository.save(group);
    }

    public void delete(Integer id) {
        groupRepository.deleteById(id);
    }
}
