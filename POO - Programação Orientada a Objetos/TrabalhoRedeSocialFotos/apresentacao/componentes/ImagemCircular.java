package apresentacao.componentes;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class ImagemCircular extends JPanel {
    private BufferedImage imagemOriginal;
    private BufferedImage imagemCircular;

    public ImagemCircular(String caminhoImagem, int width, int height) {
        try {
            Dimension tamanhoImagem = new Dimension(width, height);
            setPreferredSize(tamanhoImagem);
            setMinimumSize(tamanhoImagem);
            setMaximumSize(tamanhoImagem);
            imagemOriginal = ImageIO.read(new File(caminhoImagem));

            int tamanho = Math.min(imagemOriginal.getWidth(), imagemOriginal.getHeight());
            imagemCircular = new BufferedImage(tamanho, tamanho, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = imagemCircular.createGraphics();
            g2.setClip(new Ellipse2D.Float(0, 0, tamanho, tamanho));
            g2.drawImage(imagemOriginal, 0, 0, tamanho, tamanho, null);
            g2.dispose();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemCircular != null) {
            g.drawImage(imagemCircular, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
