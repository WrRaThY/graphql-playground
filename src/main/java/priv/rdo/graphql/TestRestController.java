package priv.rdo.graphql;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestRestController {

    @GetMapping(path = "example")
    ExampleResponse exampleResponse() {
        return new ExampleResponse("fafarafa");
    }

    static class ExampleResponse {
        private final String field;

        ExampleResponse(String field) {
            this.field = field;
        }

        public String getField() {
            return field;
        }
    }
}
