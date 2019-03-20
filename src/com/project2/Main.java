package com.project2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
  private static String filename;
  private static Document document;

  /**
   * This main function opens the xml file and calls the handler function
   * to deal with the parsing of the file
   *
   * @param argv used for user input
   * @throws ParserConfigurationException catches errors within Document
   *                                      builder methods
   * @throws IOException                  catches errors within Document builder methods
   * @throws SAXException                 catches errors within Document builder methods
   */
  public static void main(String[] argv) throws ParserConfigurationException, IOException, SAXException {
    Scanner reader = new Scanner(System.in);

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    System.out.println("Enter a filename to open or press enter for Hamlet");
    filename = reader.nextLine();
    if (filename.equals("")) {
      filename = "shaks200/hamlet.xml";
    }
    Document document = builder.parse(filename);

    document.getDocumentElement().normalize();

    Handler.handleChannelTag(document);
    overwriteFile(reader);

  }


  /**
   * Saves the XML tree as a new XML file
   * Will overwrite the original file if it has the same name as this.filename
   *
   * @param newFilename name of new XML file
   * @return true if success, false if failure
   */

  public static boolean saveFile(String newFilename) {
    boolean success = true;

    try {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      StreamResult output = new StreamResult(new File(newFilename));
      DOMSource input = new DOMSource(document);

      transformer.transform(input, output);

    } catch (TransformerException e) {
      e.printStackTrace();
      success = false;
    }

    // Change the current filename if Save As... works properly
    if (success) {
      filename = newFilename;
      System.out.println("File saved successfully\n");
    }

    return success;
  }

  public static void overwriteFile(Scanner reader) {
    System.out.println("Enter the name of the file you would like to save it as");
    filename = reader.nextLine();
    if (filename.equals("Shakespeare/hamlet.xml")) {
      System.out.println("Do you want to overwrite the file hamlet.xml? (Y/N)");
      String answer = reader.nextLine();
      if (answer.equals("Y") || answer.equals("y")) {
        saveFile(filename);
      }
    } else
      saveFile(filename);
  }
}



