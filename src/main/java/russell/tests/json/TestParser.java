package russell.tests.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class TestParser {

    final static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        final List<Map<String, Object>> root = mapper.readValue(new URL("file:///Users/russellr/Downloads/revert-events.json"), List.class);

        root.stream()
                .filter(map -> "FAILURE".equals(map.get("result")))
                .filter(map -> map.containsKey("target_entity_path") && !map.get("target_entity_path").toString().contains("/alternatives/"))
                .forEach(System.out::println);

    }


}
