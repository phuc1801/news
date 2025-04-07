package com.vnnet.newsgenerator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class GeneratorRefactor {
    public void refactor() {
        try {
            URL url = getClass().getClassLoader().getResource("generatorConfig.xml");


            File file = new File(url.getFile());


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            Path root = Paths.get(".").normalize().toAbsolutePath();
            String model_path = root.toAbsolutePath() + "\\news-common\\src\\main\\java\\com\\vnnet\\newscommon\\model\\";
            String xml_mapper_path = root.toAbsolutePath() + "\\news-common\\src\\main\\java\\com\\vnnet\\newscommon\\persistence\\";

            System.out.println("----------------------------" + root.toAbsolutePath());

            NodeList nList = doc.getElementsByTagName("table");

            System.out.println("--------------------------------------------------------------------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("----------------------------");
                    Element eElement = (Element) nNode;
                    String domainObjectName = eElement.getAttribute("domainObjectName");
                    String baseObjectName = domainObjectName.replace("Se", "Base");
                    baseObjectName = baseObjectName.replace("Sys", "Base");
                    if(baseObjectName.substring(0, 1).equals("P")){
                        baseObjectName = "Base" + baseObjectName.substring(1);
                    }
                    System.out.println("domainObjectName : " + domainObjectName + " baseObjectName:" + baseObjectName);
                    Charset charset = StandardCharsets.UTF_8;
                    String base_code = "package com.vnnet.newscommon.model;\n" +
                            "\n" +
                            "public class " + baseObjectName + " extends BaseModel {\n" +
                            "}\n";
                    if (baseObjectName != null && !baseObjectName.isEmpty()) {
                        String base_class = model_path + baseObjectName + ".java";
                        File base_file = new File(base_class);
                        if (!base_file.exists()) {
                            Files.write(Paths.get(base_class), base_code.getBytes(charset));
                        }

                        String object_class = model_path + domainObjectName + ".java";
                        System.out.println("Refactor file:" + object_class);
                        Path path = Paths.get(object_class);

                        String content = new String(Files.readAllBytes(path), charset);
                        content = content.replaceAll("public class " + domainObjectName + " implements", "public class " + domainObjectName + " extends " + baseObjectName + " implements");
                        Files.write(path, content.getBytes(charset));
                    }

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("============GeneratorRefactor================");

        GeneratorRefactor generatorRefactor = new GeneratorRefactor();
        generatorRefactor.refactor();

    }
}
