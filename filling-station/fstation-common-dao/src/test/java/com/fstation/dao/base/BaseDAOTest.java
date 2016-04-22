package com.fstation.dao.base;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;

/**
 * User: Raghu Date: Jan 27, 2010 Time: 10:35:28 AM
 */
@Deprecated
public abstract class BaseDAOTest
{
    private Connection conn = null;
    private final String CONFIG_FILE = "dbconfig.txt";
    private static boolean printedInfo = false;
    private DataSource dataSource;

    @Before
    public void initialize() {
        try {
            conn = getDBConnection(CONFIG_FILE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    protected Properties loadDBProperties(String configFile) {
        return loadProperties(configFile);
    }

    protected Properties loadProperties(String fileName) {
        Properties configProp = new Properties();

        try {
            System.out.println("reading props from '" + ClassLoader.getSystemClassLoader().getResource(fileName) + "'");
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
            configProp.load(is);

        } catch (Exception e) {
            System.out.println("Unable to Read Config File " + fileName + " :: " + e);
            System.exit(1);
        }
        return configProp;
    }

    private Connection getDBConnection(String configFile) {
        Properties configProp = loadDBProperties(configFile);

        String username = configProp.getProperty("db.username");
        String password = configProp.getProperty("db.password");

        String jdbcURL = configProp.getProperty("db.Url");
        String jdbcDriver = configProp.getProperty("db.Driver");

        Connection conn;

        if (!printedInfo) {
            printedInfo = true;
            System.out.println("Connecting " + jdbcURL + "/" + username);
        }

        try {

            Class.forName(jdbcDriver).newInstance();
            conn = DriverManager.getConnection(jdbcURL, username, password);
            return conn;
        } catch (Exception e) {
            System.out.println("exception in getting connection: " + e);
            System.exit(1);
        }
        return null;
    }

    // simple random string generator
    private static final String base = "abcdefghijklmnopqrstuvwxyz";

    private static String randomString(int len)
    {
        Random random = new Random();
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < len; i++)
            res.append(base.charAt(random.nextInt(base.length())));
        return res.toString();
    }

    /**
     * Populates all string/int/long/timestamp DECLARED fields in an object with
     * random contents using reflection.
     */
    @SuppressWarnings("unchecked")
    protected void populateObjectRandomly(Object o)
    {
        Random random = new Random();
        try {
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType() == int.class)
                    field.setInt(o, random.nextInt());
                else if (field.getType() == Integer.class)
                    field.set(o, random.nextInt());
                else if (field.getType() == long.class)
                    field.setLong(o, random.nextLong());
                else if (field.getType() == Long.class)
                    field.set(o, random.nextLong());
                else if (field.getType() == boolean.class)
                    field.setBoolean(o, random.nextBoolean());
                else if (field.getType() == Boolean.class)
                    field.set(o, random.nextBoolean());
                else if (field.getType() == String.class)
                    field.set(o, randomString(6));
                else if (field.getType() == BigDecimal.class)
                    field.set(o, new BigDecimal(new BigDecimal(random.nextFloat(), new MathContext(2)).toString())); // preserves
                                                                                                                     // integrity
                                                                                                                     // in
                                                                                                                     // comparisons
                                                                                                                     // to
                                                                                                                     // DB-retrieved
                else if (field.getType() == java.sql.Date.class)
                    field.set(o, new Date(new java.util.Date().getTime() - 5000 + random.nextInt(10000)));
                else if (field.getType() == java.sql.Timestamp.class)
                    field.set(o, new Timestamp(new java.util.Date().getTime() - 5000 + random.nextInt(10000)));
                else if (field.getType() == Collection.class || List.class.isAssignableFrom(field.getType()))
                    field.set(o, new ArrayList());
                else if (Set.class.isAssignableFrom(field.getType()))
                    field.set(o, new HashSet());
            }
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Unable to populate object: " + e);
        }
    }

    /**
     * Execute an update statement. Makes sure statement gets closed, but throws
     * any exception received.
     */
    protected void runSQL(String sql) throws SQLException
    {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            // System.out.println("execute sql: " + sql);
            stmt.executeUpdate(sql);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Execute an query returning a single result as a string. Makes sure
     * statement gets closed, but throws any exception received.
     */
    protected String getSQLResult(String sql) throws SQLException
    {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            // System.out.println("execute sql: " + sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getString(1);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            }
        }
    }

    protected static final Date makeDate(String dateStr)
    {
        SimpleDateFormat parser = new SimpleDateFormat("yyyyMMdd");
        try {
            java.util.Date temp = parser.parse(dateStr);
            return new Date(temp.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
