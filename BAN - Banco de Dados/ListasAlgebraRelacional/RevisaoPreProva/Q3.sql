-- buscar o IDs e títulos do CDs com preço superior a R$ 30,00. Se o CD possuir um intérprete, apresentar o nome e o tipo do intérprete; 
pi cds.id, cds.titulo, interpretes.nome, interpretes.tipo (sigma preco > 30 (cds join cds.interprete = interpretes.id interpretes))
