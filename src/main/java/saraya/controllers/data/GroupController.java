package saraya.controllers.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraya.entities.Group;
import saraya.services.data.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable("id") Integer id) {
        return groupService.getById(id).map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-teacher/{teacherId}")
    public ResponseEntity<Iterable<Group>> getGroupByTeacherId(@PathVariable("teacherId") Integer teacherId) {
        return ResponseEntity.ok(groupService.getByTeacherId(teacherId));
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
