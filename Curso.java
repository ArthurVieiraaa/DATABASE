import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Curso {
   
    int idCurso;
    String nomeCurso;
    int cargaHoraria;
    Professor professor;

    public Curso(int idCurso, String nomeCurso, int cargaHoraria, Professor professor) {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
    }
   
    static Curso buscaCurso(int idCurso) {
        final String url = "jdbc:mysql://localhost:3306/escola";
        final String user = "root";
        final String password = "";
        Curso curso = null;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM curso WHERE idCurso = " + idCurso);
            if (rs.next()) {
                curso = new Curso(
                    rs.getInt("idCurso"),
                    rs.getString("nomeCurso"),
                    rs.getString("cargoHoraria"),
                    Professor.buscaProfessor(rs.getInt("idProfessor"))
                );
                System.out.println(curso);
            } else {
                throw new RuntimeException("Curso não encontrado");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar curso");
        }
        return curso;
    }
 
    public String toString() {
        return  "ID: " + this.idCurso
            + "\nNome: " + this.nomeCurso
            + "\nCarga Horaria: " + this.cargaHoraria
            + "\\Professor: " + this.professor
            + "\n===================================";
    }
}