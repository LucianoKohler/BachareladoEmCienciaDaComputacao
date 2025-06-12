package apresentacao.componentes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import dados.Post;

public class ImagemQuadrada extends JPanel {
    private Image imagemRedimensionada;

    public ImagemQuadrada(byte[] imagemPost, int width, int height) {
        if (imagemPost == null) {
            imagemPost = Post.carregarImagemPadrao();
        }

        BufferedImage imagem = Post.BytesParaImage(imagemPost);
        imagemRedimensionada = imagem.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        setMaximumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemRedimensionada != null) {
            g.drawImage(imagemRedimensionada, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
