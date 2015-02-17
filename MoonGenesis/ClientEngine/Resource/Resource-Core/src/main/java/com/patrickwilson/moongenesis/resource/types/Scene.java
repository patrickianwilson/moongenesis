package com.patrickwilson.moongenesis.resource.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pwilson on 2/16/15.
 */
public class Scene {

    private String name;
    private SceneNode[] nodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SceneNode[] getNodes() {
        return nodes;
    }

    public void setNodes(SceneNode[] nodes) {
        this.nodes = nodes;
    }


    public static SceneBuilder builder() {
        return new SceneBuilder();
    }
    public static class SceneBuilder {
        private String name;
        private LinkedList<SceneNode> nodes = new LinkedList<>();

        public SceneBuilder addNode(SceneNode node) {
            nodes.add(node);
            return this;
        }

        public SceneBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SceneBuilder addSubChild(SceneNode parent, SceneNode child) {
            if (parent.getChildren() == null) {
                parent.setChildren(new SceneNode[]{});
            }

            List<SceneNode> children = new ArrayList<>();
            for (SceneNode n: parent.getChildren()) {
                children.add(n);
            }

            children.add(child);
            parent.setChildren(children.toArray(new SceneNode[children.size()]));
            return this;

        }
        public SceneNode build() {
            SceneNode result = new SceneNode();
            result.setName(this.name);
            result.setChildren(this.nodes.toArray(new SceneNode[this.nodes.size()]));
            return result;
        }


    }
}
