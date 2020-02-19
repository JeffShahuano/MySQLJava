package mysqljava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MysqlJava {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jc.jdbc.Driver");
            String url = "jdbc:mysql://localhost/tienda?user=root&password=mysqladminl";
            Connection connect = DriverManager.getConnection(url);
            Statement statement = connect.createStatement();
            
            String query = "SELECT * FROM producto";
            ResultSet resultset = statement.executeQuery(query);
            String format = "|%1$-3d|%2$-17s|%3$-5d\n";

            while (resultset.next()) {
                int idProd = resultset.getInt("id_podcuto");
                String descProd = resultset.getString("desc_producto");
                int precio = resultset.getInt("precio");
                System.out.format(format,idProd,descProd,precio);

            }

            Scanner scan = new Scanner(System.in);
            System.out.println("¿Que deseas hacer: INSERT / BORRAR / CONSULTAR/ ACTUALIZAR ?");
            String accion = scan.nextLine();
            if (accion.equals("INSERTAR")) {
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa el Id_Producto");
                String idProd = scan.nextLine();

                scan = new Scanner(System.in);
                System.out.println("Ingresa la desc_producto");
                String descProd = scan.nextLine();

                scan = new Scanner(System.in);
                System.out.println("Ingresa el precio");
                String precio = scan.nextLine();

                query = "INSERT INTO producto values (?,?,?)";
                
                PreparedStatement ps = connect.prepareStatement (query);
                ps.setInt(1,Integer.parseInt(idProd));
                ps.setString (2,descProd);
                ps.setInt(3,Integer.parseInt(precio));
                ps.executeUpdate();
                
            }else if (accion.equals("BORRAR")) {
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa el Id_Producto");
                String idProd = scan.nextLine();
                
                query = "DELETE FROM producto " + "where id_producto = ?";
                
                PreparedStatement ps = connect.prepareStatement (query);
                ps.setInt(1,Integer.parseInt(idProd));
                ps.executeUpdate();
                
            }else if (accion.equals("Actualizar")){
                
                scan = new Scanner (System.in);
                System.out.println("Ingrese el Id_Producto");
                String idProd = scan.next();
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa desc_productoo");
                String descProd = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingresa el precio");
                String precio = scan.nextLine();
                
                query = "UPDATE producto SET desc_prodcuto = ?" + "precio = ? WHERE id_Producto = ?";
                
                PreparedStatement ps = connect.prepareStatement (query);
                ps.setString (1,descProd);
                ps.setInt(2,Integer.parseInt(precio));
                ps.setInt(3,Integer.parseInt(idProd));
                
                ps.executeUpdate();
            }
            

            resultset.close();
            statement.close();
            connect.close();

        } catch (Exception e) {
         System.err.println(e);
        }

    }

}
