package apresentacao.componentes;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

public class ImagemCircular extends JPanel {
    private BufferedImage imagemOriginal;
    private BufferedImage imagemCircular;

    public ImagemCircular(byte[] dadosImagem, int width, int height) {
        try {
            // Definindo tamanho fixo pra imagem 
            Dimension tamanhoImagem = new Dimension(width, height);
            setPreferredSize(tamanhoImagem);
            setMinimumSize(tamanhoImagem);
            setMaximumSize(tamanhoImagem);

            if (dadosImagem != null) {
                imagemOriginal = ImageIO.read(new ByteArrayInputStream(dadosImagem));

                int tamanho = Math.min(imagemOriginal.getWidth(), imagemOriginal.getHeight());
                imagemCircular = new BufferedImage(tamanho, tamanho, BufferedImage.TYPE_INT_ARGB);

                // Criando uma elipse pra colocar a imagem dentro
                Graphics2D g2 = imagemCircular.createGraphics();
                g2.setClip(new Ellipse2D.Float(0, 0, tamanho, tamanho));
                g2.drawImage(imagemOriginal, 0, 0, tamanho, tamanho, null);
                g2.dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Coloca a imagem dentro da elipse
        if (imagemCircular != null) {
            g.drawImage(imagemCircular, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
