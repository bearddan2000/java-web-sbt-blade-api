package example;

import com.blade.Blade;
import com.blade.event.EventType;
import com.blade.mvc.WebContext;
import com.blade.mvc.http.Session;

public class Main {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Blade.of()
            .get("/basic-route-example", ctx -> ctx.text("GET called"))
            .post("/basic-route-example", ctx -> ctx.text("POST called"))
            .put("/basic-route-example", ctx -> ctx.text("PUT called"))
            .delete("/basic-route-example", ctx -> ctx.text("DELETE called"))
            .enableCors(true)
            .start(Main.class, args);
    }
}
