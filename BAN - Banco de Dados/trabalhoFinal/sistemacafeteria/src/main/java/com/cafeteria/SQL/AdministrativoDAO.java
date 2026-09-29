package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cafeteria.dados.ItemRelatorio;

public class AdministrativoDAO {

    public static List<Map.Entry<String, Integer>>mostraProdutosMaisVendidos(){
        ArrayList<Map.Entry<String, Integer>> produtos = new ArrayList<>();
        String sql = "SELECT p.nome AS produto, SUM(ip.quantidade) " + 
        "AS total_unidades_vendidas FROM Item_pedido ip JOIN Produto p " + 
        " ON ip.FK_produto = p.ID_produto JOIN Pedido ped ON " + 
        "ip.FK_pedido = ped.ID_pedido WHERE ped.status_pedido != 'CANCELADO' " + 
        " GROUP BY p.ID_produto, p.nome ORDER BY total_unidades_vendidas DESC;";

        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet res = st.executeQuery();
            while(res.next()){
                produtos.add(new AbstractMap.SimpleEntry<>(res.getString("produto"), res.getInt("total_unidades_vendidas")));
            }
            return produtos;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Map.Entry<String, Float>> mostraMediaDePrecoDosProdutos(){
        ArrayList<Map.Entry<String, Float>> medias = new ArrayList<>();
        String sql = "SELECT categoria, ROUND(AVG(preco::numeric), 2) AS media_preco_categoria " +
        " FROM Produto GROUP BY categoria ORDER BY media_preco_categoria DESC;";

        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet res = st.executeQuery();
            while(res.next()){
                medias.add(new AbstractMap.SimpleEntry<>(res.getString("categoria"), res.getFloat("media_preco_categoria")));
            }
            return medias;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ItemRelatorio mostraBaristaDoMes(){
        String sql = "SELECT f.nome AS barista_do_mes, COUNT(DISTINCT p.ID_pedido)" + 
        " AS total_pedidos_atendidos, ROUND(SUM(ip.quantidade * ip.preco_unitario)::numeric, 2) " + 
        " AS faturamento_gerado FROM Pedido p JOIN Funcionario f ON p.FK_funcionario = " + 
        "f.ID_funcionario JOIN Item_pedido ip ON ip.FK_pedido = p.ID_pedido WHERE p.status_pedido " + 
        "= 'atendido' AND p.data_hora_abertura >= '2026-06-01' AND p.data_hora_abertura < '2026-07-01' " + 
        " GROUP BY f.ID_funcionario, f.nome ORDER BY faturamento_gerado DESC LIMIT 1;";

        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet res = st.executeQuery();
            while(res.next()){
                return new ItemRelatorio(res.getString("barista_do_mes"), res.getInt("total_pedidos_atendidos"), res.getFloat("faturamento_gerado"));
            }
            return null;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}

/*
        -- PARA O MÊS
        String sql = "SELECT f.nome AS barista_do_mes, COUNT(DISTINCT p.ID_pedido)" + 
        " AS total_pedidos_atendidos, ROUND(SUM(ip.quantidade * ip.preco_unitario)::numeric, 2) " + 
        " AS faturamento_gerado FROM Pedido p JOIN Funcionario f ON p.FK_funcionario = " + 
        "f.ID_funcionario JOIN Item_pedido ip ON ip.FK_pedido = p.ID_pedido WHERE p.status_pedido " + 
        "= 'atendido' AND p.data_hora_abertura >= DATE_TRUNC('month', CURRENT_DATE) " + 
        " GROUP BY f.ID_funcionario, f.nome ORDER BY faturamento_gerado DESC LIMIT 1;";

        -- PARA JUNHO
        String sql = "SELECT f.nome AS barista_do_mes, COUNT(DISTINCT p.ID_pedido)" + 
        " AS total_pedidos_atendidos, ROUND(SUM(ip.quantidade * ip.preco_unitario)::numeric, 2) " + 
        " AS faturamento_gerado FROM Pedido p JOIN Funcionario f ON p.FK_funcionario = " + 
        "f.ID_funcionario JOIN Item_pedido ip ON ip.FK_pedido = p.ID_pedido WHERE p.status_pedido " + 
        "= 'atendido' AND p.data_hora_abertura >= '2026-06-01' AND p.data_hora_abertura < '2026-07-01' " + 
        " GROUP BY f.ID_funcionario, f.nome ORDER BY faturamento_gerado DESC LIMIT 1;";
*/