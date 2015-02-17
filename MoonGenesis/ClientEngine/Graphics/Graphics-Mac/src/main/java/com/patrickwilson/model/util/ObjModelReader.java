package com.patrickwilson.model.util;


import com.patrickwilson.moongenesis.resource.types.Face3D;
import com.patrickwilson.moongenesis.resource.types.Point3D;
import com.patrickwilson.moongenesis.resource.types.SceneNode;
import com.patrickwilson.moongenesis.resource.types.Vector3D;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by pwilson on 2/14/15.
 */
public class ObjModelReader {

    private InputStream modelStream;
    public ObjModelReader(InputStream model) {
        this.modelStream = model;
    }

    public SceneNode load() {
        SceneNode result = new SceneNode();

        Scanner objFileScanner = new Scanner(modelStream);

        try {
            LinkedList<Point3D> pointBuffer = new LinkedList<>();
            LinkedList<Vector3D> normalBuffer = new LinkedList<>();
            //maintain a pointer to the vertex index.
            LinkedList<int[]> facesBuffer = new LinkedList<>();
            LinkedList<int[]> facesNormalsBuffer = new LinkedList<>();


            while (objFileScanner.hasNextLine()) {
                String line = objFileScanner.nextLine();

                if (line == null || line.isEmpty()) {
                    continue;
                }

                String[] lineParts = line.split(" ");
                if (lineParts[0].startsWith("#")) {
                    //this is a comment - ignore
                    continue;
                }

                switch (lineParts[0]) {
                    case "v":
                        Point3D point = new Point3D();
                        try {
                            point.x = Float.parseFloat(lineParts[1]);
                            point.y = Float.parseFloat(lineParts[2]);
                            point.z = Float.parseFloat(lineParts[3]);
                            pointBuffer.add(point);

                        } catch (Exception e) {
                            throw new InvalidModelSyntaxException("invalid vertex discovered during import! [" + line + "]");
                        }
                        break;
                    case "vn":
                        Vector3D normal = new Vector3D();

                        try {
                            normal.vX = Float.parseFloat(lineParts[1]);
                            normal.vY = Float.parseFloat(lineParts[2]);
                            normal.vZ = Float.parseFloat(lineParts[3]);
                            normalBuffer.add(normal);
                        } catch (Exception e) {
                            throw new InvalidModelSyntaxException("invalid normal vector discovered during import! [" + line + "]");
                        }
                        break;
                    case "f":
                        //parse the faces.
                        try {
                            String[] firstVertParts = lineParts[1].split("/");
                            String[] secondVertParts = lineParts[2].split("/");
                            String[] thirdVertParts = lineParts[3].split("/");
                            int[] vertPointers = new int[3];
                            int[] normalPointers = new int[3];
                            vertPointers[0] = Integer.parseInt(firstVertParts[0]) - 1;
                            vertPointers[1] = Integer.parseInt(secondVertParts[0]) - 1;
                            vertPointers[2] = Integer.parseInt(thirdVertParts[0]) - 1;
                            facesBuffer.add(vertPointers);
                            normalPointers[0] = Integer.parseInt(firstVertParts[2]) - 1;
                            normalPointers[1] = Integer.parseInt(secondVertParts[2]) - 1;
                            normalPointers[2] = Integer.parseInt(thirdVertParts[2]) - 1;
                            facesNormalsBuffer.add(normalPointers);


                        } catch (Exception e) {
                            throw new InvalidModelSyntaxException("invalid face definition discovered during import! [" + line + "]");
                        }
                        break;
                    default:
                        continue;
                }
            }
                result.pointIndex = pointBuffer.toArray(new Point3D[]{});
                Vector3D[] normalIndex = normalBuffer.toArray(new Vector3D[]{});

                LinkedList<Face3D> faces = new LinkedList<>();
                for (int i = 0; i < facesBuffer.size(); i++) {
                    int[] facesPoints = facesBuffer.get(i);
                    int[] normalPointers = facesNormalsBuffer.get(i);

                    Face3D face = new Face3D();
                    face.vertices[0] = result.pointIndex[facesPoints[0]];
                    face.vertices[1] = result.pointIndex[facesPoints[1]];
                    face.vertices[2] = result.pointIndex[facesPoints[2]];

                    face.normals[0] = normalIndex[normalPointers[0]];
                    face.normals[1] = normalIndex[normalPointers[1]];
                    face.normals[2] = normalIndex[normalPointers[2]];

                    faces.add(face);
                }
                result.faces = faces.toArray(new Face3D[]{});

                return result;

        } finally {
            try {
                modelStream.close();
            } catch (IOException e) {
                //unable to close the model file.  ignore.
            }
        }
    }

    public static void main(String ... args) {
        InputStream sampleModelStream = ObjModelReader.class.getResourceAsStream("/cube.obj");

        ObjModelReader reader = new ObjModelReader(sampleModelStream);

        SceneNode model = reader.load();

        System.out.println("Finished");

    }
}
