package saraya.controllers.data;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraya.entities.Group;
import saraya.entities.User;
import saraya.services.data.GroupService;
import saraya.util.Views;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @JsonView(Views.BasicView.class)
    @GetMapping
    public ResponseEntity<Iterable<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAll());
    }

    @JsonView(Views.BasicView.class)
    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable("id") Integer id) {
        return groupService.getById(id).map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-teacher")
    public ResponseEntity<Iterable<Group>> getGroupByTeacherId(@RequestBody User teacher) {
        return ResponseEntity.ok(groupService.getByTeacherId(teacher));
    }

    @PostMapping
    public ResponseEntity<Group> saveGroup(@RequestBody Group group) {
        return ResponseEntity.ok(groupService.save(group));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group,
                                             @PathVariable("id") Integer id) {
        return ResponseEntity.ok(groupService.update(group, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("id") Integer id) {
        try {
            groupService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
