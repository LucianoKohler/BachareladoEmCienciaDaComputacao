package apresentacao.componentes;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class SeletorImagem {

    public static BufferedImage solicitarImagem(Component parent) {
        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Escolha uma imagem");
        seletor.setAcceptAllFileFilterUsed(false);
        seletor.addChoosableFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "png", "jpeg", "gif"));

        int resultado = seletor.showOpenDialog(parent);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File imagemSelecionada = seletor.getSelectedFile();
            try {
                return ImageIO.read(imagemSelecionada); // retorna a imagem carregada
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Erro ao carregar imagem: " + e.getMessage());
            }
        }

        return null; // usuário cancelou ou deu erro
    }
}
