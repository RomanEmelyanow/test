import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
public class Post {
       public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
           BufferedWriter fw = new BufferedWriter(new FileWriter("1.txt"));
           Class.forName("org.postgresql.Driver");
           Connection connection = DriverManager
                   .getConnection("jdbc:postgresql://127.0.0.1:5432/", "postgres", "");
           String createTable = "create table IF NOT EXISTS TEST_TAB (ID INT, DATE VARCHAR(50));";
           Statement pstgr = connection.createStatement();
           pstgr.execute(createTable);

               Thread one = new Thread(() -> {
                   while (true){
                       int interval = (int) (Math.random() * 1000);
                   try {
                       int a = (int) (Math.random() * 10000);
                       String text = Integer.toString(a);
                       String[] insert = {"insert into TEST_TAB (ID, DATE) values (" + interval + ", '" + text + "');",
                       };
                       for (String sql : insert) {
                           pstgr.execute(sql);
                       }
                       System.out.println("Dobavleno ID " + interval + " TEXT  " + text);
                       fw.write("Dobavleno ID " + interval + " TEXT  " + text + "\n");
                       fw.flush();
                       Thread.sleep(interval);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   } catch (SQLException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }


               });
               Thread two = new Thread(() -> {
                   while(true) {
                       int interval = (int) (Math.random()*1000);
                       try {
                           String select = "select * from TEST_TAB offset random() * (select count(*) from TEST_TAB) limit 1 ;";
                           ResultSet rs = pstgr.executeQuery(select);
                           if (rs.next()) {
                               int a = rs.getInt(1);
                               System.out.println("Udalena zapis' s id " + a);
                               String delete = "DELETE FROM TEST_TAB WHERE ID = " + a + ";";
                               pstgr.executeUpdate(delete);
                               fw.write("Udalena zapis' s id " + a + "\n");
                               fw.flush();
                               }

                           Thread.sleep(interval);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       } catch (SQLException e) {
                           e.printStackTrace();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }

               });
                    one.start();
                    two.start();
            }

       }
