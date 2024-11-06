import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Professor {
   
    int idProfessor;
    String nomeProfessor;
    String departamento;
 
    public Professor(int idProfessor, String nomeProfessor, String departamento) {
        this.idProfessor = idProfessor;
        this.nomeProfessor = nomeProfessor;
        this.departamento = departamento;
 
    }
   
    static Professor buscaProfessor(int idProfessor) {
        final String url = "jdbc:mysql://localhost:3306/escola";
        final String user = "root";
        final String password = "";
        Professor professor = null;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM professor WHERE idProfessor = " + idProfessor);
            if (rs.next()) {
                professor = new Professor(
                    rs.getInt("idProfessor"),
                    rs.getString("nomeProfessor"),
                    rs.getString("departamento")
                );
                System.out.println(professor);
            } else {
                throw new RuntimeException("Professor não encontrado");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar professor");
        }
        return professor;
    }
 
    public String toString() {
        return  "ID: " + this.idProfessor
            + "\nNome: " + this.nomeProfessor
            + "\nDepartamento: " + this.departamento
            + "\n===================================";
    }
}