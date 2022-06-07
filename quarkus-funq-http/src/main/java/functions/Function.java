package functions;

import io.quarkus.funqy.Funq;

public class Function {

    @Funq
    public Output function(Input input) {
        System.out.println("FUNQ HTTP EVENT: " + input);
        return new Output("Hello from HTTP Event: " + input.getMessage());
    }

}
