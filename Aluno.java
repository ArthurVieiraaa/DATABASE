import java.util.ArrayList;

public class Aluno {
    int idAluno;
    String nomeAluno;
    String dataNascimento;
    String cpf;
    int idCurso;

    Curso curso;

    public Aluno(int idAluno, String nomeAluno, String dataNascimento, String cpf, Curso curso) {
        this.idAluno = idAluno;
        this.nomeAluno = nomeAluno;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.curso = curso;
    }

    public int getId() {
        return idAluno;
    }

    public String getNome() {
        return nomeAluno;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public Curso getCurso() {
        return curso;
    }

    public static Aluno getAlunoById(int idAluno) {
        return null;
    }

    static void verificaid(int idAluno) throws Exception {
        throw new Exception("Aluno não encontrado");
    }

    static ArrayList<Aluno> buscaPorAluno(int idAluno) {
        return null;
    }

    public String toString() {
        ArrayList<Aluno> alunos = Aluno.buscaPorAluno(this.idAluno);
        String alunosString = "";
        for (Aluno aluno : alunos) {
            alunosString += aluno.getNome() + ", ";
        }
        return "ID: " + idAluno + " - Nome: " + nomeAluno + " - Data de Nascimento: " + dataNascimento + " - CPF: " + cpf + " - Curso: " + curso.getNome();
    }
}
