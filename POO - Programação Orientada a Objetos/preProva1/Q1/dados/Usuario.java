package dados;


public class Usuario {
  private String login;
  private String senha;
  private int limiteEmpLivro;
  private int limiteEmpMono;

  public Usuario(String login, String senha, int limiteEmpLivro, int limiteEmpMono) {
    this.login = login;
    this.senha = senha;
    this.limiteEmpLivro = limiteEmpLivro;
    this.limiteEmpMono = limiteEmpMono;
  }

  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }
  public void setSenha(String senha) {
    this.senha = senha;
  }

  public int getLimiteEmpLivro() {
    return limiteEmpLivro;
  }
  public void setLimiteEmpLivro(int limiteEmpLivro) {
    this.limiteEmpLivro = limiteEmpLivro;
  }

  public int getLimiteEmpMono() {
    return limiteEmpMono;
  }
  public void setLimiteEmpMono(int limiteEmpMono) {
    this.limiteEmpMono = limiteEmpMono;
  }

  public boolean podeEmpLivro() {
    return limiteEmpLivro > 0;
  }

  public boolean podeEmpMono() {
    return limiteEmpMono > 0;
  }

  public String toString() {
    return "[login=" + login + ", senha=" + senha + ", limiteEmpLivro=" + limiteEmpLivro + ", limiteEmpMono=" + limiteEmpMono + "]";
  }
}
