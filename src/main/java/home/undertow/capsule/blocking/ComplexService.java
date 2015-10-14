package home.undertow.capsule.blocking;

import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.Strand;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import home.undertow.capsule.entities.Employee;
import home.undertow.capsule.entities.Person;
import home.undertow.capsule.services.rest.RestClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by alex on 10/3/2015.
 */
@RequestMapping("/complex")
@RestController
public class ComplexService {

    private final static String id="eefa89c4-ec21-11e4-b08b-b75697636679-8e488775";

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private MongoDatabase mongoDatabase;

    @Autowired
    private RestClient restClient;

    @Value("${externalUrl}")
    private String externalUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/check")
    public String complexShit() throws Exception {
        CompletableFuture<Person> futurePerson = person();
        Employee employee = employee();

        String response = response();
        double calculated = calculate();
        return objectMapper.writeValueAsString(employee)
                + response
                + calculated
                + objectMapper.writeValueAsString(futurePerson.get());
    }

    @Suspendable
    public Employee employee(){
        return employeeDao.findOne(1l);
    }

    @Suspendable
    public String response() throws UnsupportedEncodingException {
        System.out.println(Strand.currentStrand().getId());
        return restClient.targetWithParams(externalUrl).request().get(String.class);
    }

    public double calculate(){
        double[] resultVal = new double[100_000];
        for(int i=0; i<100_000;i++){
            resultVal[i] = Math.sqrt(i*i+123.4);
        }
        return resultVal[75_000];
    }
    private CompletableFuture<Person> person() throws ExecutionException, InterruptedException {
        CompletableFuture<Person> futureResult = new CompletableFuture<>();
        MongoCollection<Document> collection = mongoDatabase.getCollection("Person");
        collection.find().filter(Filters.eq("_id", id)).first((p, throwable) -> {
            try {
                futureResult.complete(objectMapper.readValue(((Document) p).toJson().getBytes(), Person.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return futureResult;
    }

}
