package functions;

import io.quarkus.funqy.Funq;
import io.quarkus.funqy.knative.events.CloudEvent;
import io.quarkus.funqy.knative.events.CloudEventBuilder;

public class Function {

    @Funq
    public CloudEvent<Output> function(CloudEvent<Input> input) {
        System.out.println("FUNQ EVENT: " + input);
        Output output = new Output("Hello from Funq: " + input.data().getMessage());
        return CloudEventBuilder.create().build(output);
    }

}
