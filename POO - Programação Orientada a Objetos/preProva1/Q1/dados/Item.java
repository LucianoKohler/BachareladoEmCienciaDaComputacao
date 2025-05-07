package dados;

public abstract class Item {
  private int idItem;
  private String titulo;
  private String nomeAutor;
  private int ano;
  protected boolean emprestado;

  public Item(int idItem, String titulo, String nomeAutor, int ano) {
    this.idItem = idItem;
    this.titulo = titulo;
    this.nomeAutor = nomeAutor;
    this.ano = ano;
    this.emprestado = false;
  }

  public int getAno() {
    return ano;
  }
  public void setAno(int ano) {
    this.ano = ano;
  }

  public int getIdItem() {
    return idItem;
  }
  public void setIdItem(int idItem) {
    this.idItem = idItem;
  }
  
  public String getNomeAutor() {
    return nomeAutor;
  }
  public void setNomeAutor(String nomeAutor) {
    this.nomeAutor = nomeAutor;
  }

  public String getTitulo() {
    return titulo;
  }
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  
  public boolean estaEmprestado() {
    return emprestado;
  }
  public void setEmprestado(boolean emprestado) {
    this.emprestado = emprestado;
  }

  public abstract void emprestar();
  
  public void devolver() {
    this.emprestado = false;

  }

}