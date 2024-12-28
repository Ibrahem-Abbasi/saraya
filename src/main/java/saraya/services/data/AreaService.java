package saraya.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saraya.entities.Area;
import saraya.repositories.AreaRepository;

import java.util.Optional;

@Service
public class AreaService {
    private final AreaRepository areaRepository;

    @Autowired
    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public Iterable<Area> getAll() {
        return areaRepository.findAll();
    }

    public Optional<Area> getById(Integer id) {
        return areaRepository.findById(id);
    }

    public Area save(Area area) {
        return areaRepository.save(area);
    }

    public Area update(Area area, Integer id) {
        area.setId(id);
        return areaRepository.save(area);
    }

    public void delete(Integer id) {
        areaRepository.deleteById(id);
    }
}
