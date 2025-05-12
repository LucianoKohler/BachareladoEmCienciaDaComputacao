package dados;

public class Conta {
  private String userName;
  private String senha;
  private String email;
  private String dataNascimento;
  private boolean premium;

  // Construtor
  public Conta(String userName, String senha, String email, String dataNascimento, boolean premium) {
    this.userName = userName;
    this.senha = senha;
    this.email = email;
    this.dataNascimento = dataNascimento;
    this.premium = premium;
  }

  // Construtor vazio
  public Conta() {}

  // Getters e Setters
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getSenha() {
    return senha;
  }
  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getDataNascimento() {
    return dataNascimento;
  }
  public void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public boolean isPremium() {
    return premium;
  }
  public void setPremium(boolean premium) {
    this.premium = premium;
  }

  // Outros métodos
  public String toString() {
    String str = "";
    str += "Nome de Usuário: " + userName + "\n";
    str += "Senha: " + senha + "\n";
    str += "Email: " + email + "\n";
    str += "Data de Nascimento: " + dataNascimento + "\n";
    str += "Premium: " + (premium ? "Sim" : "Não") + "\n";
    return str;
  }

  public boolean equals(Object o) {
    if(o instanceof Conta){
      Conta c = (Conta) o;
      if(c.getUserName().equals(userName)){
        return true;
      }
    }
    return false;
  }
}
