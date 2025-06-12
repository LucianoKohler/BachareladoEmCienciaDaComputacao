package apresentacao.componentes;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;

import java.awt.Component;
import java.awt.image.BufferedImage;

import java.io.File;

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
