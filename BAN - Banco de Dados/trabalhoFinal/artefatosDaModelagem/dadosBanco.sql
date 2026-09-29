-- RESET TOTAL
TRUNCATE TABLE info_produto, item_pedido, Pedido, Comanda, Produto, Ingrediente, Funcionario RESTART IDENTITY CASCADE;

-----------------------------------------
--             FUNCIONÁRIOS            --
-----------------------------------------

INSERT INTO Funcionario (nome, salario, data_contratacao, cargo) VALUES

('Lucas Silva', 2100.00, '2025-01-15', 'barista'),
('Mariana Costa', 2150.00, '2025-03-10', 'barista'),
('Pedro Henrique', 2100.00, '2025-06-01', 'barista'),
('Ana Beatriz', 4500.00, '2024-10-01', 'gerente'),
('Rodrigo Souza', 4200.00, '2025-02-20', 'gerente'),
('Carlos Augusto', 1850.00, '2025-01-20', 'faxineiro'),
('Maria Madalena', 1850.00, '2025-04-15', 'faxineiro');

-----------------------------------------
--             INGREDIENTES            --
-----------------------------------------

INSERT INTO Ingrediente (nome, descricao, quantidade, unidade_medida) VALUES 

('Café', 'Pó de café', 5000, 'g'),
('Leite', 'Leite integral', 20, 'L'),
('Água', 'Água filtrada', 50000, 'mL'),
('Chocolate em pó', 'Chocolate para bebidas e sobremesas', 2000, 'g'),
('Açúcar', 'Açúcar refinado', 5000, 'g'),
('Farinha de trigo', 'Farinha para bolos e massas', 10000, 'g'),
('Ovos', 'Ovos de galinha', 300, 'un'),
('Manteiga', 'Manteiga sem sal', 3000, 'g'),
('Fermento', 'Fermento para bolos', 500, 'g'),
('Cenoura', 'Cenoura fresca', 3000, 'g'),
('Cacau', 'Cacau em pó', 1000, 'g'),
('Cream Cheese', 'Cream cheese para Red Velvet', 2000, 'g'),
('Leite condensado', 'Leite condensado para sobremesas', 2000, 'g'),
('Creme de leite', 'Creme de leite para sobremesas', 2000, 'g'),
('Bolacha maisena', 'Base de tortas e sobremesas', 3000, 'g'),
('Sorvete de creme', 'Sorvete utilizado em bebidas geladas', 5000, 'g'),
('Água com gás', 'Base para soda italiana', 10000, 'mL'),
('Xarope de frutas', 'Xarope para soda italiana', 2000, 'mL'),
('Pão italiano', 'Pão para sanduíches', 50, 'un'),
('Presunto', 'Fatiado para sanduíches', 2000, 'g'),
('Queijo mussarela', 'Queijo para sanduíches', 2000, 'g'),
('Tomate', 'Tomate fresco', 2000, 'g'),
('Alface', 'Alface fresca', 1000, 'g');

-----------------------------------------
--               PRODUTOS              --
-----------------------------------------

INSERT INTO Produto (nome, preco, categoria) VALUES 

('Café filtrado', 6.50, 'bebida quente'),
('Café filtrado com Leite', 7.50, 'bebida quente'),
('Capuccino', 10.00, 'bebida quente'),
('Chocolate quente', 12.00, 'bebida quente'),
('Bolo Red Velvet', 15.40, 'bolo'),
('Bolo de cenoura', 13.40, 'bolo'),
('Torta alemã', 15.40, 'bolo'),
('Icemaltine', 16.00, 'bebida fria'),
('Soda Italiana', 15.00, 'bebida fria'),
('Sanduíche Italiano', 14.50, 'outros'),
('Água', 4.50, 'bebida fria'),
('Água com gás', 5.50, 'bebida fria');

-----------------------------------------
--       RECEITAS (INFO_PRODUTO)       --
-----------------------------------------

INSERT INTO info_produto (FK_ingrediente, FK_produto, quantidade, observacao) VALUES

-- Café filtrado
(1, 1, 10, NULL),
(3, 1, 200, 'Água ideal entre 92ºC e 96ºC'),

-- Café filtrado com leite
(1, 2, 10, NULL),
(3, 2, 100, NULL),
(2, 2, 100, 'Aquecer sem deixar ferver'),

-- Capuccino
(1, 3, 10, NULL),
(2, 3, 150, 'Vaporizar para criar microespuma'),
(4, 3, 15, NULL),
(5, 3, 10, NULL),

-- Chocolate quente
(2, 4, 200, NULL),
(4, 4, 20, NULL),
(5, 4, 10, NULL),

-- Bolo Red Velvet
(6, 5, 250, NULL),
(7, 5, 3, 'Usar em temperatura ambiente'),
(8, 5, 100, NULL),
(11, 5, 20, NULL),
(12, 5, 150, 'Bater bem gelado para a cobertura'),

-- Bolo de cenoura
(6, 6, 250, NULL),
(7, 6, 3, NULL),
(10, 6, 200, NULL),
(4, 6, 40, NULL),

-- Torta alemã
(15, 7, 200, NULL),
(14, 7, 200, 'Usar lata gelada e sem o soro'),
(13, 7, 200, NULL),
(8, 7, 100, 'Ponto pomada (temperatura ambiente)'),

