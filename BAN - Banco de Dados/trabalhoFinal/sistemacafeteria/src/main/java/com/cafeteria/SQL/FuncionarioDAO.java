package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cafeteria.dados.Funcionario;

public class FuncionarioDAO {

    public static Boolean insereFuncionario(Funcionario f){
        String sql = "INSERT INTO Funcionario (nome, salario, data_contratacao, cargo) VALUES (?, ?, ?, ?)";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, f.getNome());
            st.setFloat(2, f.getSalario());
            st.setDate(3, java.sql.Date.valueOf(f.getData_contratacao()));
            st.setString(4, f.getCargo());
            st.executeUpdate();
            st.close();
            System.out.println("Funcionário criado com sucesso!");
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao criar funcionário: " + e.getMessage());
            return false;
        }
    }

    public static Funcionario procuraFuncionarioPorID(int fID){
        String sql = "SELECT * FROM Funcionario WHERE ID_funcionario = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, fID);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Funcionario(
                    res.getInt("ID_funcionario"),
                    res.getString("nome"), 
                    res.getFloat("salario"), 
                    res.getDate("data_contratacao").toLocalDate(),
                    res.getString("cargo"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    // Só retorna o primeiro funcionário
    public static Funcionario procuraFuncionarioPorNome(String nome){
        String sql = "SELECT * FROM Funcionario WHERE nome = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Funcionario(
                    res.getInt("ID_funcionario"),
                    res.getString("nome"), 
                    res.getFloat("salario"), 
                    res.getDate("data_contratacao").toLocalDate(),
                    res.getString("cargo"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

        public static List<Funcionario> selectAll(){
            List<Funcionario> funcionarios = new ArrayList<>();
            String sql = "SELECT * FROM Funcionario";

            try{
                Connection con = ConexaoDB.getInstancia();
                PreparedStatement st = con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    Funcionario i = new Funcionario(
                        rs.getInt("ID_funcionario"),
                        rs.getString("nome"), 
                        rs.getFloat("salario"), 
                        rs.getDate("data_contratacao").toLocalDate(),
                        rs.getString("cargo"));
                    funcionarios.add(i);
                }
                return funcionarios;
            }catch(SQLException e){
                System.out.println(e.getMessage());
                return null;
            }
        }

    public static Boolean deletaFuncionarioPorID(int fID){
        String sql = "DELETE FROM Funcionario WHERE ID_funcionario = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, fID);
            int linhasAfetadas = st.executeUpdate();
            st.close();

            if(linhasAfetadas > 0){
                System.out.println("Funcionário deletado com sucesso!");
                return true;
            }else{
                System.out.println("Funcionário não encontrado! Nenhum funcionário deletado");
                return false;
            }
        }catch(SQLException e){
            System.out.println("Erro ao deletar Funcionário: " + e.getMessage());
            
            return false;
        }
    }

    public static Boolean alteraFuncionario(String campoAlterado, int funcionarioID, String novoAtributo){
        String sql = "UPDATE Funcionario SET " + campoAlterado + " = ? WHERE ID_funcionario = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            switch (campoAlterado) {
                case "nome":
                case "cargo":
                    st.setString(1, novoAtributo);
                    break;
            
                case "salario":
                    st.setFloat(1, Float.parseFloat(novoAtributo));
                    break;

                case "data_contratacao":
                    st.setString(1, campoAlterado);
                    LocalDate dataConvertida = LocalDate.parse(novoAtributo);
                    st.setDate(1, java.sql.Date.valueOf(dataConvertida));
                    break;
                    
                default:
                    System.out.println("Escolha de campo inválida");
                    return false;
            }
            st.setInt(2, funcionarioID);
            st.executeUpdate();
            st.close();
            System.out.println("Funcionário alterado com sucesso!");

            return true;
        }catch(SQLException e){
            System.out.println("Erro ao alterar funcionário: " + e.getMessage());

            return false;
        }
    }
}
