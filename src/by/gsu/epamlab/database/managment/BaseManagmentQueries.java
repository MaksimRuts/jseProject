package by.gsu.epamlab.database.managment;

public class BaseManagmentQueries {
    static final String QUERY_DELETE_TABLES = "drop table if exists logins;\n" +
            "drop table if exists tests;\n" +
            "drop table if exists results;";

    static final String QUERY_CREATE_TABLES = "create table logins (\n" +
            "    idlogin int(11) primary key auto_increment,\n" +
            "    name varchar(20) unique key not null\n" +
            ");\n" +
            "\n" +
            "create table tests (\n" +
            "    idtest int(11) primary key auto_increment,\n" +
            "    name varchar(20) unique key not null\n" +
            ");\n" +
            "\n" +
            "create table results (\n" +
            "  id int(11) primary key auto_increment,\n" +
            "  loginid int(11) not null , \n" +
            "  testid int(11) not null ,\n" +
            "  data date not null,\n" +
            "  mark int(10) not null,\n" +
            "  foreign key (loginid) references logins(idlogin),\n" +
            "  foreign key (testid) references tests(idtest)\n" +
            ");";

    public static final String TABLE_LOGINS = "logins";
    public static final String TABLE_TESTS = "tests";

    // TODO придумать как можно использовать один PrepareStatement для однообразных запросоы (проблема с ковычками)
    public static final String PREPARE_INSERT_NAMES_TO_LOGINS = "insert into logins (name) values (?);";
    public static final String PREPARE_INSERT_NAMES_TO_TESTS = "insert into tests (name) values (?);";
    public static final String PREPARE_INSERT_TO_RESULTS = "insert into results (loginId, testId, data, mark) values (?, ?, ?, ?);";

    public static final String PREPARE_SELECT_NAMES_FROM_LOGINS = "select * from logins where name = ?;";
    public static final String PREPARE_SELECT_NAMES_FROM_TESTS = "select * from tests where name = ?;";

    // TODO переписать запрос так, что б он возвращал данные согласно классу Result (вместо ID заполнял данные именами)
    public static final String PREPARE_SELECT_FROM_RESULTS = "select * from results;";
}
