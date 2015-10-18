package home.undertow.capsule.blocking;

import home.undertow.capsule.entities.Coordinate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alex on 10/17/2015.
 */

public interface CoordinateDao extends CrudRepository<Coordinate, Long>{
}
