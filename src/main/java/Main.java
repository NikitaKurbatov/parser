import model.People;
import model.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Root root = new Root();
        Document doc;
        try {
            doc = buildDocument();
        } catch (Exception e) {
            System.out.println("Don open file " + e.toString());
            return;
        }

        Node rootNode = doc.getFirstChild();
        NodeList rootChild = rootNode.getChildNodes();
        String mainName = null;
        Node peopleNode = null;
        for (int i = 0; i < rootChild.getLength(); i++) {
            if (rootChild.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (rootChild.item(i).getNodeName()) {
                case "name": {
                    mainName = rootChild.item(i).getTextContent();
//                    System.out.println("mainName: " + mainName);
                }
                case "people": {
                    peopleNode = rootChild.item(i);
//                    System.out.println("peopleNode " + peopleNode);
                }
            }
        }
        root.setName(mainName);
        if (peopleNode == null) {
            return;
        }
        List<People> peopleList = new ArrayList<>();
        NodeList peopleChild = peopleNode.getChildNodes();
        for (int i = 0; i < peopleChild.getLength(); i++) {
            if (peopleChild.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (!peopleChild.item(i).getNodeName().equals("element")) {
                continue;
            }
            NodeList elementChild = peopleChild.item(i).getChildNodes();
            String name = "";
            int age = 0;
            for (int j = 0; j < elementChild.getLength(); j++) {
                if (elementChild.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                switch (elementChild.item(j).getNodeName()) {
                    case "name": {
                        name = elementChild.item(j).getTextContent();
                        break;
                    }
                    case "age": {
                        age = Integer.valueOf(elementChild.item(j).getTextContent());
                        break;
                    }
                }
            }
            People people = new People(name, age);
            peopleList.add(people);
        }
        root.setName(mainName);
        root.setPeople(peopleList);

//        root.getPeople().stream().filter(people -> {
//            return people.getAge() == 222;
//        }).forEach(people -> {
//            System.out.println("Peopel age = 20 " + people.toString());
//        });

        System.out.println("Root " + root.toString());
    }


    private static Document buildDocument() throws Exception {
        File file = new File("test.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }
}
