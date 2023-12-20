package ua.kiev.prog.case2;

import ua.kiev.prog.shared.Client;
import ua.kiev.prog.shared.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection())
        {
            // remove this
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Clients");
                    //st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            ClientDAOImpl2 dao = new ClientDAOImpl2(conn, "Clients");
            dao.createTable(Client.class);

            Client c = new Client("Ariana", 46, 70);
            dao.add(c);
            Client a = new Client("Vlad", 18, 7324);
            dao.add(a);
            Client b = new Client("Anna", 9, 120.40);
            dao.add(b);
            Client d = new Client("Volodymyr", 67, 900);
            dao.add(d);


            int id = c.getId(); // DZ1
            System.out.println("id C user = " + id);
            System.out.println("id D user = " + d.getId());
            System.out.println("id A user = " + a.getId());

            List<Client> list = dao.getAll(Client.class);
            for (Client cli : list)
                System.out.println(cli);

            list.get(0).setAge(55);
            dao.update(list.get(0));


            List<Client> list2 = dao.getAll(Client.class, "name", "age");
            List<Client> list3 = dao.getAll(Client.class, "age");
            System.out.println("\n Age ");
            for (Client cli : list3)
                System.out.println(cli);
            System.out.println("\n Age + name ");
            for (Client cli : list2)
                System.out.println(cli);

            dao.delete(list.get(0));
        }
    }
}
