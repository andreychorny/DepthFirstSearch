package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    Graph graph;

    @FXML
    TextField fieldAddVerticle;
    @FXML
    TextField fieldAddEdgeFrom;
    @FXML
    TextField fieldAddEdgeTo;
    @FXML
    TextField fieldAddEdgeLength;

    private Map<String,List<String>> nodes = new TreeMap<>();

    @FXML
    private void addVerticle(ActionEvent event) {
        String newNode = fieldAddVerticle.getText();
        if(!newNode.equals("") && !nodes.containsKey(newNode)) {
            nodes.put(newNode, new ArrayList<String>());
            graph.addNode(newNode);
            graph.getNode(newNode).addAttribute("ui.style", "shape:circle;" +
                    "fill-color: grey;size: 50px; text-alignment: center; text-size: 20px;");
            graph.getNode(newNode).addAttribute("label", graph.getNode(newNode).getId());}
    }

    @FXML
    private void addEdge(ActionEvent event){
        String fromNode = fieldAddEdgeFrom.getText();
        String toNode = fieldAddEdgeTo.getText();
        Integer length = Integer.parseInt(fieldAddEdgeLength.getText());
        if(nodes.containsKey(fromNode) && nodes.containsKey(toNode)){
            nodes.get(fromNode).add(toNode);
            nodes.get(toNode).add(fromNode);
            graph.addEdge(fromNode+toNode, fromNode, toNode).addAttribute("length", length);
            graph.getEdge(fromNode+toNode).addAttribute("ui.style","text-size: 20px;");
            graph.getEdge(fromNode+toNode).addAttribute("label", "" +
                    (int) graph.getEdge(fromNode+toNode).getNumber("length"));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph = new SingleGraph("Graph");
        System.out.println("!!!MAIN");
        graph.display();
    }
}
