package by.gsu.epamlab.database.managment;

public class BaseManagmentQueries {
    static final String QUERY_DROP_LOGINS = "drop table if exists logins;";
    static final String QUERY_DROP_TESTS = "drop table if exists tests;";
    static final String QUERY_DROP_RESULTS = "drop table if exists results;";

    static final String QUERY_CREATE_LOGINS = "create table logins (\n" +
            "    idlogin int(11) primary key auto_increment,\n" +
            "    name varchar(20) unique key not null\n" +
            ");";

    static final String QUERY_CREATE_TESTS = "create table tests (\n" +
            "    idtest int(11) primary key auto_increment,\n" +
            "    name varchar(20) unique key not null\n" +
            ");";

    static final String QUERY_CREATE_RESULTS = "create table results (\n" +
            "  id int(11) primary key auto_increment,\n" +
            "  loginid int(11) not null , \n" +
            "  testid int(11) not null ,\n" +
            "  dat date not null,\n" +
            "  mark int(10) not null,\n" +
            "  foreign key (loginid) references logins(idlogin),\n" +
            "  foreign key (testid) references tests(idtest)\n" +
            ");";

    public static final String QUERY_SELECT_MEAN_MARK = "SELECT name as login, AVG(mark) AS mean FROM results, logins\n" +
            "    WHERE results.loginId = logins.idLogin\n" +
            "    GROUP BY logins.idLogin\n" +
            "    ORDER BY mean DESC;";

    public static final String PREPARE_INSERT_NAMES_TO_LOGINS = "insert into logins (name) values (?);";
    public static final String PREPARE_INSERT_NAMES_TO_TESTS = "insert into tests (name) values (?);";
    public static final String PREPARE_INSERT_TO_RESULTS = "insert into results (loginId, testId, dat, mark) values (?, ?, ?, ?);";

    public static final String PREPARE_SELECT_NAMES_FROM_LOGINS = "select * from logins where name = ?;";
    public static final String PREPARE_SELECT_NAMES_FROM_TESTS = "select * from tests where name = ?;";

    public static final String PREPARE_SELECT_FROM_RESULTS = "select logins.name as login, tests.name as test, dat, " +
            "mark from logins, tests, results where results.loginId = logins.idLogin AND results.testId = tests.idTest;";

    public static final String PREPARE_SELECT_FROM_RESULTS_FOR_MONTH = "select logins.name as login, tests.name as test, dat, mark \n" +
            "from logins, tests, results \n" +
            "where results.loginId = logins.idLogin " +
            "AND results.testId = tests.idTest " +
            "AND dat between ? AND ? " +
            "ORDER BY dat;";
}
