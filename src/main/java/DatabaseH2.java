import java.sql.*;

public class DatabaseH2 {

    Connection conn = null;
    Statement st = null;
    PreparedStatement prepSt = null;
    ResultSet res = null;

    public Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/test");
    }

    public void createTable() {
        try {
            conn = getNewConnection();
            String createQuery = "CREATE TABLE players " +
                    "(name TEXT, points INTEGER)";
            conn.createStatement().executeUpdate(createQuery);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public void addPlayer(String nameX, String name0) {
        String addPlayer = "insert into players values(?, ?)";
        boolean plX = false;
        boolean pl0 = false;
        try {
            conn = getNewConnection();
            res = conn.createStatement().executeQuery("select * from players");
            while (res.next()) {
                if (res.getString(1).equals(nameX)) plX = true;
                if (res.getString(1).equals(name0)) pl0 = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
//            if (!plX) {
                prepSt = conn.prepareStatement(addPlayer);
                prepSt.setString(1, nameX);
                prepSt.setInt(2, 0);
                prepSt.execute();
//            }
            if (!pl0) {
                prepSt = conn.prepareStatement(addPlayer);
                prepSt.setString(1, name0);
                prepSt.setInt(2, 0);
                prepSt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPoint(String winner) {
        System.out.println(winner);
        int i = 0;
        try {
            conn = getNewConnection();
            res = conn.createStatement().executeQuery("select * from players");
            while (res.next()) {
                if (res.getString(1).equals(winner)) {
                    i = res.getInt(2);
                }
                System.out.println(res.getString(1) + " - " + res.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
//            conn.createStatement().executeUpdate("update players set points=" + String.valueOf(i + 1) + "where name=" + winner);
            prepSt = conn.prepareStatement("insert into players(aleks) values(?)");
            prepSt.setInt(2, i + 1);
            prepSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        String show = "select * from players";
        try {
            res = conn.createStatement().executeQuery(show);
            System.out.println(res);
            while (res.next()) {
                System.out.println(res.getString(1) + " - " + res.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void kill() {
        String kill = "drop table players";
        try {
            conn.createStatement().executeUpdate(kill);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
