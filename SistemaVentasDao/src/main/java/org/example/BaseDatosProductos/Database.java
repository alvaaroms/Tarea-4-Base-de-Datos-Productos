package org.example.BaseDatosProductos;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class Database {
    private static final List<URLClassLoader> DRIVER_LOADERS = new ArrayList<>();
    private static final String[] JDBC_URLS = {
            "jdbc:oracle:thin:@//localhost:1521/XEPDB1",
            "jdbc:oracle:thin:@localhost:1521:XE",
            "jdbc:oracle:thin:@localhost:1521/XE"
    };
    private static final String USER = "LOLO";
    private static final String PASSWORD = "LOLO";

    public static Connection getConnection() throws SQLException {
        ensureOracleDriverLoaded();

        List<String> errores = new ArrayList<>();
        for (String jdbcUrl : JDBC_URLS) {
            try {
                return DriverManager.getConnection(jdbcUrl, USER, PASSWORD);
            } catch (SQLException e) {
                errores.add(jdbcUrl + " -> " + e.getMessage());
            }
        }

        throw new SQLException("No se pudo conectar a Oracle con ninguna URL probada: " + String.join(" | ", errores));
    }

    private static void ensureOracleDriverLoaded() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            return;
        } catch (ClassNotFoundException ignored) {
            // Intentamos cargar el JAR incluido en resources cuando la ejecución no arrastra dependencias Maven.
        }

        URL driverJar = findBundledDriverJar();

        if (driverJar != null) {
            try {
                URLClassLoader loader = new URLClassLoader(new URL[]{driverJar}, Database.class.getClassLoader());
                if (!DRIVER_LOADERS.contains(loader)) {
                    DRIVER_LOADERS.add(loader);
                }
                Class<?> driverClass = Class.forName("oracle.jdbc.OracleDriver", true, loader);
                Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance();
                DriverManager.registerDriver(new DriverShim(driver));
                return;
            } catch (Exception e) {
                throw new SQLException("No se pudo cargar el driver Oracle JDBC desde el JAR incluido.", e);
            }
        }

        throw new SQLException("No se encontró el driver Oracle JDBC en el classpath ni como recurso (ojdbc17.jar/ojdbc11.jar).");
    }

    private static URL findBundledDriverJar() throws SQLException {
        URL driverJar = Database.class.getClassLoader().getResource("ojdbc17.jar");
        if (driverJar != null) {
            return driverJar;
        }

        driverJar = Database.class.getClassLoader().getResource("ojdbc11.jar");
        if (driverJar != null) {
            return driverJar;
        }

        File[] candidates = {
                new File("src/main/resources/ojdbc17.jar"),
                new File("src/main/resources/ojdbc11.jar"),
                new File("target/classes/ojdbc17.jar"),
                new File("target/classes/ojdbc11.jar")
        };

        for (File candidate : candidates) {
            if (candidate.isFile()) {
                try {
                    return candidate.toURI().toURL();
                } catch (Exception e) {
                    throw new SQLException("No se pudo convertir la ruta del driver Oracle a URL.", e);
                }
            }
        }

        return null;
    }

    private static final class DriverShim implements Driver {
        private final Driver delegate;

        private DriverShim(Driver delegate) {
            this.delegate = delegate;
        }

        @Override
        public Connection connect(String url, Properties info) throws SQLException {
            return delegate.connect(url, info);
        }

        @Override
        public boolean acceptsURL(String url) throws SQLException {
            return delegate.acceptsURL(url);
        }

        @Override
        public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
            return delegate.getPropertyInfo(url, info);
        }

        @Override
        public int getMajorVersion() {
            return delegate.getMajorVersion();
        }

        @Override
        public int getMinorVersion() {
            return delegate.getMinorVersion();
        }

        @Override
        public boolean jdbcCompliant() {
            return delegate.jdbcCompliant();
        }

        @Override
        public Logger getParentLogger() {
            try {
                return delegate.getParentLogger();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void initialize() {
        String sql = "CREATE TABLE productos (" +
                "id NUMBER PRIMARY KEY, " +
                "nombre VARCHAR2(100) NOT NULL, " +
                "precio NUMBER(10,2) NOT NULL, " +
                "vendedor VARCHAR2(100) NOT NULL" +
                ")";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            String message = e.getMessage();
            if (message != null && message.contains("ORA-00955")) {
                return;
            }
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
}
