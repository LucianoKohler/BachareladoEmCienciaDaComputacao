package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafeteria.dados.Comanda;

public class ComandaDAO {

    // Só precisa da mesa pois o resto é gerado na hora (data abertura, status)
    public static Boolean criaComanda(int numero_mesa){
        String sql = "INSERT INTO Comanda (numero_mesa) VALUES (?)";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, numero_mesa);
            st.executeUpdate();
            st.close();
            System.out.println("Comanda criada com sucesso!");
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao criar comanda: " + e.getMessage());
            return false;
        }
    }

    public static Comanda procuraComandaPorID(int cID){
        String sql = "SELECT * FROM Comanda WHERE ID_comanda = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cID);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Comanda(
                    res.getInt("ID_comanda"),
                    res.getInt("numero_mesa"), 
                    res.getTimestamp("data_hora_abertura").toLocalDateTime(),
                    res.getString("status_pgto"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Comanda> selectAll(String tipo){
        List<Comanda> comandas = new ArrayList<>();
        
        try{
            Connection con = ConexaoDB.getInstancia();
            String sql;
            if(tipo.length() == 0){
                sql = "SELECT * FROM Comanda";                
            }else{
                sql = "SELECT * FROM Comanda WHERE status_pgto = ?";
            }
            PreparedStatement st = con.prepareStatement(sql);
            if(tipo.length() > 0) st.setString(1, tipo);

            ResultSet res = st.executeQuery();
            while(res.next()){
                Comanda c = new Comanda(
                    res.getInt("ID_comanda"),
                    res.getInt("numero_mesa"), 
                    res.getTimestamp("data_hora_abertura").toLocalDateTime(),
                    res.getString("status_pgto"));
                comandas.add(c);
            }
            return comandas;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Boolean deletaComandaPorID(int cID){
        String sql = "DELETE FROM Comanda WHERE ID_comanda = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cID);
            int linhasAfetadas = st.executeUpdate();
            st.close();

            if(linhasAfetadas > 0){
                System.out.println("Comanda deletada com sucesso!");
                return true;
            }else{
                System.out.println("Comanda não encontrada! Nenhuma comanda deletada");
                return false;
            }
        }catch(SQLException e){
            System.out.println("Erro ao deletar Comanda: " + e.getMessage());
            
            return false;
        }
    }

    public static Boolean alteraComanda(String campoAlterado, int comandaID, String novoAtributo){
        String sql = "UPDATE Comanda SET " + campoAlterado + " = ? WHERE ID_comanda = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            switch (campoAlterado) {
                case "status_pgto":
                    st.setString(1, novoAtributo);
                    break;
            
                case "numero_mesa":
                    st.setInt(1, Integer.parseInt(novoAtributo));
                    break;
                    
                default:
                    System.out.println("Escolha de campo inválida");
                    return false;
            }
            st.setInt(2, comandaID);
            st.executeUpdate();
            st.close();
            System.out.println("Comanda alterada com sucesso!");

            return true;
        }catch(SQLException e){
            System.out.println("Erro ao alterar Comanda: " + e.getMessage());

            return false;
        }
    }

    public static boolean possuiPedidosPendentes(int idComanda) {
        String sql = "SELECT COUNT(*) AS total_pendente FROM Pedido WHERE FK_comanda = ? AND status_pedido NOT IN ('atendido', 'cancelado')";

        try (Connection con = ConexaoDB.getInstancia();
             PreparedStatement st = con.prepareStatement(sql)) {
            
            st.setInt(1, idComanda);
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total_pendente") > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar pendências da comanda: " + e.getMessage());
            return true; // bloqueia em caso de erro
        }
        return false;
    }

    public static double calcularTotal(int comandaID){
        String sql = "SELECT COALESCE(SUM(ip.quantidade * ip.preco_unitario), 0) " + 
        " AS total FROM Item_pedido ip JOIN Pedido p ON p.ID_pedido = ip.FK_pedido " + 
        "WHERE p.FK_comanda = ? AND status_pedido = 'atendido';";

        try {
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, comandaID);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

            return 0;
        
        }catch(SQLException e){
            System.out.println("Erro ao calcular total da Comanda: " + e.getMessage());
            return 0;
        }
    }
}
