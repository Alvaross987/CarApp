package car.app.db;

import org.flywaydb.core.Flyway;

public class FlywayUtil {
	
	
	public static void migrate() {

		Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://db/postgres", "postgres", "docker")
				.load();
		flyway.migrate();
	}

}
