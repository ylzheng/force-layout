package org.gephi.toolkit.demos;

import SequenceGraphClustering.Edge;
import SequenceGraphClustering.Queue;
import SequenceGraphClustering.SeqInfor;
import org.gephi.graph.api.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yqb7 on 8/27/15.
 */
public class Utils {
    public static void printlnJson (Node[] nodes, Queue<Edge> mst_e, ArrayList<SeqInfor> seq_uniq,
                                    String outputJson) throws IOException {
        FileWriter outputstream = null;
        try{
            outputstream = new FileWriter(new File(outputJson));
        }catch (IOException e){
            e.printStackTrace();
        }
        assert outputstream != null;
        outputstream.write("{ "+ '"' + "nodes" + '"' + ": [ \n");
        for (int i = 0; i < nodes.length; i++) {
            if ( i < nodes.length -1 && seq_uniq.get(i).group !=3 ){
                /*outputstream.write("{" + '"' + "name" + '"' + ": " + '"'+ nodes[i].getLabel() +'"' + ", " +
                        '"' + "id" + '"' + ": " + '"' + seq_uniq.get(i).seqIndex_uniqNode + '"' +", " +
                        '"' + "x" + '"' + ": "  + (nodes[i].x()+7000.00)/15 + ", " +
                        '"' + "y" + '"' + ": " + (nodes[i].y()+6000.00)/15 + ", " +
                        '"' +"fixed" + '"' +  ": " + "true, "  + '"' + "genotype" + '"' + ": " + '"' +
                        seq_uniq.get(i).genotype + '"' + ", " +  '"' + "diversity" +'"' + ": " + '"' + "0.015" +
                        '"' + "," +'"' + "group" + '"' + ":" + seq_uniq.get(i).group +
                        "}" + ",\n");*/

                outputstream.write("{" + '"' + "name" + '"' + ": " + '"'+ nodes[i].getNodeData().getLabel() +'"' + ", " +
                        '"' + "id" + '"' + ": " + '"' + seq_uniq.get(i).seqIndex_uniqNode + '"' +", " +
                        '"' + "x" + '"' + ": "  + (nodes[i].getNodeData().x()+7000.00)/15 + ", " +
                        '"' + "y" + '"' + ": " + (nodes[i].getNodeData().y()+6000.00)/15 + ", " +
                        '"' +"fixed" + '"' +  ": " + "true, "  + '"' + "genotype" + '"' + ": " + '"' +
                        seq_uniq.get(i).genotype + '"' + ", " +  '"' + "diversity" +'"' + ": " + '"' + "0.015" +
                        '"' + "," +'"' + "group" + '"' + ":" + seq_uniq.get(i).group +
                        "}" + ",\n");
            }
            if ( i < nodes.length -1 && seq_uniq.get(i).group ==3 ) {
                outputstream.write("{" + '"' + "name" + '"' + ": " + '"' + nodes[i].getNodeData().getLabel() + '"' + ", " +
                        '"' + "id" + '"' + ": " + '"' + seq_uniq.get(i).seqIndex_uniqNode + '"' + ", " +
                        '"' + "x" + '"' + ": " + (nodes[i].getNodeData().x() + 7000.00) /15 + ", " +
                        '"' + "y" + '"' + ": " + (nodes[i].getNodeData().y() + 6000.00) /15 + ", " +
                        '"' + "fixed" + '"' + ": " + "false, " + '"' + "genotype" + '"' + ": " + '"' +
                        seq_uniq.get(i).genotype + '"' + ", " + '"' + "diversity" + '"' + ": " + '"' + "1.5" +
                        '"' + "," + '"' + "group" + '"' + ":" + seq_uniq.get(i).group +
                        "}" + ",\n");
            }
            if(i == nodes.length-1){
                outputstream.write("{" + '"' + "name" + '"' + ": " + '"'+ nodes[i].getNodeData().getLabel() +'"' + ", " +
                        '"' + "id" + '"' + ": " + '"' + seq_uniq.get(i).seqIndex_uniqNode + '"' +", " +
                        '"' + "x" + '"' + ": " + (nodes[i].getNodeData().x()+7000.00)/15 + ", " +
                        '"' + "y" + '"' + ": " + (nodes[i].getNodeData().y()+6000.00)/15 + ", " +
                        '"' +"fixed" + '"' +  ": " + "true, "  + '"' + "genotype" + '"' + ": " + '"' +
                        seq_uniq.get(i).genotype + '"' + ", " +  '"' + "diversity" +'"' + ": " + '"' + "0.015" +
                        '"' + "," +'"' + "group" + '"' + ":" + seq_uniq.get(i).group +
                        "}]" + ",\n");
            }
        }
        outputstream.write('"' + "links" + '"' + ": [ \n");
        int j = 0;
        for(Edge aedge : mst_e){
        //for (int j = 0; j < mst_e.size(); j++){
            //if(j < mst_e.size()-1){
            if(j < mst_e.size()-1){
                SeqInfor v = aedge.either();
                SeqInfor w = aedge.other(v);
                outputstream.write("{" + '"' + "source" + '"' + ": " + v.seqIndex_uniqNode + ", " + '"' +
                        "target" + '"' + ":" + w.seqIndex_uniqNode + ", " + '"' + "value" + '"' + ": " + aedge.weight() / 100 +
                        "},\n");
            }
            if(j  == mst_e.size()-1){
                System.out.println("nonext");
                SeqInfor v = aedge.either();
                SeqInfor w = aedge.other(v);
                outputstream.write("{" + '"' + "source" + '"' + ": " + v.seqIndex_uniqNode + ", " + '"' +
                        "target" + '"' + ":" + w.seqIndex_uniqNode + ", " + '"' + "value" + '"' + ": " + aedge.weight() / 100 +
                        "}]}\n");
            }
            j++;
        }
        outputstream.close();
    }
}
