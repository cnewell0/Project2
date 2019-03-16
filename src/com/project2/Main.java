package com.project2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.*;
import java.io.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] argv) throws ParserConfigurationException, IOException, SAXException {

    File xmlFile = new File("shakespeare.2.00.xml");


    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    Document document = builder.parse(xmlFile);

    document.getDocumentElement().normalize();

    Handler.handleChannelTag(document);


  }
}
