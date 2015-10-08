import javax.management.modelmbean.InvalidTargetObjectTypeException;
import java.sql.*;
public class SQLConnector {
    int BankAccountNo=0;

        public void createAccount(Account account) {
            try {
                // The newInstance() call is a work around for some
                // broken Java implementations

                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (Exception ex) {
                // handle the error
                System.out.println(ex);
            }

            Connection conn = null;

            try
            {
                String url = "jdbc:mysql://localhost/BankDB";
                String user = "root";
                String password = "ch@ngeme1";

                conn = DriverManager.getConnection(url, user, password);


                Statement st = conn.createStatement();
                String sql = "insert into BankAccount(Forename, Surname, Savings) values('" + account.firstname + "','" + account.surname+"', "+account.getSavings()+")";
                st.execute(sql);
                sql = "insert into BankAccount(AccountTypeID) select AccountTypeID from AccountType where AccountType ='"+account.getAccounttype()+"'";
                st.execute(sql);
                BankAccountNo++;
                //sql = "insert into BankAccount(AccountTypeID) select AccountTypeID from AccountType where AccountType="+account.getAccounttype();
                //st.execute(sql);

                /*
                ResultSet rs = st.executeQuery("select * from AccountType");

                while(rs.next()) {
                    String out = String.format("firstname: %s, surname: %s", rs.getString("firstname"), rs.getString("surname"));
                    System.out.println(out);
                }
                */

                st.close();
                conn.close();
            }
            catch(SQLException e) {
                System.out.println(e);
            }
        }

    /*public void insertdata(Account account){
        try{
            main("insert into AccountType(AccountType, OverdraftLimit, DeductionCharge) values('" + account.getAccounttype() + "','" + account.overdraftlimit+"','"+account.getDeductionCharge()+")");
            main("insert into BankAccount(Forename, Surname, Savings) values('" + account.firstname + "','" + account.surname+"','"+account.getSavings()+")");
        }
        catch (Exception ex){} */

    }

