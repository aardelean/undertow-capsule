package home.undertow.capsule.nio;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.undertow.capsule.HandlerPath;
import home.undertow.capsule.blocking.EmployeeDao;
import home.undertow.capsule.entities.Employee;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by alex on 9/14/2015.
 */
@Component
@HandlerPath(path="/nio/employee")
public class EmployeeFacadeHandler implements HttpHandler {


    @Autowired
    private EmployeeDao employeeDao;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Employee result = null;
        result = employeeDao.findOne(1l);
        exchange.getResponseSender().send(mapper.writeValueAsString(result));
    }
}
