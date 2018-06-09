package script;

import dbUtil.DbConnection;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;

import java.io.File;
import java.sql.DriverManager;

public class SQLExe {

    public static void executeSql(String sqlFilePath, String delimiter) {
        final class SqlExecuter extends SQLExec {
            public SqlExecuter() {
                Project project = new Project();
                project.init();
                setProject(project);
                setTaskType("sql");
                setTaskName("sql");
            }
        }

        SqlExecuter executer = new SqlExecuter();
        executer.setSrc(new File(sqlFilePath));
        executer.setPassword(DbConnection.getPASSWORD());
        executer.setUserid(DbConnection.getUSERNAME());
        executer.setUrl(DbConnection.getCONNECTION());
        executer.setDriver(DbConnection.getDRIVER());
        executer.setDelimiter(delimiter);
        try {
            executer.execute();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}