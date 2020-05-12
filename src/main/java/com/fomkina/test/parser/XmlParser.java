package com.fomkina.test.parser;

import com.fomkina.test.service.BoxService;
import com.fomkina.test.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.stream.IntStream;

/**
 * User: Anastasia Fomkina
 * Time: 2020-05-12 15:05
 */
@Component
public class XmlParser {

    private static final Logger log = LoggerFactory.getLogger(XmlParser.class);
    private static final int ZERO = 0;

    @Autowired
    private BoxService boxService;
    @Autowired
    private ItemService itemService;

    public void parseXml(String fileName) {
        try {
            log.debug("Trying to parse xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(new File(fileName));

            NodeList storageNodeList = document.getElementsByTagName("Storage");
            if (storageNodeList.getLength() != ZERO) {
                NodeList childNodeList = storageNodeList.item(ZERO).getChildNodes();

                IntStream.range(ZERO, childNodeList.getLength()).forEach(i -> parseNode(childNodeList.item(i), null));
            }
            log.debug("Xml was successfully parsed");
        } catch (Throwable throwable) {
            log.warn("Couldn't parse this xml-file", throwable);
        }
    }

    private void parseNode(Node node, Long containerId) {
        if (Node.ELEMENT_NODE == node.getNodeType()) {
            Element element = (Element) node;
            Long id = Long.valueOf(element.getAttribute("id"));

            String nodeName = node.getNodeName();

            if (nodeName.equals("Box")) {
                log.debug("Creating box");
                boxService.create(id, containerId);
                log.debug("Box was successfully created");

                NodeList childNodeList = node.getChildNodes();

                IntStream.range(ZERO, childNodeList.getLength()).forEach(i -> parseNode(childNodeList.item(i), id));

            } else if (nodeName.equals("Item")) {
                String color = element.getAttribute("color");

                if (color.isEmpty()) {
                    log.debug("Creating item without color");
                    itemService.createWithoutColor(id, containerId);
                } else {
                    log.debug("Creating item");
                    itemService.create(id, containerId, color);
                    log.debug("Item was successfully created");
                }
            }
        }
    }
}
