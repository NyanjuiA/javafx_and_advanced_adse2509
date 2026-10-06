package com.adse2509.sess08_design_patterns_and_other_features;

public class DelegationDemo
{
    public static void main(String[] args)
    {
        // Declare and instantiate a DocumentPrinter object
        DocumentPrinter printer = new DocumentPrinter();

        // Print a PDF Document
        printer.printDocument("These are the contents of the PDF Document.","PDF");

        // Print a Text Document
        printer.printDocument("These are the contents of the Text Document.","Text");
    }
}

class DocumentPrinter
{
    private final PDFPrinter pdfPrinter;
    private final TextPrinter textPrinter;

    public DocumentPrinter()
    {
        this.pdfPrinter = new PDFPrinter();
        this.textPrinter = new TextPrinter();
    }

    /**
     * Prints the document by delegating the printing work to the appropriate
     * service (pdf or text printer service).
     *
     * @param content The content(s) of the document to be printed
     * @param type The type of document to be printed
     */
    public void printDocument(String content, String type)
    {
        if("pdf".equalsIgnoreCase(type))
            pdfPrinter.print(content);
        else if("text".equalsIgnoreCase(type))
            textPrinter.print(content);
        else
            System.err.println("Unfortunately, " + type + " is not supported");
    }
}

/** PrintService interface defines the print operation (print() method). */
interface PrintService {void print(String document);}

/**
 * PDFPrinter class is a specific implementation of the PrintService interface for
 * PDF documents
 */
class PDFPrinter implements PrintService
{
    @Override
    public void print(String document)
    {
        System.out.println("Printing PDF document: " + document);
    }
}
/**
 * TextPrinter class is a specific implementation of the PrintService interface for
 * Text documents
 */
class TextPrinter implements PrintService
{
    @Override
    public void print(String document)
    {
        System.out.println("Printing text document: " + document);
    }
}