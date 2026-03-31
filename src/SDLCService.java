import java.sql.*;

public class SDLCService {

    public static int crearProyecto(String nombre, String descripcion) {
        int idGenerado = 0;

        try (Connection conn = ConexionOracle.getConexion()) {

            String sql = "INSERT INTO PROYECTOS (NOMBRE, DESCRIPCION) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID_PROYECTO"});
            ps.setString(1, nombre);
            ps.setString(2, descripcion);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return idGenerado;
    }

    public static void registrarEtapa(int idProyecto, String etapa, String descripcion, String estado) {

        try (Connection conn = ConexionOracle.getConexion()) {
            CallableStatement cs = conn.prepareCall("{call REGISTRAR_ETAPA(?,?,?,?)}");

            cs.setInt(1, idProyecto);
            cs.setString(2, etapa);
            cs.setString(3, descripcion);
            cs.setString(4, estado);

            cs.execute();

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mostrarProyecto(int idProyecto) {

        try (Connection conn = ConexionOracle.getConexion()) {
            System.out.println("\n==== PROYECTOS ====");

            String sqlProyecto = "SELECT * FROM PROYECTOS WHERE ID_PROYECTO = ?";
            PreparedStatement ps = conn.prepareStatement(sqlProyecto);
            ps.setInt(1, idProyecto);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID_PROYECTO"));
                System.out.println("Nombre: " + rs.getString("NOMBRE"));
                System.out.println("Descripción: " + rs.getString("DESCRIPCION"));
            }

            System.out.println("\n===== CICLO DE VIDA =====");

            String sqlEtapas = "SELECT * FROM ETAPAS_SDL WHERE ID_PROYECTO = ?";
            PreparedStatement ps2 = conn.prepareStatement(sqlEtapas);
            ps2.setInt(1, idProyecto);

            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                System.out.println("----------------------------------");
                System.out.println("Etapa: " + rs2.getString("NOMBRE_ETAPA"));
                System.out.println("Descripción: " + rs2.getString("DESCRIPCION"));
                System.out.println("Estado: " + rs2.getString("ESTADO"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
