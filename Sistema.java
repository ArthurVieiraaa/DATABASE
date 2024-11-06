import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) throws Exception {
        final String url = "jdbc:mysql://localhost:3306/escola";
        final String user = "root";
        final String password = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Sistema de Cadastro de Professores, Cursos e Alunos ***");

        int opcao = 0;

        do{
            System.out.println("\nMenu:");
            System.out.println("[1] - Cadastrar Professor");
            System.out.println("[2] - Cadastrar Curso");
            System.out.println("[3] - Cadastrar Aluno");
            System.out.println("[4] - Listar Professores");
            System.out.println("[5] - Listar Cursos");
            System.out.println("[6] - Listar Alunos");
            System.out.println("[7] - Encerrar Programa");
            System.out.println("\nDigite uma opção:");
            opcao = scanner.nextInt();

            String nomeProfessor, departamento, nomeCurso, dataNascimento, cpf, nomeAluno;
            int idProfessor = 0, idCurso = 0, cargaHoraria;

            switch (opcao) {
                case 1:
                try {
                    System.out.println("\nCadastrar Professor:");
                    System.out.println("Digite o Nome do Professor:");
                    nomeProfessor = scanner.next();
                    System.out.println("Digite o Departamento do Professor:");
                    departamento = scanner.next();
                    
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO professor "
                        + " (nomeProfessor, departamento) VALUES "
                        + " ('" + nomeProfessor + "', '" + departamento + "')");
                    if(!sql) {
                        System.out.println("Falha na execução");
                    } else {
                        System.out.println("Professor cadastrado com sucesso!");
                    }
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;
                case 2:
                try{
                    System.out.println("\nCadastrar Curso:");
                    System.out.println("Digite o Nome do Curso:");
                    nomeCurso = scanner.next();
                    System.out.println("Digite a Carga Horária do Curso:");
                    cargaHoraria = scanner.nextInt();
                    System.out.println("Digite o ID do Professor do Curso:");
                    idProfessor = scanner.nextInt();
                    
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO curso "
                        + " (nomeCurso, cargaHoraria, idProfessor) VALUES "
                        + " ('" + nomeCurso + "', " + cargaHoraria + idProfessor + ")");
                    if(!sql) {
                        System.out.println("Falha na execução");
                    } else {
                        System.out.println("Curso cadastrado com sucesso!");
                    }
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;
                case 3:
                try{
                    System.out.println("\nCadastrar Aluno:");
                    System.out.println("Digite o Nome do Aluno:");
                    nomeAluno = scanner.next();
                    System.out.println("Digite a Data de Nascimento do Aluno:");
                    dataNascimento = scanner.next();
                    System.out.println("Digite o CPF do Aluno:");
                    cpf = scanner.next();
                    System.out.println("Digite o ID do Curso do Aluno:");
                    idCurso = scanner.nextInt();
                    
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO aluno "
                        + " (nomeAluno, dataNascimento, cpf, idCurso) VALUES "
                        + " ('" + nomeAluno + "', '" + dataNascimento + "', '" + cpf + "', " + idCurso + ")");
                    if(!sql) {
                        System.out.println("Falha na execução");
                    } else {
                        System.out.println("Aluno cadastrado com sucesso!");
                    }
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;
                case 4: {
                    System.out.println("\nListar Professores:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM professor");
                    while(sql.next()) {
                        Professor professor = new Professor(
                            sql.getInt("idProfessor"),
                            sql.getString("nomeProfessor"),
                            sql.getString("departamento")
                        );

                        System.out.println(professor);
                    }
                    con.close();
                    break;
                }   
                case 5: {
                    System.out.println("\nListar Cursos:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM curso");
                    while(sql.next()) {
                        Curso curso = new Curso(
                            sql.getInt("idCurso"),
                            sql.getString("nomeCurso"),
                            sql.getInt("cargaHoraria"),
                            Professor.buscaProfessor(sql.getInt("idProfessor"))
                        );

                        System.out.println(curso);
                    }
                    con.close();
                    break;
                }
                case 6: {
                    System.out.println("\nListar Alunos:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM aluno");
                    while(sql.next()) {
                        Aluno aluno = new Aluno(
                            sql.getInt("idAluno"),
                            sql.getString("nomeAluno"),
                            sql.getString("dataNascimento"),
                            sql.getString("cpf"),
                            Curso.buscaCurso(sql.getInt("idCurso"))
                        );

                        System.out.println(aluno);
                    }
                    con.close();
                    break;
                }
                case 7:
                    System.out.println("\nEncerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }while (opcao != 7);
        scanner.close();
    }
}