-- Icemaltine
(16, 8, 150, NULL),
(4, 8, 30, NULL),
(2, 8, 150, NULL),

-- Soda Italiana
(17, 9, 300, 'Completar copo com gelo antes da água'),
(18, 9, 50, NULL),

-- Sanduíche Italiano
(19, 10, 1, 'Tostar levemente na chapa'),
(20, 10, 50, NULL),
(21, 10, 50, NULL),
(22, 10, 30, NULL),
(23, 10, 20, 'Secar bem para não amolecer o pão');

-----------------------------------------
--              COMANDAS               --
-----------------------------------------
INSERT INTO Comanda (numero_mesa, data_hora_abertura, status_pgto) VALUES
(4, '2026-05-29 09:15:00', 'paga'),   
(12, '2026-05-29 10:30:00', 'paga'),  
(2, '2026-06-05 08:30:00', 'paga'),   
(5, '2026-06-08 14:20:00', 'paga'),   
(8, '2026-06-12 16:15:00', 'paga'),   
(3, '2026-06-15 09:10:00', 'paga'),   
(1, '2026-06-18 10:45:00', 'paga'),   
(4, '2026-06-20 18:00:00', 'paga'),   
(10, '2026-06-30 14:00:00', 'aberta'), 
(7, CURRENT_TIMESTAMP, 'aberta');      

-----------------------------------------
--              PEDIDOS                --
-----------------------------------------
INSERT INTO Pedido (FK_comanda, FK_funcionario, data_hora_abertura, status_pedido, observacao) VALUES

(1, 1, '2026-06-29 09:20:00', 'atendido', 'cappucinos com bastante espuma'),
(1, 2, '2026-06-29 09:45:00', 'atendido', 'água com gelo e limão'),
(2, 3, '2026-06-29 10:35:00', 'atendido', NULL),
(3, 1, '2026-06-05 08:35:00', 'atendido', 'Leite bem quente'),
(3, 1, '2026-06-05 08:50:00', 'atendido', NULL),
(4, 2, '2026-06-08 14:25:00', 'atendido', 'Para viagem'),
(5, 3, '2026-06-12 16:20:00', 'atendido', NULL),
(5, 3, '2026-06-12 16:40:00', 'atendido', 'Fatias grandes'),
(6, 1, '2026-06-15 09:15:00', 'cancelado', NULL),
(6, 2, '2026-06-15 09:25:00', 'atendido', NULL),
(7, 1, '2026-06-18 10:50:00', 'atendido', NULL),
(8, 2, '2026-06-20 18:05:00', 'atendido', 'Sem canudo'),
(8, 2, '2026-06-20 18:30:00', 'atendido', NULL),
(9, 3, '2026-06-22 13:35:00', 'atendido', 'Copos com gelo'),
(10, NULL, CURRENT_TIMESTAMP, 'pendente', 'sanduiche sem tomate');

-----------------------------------------
--         ITENS DO PEDIDO             --
-----------------------------------------
INSERT INTO Item_pedido (FK_pedido, FK_produto, quantidade, preco_unitario) VALUES

-- Pedido 1 
(1, 3, 2, 10.00),        -- 2x Capuccino
(1, 5, 1, 15.40),        -- 1x Red Velvet
-- Pedido 2 
(2, 12, 1, 5.50),        -- 1x Água com gás
-- Pedido 3 
(3, 10, 1, 14.50),       -- 1x Sanduíche Italiano
(3, 9, 1, 15.00),        -- 1x Soda Italiana
-- Pedido 4 
(4, 10, 2, 14.50),       -- 1x Sanduíche Italiano
(4, 11, 1, 4.50),        -- 1x Água
-- Pedido 5
(5, 3, 2, 10.00),        -- 2x Capuccino
(5, 6, 1, 13.40),        -- 1x Bolo de cenoura
-- Pedido 6
(6, 11, 1, 4.50),        -- 1x Água
-- Pedido 7
(7, 8, 1, 16.00),        -- 1x Icemaltine
(7, 7, 1, 15.40),        -- 1x Torta alemã
-- Pedido 8
(8, 1, 2, 6.50),         -- 2x Café filtrado
(8, 10, 2, 14.50),       -- 2x Sanduíche Italiano
-- Pedido 9
(9, 5, 2, 15.40),        -- 2x Bolo Red Velvet
-- Pedido 10 
(10, 4, 1, 12.00),       -- 1x Chocolate quente
-- Pedido 11
(11, 4, 1, 12.00),       -- 1x Chocolate quente
(11, 5, 1, 15.40),       -- 1x Bolo Red Velvet
-- Pedido 12
(12, 2, 3, 7.50),        -- 3x Café filtrado com Leite
-- Pedido 13
(13, 9, 1, 15.00),       -- 1x Soda Italiana
(13, 10, 1, 14.50),      -- 1x Sanduíche Italiano
-- Pedido 14
(14, 3, 1, 10.00),       -- 1x Capuccino
-- Pedido 15
(15, 1, 2, 6.50),        -- 2x Café filtrado
(15, 6, 1, 13.40);       -- 1x Bolo de cenoura