package saraya.controllers.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saraya.entities.Area;
import saraya.services.data.AreaService;

@RestController
@RequestMapping("/area")
public class AreaController {
    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Area>> getAllAreas() {
        return ResponseEntity.ok(areaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable("id") Integer id) {
        return areaService.getById(id).map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Area> saveArea(@RequestBody Area area) {
        return ResponseEntity.ok(areaService.save(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> updateArea(@PathVariable("id") Integer id, @RequestBody Area area) {
        return ResponseEntity.ok(areaService.update(area, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArea(@PathVariable("id") Integer id) {
        try {
            areaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
