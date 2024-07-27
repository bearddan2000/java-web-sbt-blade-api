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
            .addStatics("/custom-static")
            // .showFileList(true)
            .enableCors(true)
            .before("/user/*", ctx -> log.info("[NarrowedHook] Before '/user/*', URL called: " + ctx.uri()))
            .on(EventType.SERVER_STARTED, e -> {
                String version = WebContext.blade()
                    .env("app.version")
                    .orElse("N/D");
                log.info("[Event::serverStarted] Loading 'app.version' from configuration, value: " + version);
            })
            .on(EventType.SESSION_CREATED, e -> {
                Session session = (Session) e.attribute("session");
                session.attribute("mySessionValue", "Baeldung");
            })
            .use(new MyMiddleware())
            .start(Main.class, args);
    }
}
