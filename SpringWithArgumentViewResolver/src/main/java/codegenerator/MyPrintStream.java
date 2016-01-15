package codegenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPrintStream {

    public void println() {
        java.lang.System.out.println();
    }

    public void println(Object whatToPrint) {
        java.lang.System.out.println(whatToPrint);
        java.lang.System.out.println(whatToPrint);
        java.lang.System.out.println(whatToPrint);
        java.lang.System.out.println(Thread.currentThread().getStackTrace()[2]);
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineno = Thread.currentThread().getStackTrace()[2].getLineNumber();

        try {
            java.util.Properties prop = new java.util.Properties();
            prop.load(new FileInputStream("D:\\MOSTIMPORTANTS\\properties.properties"));
            int programid = Integer.parseInt(prop.getProperty("programid"));


            String selectQuery = "SELECT Top 1 * FROM mydream where outputonscreen is null and filename='" + className
                    + "' and methodname='" + methodName
                    + "' and lineno=" + lineno
                    + " and programid=" + programid
                    + " Order By ID ASC";




            Connection cnn;

            int rowToUpdate = 0;
            String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:\\MOSTIMPORTANTS\\Database1.mdb;";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            cnn = DriverManager.getConnection(url);
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                rowToUpdate = rs.getInt(1);
            }
            rs.close();
            st.close();
            Date d = new Date();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            String s = df.format(d);
            String updateQuery = "update mydream set outputonscreen='[" + s + "]" + whatToPrint.toString().replace("'", "") + "' where ID=" + rowToUpdate + "";
            Statement st1 = cnn.createStatement();
            st1.executeUpdate(updateQuery);
            st1.close();
            cnn.close();
        } catch (Exception ex) {
            try {
                File f = new File("d:\\FileVariables.txt");
                f.createNewFile();
                FileWriter writer = new FileWriter(f);
                writer.write(ex.getMessage());
                writer.close();
                System.out.println("Problem is : " + ex.getMessage());
            } catch (Exception ex1) {
            }
        }
    }

    public void print(Object whatToPrint) {
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(Thread.currentThread().getStackTrace()[2]);
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineno = Thread.currentThread().getStackTrace()[2].getLineNumber();

        try {
            java.util.Properties prop = new java.util.Properties();
            prop.load(new FileInputStream("D:\\MOSTIMPORTANTS\\properties.properties"));
            int programid = Integer.parseInt(prop.getProperty("programid"));


            String selectQuery = "SELECT Top 1 * FROM mydream where outputonscreen is null and filename='" + className
                    + "' and methodname='" + methodName
                    + "' and lineno=" + lineno
                    + " and programid=" + programid
                    + " Order By ID ASC";




            Connection cnn;

            int rowToUpdate = 0;
            String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:\\MOSTIMPORTANTS\\Database1.mdb;";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            cnn = DriverManager.getConnection(url);
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                rowToUpdate = rs.getInt(1);
            }
            rs.close();
            st.close();
            String updateQuery = "update mydream set outputonscreen='" + whatToPrint.toString().replace("'", "") + "' where ID=" + rowToUpdate + "";
            Statement st1 = cnn.createStatement();
            st1.executeUpdate(updateQuery);
            st1.close();
            cnn.close();
        } catch (Exception ex) {
            try {
                File f = new File("d:\\FileVariables.txt");
                f.createNewFile();
                FileWriter writer = new FileWriter(f);
                writer.write(ex.getMessage());
                writer.close();
                System.out.println("Problem is : " + ex.getMessage());
            } catch (Exception ex1) {
            }
        }
    }

    public void printf(Object whatToPrint, String format) {
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(Thread.currentThread().getStackTrace()[2]);
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineno = Thread.currentThread().getStackTrace()[2].getLineNumber();

        try {
            java.util.Properties prop = new java.util.Properties();
            prop.load(new FileInputStream("D:\\MOSTIMPORTANTS\\properties.properties"));
            int programid = Integer.parseInt(prop.getProperty("programid"));


            String selectQuery = "SELECT Top 1 * FROM mydream where outputonscreen is null and filename='" + className
                    + "' and methodname='" + methodName
                    + "' and lineno=" + lineno
                    + " and programid=" + programid
                    + " Order By ID ASC";




            Connection cnn;

            int rowToUpdate = 0;
            String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:\\MOSTIMPORTANTS\\Database1.mdb;";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            cnn = DriverManager.getConnection(url);
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                rowToUpdate = rs.getInt(1);
            }
            rs.close();
            st.close();
            String updateQuery = "update mydream set outputonscreen='" + whatToPrint.toString().replace("'", "") + "' where ID=" + rowToUpdate + "";
            Statement st1 = cnn.createStatement();
            st1.executeUpdate(updateQuery);
            st1.close();
            cnn.close();
        } catch (Exception ex) {
            try {
                File f = new File("d:\\FileVariables.txt");
                f.createNewFile();
                FileWriter writer = new FileWriter(f);
                writer.write(ex.getMessage());
                writer.close();
                System.out.println("Problem is : " + ex.getMessage());
            } catch (Exception ex1) {
            }
        }

    }

    public void printf(Object whatToPrint, Object format) {
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(Thread.currentThread().getStackTrace()[2]);
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineno = Thread.currentThread().getStackTrace()[2].getLineNumber();

        try {
            java.util.Properties prop = new java.util.Properties();
            prop.load(new FileInputStream("D:\\MOSTIMPORTANTS\\properties.properties"));
            int programid = Integer.parseInt(prop.getProperty("programid"));


            String selectQuery = "SELECT Top 1 * FROM mydream where outputonscreen is null and filename='" + className
                    + "' and methodname='" + methodName
                    + "' and lineno=" + lineno
                    + " and programid=" + programid
                    + " Order By ID ASC";




            Connection cnn;

            int rowToUpdate = 0;
            String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:\\MOSTIMPORTANTS\\Database1.mdb;";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            cnn = DriverManager.getConnection(url);
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                rowToUpdate = rs.getInt(1);
            }
            rs.close();
            st.close();
            String updateQuery = "update mydream set outputonscreen='" + whatToPrint.toString().replace("'", "") + "' where ID=" + rowToUpdate + "";
            Statement st1 = cnn.createStatement();
            st1.executeUpdate(updateQuery);
            st1.close();
            cnn.close();
        } catch (Exception ex) {
            try {
                File f = new File("d:\\FileVariables.txt");
                f.createNewFile();
                FileWriter writer = new FileWriter(f);
                writer.write(ex.getMessage());
                writer.close();
                System.out.println("Problem is : " + ex.getMessage());
            } catch (Exception ex1) {
            }
        }
    }

    public void printf(Object whatToPrint, double format) {
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(Thread.currentThread().getStackTrace()[2]);
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineno = Thread.currentThread().getStackTrace()[2].getLineNumber();

        try {
            java.util.Properties prop = new java.util.Properties();
            prop.load(new FileInputStream("D:\\MOSTIMPORTANTS\\properties.properties"));
            int programid = Integer.parseInt(prop.getProperty("programid"));


            String selectQuery = "SELECT Top 1 * FROM mydream where outputonscreen is null and filename='" + className
                    + "' and methodname='" + methodName
                    + "' and lineno=" + lineno
                    + " and programid=" + programid
                    + " Order By ID ASC";




            Connection cnn;

            int rowToUpdate = 0;
            String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:\\MOSTIMPORTANTS\\Database1.mdb;";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            cnn = DriverManager.getConnection(url);
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                rowToUpdate = rs.getInt(1);
            }
            rs.close();
            st.close();
            String updateQuery = "update mydream set outputonscreen='" + whatToPrint.toString().replace("'", "") + "' where ID=" + rowToUpdate + "";
            Statement st1 = cnn.createStatement();
            st1.executeUpdate(updateQuery);
            st1.close();
            cnn.close();
        } catch (Exception ex) {
            try {
                File f = new File("d:\\FileVariables.txt");
                f.createNewFile();
                FileWriter writer = new FileWriter(f);
                writer.write(ex.getMessage());
                writer.close();
                System.out.println("Problem is : " + ex.getMessage());
            } catch (Exception ex1) {
            }
        }
    }

    public void printf(Object whatToPrint, int format) {
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(whatToPrint);
        java.lang.System.out.print(Thread.currentThread().getStackTrace()[2]);
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineno = Thread.currentThread().getStackTrace()[2].getLineNumber();

        try {
            java.util.Properties prop = new java.util.Properties();
            prop.load(new FileInputStream("D:\\MOSTIMPORTANTS\\properties.properties"));
            int programid = Integer.parseInt(prop.getProperty("programid"));


            String selectQuery = "SELECT Top 1 * FROM mydream where outputonscreen is null and filename='" + className
                    + "' and methodname='" + methodName
                    + "' and lineno=" + lineno
                    + " and programid=" + programid
                    + " Order By ID ASC";




            Connection cnn;

            int rowToUpdate = 0;
            String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:\\MOSTIMPORTANTS\\Database1.mdb;";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            cnn = DriverManager.getConnection(url);
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(selectQuery);
            while (rs.next()) {
                rowToUpdate = rs.getInt(1);
            }
            rs.close();
            st.close();
            String updateQuery = "update mydream set outputonscreen='" + whatToPrint.toString().replace("'", "") + "' where ID=" + rowToUpdate + "";
            Statement st1 = cnn.createStatement();
            st1.executeUpdate(updateQuery);
            st1.close();
            cnn.close();
        } catch (Exception ex) {
            try {
                File f = new File("d:\\FileVariables.txt");
                f.createNewFile();
                FileWriter writer = new FileWriter(f);
                writer.write(ex.getMessage());
                writer.close();
                System.out.println("Problem is : " + ex.getMessage());
            } catch (Exception ex1) {
            }
        }
    }
}
