/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;

/**
 *
 * @author hasmy
 */
public class printerUtil 
{
    public static void printLandscape(final Node node) 
    {
        PrinterJob job = PrinterJob.createPrinterJob();
        //Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        //printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
//        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
//        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
//        node.getTransforms().add(new Scale(scaleX, scaleY));
 
        //System.out.println("Printer: "+job.getPrinter());
        //PrinterJob job = PrinterJob.createPrinterJob();
        job.getJobSettings().setPageLayout(pageLayout);
        if (job != null) {
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }
    } 
}
