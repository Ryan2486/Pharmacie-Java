package just.demo3.Action;


import javafx.scene.Scene;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;


import java.io.File;
import java.io.IOException;


public class Pdf {


    public void generatePDF(Scene scene,String Nom_client,String Date) throws IOException {

        PDDocument document = new PDDocument();
        int width =600;
        int height = 800;

        WritableImage writableImage = new WritableImage(width, height);
        scene.snapshot(writableImage);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                javafx.scene.paint.Color fxColor = writableImage.getPixelReader().getColor(x, y);
                int argb = (fxColor.getOpacity() < 1.0) ? 0x00FFFFFF & (int) (fxColor.getOpacity() * 255) << 24 : 0xFF000000;
                argb |= ((int) (fxColor.getRed() * 255) << 16);
                argb |= ((int) (fxColor.getGreen() * 255) << 8);
                argb |= ((int) (fxColor.getBlue() * 255));
                bufferedImage.setRGB(x, y, argb);
            }
        }
        BufferedImage image=bufferedImage;




        PDPage page = new PDPage();
        page.setMediaBox(new PDRectangle((float) width, (float) height));


        document.addPage(page);

        // Créer un objet PDPageContentStream pour écrire sur la page
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Dessiner l'image sur la page
        contentStream.drawImage(LosslessFactory.createFromImage(document, image), 0, 0,
                (int) scene.getWidth(), (int) scene.getHeight());

        // Terminer l'écriture sur la page
        contentStream.close();


        String Nom_facture="Facture de "+Nom_client+" du "+Date+".pdf";
        File out=new File(Nom_facture);
        document.save(out);
        document.close();
    }
}

